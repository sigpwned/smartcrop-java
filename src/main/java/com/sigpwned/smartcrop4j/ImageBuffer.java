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
import java.util.Arrays;

class ImageBuffer {

  public static ImageBuffer fromBufferedImage(BufferedImage bufferedImage) {
    final int width = bufferedImage.getWidth();
    final int height = bufferedImage.getHeight();
    return new ImageBuffer(bufferedImage.getWidth(), bufferedImage.getHeight(),
        bufferedImage.getRGB(0, 0, width, height, null, 0, width));
  }

  private static int[] getDefaultData(int width, int height) {
    int[] result = new int[width * height];
    Arrays.fill(result, 0xff000000);
    return result;
  }

  public final int width;
  public final int height;
  public final int[] data;

  public ImageBuffer(int width, int height) {
    this(width, height, getDefaultData(width, height));
  }

  private ImageBuffer(int width, int height, int[] data) {
    if (width <= 0) {
      throw new IllegalArgumentException("width must be positive");
    }
    if (height <= 0) {
      throw new IllegalArgumentException("height must be positive");
    }
    if (data == null) {
      throw new NullPointerException();
    }
    if (data.length != width * height) {
      throw new IllegalArgumentException("data length must be width * height");
    }
    this.width = width;
    this.height = height;
    this.data = data;
  }

  public int[] getRGB() {
    return data;
  }
}
