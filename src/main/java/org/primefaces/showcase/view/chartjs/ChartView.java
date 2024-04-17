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
package org.primefaces.showcase.view.chartjs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import software.xdev.chartjs.model.charts.*;
import software.xdev.chartjs.model.color.Color;
import software.xdev.chartjs.model.data.*;
import software.xdev.chartjs.model.datapoint.BubbleDataPoint;
import software.xdev.chartjs.model.datapoint.ScatterDataPoint;
import software.xdev.chartjs.model.dataset.*;
import software.xdev.chartjs.model.enums.FontStyle;
import software.xdev.chartjs.model.enums.ScalesPosition;
import software.xdev.chartjs.model.options.*;
import software.xdev.chartjs.model.options.elements.Fill;
import software.xdev.chartjs.model.options.scales.*;
import software.xdev.chartjs.model.options.ticks.CategoryTicks;
import software.xdev.chartjs.model.options.ticks.RadialLinearTicks;

import org.primefaces.event.ItemSelectEvent;

@Named
@RequestScoped
public class ChartView implements Serializable {

    private static final long serialVersionUID = 1L;

    private String json;
    private String barModel;
    private String bubbleModel;
    private String cartesianLinerModel;
    private String donutModel;
    private String lineModel;
    private String pieModel;
    private String polarAreaModel;
    private String radarModel;
    private String scatterModel;
    private String stackedBarModel;

    @PostConstruct
    public void init() {
        createBarModel();
        createBubbleModel();
        createCartesianLinerModel();
        createDonutModel();
        createJsonModel();
        createLineModel();
        createPieModel();
        createPolarAreaModel();
        createRadarModel();
        createScatterModel();
        createStackedBarModel();
    }

    private void createPieModel() {
        pieModel = new PieChart()
                .setData(new PieData()
                .addDataset(new PieDataset()
                        .setData(BigDecimal.valueOf(300), BigDecimal.valueOf(50), BigDecimal.valueOf(100))
                        .setLabel("My First Dataset")
                        .addBackgroundColors(new Color(255, 99, 132), new Color(54, 162, 235), new Color(255, 205, 86))
                )
                .setLabels("Red", "Blue", "Yellow"))
                .toJson();
    }

    private void createPolarAreaModel() {
        polarAreaModel = new PolarChart()
                .setData(new PolarData()
                .addDataset(new PolarDataset()
                        .setData(BigDecimal.valueOf(11),
                                BigDecimal.valueOf(16),
                                BigDecimal.valueOf(7),
                                BigDecimal.valueOf(3),
                                BigDecimal.valueOf(14))
                        .setLabel("My First Dataset")
                        .addBackgroundColors(
                                new Color(255, 99, 132),
                                new Color(75, 192, 192),
                                new Color(255, 205, 86),
                                new Color(201, 203, 207),
                                new Color(54, 162, 235)
                        )
                )
                .setLabels("Red", "Green", "Yellow", "Grey", "Blue" ))
                .toJson();
    }

    public void createLineModel() {
        lineModel = new LineChart()
                .setData(new LineData()
                .addDataset(new LineDataset()
                        .setData(65, 59, 80, 81, 56, 55, 40)
                        .setLabel("My First Dataset")
                        .setBorderColor(new Color(75, 192, 192))
                        .setLineTension(0.1f)
                        .setFill(new Fill<Boolean>(false)))
                .setLabels("January", "February", "March", "April", "May", "June", "July"))
                .setOptions(new LineOptions()
                        .setResponsive(true)
                        .setMaintainAspectRatio(false)
                        .setPlugins(new Plugins()
                                .setTitle(new Title()
                                        .setDisplay(true)
                                        .setText("Line Chart Subtitle")))
                ).toJson();
    }

    public void createScatterModel() {
        scatterModel = new ScatterChart()
                .setData(new ScatterData()
                .addDataset(new ScatterDataset()
                        .addData(new ScatterDataPoint(-10, 0))
                        .addData(new ScatterDataPoint(0, 10))
                        .addData(new ScatterDataPoint(10, 5))
                        .addData(new ScatterDataPoint(8, 14))
                        .addData(new ScatterDataPoint(12, 2))
                        .addData(new ScatterDataPoint(13, 7))
                        .addData(new ScatterDataPoint(6, 9))
                        .setLabel("Red Dataset")
                        .setBorderColor(new Color(249, 24, 24))
                        .setShowLine(Boolean.FALSE)
                        .setFill(new Fill<Boolean>(true)))
                )
                .setOptions(new LineOptions()
                        .setResponsive(true)
                        .setShowLine(Boolean.FALSE)
                        .setScales(new Scales()
                                .addScale(Scales.ScaleAxis.X, new LinearScale().setPosition(ScalesPosition.BOTTOM)))
                        .setPlugins(new Plugins()
                                .setTitle(new Title()
                                        .setDisplay(true)
                                        .setText("Scatter Chart")))
                ).toJson();
    }

