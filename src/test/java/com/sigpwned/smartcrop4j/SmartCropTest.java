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
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by flask on 2015. 10. 27..
 */
public class SmartCropTest {

  static String samplePath = "src/test/resources/sample";
  static String debugPath = "src/test/resources/debug";
  static String resultPath = "src/test/resources/result";

  static Map<String, BufferedImage> bufferedImages = new ConcurrentHashMap<>();
  static Map<String, ImageAnalysis> cropResults = new ConcurrentHashMap<>();

  @BeforeClass
  public static void setup() throws Exception {

  }

//  @AfterClass
//  public static void teardown() {
//    cropResults.forEach((name, cropResult) -> {
//      new Thread(() -> {
//        try {
//          long b = System.currentTimeMillis();
//          String newName = name; // name.replace("jpg", "png");
//          ImageIO.write(cropResult.debugImage, "jpg", new File(debugPath, newName));
//          ImageIO.write(cropResult.resultImage, "jpg", new File(resultPath, newName));
//          System.out.println(
//              "saved... " + newName + " / took " + (System.currentTimeMillis() - b) + "ms");
//        } catch (IOException e) {
//          e.printStackTrace();
//        }
//      }).run();
//    });
//  }

  @Test
  public void test() throws Exception {
    System.out.println("class = " + getClass().getResource("samples/img.jpg"));
    System.out.println("classloader = " + Thread.currentThread().getContextClassLoader().getResource("com/sigpwned/smartcrop4j/samples/img.jpg"));
  }
}
