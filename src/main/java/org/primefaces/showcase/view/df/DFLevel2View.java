/*
 * The MIT License
 *
 * Copyright (c) 2009-2024 PrimeTek Informatics
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
package org.primefaces.showcase.view.df;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.HashMap;
import java.util.Map;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

@Named("dfLevel2View")
@RequestScoped
@RegisterForReflection(serialization = true)
public class DFLevel2View {

    public void openLevel3() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        PrimeFaces.current().dialog().openDynamic("level3", options, null);
    }

    public void onReturnFromLevel3(SelectEvent event) {
        //pass back to level 1
        PrimeFaces.current().dialog().closeDynamic(event.getObject());
    }
}