    public void createCartesianLinerModel() {
        cartesianLinerModel = new LineChart()
                .setData(new LineData()
                .addDataset(new LineDataset()
                        .setData(20, 50, 100, 75, 25, 0)
                        .setLabel("Left Dataset")
                        .setLineTension(0.5f)
                        .setYAxisID("left-y-axis")
                        .setFill(new Fill<Boolean>(true)))
                .addDataset(new LineDataset()
                        .setData(0.1, 0.5, 1.0, 2.0, 1.5, 0)
                        .setLabel("Right Dataset")
                        .setLineTension(0.5f)
                        .setYAxisID("right-y-axis")
                        .setFill(new Fill<Boolean>(true)))
                .setLabels("Jan", "Feb", "Mar", "Apr", "May", "Jun"))
                .setOptions(new LineOptions()
                        .setResponsive(true)
                        .setMaintainAspectRatio(false)
                        .setScales(new Scales()
                                .addScale("left-y-axis", new LinearScale().setPosition(ScalesPosition.LEFT))
                                .addScale("right-y-axis", new LinearScale().setPosition(ScalesPosition.RIGHT)))
                        .setPlugins(new Plugins()
                                .setTitle(new Title()
                                        .setDisplay(true)
                                        .setText("Cartesian Linear Chart")))
                ).toJson();
    }



    public void createBarModel() {
        barModel = new BarChart()
                .setData(new BarData()
                .addDataset(new BarDataset()
                        .setData(65, 59, 80, 81, 56, 55, 40)
                        .setLabel("My First Dataset")
                        .setBackgroundColor(new Color(255, 99, 132, 0.2))
                        .setBorderColor(new Color(255, 99, 132))
                        .setBorderWidth(1))
                .addDataset(new BarDataset()
                        .setData(85, 69, 20, 51, 76, 75, 10)
                        .setLabel("My Second Dataset")
                        .setBackgroundColor(new Color(255, 159, 64, 0.2))
                        .setBorderColor(new Color(255, 159, 64))
                        .setBorderWidth(1)
                )
                .setLabels("January", "February", "March", "April", "May", "June", "July"))
                .setOptions(new BarOptions()
                        .setResponsive(true)
                        .setMaintainAspectRatio(false)
                        .setIndexAxis(BarOptions.IndexAxis.X)
                        .setScales(new Scales().addScale(Scales.ScaleAxis.Y, new BarScale<CategoryTicks>()
                                .setBarPercentage(BigDecimal.valueOf(0.9))
                                .setStacked(false)
                                .setTicks(new CategoryTicks()
                                        .setAutoSkip(true)
                                        .setMirror(true)))
                        )
                        .setPlugins(new Plugins()
                                .setTitle(new Title()
                                        .setDisplay(true)
                                        .setText("Bar Chart using XDEV java model")))
                ).toJson();
    }

    public void createStackedBarModel() {
        stackedBarModel = new BarChart()
                .setData(new BarData()
                .addDataset(new BarDataset()
                        .setData(62, -58, -49, 25, 4, 77, -41)
                        .setLabel("Dataset 1")
                        .setBackgroundColor(new Color(255, 99, 132)))
                .addDataset(new BarDataset()
                        .setData(-1, 32, -52, 11, 97, 76, -78)
                        .setLabel("Dataset 2")
                        .setBackgroundColor(new Color(54, 162, 235)))
                .addDataset(new BarDataset()
                        .setData(-44, 25, 15, 92, 80, -25, -11)
                        .setLabel("Dataset 3")
                        .setBackgroundColor(new Color(75, 192, 192)))
                .setLabels("January", "February", "March", "April", "May", "June", "July"))
                .setOptions(new BarOptions()
                        .setResponsive(true)
                        .setMaintainAspectRatio(false)
                        .setScales(new Scales()
                                .addScale(Scales.ScaleAxis.X, new BarScale<CategoryTicks>()
                                        .setStacked(true)
                                        .setTicks(new CategoryTicks()))
                                .addScale(Scales.ScaleAxis.Y, new BarScale<CategoryTicks>()
                                        .setStacked(true)
                                        .setTicks(new CategoryTicks()))
                        )
                        .setPlugins(new Plugins()
                                .setTooltip(new Tooltip().setMode("index"))
                                .setTitle(new Title()
                                        .setDisplay(true)
                                        .setText("Bar Chart - Stacked")))
                ).toJson();
    }

