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
package org.primefaces.showcase.view.data.datatable;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.primefaces.showcase.domain.Customer;
import org.primefaces.showcase.service.CustomerService;

@Named("dtRowGroupView")
@ViewScoped
@RegisterForReflection(serialization = true)
public class RowGroupView implements Serializable {

    @Inject
    CustomerService service;
    private List<Customer> customers;

    @PostConstruct
    public void init() {
        customers = service.getCustomers(50);
    }

    public long getTotalCount(String name) {
        return customers.stream().filter(customer -> name.equals(customer.getRepresentative().getName())).count();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setService(CustomerService service) {
        this.service = service;
    }
}