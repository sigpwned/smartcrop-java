/*-
 * =================================LICENSE_START==================================
 * smartcrop4j
 * ====================================SECTION=====================================
 * Copyright (C) 2024 Andy Boothe
 * ====================================SECTION=====================================
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * ==================================LICENSE_END===================================
 */
package com.sigpwned.smartcrop4j;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

import com.sigpwned.smartcrop4j.util.BufferedImages;
import com.sigpwned.smartcrop4j.util.Rects;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author flask
 */
public class ImageAnalyzer {

  private final Options options;
  private int[] cd;

  public ImageAnalyzer() {
    this(Options.DEFAULT);
  }

  public ImageAnalyzer(Options options) {
    this.options = requireNonNull(options);
  }

  public ImageAnalysis analyze(BufferedImage input) {
    try {
      ImageBuffer inputBuffer = ImageBuffer.fromBufferedImage(input);
      ImageBuffer outputBuffer = new ImageBuffer(input.getWidth(), input.getHeight());

      prepareCie(inputBuffer);
      edgeDetect(inputBuffer, outputBuffer);
      skinDetect(inputBuffer, outputBuffer);
      saturationDetect(inputBuffer, outputBuffer);

      BufferedImage scoreImage = BufferedImages.scaled(input,
          1.0f / getOptions().getScoreDownSample(), getOptions().getBufferedBitmapType());
      ImageBuffer scoreBuffer = ImageBuffer.fromBufferedImage(scoreImage);

      List<Crop> crops = regions(scoreBuffer).stream().map(
          region -> new Crop(Rects.scaled(region, getOptions().getScoreDownSample()),
              score(scoreBuffer, region))).collect(toList());

      return new ImageAnalysis(crops);
    } finally {
      cd = null;
    }
  }

  private List<Rect> regions(ImageBuffer score) {
    return regions(score.width, score.height);
  }

  private List<Rect> regions(int width, int height) {
    List<Rect> result = new ArrayList<>();
    int minDimension = Math.min(width, height);

    for (float scale = getOptions().getMaxScale(); scale >= getOptions().getMinScale();
        scale -= getOptions().getScaleStep()) {
      for (int y = 0; y + minDimension * scale <= height; y += getOptions().getScoreDownSample()) {
        for (int x = 0; x + minDimension * scale <= width; x += getOptions().getScoreDownSample()) {
          result.add(new Rect(x, y, (int) (minDimension * scale), (int) (minDimension * scale)));
        }
      }
    }

    return Collections.unmodifiableList(result);
  }

