/*
 * Copyright (c) 2011-2023 PrimeFaces Extensions
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package org.primefaces.showcase.view.input;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import org.primefaces.event.SelectEvent;
import org.primefaces.extensions.model.inputphone.Country;

/**
 * @author Jasper de Vries &lt;jepsar@gmail.com&gt;
 */
@Named
@ViewScoped
@Data
@RegisterForReflection(serialization = true)
public class InputPhoneView implements Serializable {

    private static final long serialVersionUID = 1L;
    private String phoneNumber;
    private String localized;
    private String geoIp;
    private Map<String, String> localizedCountries;

    @PostConstruct
    protected void init() {
        localizedCountries = new HashMap<>();
        localizedCountries.put("nl", "Nederland");
        localizedCountries.put("be", "België");
        localizedCountries.put("de", "Duitsland");
    }

    public void onCountrySelect(final SelectEvent<Country> event) {
        final Country country = event.getObject();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Selected " + country.getName(), null));
    }

    public void submit() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Phone Number  " + phoneNumber, null));
    }

}