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

import java.util.Objects;

/**
 * @author sigpwned
 */
public class Rect {

  private final int x;
  private final int y;
  private final int width;
  private final int height;

  public Rect(int x, int y, int width, int height) {
    if (x < 0) {
      throw new IllegalArgumentException("x must not be negative");
    }
    if (y < 0) {
      throw new IllegalArgumentException("y must not be negative");
    }
    if (width <= 0) {
      throw new IllegalArgumentException("width must be positive");
    }
    if (height <= 0) {
      throw new IllegalArgumentException("height must be positive");
    }
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rect other = (Rect) o;
    return x == other.x && y == other.y && width == other.width && height == other.height;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, width, height);
  }

  @Override
  public String toString() {
    return "Rect{" + "x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + '}';
  }
}
