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
package com.sigpwned.smartcrop4j.util;

import com.sigpwned.smartcrop4j.Rect;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public final class BufferedImages {

  private BufferedImages() {
  }

  public static BufferedImage cropped(BufferedImage image, Rect region, int type) {
    final BufferedImage result = new BufferedImage(region.getWidth(), region.getHeight(), type);

    final Graphics2D g = (Graphics2D) image.getGraphics();
    try {
      g.drawImage(image, 0, 0, region.getWidth(), region.getHeight(), region.getX(), region.getY(),
          region.getWidth(), region.getHeight(), null);
    } finally {
      g.dispose();
    }

    return result;
  }

  public static BufferedImage scaled(BufferedImage image, float scale, int type) {
    BufferedImage result = new BufferedImage(
        (int) Math.max(image.getWidth() * scale, 1.0f),
        (int) Math.max(image.getHeight() * scale, 1.0f),
        type);

    final Graphics2D g = (Graphics2D) image.getGraphics();
    try {
      g.drawImage(image, 0, 0, result.getWidth(), result.getHeight(), 0, 0, image.getWidth(),
          image.getHeight(), null);
    } finally {
      g.dispose();
    }

    return result;
  }
}
