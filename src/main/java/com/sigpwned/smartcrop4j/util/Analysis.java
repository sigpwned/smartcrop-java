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

import com.sigpwned.smartcrop4j.ImageAnalysis;
import com.sigpwned.smartcrop4j.ImageAnalyzer;
import com.sigpwned.smartcrop4j.Options;
import com.sigpwned.smartcrop4j.Rect;
import java.awt.image.BufferedImage;

/**
 * @author sigpwned
 */
public final class Analysis {

  private Analysis() {
  }

  public static ImageAnalysis analyze(BufferedImage input) {
    return analyze(Options.DEFAULT, input);
  }

  public static ImageAnalysis analyze(BufferedImage input, int cropWidth, int cropHeight) {
    return analyze(Options.ofDimensions(cropWidth, cropHeight), input);
  }

  public static ImageAnalysis analyze(Options options, BufferedImage input) {
    return new ImageAnalyzer(options).analyze(input);
  }

  public static Rect getBestCrop(BufferedImage input) {
    return getBestCrop(Options.DEFAULT, input);
  }

  public static Rect getBestCrop(BufferedImage input, int width, int height) {
    return getBestCrop(Options.ofDimensions(width, height), input);
  }

  public static Rect getBestCrop(Options options, BufferedImage input) {
    return analyze(options, input).getTopCrop().getRegion();
  }
}