    public void createRadarModel() {
        radarModel = new RadarChart()
                .setData(new RadarData()
                .addDataset(new RadarDataset()
                        .setData(BigDecimal.valueOf(2.2),
                                BigDecimal.valueOf(3),
                                BigDecimal.valueOf(2.4),
                                BigDecimal.valueOf(1.1),
                                BigDecimal.valueOf(3))
                        .setLabel("P.Practitioner")
                        .setLineTension(0.1f)
                        .setBackgroundColor(new Color(102, 153, 204, 0.2))
                        .setBorderColor(new Color(102, 153, 204, 1))
                        .setPointBackgroundColor(List.of(new Color(102, 153, 204, 1)))
                        .setPointBorderColor(List.of(Color.WHITE))
                        .setPointHoverRadius(List.of(5))
                        .setPointHoverBackgroundColor(List.of(Color.WHITE))
                        .setPointHoverBorderColor(List.of(new Color(102, 153, 204, 1))))
                .addDataset(new RadarDataset()
                        .setData(BigDecimal.valueOf(2.1),
                                BigDecimal.valueOf(3),
                                BigDecimal.valueOf(3),
                                BigDecimal.valueOf(2.7),
                                BigDecimal.valueOf(3))
                        .setLabel("P.Manager")
                        .setLineTension(0.1f)
                        .setBackgroundColor(new Color(255, 204, 102, 0.2))
                        .setBorderColor(new Color(255, 204, 102, 1))
                        .setPointBackgroundColor(List.of(new Color(255, 204, 102, 1)))
                        .setPointBorderColor(List.of(Color.WHITE))
                        .setPointHoverRadius(List.of(5))
                        .setPointHoverBackgroundColor(List.of(Color.WHITE))
                        .setPointHoverBorderColor(List.of(new Color(255, 204, 102, 1))))
                .setLabels("Process Excellence", "Problem Solving", "Facilitation", "Project Mgmt", "Change Mgmt"))
                .setOptions(new RadarOptions()
                        .setResponsive(true)
                        .setMaintainAspectRatio(false)
                        .setScales(new Scales().addScale(Scales.ScaleAxis.Y, new RadialLinearScale<RadialLinearTicks>()
                                .setAngleLines(new AngleLines()
                                        .setDisplay(Boolean.TRUE)
                                        .setLineWidth(BigDecimal.valueOf(0.5))
                                        .setColor(new Color(128, 128, 128, 0.2)))
                                .setPointLabels(new PointLabels()
                                        .setFontSize(BigDecimal.valueOf(14))
                                        .setFontStyle(FontStyle.NORMAL)
                                        .setFontFamily("Lato, sans-serif")
                                        .setFontColor(new Color(204, 204, 204, 1)))
                                .setTicks(new RadialLinearTicks()
                                        .setBeginAtZero(Boolean.TRUE)
                                        .setDisplay(false)
                                        .setMin(BigDecimal.ZERO)
                                        .setAutoSkip(false)
                                        .setStepSize(BigDecimal.valueOf(0.2))
                                        .setMax(BigDecimal.valueOf(3))))
                        )
                        .setPlugins(new Plugins()
                                .setTitle(new Title()
                                        .setDisplay(true)
                                        .setText("Radar Chart")))
                ).toJson();

    }

    public void createBubbleModel() {
        bubbleModel = new BubbleChart()
                .setData(new BubbleData()
                .addDataset(new BubbleDataset()
                        .addData(new BubbleDataPoint(BigDecimal.valueOf(20), BigDecimal.valueOf(30), BigDecimal.valueOf(15)))
                        .addData(new BubbleDataPoint(BigDecimal.valueOf(40), BigDecimal.valueOf(10), BigDecimal.valueOf(10)))
                        .setLabel("My First Dataset")
                        .setBackgroundColor(new Color(255, 99, 132))
                        .setBorderColor(new Color(255, 99, 132))
                )).toJson();
    }

    public void createDonutModel() {
        donutModel = new DoughnutChart()
                .setData(new DoughnutData()
                .addDataset(new DoughnutDataset()
                        .setData(BigDecimal.valueOf(300),
                                BigDecimal.valueOf(50),
                                BigDecimal.valueOf(100))
                        .addBackgroundColors(
                                new Color(255, 99, 132),
                                new Color(54, 162, 235),
                                new Color(255, 205, 86))
                )
                .setLabels("Red", "Yellow", "Blue"))
                .setOptions(new DoughnutOptions().setMaintainAspectRatio(Boolean.FALSE))
                .toJson();
    }

