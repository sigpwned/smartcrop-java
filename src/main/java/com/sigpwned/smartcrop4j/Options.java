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

import java.awt.image.BufferedImage;

/**
 * @author flask
 */
public class Options {

  public static final Options DEFAULT = new Options();

  public static Options ofDimensions(int width, int height) {
    return new Options().width(width).height(height);
  }

  private int width = 100;
  private int height = 100;
  private float detailWeight = .2f;
  private float[] skinColor = {0.7f, 0.57f, 0.44f};
  private float skinBias = .01f;
  private float skinBrightnessMin = 0.2f;
  private float skinBrightnessMax = 1.0f;
  private float skinThreshold = 0.8f;
  private float skinWeight = 1.8f;
  private float saturationBrightnessMin = 0.05f;
  private float saturationBrightnessMax = 0.9f;
  private float saturationThreshold = 0.4f;
  private float saturationBias = 0.2f;
  private float saturationWeight = 0.3f;
  // step * minscale rounded down to the next power of two should be good
  private int scoreDownsampleFactor = 8;
  private int scoreDownsampleMinSize = 128;
  private int step;
  private float scaleStep = 0.1f;
  private float minScale = 1.0f;
  private float maxScale = 1.0f;
  private float edgeRadius = 0.4f;
  private float edgeWeight = -20f;
  private float outsideImportance = -.5f;
  private boolean ruleOfThirds = true;
  private int bufferedBitmapType = BufferedImage.TYPE_INT_ARGB;

  public int getWidth() {
    return width;
  }

  public Options width(int cropWidth) {
    this.width = cropWidth;
    return this;
  }

  public int getHeight() {
    return height;
  }

  public Options height(int cropHeight) {
    this.height = cropHeight;
    return this;
  }

  public float getDetailWeight() {
    return detailWeight;
  }

  public Options detailWeight(float detailWeight) {
    this.detailWeight = detailWeight;
    return this;
  }

  public float[] getSkinColor() {
    return skinColor;
  }

  public Options skinColor(float[] skinColor) {
    this.skinColor = skinColor;
    return this;
  }

  public float getSkinBias() {
    return skinBias;
  }

  public Options skinBias(float skinBias) {
    this.skinBias = skinBias;
    return this;
  }

  public float getSkinBrightnessMin() {
    return skinBrightnessMin;
  }

  public Options skinBrightnessMin(float skinBrightnessMin) {
    this.skinBrightnessMin = skinBrightnessMin;
    return this;
  }

  public float getSkinBrightnessMax() {
    return skinBrightnessMax;
  }

  public Options skinBrightnessMax(float skinBrightnessMax) {
    this.skinBrightnessMax = skinBrightnessMax;
    return this;
  }

  public float getSkinThreshold() {
    return skinThreshold;
  }

  public Options skinThreshold(float skinThreshold) {
    this.skinThreshold = skinThreshold;
    return this;
  }

  public float getSkinWeight() {
    return skinWeight;
  }

  public Options skinWeight(float skinWeight) {
    this.skinWeight = skinWeight;
    return this;
  }

  public float getSaturationBrightnessMin() {
    return saturationBrightnessMin;
  }

  public Options saturationBrightnessMin(float saturationBrightnessMin) {
    this.saturationBrightnessMin = saturationBrightnessMin;
    return this;
  }

  public float getSaturationBrightnessMax() {
    return saturationBrightnessMax;
  }

  public Options saturationBrightnessMax(float saturationBrightnessMax) {
    this.saturationBrightnessMax = saturationBrightnessMax;
    return this;
  }

  public float getSaturationThreshold() {
    return saturationThreshold;
  }

  public Options saturationThreshold(float saturationThreshold) {
    this.saturationThreshold = saturationThreshold;
    return this;
  }

  public float getSaturationBias() {
    return saturationBias;
  }

  public Options saturationBias(float saturationBias) {
    this.saturationBias = saturationBias;
    return this;
  }

  public float getSaturationWeight() {
    return saturationWeight;
  }

  public Options saturationWeight(float saturationWeight) {
    this.saturationWeight = saturationWeight;
    return this;
  }

  public int getScoreDownsampleFactor() {
    return scoreDownsampleFactor;
  }

  public Options scoreDownsampleFactor(int scoreDownsampleFactor) {
    this.scoreDownsampleFactor = scoreDownsampleFactor;
    return this;
  }

  public int getScoreDownsampleMinSize() {
    return scoreDownsampleMinSize;
  }

  public Options scoreDownsampleMinSize(int scoreDownsampleMinSize) {
    this.scoreDownsampleMinSize = scoreDownsampleMinSize;
    return this;
  }

  public int getStep() {
    return step;
  }

  public Options step(int step) {
    this.step = step;
    return this;
  }

  public float getScaleStep() {
    return scaleStep;
  }

  public Options scaleStep(float scaleStep) {
    this.scaleStep = scaleStep;
    return this;
  }

  public float getMinScale() {
    return minScale;
  }

  public Options minScale(float minScale) {
    this.minScale = minScale;
    return this;
  }

  public float getMaxScale() {
    return maxScale;
  }

  public Options maxScale(float maxScale) {
    this.maxScale = maxScale;
    return this;
  }

  public float getEdgeRadius() {
    return edgeRadius;
  }

  public Options edgeRadius(float edgeRadius) {
    this.edgeRadius = edgeRadius;
    return this;
  }

  public float getEdgeWeight() {
    return edgeWeight;
  }

  public Options edgeWeight(float edgeWeight) {
    this.edgeWeight = edgeWeight;
    return this;
  }

  public float getOutsideImportance() {
    return outsideImportance;
  }

  public Options outsideImportance(float outsideImportance) {
    this.outsideImportance = outsideImportance;
    return this;
  }

  public boolean isRuleOfThirds() {
    return ruleOfThirds;
  }

  public Options ruleOfThirds(boolean ruleOfThirds) {
    this.ruleOfThirds = ruleOfThirds;
    return this;
  }

  public int getBufferedBitmapType() {
    return bufferedBitmapType;
  }

  public Options bufferedBitmapType(int bufferedBitmapType) {
    this.bufferedBitmapType = bufferedBitmapType;
    return this;
  }
}
