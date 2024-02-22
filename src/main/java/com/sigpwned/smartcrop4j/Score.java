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
public class Score implements Comparable<Score> {

  private final float detail;
  private final float saturation;
  private final float skin;
  private final float total;

  public Score(float detail, float saturation, float skin, float total) {
    this.detail = detail;
    this.saturation = saturation;
    this.skin = skin;
    this.total = total;
  }

  public float getDetail() {
    return detail;
  }

  public float getSaturation() {
    return saturation;
  }

  public float getSkin() {
    return skin;
  }

  public float getTotal() {
    return total;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Score score = (Score) o;
    return Float.compare(detail, score.detail) == 0
        && Float.compare(saturation, score.saturation) == 0
        && Float.compare(skin, score.skin) == 0
        && Float.compare(total, score.total) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(detail, saturation, skin, total);
  }

  @Override
  public String toString() {
    return "Score{" +
        "detail=" + detail +
        ", saturation=" + saturation +
        ", skin=" + skin +
        ", total=" + total +
        '}';
  }

  @Override
  public int compareTo(Score that) {
    return Float.compare(this.getTotal(), that.getTotal());
  }
}
