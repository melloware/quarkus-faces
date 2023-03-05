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
package org.primefaces.extensions.showcase.webapp;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;
import org.primefaces.showcase.util.VirtualMachine;

import lombok.Data;
import lombok.extern.jbosslog.JBossLog;

/**
 * TechnicalInfo.
 *
 * @author Oleg Varaksin / last modified by Melloware
 * @version $Revision$
 */
@ApplicationScoped
@Named
@Data
@JBossLog
public class TechnicalInfo {

    private String quarkus;
    private String omniFaces;
    private String primeFaces;
    private String primeFacesExt;
    private String facesImpl;
    private String server;

    @PostConstruct
    protected void initialize() {
        quarkus = "Quarkus: " + StringUtils.defaultIfEmpty(
                    io.quarkus.bootstrap.runner.QuarkusEntryPoint.class.getPackage().getImplementationVersion(), "???");
        omniFaces = "OmniFaces: " + StringUtils.defaultIfEmpty(
                    org.omnifaces.util.Faces.class.getPackage().getImplementationVersion(), "???");
        // PF version
        primeFaces = "PrimeFaces: " + StringUtils.defaultIfEmpty(
                    org.primefaces.util.Constants.class.getPackage().getImplementationVersion(), "???");
        primeFacesExt = "PrimeFaces Extensions: " + StringUtils
                    .defaultIfEmpty(org.primefaces.extensions.util.Constants.class.getPackage()
                                .getImplementationVersion(), "???");
        facesImpl = StringUtils.removeIgnoreCase(StringUtils.removeIgnoreCase(Faces.getImplInfo(), "Core"), "Impl");
        server = Faces.getServerInfo();
    }

    public String getMemory() {
        return VirtualMachine.getMemoryStatisticsAsString();
    }

}
