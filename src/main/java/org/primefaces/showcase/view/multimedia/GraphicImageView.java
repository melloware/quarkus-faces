/*
 * The MIT License
 *
 * Copyright (c) 2009-2021 PrimeTek
 *
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
 */
package org.primefaces.showcase.view.multimedia;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;
import jakarta.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import io.quarkus.runtime.annotations.RegisterForReflection;

@Named
@RequestScoped
@RegisterForReflection
public class GraphicImageView {

    private StreamedContent graphicText;

    private StreamedContent chart;

    @PostConstruct
    public void init() {
        try {
            graphicText = DefaultStreamedContent.builder()
                        .contentType("image/png")
                        .stream(() -> {
                            try {
                                BufferedImage bufferedImg = new BufferedImage(100, 25, BufferedImage.TYPE_INT_RGB);
                                Graphics2D g2 = bufferedImg.createGraphics();
                                g2.drawString("This is a text", 0, 10);
                                ByteArrayOutputStream os = new ByteArrayOutputStream();
                                ImageIO.write(bufferedImg, "png", os);
                                return new ByteArrayInputStream(os.toByteArray());
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                                return null;
                            }
                        })
                        .build();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StreamedContent getGraphicText() {
        return graphicText;
    }

    public StreamedContent getChart() {
        return chart;
    }
}
