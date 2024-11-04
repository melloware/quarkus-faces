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
package org.primefaces.showcase.view.data.dataexporter;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.primefaces.model.LazyDataModel;
import org.primefaces.showcase.domain.Customer;
import org.primefaces.showcase.service.CustomerService;
import org.primefaces.showcase.view.data.datatable.LazyCustomerDataModel;

@Named("deLazyView")
@ViewScoped
@RegisterForReflection(serialization = true)
public class LazyView implements Serializable {

    @Inject
    CustomerService service;
    private LazyDataModel<Customer> lazyModel;
    private List<Customer> filteredCustomers;

    @PostConstruct
    public void init() {
        lazyModel = new LazyCustomerDataModel(service.getCustomers(200));
    }

    public LazyDataModel<Customer> getLazyModel() {
        return lazyModel;
    }

    public List<Customer> getFilteredCustomers() {
        return filteredCustomers;
    }

    public void setFilteredCustomers(List<Customer> filteredCustomers) {
        this.filteredCustomers = filteredCustomers;
    }

    public void setService(CustomerService service) {
        this.service = service;
    }
}