  private Score score(ImageBuffer output, Rect region) {
    int[] od = output.getRGB();
    int width = output.width;
    int height = output.height;

    float skin = 0.0f;
    float detail = 0.0f;
    float saturation = 0.0f;
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int p = y * width + x;
        float importance = importance(region, x, y);
        float detailxy = (od[p] >> 8 & 0xff) / 255f;
        skin = skin + (od[p] >> 16 & 0xff) / 255f * (detailxy + getOptions().getSkinBias())
            * importance;
        detail = detail + detailxy * importance;
        saturation =
            saturation + (od[p] & 0xff) / 255f * (detail + getOptions().getSaturationBias())
                * importance;
      }
    }

    final float weightedDetail = detail * getOptions().getDetailWeight();
    final float weightedSkin = skin * getOptions().getSkinWeight();
    final float weightedSaturation = saturation * getOptions().getSaturationWeight();

    float total = (weightedDetail + weightedSkin + weightedSaturation) / region.getWidth()
        / region.getHeight();

    return new Score(detail, saturation, skin, total);
  }

  private float importance(Rect region, int x, int y) {
    if (region.getX() > x || x >= region.getX() + region.getWidth() || region.getY() > y
        || y >= region.getY() + region.getHeight()) {
      return getOptions().getOutsideImportance();
    }
    float fx = (float) (x - region.getX()) / region.getWidth();
    float fy = (float) (y - region.getY()) / region.getHeight();
    float px = Math.abs(0.5f - fx) * 2;
    float py = Math.abs(0.5f - fy) * 2;
    // distance from edg;
    float dx = Math.max(px - 1.0f + getOptions().getEdgeRadius(), 0);
    float dy = Math.max(py - 1.0f + getOptions().getEdgeRadius(), 0);
    float d = (dx * dx + dy * dy) * getOptions().getEdgeWeight();
    d += (float) (1.4142135f - Math.sqrt(px * px + py * py));
    if (getOptions().isRuleOfThirds()) {
      d += (Math.max(0, d + 0.5f) * 1.2f) * (thirds(px) + thirds(py));
    }
    return d;
  }

  private void prepareCie(ImageBuffer i) {
    int[] id = i.getRGB();
    cd = new int[id.length];
    int w = i.width;
    int h = i.height;

    int p;
    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        p = y * w + x;
        cd[p] = cie(id[p]);
      }
    }
  }

  private void edgeDetect(ImageBuffer i, ImageBuffer o) {
    int[] od = o.getRGB();
    int w = i.width;
    int h = i.height;
    int p;
    int lightness;

    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        p = y * w + x;
        if (x == 0 || x >= w - 1 || y == 0 || y >= h - 1) {
          lightness = 0;
        } else {
          lightness =
              cd[p] * 8 - cd[p - w - 1] - cd[p - w] - cd[p - w + 1] - cd[p - 1] - cd[p + 1] - cd[
                  p + w - 1] - cd[p + w] - cd[p + w + 1];
        }

        od[p] = clamp(lightness) << 8 | (od[p] & 0xffff00ff);
      }
    }
  }

  private void skinDetect(ImageBuffer i, ImageBuffer o) {
    int[] id = i.getRGB();
    int[] od = o.getRGB();
    int w = i.width;
    int h = i.height;
    float invSkinThreshold = 255f / (1 - getOptions().getSkinThreshold());

    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        int p = y * w + x;
        float lightness = cd[p] / 255f;
        float skin = calcSkinColor(id[p]);
        if (skin > getOptions().getSkinThreshold()
            && lightness >= getOptions().getSkinBrightnessMin()
            && lightness <= getOptions().getSkinBrightnessMax()) {
          od[p] = ((Math.round((skin - getOptions().getSkinThreshold()) * invSkinThreshold)) & 0xff)
              << 16 | (od[p] & 0xff00ffff);
        } else {
          od[p] &= 0xff00ffff;
        }
      }
    }
  }

  private void saturationDetect(ImageBuffer i, ImageBuffer o) {
    int[] id = i.getRGB();
    int[] od = o.getRGB();
    int w = i.width;
    int h = i.height;
    float invSaturationThreshold = 255f / (1 - getOptions().getSaturationThreshold());

    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        int p = y * w + x;
        float lightness = cd[p] / 255f;
        float sat = saturation(id[p]);
        if (sat > getOptions().getSaturationThreshold()
            && lightness >= getOptions().getSaturationBrightnessMin()
            && lightness <= getOptions().getSaturationBrightnessMax()) {
          od[p] =
              (Math.round((sat - getOptions().getSaturationThreshold()) * invSaturationThreshold)
                  & 0xff) | (od[p] & 0xffffff00);
        } else {
          od[p] &= 0xffffff00;
        }
      }
    }
  }

  private float calcSkinColor(int rgb) {
    int r = rgb >> 16 & 0xff;
    int g = rgb >> 8 & 0xff;
    int b = rgb & 0xff;

    float mag = (float) Math.sqrt(r * r + g * g + b * b);
    float rd = (r / mag - getOptions().getSkinColor()[0]);
    float gd = (g / mag - getOptions().getSkinColor()[1]);
    float bd = (b / mag - getOptions().getSkinColor()[2]);
    return 1f - (float) Math.sqrt(rd * rd + gd * gd + bd * bd);
  }

  private int clamp(int v) {
    return Math.max(0, Math.min(v, 0xff));
  }

  private int cie(int rgb) {
    int r = rgb >> 16 & 0xff;
    int g = rgb >> 8 & 0xff;
    int b = rgb & 0xff;
    return Math.min(0xff, (int) (0.2126f * b + 0.7152f * g + 0.0722f * r + .5f));
  }

  private float saturation(int rgb) {
    float r = (rgb >> 16 & 0xff) / 255f;
    float g = (rgb >> 8 & 0xff) / 255f;
    float b = (rgb & 0xff) / 255f;

    float maximum = Math.max(r, Math.max(g, b));
    float minimum = Math.min(r, Math.min(g, b));
    if (maximum == minimum) {
      return 0;
    }
    float l = (maximum + minimum) / 2f;
    float d = maximum - minimum;
    return l > 0.5f ? d / (2f - maximum - minimum) : d / (maximum + minimum);
  }

  // gets value in the range of [0, 1] where 0 is the center of the pictures
  // returns weight of rule of thirds [0, 1]
  private float thirds(float x) {
    x = ((x - (1 / 3f) + 1.0f) % 2.0f * 0.5f - 0.5f) * 16f;
    return Math.max(1.0f - x * x, 0);
  }

  private Options getOptions() {
    return options;
  }
}
