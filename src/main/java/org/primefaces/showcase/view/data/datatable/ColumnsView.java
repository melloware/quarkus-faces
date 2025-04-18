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
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.showcase.domain.Customer;
import org.primefaces.showcase.service.CustomerService;

@Named("dtColumnsView")
@ViewScoped
@RegisterForReflection(serialization = true)
public class ColumnsView implements Serializable {

    @Inject
    CustomerService service;
    private String columnTemplate = "name country date status activity";
    private List<ColumnModel> columns;
    private List<Customer> customers;
    private List<Customer> filteredCustomers;
    private Map<String, Class> validColumns;

    @PostConstruct
    public void init() {
        customers = service.getCustomers(10);

        validColumns = Stream.of(Customer.class.getDeclaredFields()).collect(Collectors.toMap(Field::getName, Field::getType));
        createDynamicColumns();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> getFilteredCustomers() {
        return filteredCustomers;
    }

    public void setFilteredCustomers(List<Customer> filteredCustomers) {
        this.filteredCustomers = filteredCustomers;
    }

    public CustomerService getService() {
        return service;
    }

    public void setService(CustomerService service) {
        this.service = service;
    }

    public String getColumnTemplate() {
        return columnTemplate;
    }

    public void setColumnTemplate(String columnTemplate) {
        this.columnTemplate = columnTemplate;
    }

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }

    private void createDynamicColumns() {
        String[] columnKeys = columnTemplate.split(" ");
        columns = new ArrayList<>();

        for (String columnKey : columnKeys) {
            String key = columnKey.trim();

            if (validColumns.containsKey(key)) {
                columns.add(new ColumnModel(columnKey.toUpperCase(), columnKey, validColumns.get(key)));
            }
        }
    }

    public void updateColumns() {
        //reset table state
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:customers");
        table.resetColumns();

        //update columns
        createDynamicColumns();
    }

    @RegisterForReflection(serialization = true)
    public static class ColumnModel implements Serializable {

        private final String header;
        private final String property;
        private final Class<?> klazz;
        private String type;

        public ColumnModel(String header, String property, Class klazz) {
            this.header = header;
            this.property = property;
            this.klazz = klazz;
            initType();
        }

        public String getHeader() {
            return header;
        }

        public String getProperty() {
            return property;
        }

        public String getType() {
            return type;
        }

        public Class<?> getKlazz() {
            return klazz;
        }

        private void initType() {
            if (Temporal.class.isAssignableFrom(klazz)) {
                type = "date";
            } else if (klazz.isEnum()) {
                type = "enum";
            }
        }
    }
}