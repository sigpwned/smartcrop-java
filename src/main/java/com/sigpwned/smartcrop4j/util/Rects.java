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

/**
 * @author sigpwned
 */
public final class Rects {

  private Rects() {
  }

  public static Rect scaled(Rect region, float scale) {
    if (region == null) {
      throw new NullPointerException();
    }
    if (scale <= 0.0f) {
      throw new IllegalArgumentException("scale must be positive");
    }
    if(scale == 1.0f) {
      return region;
    }
    return new Rect(
        (int) Math.max(region.getX() * scale, 1.0f),
        (int) Math.max(region.getY() * scale, 1.0f),
        (int) Math.max(region.getWidth() * scale, 1.0f),
        (int) Math.max(region.getHeight() * scale, 1.0f));
  }
}