    public void createJsonModel() {
        json = """
                {
                   "type":"line",
                   "data":{
                      "datasets":[
                         {
                            "backgroundColor":"rgba(40, 180, 99, 0.3)",
                            "borderColor":"rgb(40, 180, 99)",
                            "borderWidth":1,
                            "data":[
                               {
                                  "x":1699457269877,
                                  "y":20
                               },
                               {
                                  "x":1700047109694,
                                  "y":20
                               }
                            ],
                            "hidden":false,
                            "label":"Device Id: 524967 Register: A - total Wh ",
                            "minBarLength":3
                         },
                         {
                            "backgroundColor":"rgba(218, 117, 255, 0.3)",
                            "borderColor":"rgb(218, 117, 255)",
                            "borderWidth":1,
                            "data":[
                               {
                                  "x":1699457267847,
                                  "y":10
                               },
                               {
                                  "x":1700047108397,
                                  "y":234
                               }
                            ],
                            "hidden":false,
                            "label":"Device Id: 524967 Register: A+ total Wh ",
                            "minBarLength":3
                         }
                      ]
                   },
                   "options":{
                      "plugins":{
                         "legend":{
                            "display":true,
                            "fullWidth":true,
                            "position":"top",
                            "reverse":false,
                            "rtl":false
                         },
                         "title":{
                            "display":true,
                            "text":"Values from the meter"
                         },
                         "zoom":{
                            "pan":{
                               "enabled":true,
                               "mode":"xy",
                               "threshold":5
                            },
                            "zoom":{
                               "wheel":{
                                  "enabled":true
                               },
                               "pinch":{
                                  "enabled":true
                               },
                               "mode":"xy"
                            }
                         }
                      },
                      "scales":{
                         "x":{
                            "beginAtZero":false,
                            "offset":true,
                            "reverse":false,
                            "stacked":true,
                            "ticks":{
                               "autoSkip":true,
                               "maxRotation":0,
                               "minRotation":0,
                               "mirror":false,
                               "source":"data"
                            },
                            "time":{
                               "displayFormats":{
                                  "minute":"dd.LL T"
                               },
                               "round":"minute",
                               "stepSize":"60",
                               "unit":"minute"
                            },
                            "type":"time"
                         },
                         "y":{
                            "beginAtZero":false,
                            "offset":false,
                            "reverse":false,
                            "stacked":true,
                            "ticks":{
                               "autoSkip":true,
                               "mirror":false
                            }
                         }
                      },
                      "showLine":true,
                      "spanGaps":false
                   }
                }\
                """;
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Value: " + event.getData()
                + ", Item Index: " + event.getItemIndex()
                + ", DataSet Index:" + event.getDataSetIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getPieModel() {
        return pieModel;
    }

    public void setPieModel(String pieModel) {
        this.pieModel = pieModel;
    }

    public String getPolarAreaModel() {
        return polarAreaModel;
    }

    public void setPolarAreaModel(String polarAreaModel) {
        this.polarAreaModel = polarAreaModel;
    }

    public String getLineModel() {
        return lineModel;
    }

    public void setLineModel(String lineModel) {
        this.lineModel = lineModel;
    }

    public String getCartesianLinerModel() {
        return cartesianLinerModel;
    }

    public void setCartesianLinerModel(String cartesianLinerModel) {
        this.cartesianLinerModel = cartesianLinerModel;
    }

    public String getBarModel() {
        return barModel;
    }

    public void setBarModel(String barModel) {
        this.barModel = barModel;
    }

    public String getStackedBarModel() {
        return stackedBarModel;
    }

    public void setStackedBarModel(String stackedBarModel) {
        this.stackedBarModel = stackedBarModel;
    }

    public String getRadarModel() {
        return radarModel;
    }

    public void setRadarModel(String radarModel) {
        this.radarModel = radarModel;
    }

    public String getBubbleModel() {
        return bubbleModel;
    }

    public void setBubbleModel(String bubbleModel) {
        this.bubbleModel = bubbleModel;
    }

    public String getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(String donutModel) {
        this.donutModel = donutModel;
    }

    public String getScatterModel() {
        return scatterModel;
    }

    public void setScatterModel(String scatterModel) {
        this.scatterModel = scatterModel;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
