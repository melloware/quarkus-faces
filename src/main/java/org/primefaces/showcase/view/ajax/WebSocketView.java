/*
 * The MIT License
 *
 * Copyright (c) 2009-2024 PrimeTek
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
package org.primefaces.showcase.view.ajax;

import jakarta.annotation.PostConstruct;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serial;
import java.io.Serializable;

import io.quarkus.logging.Log;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class WebSocketView implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    @Push
    PushContext pushChannel;

    @Getter
    @Setter
    private int count = 0;

    @PostConstruct
    public void init() {
        // Start a thread to increment count and send message 5 times to simulate some server activity
        // NOTE: Do not use threads in your real application this is just to simulate backend activity!
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(5000); // Sleep for 5 seconds
                    count++;
                    sendMessage();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        thread.start();
    }

    public void sendMessage() {
        Log.infof("Sending message: %d", count);
        pushChannel.send("quarkusMessage");
    }

}