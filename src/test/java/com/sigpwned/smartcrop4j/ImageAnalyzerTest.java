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


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;

import com.sigpwned.smartcrop4j.util.Analysis;
import com.sigpwned.smartcrop4j.util.Cropping;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import org.junit.Test;

/**
 * @author flask
 */
public class ImageAnalyzerTest {

  @Test
  public void givenSample_whenCropSample_thenGetExpectedCrop() throws Exception {
    URL kittyUrl = getClass().getResource("samples/kitty.jpg");
    File kittyFile = new File(kittyUrl.toURI());
    BufferedImage kittyImage = ImageIO.read(kittyFile);

    ImageAnalysis analysis = Analysis.analyze(kittyImage);

    assertThat(analysis.getTopCrop().getRegion(), is(new Rect(1, 1, 424, 424)));
  }

  @Test
  public void givenSampleDirectory_whenCropAllSamples_thenAllImagesAreSmaller() throws Exception {
    URL exampleSampleUrl = getClass().getResource("samples/kitty.jpg");
    File samplesDir = new File(exampleSampleUrl.toURI()).getParentFile();
    for (File sample : samplesDir.listFiles()) {
      if (sample.getName().endsWith(".jpg")) {
        BufferedImage originalImage = ImageIO.read(sample);
        BufferedImage croppedImage = Cropping.crop(originalImage);
        assertThat(croppedImage.getWidth(), lessThanOrEqualTo(originalImage.getWidth()));
        assertThat(croppedImage.getHeight(), lessThanOrEqualTo(originalImage.getHeight()));
      }
    }
  }
}
