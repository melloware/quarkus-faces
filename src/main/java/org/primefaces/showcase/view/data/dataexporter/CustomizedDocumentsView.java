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
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.primefaces.component.export.ExcelOptions;
import org.primefaces.component.export.PDFOptions;
import org.primefaces.component.export.PDFOrientationType;
import org.primefaces.showcase.domain.Product;
import org.primefaces.showcase.service.ProductService;

@Named
@RequestScoped
@RegisterForReflection(serialization = true)
public class CustomizedDocumentsView implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Inject
    ProductService service;
    private List<Product> products;
    private List<Product> products2;
    private ExcelOptions excelOpt;
    private PDFOptions pdfOpt;

    @PostConstruct
    public void init() {
        products = service.getProducts(100);

        products2 = service.getProducts(100);
        customizationOptions();
    }

    public void customizationOptions() {
        excelOpt = new ExcelOptions();
        excelOpt.setFacetBgColor("#F88017");
        excelOpt.setFacetFontSize("10");
        excelOpt.setFacetFontColor("#0000ff");
        excelOpt.setFacetFontStyle("BOLD");
        excelOpt.setCellFontColor("#145c14");
        excelOpt.setCellFontSize("8");
        excelOpt.setFontName("Verdana");

        pdfOpt = new PDFOptions();
        pdfOpt.setFacetBgColor("#F88017");
        pdfOpt.setFacetFontColor("#0000ff");
        pdfOpt.setFacetFontStyle("BOLD");
        pdfOpt.setCellFontSize("12");
        pdfOpt.setFontName("Courier");
        pdfOpt.setOrientation(PDFOrientationType.LANDSCAPE);
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Product> getProducts2() {
        return products2;
    }

    public ExcelOptions getExcelOpt() {
        return excelOpt;
    }

    public void setExcelOpt(ExcelOptions excelOpt) {
        this.excelOpt = excelOpt;
    }

    public PDFOptions getPdfOpt() {
        return pdfOpt;
    }

    public void setPdfOpt(PDFOptions pdfOpt) {
        this.pdfOpt = pdfOpt;
    }

    public void setService(ProductService service) {
        this.service = service;
    }

    public void onRowExportXLS(Object document) {
        SXSSFWorkbook wb = (SXSSFWorkbook) document;
        SXSSFSheet sheet = wb.getSheetAt(0);
        int rowIndex = sheet.getLastRowNum();
        if (rowIndex % 2 == 0) { //striped rows
            SXSSFRow header = sheet.getRow(rowIndex);

            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
                SXSSFCell cell = header.getCell(i);

                cell.setCellStyle(cellStyle);
            }
        }
    }

    public void preProcessPDF(Object document) throws IOException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
        URL url = new URL("https://www.primefaces.org/wp-content/uploads/2016/10/prime_logo_new.png");
        pdf.add(Image.getInstance(Objects.requireNonNull(url)));
    }
}