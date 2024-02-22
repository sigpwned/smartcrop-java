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

import static java.util.Collections.*;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Objects;

public class ImageAnalysis {
  private final List<Crop> crops;

  public ImageAnalysis(List<Crop> crops) {
    this.crops = unmodifiableList(requireNonNull(crops).stream()
        .sorted((a, b) -> -Float.compare(a.getScore().getTotal(), b.getScore().getTotal()))
        .collect(toList()));
    if (crops.isEmpty()) {
      throw new IllegalArgumentException("crops must not be empty");
    }
  }

  public Crop getTopCrop() {
    return getCrops().get(0);
  }

  public List<Crop> getCrops() {
    return crops;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ImageAnalysis)) {
      return false;
    }
    ImageAnalysis that = (ImageAnalysis) o;
    return Objects.equals(getCrops(), that.getCrops());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCrops());
  }

  @Override
  public String toString() {
    return "ImageAnalysis{" +
        "crops=" + crops +
        '}';
  }
}
