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
package org.primefaces.showcase.menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class AppMenu {

    List<MenuCategory> menuCategories;
    List<MenuItem> menuItems;

    @PostConstruct
    public void init() {
        menuCategories = new ArrayList<>();
        menuItems = new ArrayList<>();

        //AJAX FRAMEWORK CATEGORY START
        List<MenuItem> ajaxFrameworkMenuItems = new ArrayList<>();
        ajaxFrameworkMenuItems.add(new MenuItem("Introduction", "/secure/ui/ajax/basic"));
        ajaxFrameworkMenuItems.add(new MenuItem("Process", "/secure/ui/ajax/process"));
        ajaxFrameworkMenuItems.add(new MenuItem("PartialSubmit", "/secure/ui/ajax/partialSubmit"));
        ajaxFrameworkMenuItems.add(new MenuItem("Selector", "/secure/ui/ajax/selector"));
        ajaxFrameworkMenuItems.add(new MenuItem("Search", "/secure/ui/ajax/search"));
        ajaxFrameworkMenuItems.add(new MenuItem("Validation", "/secure/ui/ajax/validation"));
        ajaxFrameworkMenuItems.add(new MenuItem("RemoteCommand", "/secure/ui/ajax/remoteCommand"));
        ajaxFrameworkMenuItems.add(new MenuItem("Observer", "/secure/ui/ajax/observer", "New"));
        ajaxFrameworkMenuItems.add(new MenuItem("Poll", "/secure/ui/ajax/poll"));
        ajaxFrameworkMenuItems.add(new MenuItem("Fragment", "/secure/ui/ajax/fragment"));
        ajaxFrameworkMenuItems.add(new MenuItem("Status", "/secure/ui/ajax/status"));
        ajaxFrameworkMenuItems.add(new MenuItem("Lifecycle", "/secure/ui/ajax/lifecycle"));
        menuCategories.add(new MenuCategory("Ajax Framework", ajaxFrameworkMenuItems));
        //AJAX FRAMEWORK CATEGORY END

        //FORM CATEGORY START
        List<MenuItem> formMenuItems = new ArrayList<>();
        formMenuItems.add(new MenuItem("AutoComplete", "/secure/ui/input/autoComplete"));

        formMenuItems.add(new MenuItem("CascadeSelect", "/secure/ui/input/cascadeSelect", "New"));
        formMenuItems.add(new MenuItem("Chips", "/secure/ui/input/chips"));

        List<MenuItem> colorPickerMenuItems = new ArrayList<>();
        colorPickerMenuItems.add(new MenuItem("Popup", "/secure/ui/input/colorPicker"));
        colorPickerMenuItems.add(new MenuItem("Inline", "/secure/ui/input/colorPickerInline"));
        formMenuItems.add(new MenuItem("ColorPicker", colorPickerMenuItems));

        //DatePicker Nested MenuItem
        List<MenuItem> datePickerMenuItems = new ArrayList<>();
        datePickerMenuItems.add(new MenuItem("java.util.Date", "/secure/ui/input/datepicker/datePicker"));
        datePickerMenuItems.add(new MenuItem("Java 8+ Date APIs", "/secure/ui/input/datepicker/datePickerJava8"));
        datePickerMenuItems.add(new MenuItem("Metadata", "/secure/ui/input/datepicker/metadata"));
        formMenuItems.add(new MenuItem("DatePicker", datePickerMenuItems));

        formMenuItems.add(new MenuItem("Inplace", "/secure/ui/input/inplace"));
        formMenuItems.add(new MenuItem("InputGroup", "/secure/ui/input/inputGroup"));
        formMenuItems.add(new MenuItem("InputMask", "/secure/ui/input/inputMask"));
        formMenuItems.add(new MenuItem("InputNumber", "/secure/ui/input/inputNumber"));
        formMenuItems.add(new MenuItem("InputPhone", "/secure/ui/input/inputPhone"));
        formMenuItems.add(new MenuItem("InputText", "/secure/ui/input/inputText"));
        formMenuItems.add(new MenuItem("InputTextArea", "/secure/ui/input/inputTextarea"));
        formMenuItems.add(new MenuItem("KeyFilter", "/secure/ui/input/keyFilter"));
        formMenuItems.add(new MenuItem("Keyboard", "/secure/ui/input/keyboard"));
        formMenuItems.add(new MenuItem("Knob", "/secure/ui/input/knob"));
        formMenuItems.add(new MenuItem("MultiSelectListBox", "/secure/ui/input/multiSelectListbox"));
        formMenuItems.add(new MenuItem("MonacoEditor", "/secure/ui/input/monacoEditor"));
        formMenuItems.add(new MenuItem("Password", "/secure/ui/input/password"));
        formMenuItems.add(new MenuItem("Rating", "/secure/ui/input/rating"));
        formMenuItems.add(new MenuItem("SelectBooleanButton", "/secure/ui/input/booleanButton"));
        formMenuItems.add(new MenuItem("SelectBooleanCheckbox", "/secure/ui/input/booleanCheckbox"));
        formMenuItems.add(new MenuItem("SelectOneButton", "/secure/ui/input/oneButton"));
        formMenuItems.add(new MenuItem("SelectOneRadio", "/secure/ui/input/oneRadio"));
        formMenuItems.add(new MenuItem("SelectCheckboxMenu", "/secure/ui/input/checkboxMenu"));
        formMenuItems.add(new MenuItem("SelectOneMenu", "/secure/ui/input/oneMenu"));
        formMenuItems.add(new MenuItem("SelectOneListbox", "/secure/ui/input/listbox"));
        formMenuItems.add(new MenuItem("SelectManyButton", "/secure/ui/input/manyButton"));
        formMenuItems.add(new MenuItem("SelectManyMenu", "/secure/ui/input/manyMenu"));
        formMenuItems.add(new MenuItem("SelectManyCheckbox", "/secure/ui/input/manyCheckbox"));
        formMenuItems.add(new MenuItem("Signature", "/secure/ui/input/signature"));
        formMenuItems.add(new MenuItem("Slider", "/secure/ui/input/slider"));
        formMenuItems.add(new MenuItem("Spinner", "/secure/ui/input/spinner"));
        formMenuItems.add(new MenuItem("TextEditor", "/secure/ui/input/textEditor"));
        formMenuItems.add(new MenuItem("ToggleSwitch", "/secure/ui/input/toggleSwitch"));
        formMenuItems.add(new MenuItem("TriStateCheckbox", "/secure/ui/input/triStateCheckbox"));
        menuCategories.add(new MenuCategory("Form", formMenuItems));
        //FORM CATEGORY END

        //BUTTON CATEGORY START
        List<MenuItem> buttonMenuItems = new ArrayList<>();
        buttonMenuItems.add(new MenuItem("Button", "/secure/ui/button/button"));
        buttonMenuItems.add(new MenuItem("CommandButton", "/secure/ui/button/commandButton"));
        buttonMenuItems.add(new MenuItem("CommandLink", "/secure/ui/button/commandLink"));
        buttonMenuItems.add(new MenuItem("Link", "/secure/ui/button/link"));
        buttonMenuItems.add(new MenuItem("LinkButton", "/secure/ui/button/linkButton"));
        buttonMenuItems.add(new MenuItem("SplitButton", "/secure/ui/button/splitButton"));
        menuCategories.add(new MenuCategory("Button", buttonMenuItems));
        //BUTTON CATEGORY END

        //DATA CATEGORY START
        List<MenuItem> dataMenuItems = new ArrayList<>();
        dataMenuItems.add(new MenuItem("Carousel", "/secure/ui/data/carousel"));
        dataMenuItems.add(new MenuItem("Chronoline", "/secure/ui/data/chronoline", "New"));

        //DataExporter Nested MenuItem
        List<MenuItem> dataExporterMenuItems = new ArrayList<>();
        dataExporterMenuItems.add(new MenuItem("Basic", "/secure/ui/data/dataexporter/basic"));
        dataExporterMenuItems.add(new MenuItem("Lazy", "/secure/ui/data/dataexporter/lazy"));
        dataExporterMenuItems.add(new MenuItem("Exclude", "/secure/ui/data/dataexporter/excludeColumns"));
        dataExporterMenuItems.add(new MenuItem("Customized", "/secure/ui/data/dataexporter/customizedDocuments"));
        dataMenuItems.add(new MenuItem("DataExporter", dataExporterMenuItems));

        //DataScroller Nested MenuItem
        List<MenuItem> dataScrollerMenuItems = new ArrayList<>();
        dataScrollerMenuItems.add(new MenuItem("Basic", "/secure/ui/data/datascroller/basic"));
        dataScrollerMenuItems.add(new MenuItem("Inline", "/secure/ui/data/datascroller/inline"));
        dataScrollerMenuItems.add(new MenuItem("Loader", "/secure/ui/data/datascroller/loader"));
        dataMenuItems.add(new MenuItem("DataScroller", dataScrollerMenuItems));

        //DataTable Nested MenuItem
        List<MenuItem> dataTableMenuItems = new ArrayList<>();
        dataTableMenuItems.add(new MenuItem("Basic", "/secure/ui/data/datatable/basic"));
        dataTableMenuItems.add(new MenuItem("ColToggler", "/secure/ui/data/datatable/columnToggler"));
        dataTableMenuItems.add(new MenuItem("Columns", "/secure/ui/data/datatable/columns"));
        dataTableMenuItems.add(new MenuItem("ContextMenu", "/secure/ui/data/datatable/contextMenu"));
        dataTableMenuItems.add(new MenuItem("Crud", "/secure/ui/data/datatable/crud"));
        dataTableMenuItems.add(new MenuItem("DisplayPriority", "/secure/ui/data/datatable/displayPriority"));
        dataTableMenuItems.add(new MenuItem("Edit", "/secure/ui/data/datatable/edit"));
        dataTableMenuItems.add(new MenuItem("Expansion", "/secure/ui/data/datatable/expansion"));
        dataTableMenuItems.add(new MenuItem("Facets", "/secure/ui/data/datatable/facets"));
        dataTableMenuItems.add(new MenuItem("Field", "/secure/ui/data/datatable/field"));
        dataTableMenuItems.add(new MenuItem("Filter", "/secure/ui/data/datatable/filter"));
        dataTableMenuItems.add(new MenuItem("Gridlines", "/secure/ui/data/datatable/gridlines", "New"));
        dataTableMenuItems.add(new MenuItem("Group", "/secure/ui/data/datatable/group"));
        dataTableMenuItems.add(new MenuItem("Lazy", "/secure/ui/data/datatable/lazy"));
        dataTableMenuItems.add(new MenuItem("MultiViewState", "/secure/ui/data/datatable/multiViewState"));
        dataTableMenuItems.add(new MenuItem("Paginator", "/secure/ui/data/datatable/paginator"));
        dataTableMenuItems.add(new MenuItem("Reorder", "/secure/ui/data/datatable/reorder"));
        dataTableMenuItems.add(new MenuItem("Resize", "/secure/ui/data/datatable/columnResize"));
        dataTableMenuItems.add(new MenuItem("Responsive", "/secure/ui/data/datatable/responsive"));
        dataTableMenuItems.add(new MenuItem("Row Add", "/secure/ui/data/datatable/addRow"));
        dataTableMenuItems.add(new MenuItem("Row Color", "/secure/ui/data/datatable/rowColor"));
        dataTableMenuItems.add(new MenuItem("Row Group", "/secure/ui/data/datatable/rowGroup"));
        dataTableMenuItems.add(new MenuItem("RTL", "/secure/ui/data/datatable/rtl"));
        dataTableMenuItems.add(new MenuItem("Scroll", "/secure/ui/data/datatable/scroll"));
        dataTableMenuItems.add(new MenuItem("Selection", "/secure/ui/data/datatable/selection"));
        dataTableMenuItems.add(new MenuItem("Size", "/secure/ui/data/datatable/size", "New"));
        dataTableMenuItems.add(new MenuItem("Sort", "/secure/ui/data/datatable/sort"));
        dataTableMenuItems.add(new MenuItem("Sticky", "/secure/ui/data/datatable/sticky"));
        dataTableMenuItems.add(new MenuItem("Striped", "/secure/ui/data/datatable/striped", "New"));
        dataMenuItems.add(new MenuItem("DataTable", dataTableMenuItems));

        //DataView Nested MenuItem
        List<MenuItem> dataViewMenuItems = new ArrayList<>();
        dataViewMenuItems.add(new MenuItem("Basic", "/secure/ui/data/dataview/basic"));
        dataViewMenuItems.add(new MenuItem("MultiViewState", "/secure/ui/data/dataview/multiViewState"));
        dataViewMenuItems.add(new MenuItem("Lazy", "/secure/ui/data/dataview/lazy"));
        dataViewMenuItems.add(new MenuItem("Responsive", "/secure/ui/data/dataview/responsive"));
        dataMenuItems.add(new MenuItem("DataView", dataViewMenuItems));

        //Diagram Nested MenuItem
        List<MenuItem> diagramMenuItems = new ArrayList<>();
        diagramMenuItems.add(new MenuItem("Basic", "/secure/ui/data/diagram/basic"));
        diagramMenuItems.add(new MenuItem("Hierarchical", "/secure/ui/data/diagram/hierarchical"));
        diagramMenuItems.add(new MenuItem("FlowChart", "/secure/ui/data/diagram/flowchart"));
        diagramMenuItems.add(new MenuItem("StateMachine", "/secure/ui/data/diagram/statemachine"));
        diagramMenuItems.add(new MenuItem("Editable", "/secure/ui/data/diagram/editable"));
        dataMenuItems.add(new MenuItem("Diagram", diagramMenuItems));

        //Gmap Nested MenuItem
        List<MenuItem> gmapMenuItems = new ArrayList<>();
        gmapMenuItems.add(new MenuItem("Basic", "/secure/ui/data/gmap/basic"));
        gmapMenuItems.add(new MenuItem("Event", "/secure/ui/data/gmap/event"));
        gmapMenuItems.add(new MenuItem("Markers", "/secure/ui/data/gmap/markers"));
        gmapMenuItems.add(new MenuItem("Selection", "/secure/ui/data/gmap/markerSelection"));
        gmapMenuItems.add(new MenuItem("Add Markers", "/secure/ui/data/gmap/addMarkers"));
        gmapMenuItems.add(new MenuItem("Draggable", "/secure/ui/data/gmap/draggableMarkers"));
        gmapMenuItems.add(new MenuItem("InfoWindow", "/secure/ui/data/gmap/infoWindow"));
        gmapMenuItems.add(new MenuItem("Polylines", "/secure/ui/data/gmap/polylines"));
        gmapMenuItems.add(new MenuItem("Polygons", "/secure/ui/data/gmap/polygons"));
        gmapMenuItems.add(new MenuItem("Circles", "/secure/ui/data/gmap/circles"));
        gmapMenuItems.add(new MenuItem("Rectangles", "/secure/ui/data/gmap/rectangles"));
        gmapMenuItems.add(new MenuItem("StreetView", "/secure/ui/data/gmap/street"));
        gmapMenuItems.add(new MenuItem("Controls", "/secure/ui/data/gmap/controls"));
        gmapMenuItems.add(new MenuItem("Geocode", "/secure/ui/data/gmap/geocode"));
        gmapMenuItems.add(new MenuItem("Dialog", "/secure/ui/data/gmap/mapDialog"));
        dataMenuItems.add(new MenuItem("Gmap", gmapMenuItems));

        //HorizontalTree Nested MenuItem
        List<MenuItem> horizontalTreeMenuItems = new ArrayList<>();
        horizontalTreeMenuItems.add(new MenuItem("Basic", "/secure/ui/data/htree/basic"));
        horizontalTreeMenuItems.add(new MenuItem("Icon", "/secure/ui/data/htree/icon"));
        horizontalTreeMenuItems.add(new MenuItem("Selection", "/secure/ui/data/htree/selection"));
        horizontalTreeMenuItems.add(new MenuItem("Events", "/secure/ui/data/htree/events"));
        horizontalTreeMenuItems.add(new MenuItem("ContextMenu", "/secure/ui/data/htree/contextMenu"));
        dataMenuItems.add(new MenuItem("HorizontalTree", horizontalTreeMenuItems));

        dataMenuItems.add(new MenuItem("OrderList", "/secure/ui/data/orderList"));
        dataMenuItems.add(new MenuItem("Organigram", "/secure/ui/data/organigram"));
        dataMenuItems.add(new MenuItem("Mindmap", "/secure/ui/data/mindmap"));
        dataMenuItems.add(new MenuItem("PickList", "/secure/ui/data/pickList"));

        //Schedule Nested MenuItem
        List<MenuItem> scheduleMenuItems = new ArrayList<>();
        scheduleMenuItems.add(new MenuItem("Basic", "/secure/ui/data/schedule/basic"));
        scheduleMenuItems.add(new MenuItem("Configuration", "/secure/ui/data/schedule/configuration"));
        scheduleMenuItems.add(new MenuItem("Lazy", "/secure/ui/data/schedule/lazy"));
        scheduleMenuItems.add(new MenuItem("Locale IL8N", "/secure/ui/data/schedule/localization"));
        scheduleMenuItems.add(new MenuItem("Extender", "/secure/ui/data/schedule/extender"));
        dataMenuItems.add(new MenuItem("Schedule", scheduleMenuItems));

        dataMenuItems.add(new MenuItem("TagCloud", "/secure/ui/data/tagCloud"));

        //Timeline Nested MenuItem
        List<MenuItem> timelineMenuItems = new ArrayList<>();
        timelineMenuItems.add(new MenuItem("Basic", "/secure/ui/data/timeline/basic"));
        timelineMenuItems.add(new MenuItem("Limit Range", "/secure/ui/data/timeline/limitRange"));
        timelineMenuItems.add(new MenuItem("Custom", "/secure/ui/data/timeline/custom"));
        timelineMenuItems.add(new MenuItem("DragDrop", "/secure/ui/data/timeline/dragdrop"));
        timelineMenuItems.add(new MenuItem("Linked Timelines", "/secure/ui/data/timeline/linked"));
        timelineMenuItems.add(new MenuItem("All Events", "/secure/ui/data/timeline/allEvents"));
        timelineMenuItems.add(new MenuItem("Grouping", "/secure/ui/data/timeline/grouping"));
        timelineMenuItems.add(new MenuItem("Nested Grouping", "/secure/ui/data/timeline/nestedGrouping"));
        timelineMenuItems.add(new MenuItem("Editable Server-Side", "/secure/ui/data/timeline/editServer"));
        timelineMenuItems.add(new MenuItem("Lazy Loading", "/secure/ui/data/timeline/lazy"));
        dataMenuItems.add(new MenuItem("Timeline", timelineMenuItems));

        //Tree Nested MenuItem
        List<MenuItem> treeMenuItems = new ArrayList<>();
        treeMenuItems.add(new MenuItem("Basic", "/secure/ui/data/tree/basic"));
        treeMenuItems.add(new MenuItem("Icon", "/secure/ui/data/tree/icon"));
        treeMenuItems.add(new MenuItem("Selection", "/secure/ui/data/tree/selection"));
        treeMenuItems.add(new MenuItem("Events", "/secure/ui/data/tree/events"));
        treeMenuItems.add(new MenuItem("DragDrop", "/secure/ui/data/tree/dragdrop"));
        treeMenuItems.add(new MenuItem("ContextMenu", "/secure/ui/data/tree/contextMenu"));
        treeMenuItems.add(new MenuItem("Animate", "/secure/ui/data/tree/animate"));
        treeMenuItems.add(new MenuItem("RTL", "/secure/ui/data/tree/rtl"));
        treeMenuItems.add(new MenuItem("Filter", "/secure/ui/data/tree/filter"));
        treeMenuItems.add(new MenuItem("Lazy Loading", "/secure/ui/data/tree/lazyloading"));
        dataMenuItems.add(new MenuItem("Tree", treeMenuItems));

        //TreeTable Nested MenuItem
        List<MenuItem> treeTableMenuItems = new ArrayList<>();
        treeTableMenuItems.add(new MenuItem("Basic", "/secure/ui/data/treetable/basic"));
        treeTableMenuItems.add(new MenuItem("Size", "/secure/ui/data/treetable/size", "New"));
        treeTableMenuItems.add(new MenuItem("Gridlines", "/secure/ui/data/treetable/gridlines", "New"));
        treeTableMenuItems.add(new MenuItem("Selection", "/secure/ui/data/treetable/selection"));
        treeTableMenuItems.add(new MenuItem("Events", "/secure/ui/data/treetable/events"));
        treeTableMenuItems.add(new MenuItem("ContextMenu", "/secure/ui/data/treetable/contextMenu"));
        treeTableMenuItems.add(new MenuItem("Scroll", "/secure/ui/data/treetable/scroll"));
        treeTableMenuItems.add(new MenuItem("Resize", "/secure/ui/data/treetable/resize"));
        treeTableMenuItems.add(new MenuItem("Sort", "/secure/ui/data/treetable/sort"));
        treeTableMenuItems.add(new MenuItem("Filter", "/secure/ui/data/treetable/filter"));
        treeTableMenuItems.add(new MenuItem("Columns", "/secure/ui/data/treetable/columns"));
        treeTableMenuItems.add(new MenuItem("Responsive", "/secure/ui/data/treetable/responsive"));
        treeTableMenuItems.add(new MenuItem("Edit", "/secure/ui/data/treetable/edit"));
        treeTableMenuItems.add(new MenuItem("Paginator", "/secure/ui/data/treetable/paginator"));
        treeTableMenuItems.add(new MenuItem("MultiViewState", "/secure/ui/data/treetable/multiViewState"));
        treeTableMenuItems.add(new MenuItem("DisplayPriority", "/secure/ui/data/treetable/displayPriority"));
        dataMenuItems.add(new MenuItem("TreeTable", treeTableMenuItems));
        menuCategories.add(new MenuCategory("Data", dataMenuItems));
        //DATA CATEGORY END

        //PANEL CATEGORY START
        List<MenuItem> panelMenuItems = new ArrayList<>();
        panelMenuItems.add(new MenuItem("Accordion", "/secure/ui/panel/accordionPanel"));
        panelMenuItems.add(new MenuItem("Card", "/secure/ui/panel/card", "New"));
        panelMenuItems.add(new MenuItem("Dashboard", "/secure/ui/panel/dashboard"));
        panelMenuItems.add(new MenuItem("Divider", "/secure/ui/panel/divider", "New"));
        panelMenuItems.add(new MenuItem("Fieldset", "/secure/ui/panel/fieldset"));
        panelMenuItems.add(new MenuItem("OutputPanel", "/secure/ui/panel/outputPanel"));
        panelMenuItems.add(new MenuItem("Panel", "/secure/ui/panel/panel"));
        panelMenuItems.add(new MenuItem("PanelGrid", "/secure/ui/panel/panelGrid"));
        panelMenuItems.add(new MenuItem("Splitter", "/secure/ui/panel/splitter", "New"));
        panelMenuItems.add(new MenuItem("ScrollPanel", "/secure/ui/panel/scrollPanel"));
        panelMenuItems.add(new MenuItem("TabView", "/secure/ui/panel/tabView"));
        panelMenuItems.add(new MenuItem("Toolbar", "/secure/ui/panel/toolbar"));
        panelMenuItems.add(new MenuItem("Wizard", "/secure/ui/panel/wizard"));
        menuCategories.add(new MenuCategory("Panel", panelMenuItems));
        //PANEL CATEGORY END

        //OVERLAY CATEGORY START
        List<MenuItem> overlayMenuItems = new ArrayList<>();
        overlayMenuItems.add(new MenuItem("ConfirmDialog", "/secure/ui/overlay/confirmDialog"));
        overlayMenuItems.add(new MenuItem("ConfirmPopup", "/secure/ui/overlay/confirmPopup", "New"));
        overlayMenuItems.add(new MenuItem("Dialog", "/secure/ui/overlay/dialog"));
        overlayMenuItems.add(new MenuItem("OverlayPanel", "/secure/ui/overlay/overlayPanel"));
        overlayMenuItems.add(new MenuItem("Sidebar", "/secure/ui/overlay/sidebar"));

        //Tooltip Nested MenuItem
        List<MenuItem> tooltipMenuItems = new ArrayList<>();
        tooltipMenuItems.add(new MenuItem("Options", "/secure/ui/overlay/tooltip/tooltipOptions"));
        tooltipMenuItems.add(new MenuItem("Global", "/secure/ui/overlay/tooltip/tooltipGlobal"));
        overlayMenuItems.add(new MenuItem("Tooltip", tooltipMenuItems));

        menuCategories.add(new MenuCategory("Overlay", overlayMenuItems));
        //OVERLAY CATEGORY END

        //MENU CATEGORY START
        List<MenuItem> menuMenuItems = new ArrayList<>();
        menuMenuItems.add(new MenuItem("Breadcrumb", "/secure/ui/menu/breadcrumb"));

        //ContextMenu Nested MenuItem
        List<MenuItem> contextMenuItems = new ArrayList<>();
        contextMenuItems.add(new MenuItem("Basic", "/secure/ui/menu/contextmenu/basic"));
        contextMenuItems.add(new MenuItem("Tiered", "/secure/ui/menu/contextmenu/tiered"));
        contextMenuItems.add(new MenuItem("Target", "/secure/ui/menu/contextmenu/target"));
        contextMenuItems.add(new MenuItem("DataTable", "/secure/ui/data/datatable/contextMenu"));
        contextMenuItems.add(new MenuItem("Tree", "/secure/ui/data/tree/contextMenu"));
        contextMenuItems.add(new MenuItem("TreeTable", "/secure/ui/data/treetable/contextMenu"));
        menuMenuItems.add(new MenuItem("ContextMenu", contextMenuItems));

        menuMenuItems.add(new MenuItem("Dock", "/secure/ui/menu/dock"));
        menuMenuItems.add(new MenuItem("MegaMenu", "/secure/ui/menu/megaMenu"));
        menuMenuItems.add(new MenuItem("Menu", "/secure/ui/menu/menu"));
        menuMenuItems.add(new MenuItem("Menubar", "/secure/ui/menu/menubar"));
        menuMenuItems.add(new MenuItem("MenuButton", "/secure/ui/menu/menuButton"));
        menuMenuItems.add(new MenuItem("PanelMenu", "/secure/ui/menu/panelMenu"));
        menuMenuItems.add(new MenuItem("SlideMenu", "/secure/ui/menu/slideMenu"));
        menuMenuItems.add(new MenuItem("Stack", "/secure/ui/menu/stack"));
        menuMenuItems.add(new MenuItem("Steps", "/secure/ui/menu/steps"));
        menuMenuItems.add(new MenuItem("TabMenu", "/secure/ui/menu/tabMenu"));
        menuMenuItems.add(new MenuItem("TieredMenu", "/secure/ui/menu/tieredMenu"));
        menuCategories.add(new MenuCategory("Menu", menuMenuItems));
        //MENU CATEGORY END

        //CHARTS CATEGORY START
        List<MenuItem> chartsMenuItems = new ArrayList<>();

        //Bar Nested MenuItem
        chartsMenuItems.add(new MenuItem("Bar", "/secure/ui/chartjs/bar/bar"));
        chartsMenuItems.add(new MenuItem("Bubble", "/secure/ui/chartjs/bubble"));
        chartsMenuItems.add(new MenuItem("Donut", "/secure/ui/chartjs/donut"));
        chartsMenuItems.add(new MenuItem("Line", "/secure/ui/chartjs/line"));
        chartsMenuItems.add(new MenuItem("Pie", "/secure/ui/chartjs/pie"));
        chartsMenuItems.add(new MenuItem("Scatter", "/secure/ui/chartjs/scatter"));
        chartsMenuItems.add(new MenuItem("PolarArea", "/secure/ui/chartjs/polararea"));
        chartsMenuItems.add(new MenuItem("Radar", "/secure/ui/chartjs/radar"));
        chartsMenuItems.add(new MenuItem("Mixed", "/secure/ui/chartjs/mixed"));
        chartsMenuItems.add(new MenuItem("Interactive", "/secure/ui/chartjs/interactive"));
        chartsMenuItems.add(new MenuItem("Export", "/secure/ui/chartjs/export"));
        menuCategories.add(new MenuCategory("Charts", chartsMenuItems));
        //CHARTS CATEGORY END

        //MESSAGES CATEGORY START
        List<MenuItem> messagesMenuItems = new ArrayList<>();
        messagesMenuItems.add(new MenuItem("Growl", "/secure/ui/message/growl"));
        messagesMenuItems.add(new MenuItem("Messages", "/secure/ui/message/messages"));
        messagesMenuItems.add(new MenuItem("StaticMessage", "/secure/ui/message/staticMessage"));
        menuCategories.add(new MenuCategory("Messages", messagesMenuItems));
        //MESSAGES CATEGORY END

        //MULTIMEDIA CATEGORY START
        List<MenuItem> multimediaMenuItems = new ArrayList<>();
        multimediaMenuItems.add(new MenuItem("Audio", "/secure/ui/multimedia/audio", "New"));
        multimediaMenuItems.add(new MenuItem("Barcode", "/secure/ui/multimedia/barcode"));
        multimediaMenuItems.add(new MenuItem("Compare", "/secure/ui/multimedia/compare"));

        //Cropper Nested MenuItem
        List<MenuItem> cropperMenuItems = new ArrayList<>();
        cropperMenuItems.add(new MenuItem("Basic", "/secure/ui/multimedia/cropper/cropper"));
        cropperMenuItems.add(new MenuItem("Dynamic", "/secure/ui/multimedia/cropper/dynamic"));
        cropperMenuItems.add(new MenuItem("FileUpload", "/secure/ui/multimedia/cropper/fileupload"));
        multimediaMenuItems.add(new MenuItem("Cropper", cropperMenuItems));

        multimediaMenuItems.add(new MenuItem("Graphic Image", "/secure/ui/multimedia/graphicImage"));
        //Galleria Nested MenuItem
        List<MenuItem> galleriaMenuItems = new ArrayList<>();
        galleriaMenuItems.add(new MenuItem("Basic", "/secure/ui/multimedia/galleria/basic"));
        galleriaMenuItems.add(new MenuItem("Programmatic", "/secure/ui/multimedia/galleria/programmatic"));
        galleriaMenuItems.add(new MenuItem("Indicator", "/secure/ui/multimedia/galleria/indicator"));
        galleriaMenuItems.add(new MenuItem("Thumbnail", "/secure/ui/multimedia/galleria/thumbnail"));
        galleriaMenuItems.add(new MenuItem("Navigator", "/secure/ui/multimedia/galleria/navigator"));
        galleriaMenuItems.add(new MenuItem("Responsive", "/secure/ui/multimedia/galleria/responsive"));
        galleriaMenuItems.add(new MenuItem("FullScreen", "/secure/ui/multimedia/galleria/fullscreen"));
        galleriaMenuItems.add(new MenuItem("AutoPlay", "/secure/ui/multimedia/galleria/autoplay"));
        galleriaMenuItems.add(new MenuItem("Caption", "/secure/ui/multimedia/galleria/caption"));
        multimediaMenuItems.add(new MenuItem("Galleria", galleriaMenuItems));

        multimediaMenuItems.add(new MenuItem("Media", "/secure/ui/multimedia/media"));

        //PhotoCam Nested MenuItem
        List<MenuItem> photoCamMenuItems = new ArrayList<>();
        photoCamMenuItems.add(new MenuItem("PhotoCam", "/secure/ui/multimedia/photocam/photoCam.xhtml"));
        photoCamMenuItems.add(new MenuItem("Device Selection", "/secure/ui/multimedia/photocam/deviceSelection.xhtml"));
        multimediaMenuItems.add(new MenuItem("PhotoCam", photoCamMenuItems));

        multimediaMenuItems.add(new MenuItem("Switch", "/secure/ui/multimedia/switch"));
        multimediaMenuItems.add(new MenuItem("Video", "/secure/ui/multimedia/video", "New"));
        menuCategories.add(new MenuCategory("Multimedia", multimediaMenuItems));
        //MULTIMEDIA CATEGORY END

        //FILE CATEGORY START
        List<MenuItem> fileMenuItems = new ArrayList<>();

        //Upload Nested MenuItem
        List<MenuItem> uploadMenuItems = new ArrayList<>();
        uploadMenuItems.add(new MenuItem("Basic", "/secure/ui/file/upload/basic"));
        uploadMenuItems.add(new MenuItem("Basic Auto", "/secure/ui/file/upload/basicAuto"));
        uploadMenuItems.add(new MenuItem("Single", "/secure/ui/file/upload/single"));
        uploadMenuItems.add(new MenuItem("Multiple", "/secure/ui/file/upload/multiple"));
        uploadMenuItems.add(new MenuItem("Auto", "/secure/ui/file/upload/auto"));
        uploadMenuItems.add(new MenuItem("DragDrop", "/secure/ui/file/upload/dnd"));
        uploadMenuItems.add(new MenuItem("Chunked", "/secure/ui/file/upload/chunked"));
        uploadMenuItems.add(new MenuItem("Tooltips", "/secure/ui/file/upload/tooltips"));
        fileMenuItems.add(new MenuItem("Upload", uploadMenuItems));

        fileMenuItems.add(new MenuItem("Download", "/secure/ui/file/download"));
        menuCategories.add(new MenuCategory("File", fileMenuItems));
        //FILE CATEGORY END

        //DRAGDROP CATEGORY START
        List<MenuItem> dragDropMenuItems = new ArrayList<>();
        dragDropMenuItems.add(new MenuItem("Draggable", "/secure/ui/dnd/draggable"));
        dragDropMenuItems.add(new MenuItem("DataView", "/secure/ui/dnd/dataView"));
        dragDropMenuItems.add(new MenuItem("DataTable", "/secure/ui/dnd/dataTable"));
        dragDropMenuItems.add(new MenuItem("Custom", "/secure/ui/dnd/custom"));
        menuCategories.add(new MenuCategory("DragDrop", dragDropMenuItems));
        //DRAGDROP CATEGORY END

        //CLIENT SIDE VALIDATION CATEGORY START
        List<MenuItem> clientSideValidationMenuItems = new ArrayList<>();
        clientSideValidationMenuItems.add(new MenuItem("Basic", "/secure/ui/csv/basic"));
        clientSideValidationMenuItems.add(new MenuItem("Bean", "/secure/ui/csv/bean"));
        clientSideValidationMenuItems.add(new MenuItem("Custom", "/secure/ui/csv/custom"));
        clientSideValidationMenuItems.add(new MenuItem("Event", "/secure/ui/csv/event"));
        menuCategories.add(new MenuCategory("Client Side Validation", clientSideValidationMenuItems));
        //CLIENT SIDE VALIDATION CATEGORY END

        //DIALOG FRAMEWORK CATEGORY START
        List<MenuItem> dialogFrameworkMenuItems = new ArrayList<>();
        dialogFrameworkMenuItems.add(new MenuItem("Basic", "/secure/ui/df/basic"));
        dialogFrameworkMenuItems.add(new MenuItem("Data", "/secure/ui/df/data"));
        dialogFrameworkMenuItems.add(new MenuItem("Message", "/secure/ui/df/message"));
        dialogFrameworkMenuItems.add(new MenuItem("Nested", "/secure/ui/df/nested"));
        menuCategories.add(new MenuCategory("Dialog Framework", dialogFrameworkMenuItems));
        //DIALOG FRAMEWORK CATEGORY END

        //MISC CATEGORY START
        List<MenuItem> miscMenuItems = new ArrayList<>();
        miscMenuItems.add(new MenuItem("Avatar", "/secure/ui/misc/avatar", "New"));
        miscMenuItems.add(new MenuItem("Badge", "/secure/ui/misc/badge", "New"));
        miscMenuItems.add(new MenuItem("Chip", "/secure/ui/misc/chip", "New"));
        miscMenuItems.add(new MenuItem("ScrollTop", "/secure/ui/misc/scrollTop", "New"));
        miscMenuItems.add(new MenuItem("Skeleton", "/secure/ui/misc/skeleton", "New"));
        miscMenuItems.add(new MenuItem("Tag", "/secure/ui/misc/tag", "New"));
        miscMenuItems.add(new MenuItem("AutoUpdate", "/secure/ui/misc/autoUpdate"));
        miscMenuItems.add(new MenuItem("OutputLabel", "/secure/ui/misc/outputLabel"));
        miscMenuItems.add(new MenuItem("BlockUI", "/secure/ui/misc/blockUI"));
        miscMenuItems.add(new MenuItem("Cache", "/secure/ui/misc/cache"));
        miscMenuItems.add(new MenuItem("Captcha", "/secure/ui/misc/captcha"));
        miscMenuItems.add(new MenuItem("Clock", "/secure/ui/misc/clock"));
        miscMenuItems.add(new MenuItem("Context", "/secure/ui/misc/context"));

        //DefaultCommand Nested MenuItem
        List<MenuItem> defaultCommandMenuItems = new ArrayList<>();
        defaultCommandMenuItems.add(new MenuItem("Basic", "/secure/ui/misc/defaultcommand/basic"));
        defaultCommandMenuItems.add(new MenuItem("Multiple", "/secure/ui/misc/defaultcommand/multiple"));
        miscMenuItems.add(new MenuItem("DefaultCommand", defaultCommandMenuItems));

        miscMenuItems.add(new MenuItem("Effect", "/secure/ui/misc/effect"));
        miscMenuItems.add(new MenuItem("ExceptionHandler", "/secure/ui/misc/exceptionHandler"));
        miscMenuItems.add(new MenuItem("IdleMonitor", "/secure/ui/misc/idleMonitor"));
        miscMenuItems.add(new MenuItem("ImportConstants", "/secure/ui/misc/importConstants"));
        miscMenuItems.add(new MenuItem("ImportEnum", "/secure/ui/misc/importEnum"));
        miscMenuItems.add(new MenuItem("Log", "/secure/ui/misc/log"));
        miscMenuItems.add(new MenuItem("Focus", "/secure/ui/misc/focus"));
        miscMenuItems.add(new MenuItem("Hotkey", "/secure/ui/misc/hotkey"));
        miscMenuItems.add(new MenuItem("Printer", "/secure/ui/misc/printer"));
        miscMenuItems.add(new MenuItem("ProgressBar", "/secure/ui/misc/progressBar"));
        miscMenuItems.add(new MenuItem("ResetInput", "/secure/ui/misc/resetInput"));
        miscMenuItems.add(new MenuItem("Resizable", "/secure/ui/misc/resizable"));
        miscMenuItems.add(new MenuItem("Spotlight", "/secure/ui/misc/spotlight"));
        miscMenuItems.add(new MenuItem("Sticky", "/secure/ui/misc/sticky"));

        //Terminal Nested MenuItem
        List<MenuItem> terminalCommandMenuItems = new ArrayList<>();
        terminalCommandMenuItems.add(new MenuItem("Basic", "/secure/ui/misc/terminal/basic"));
        terminalCommandMenuItems.add(new MenuItem("Autocomplete", "/secure/ui/misc/terminal/autocomplete"));
        miscMenuItems.add(new MenuItem("Terminal", terminalCommandMenuItems));
        menuCategories.add(new MenuCategory("Misc", miscMenuItems));
        //MISC CATEGORY END

        for (MenuCategory category : menuCategories) {
            for (MenuItem menuItem : category.getMenuItems()) {
                menuItem.setParentLabel(category.getLabel());
                if (menuItem.getUrl() != null) {
                    menuItems.add(menuItem);
                }
                if (menuItem.getMenuItems() != null) {
                    for (MenuItem item : menuItem.getMenuItems()) {
                        item.setParentLabel(menuItem.getLabel());
                        if (item.getUrl() != null) {
                            menuItems.add(item);
                        }
                    }
                }
            }
        }
    }

    public List<MenuItem> completeMenuItem(String query) {
        String queryLowerCase = query.toLowerCase();
        List<MenuItem> filteredItems = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getUrl() != null && (item.getLabel().toLowerCase().contains(queryLowerCase) ||
                        item.getParentLabel().toLowerCase().contains(queryLowerCase))) {
                filteredItems.add(item);
            }
            if (item.getBadge() != null) {
                if (item.getBadge().toLowerCase().contains(queryLowerCase)) {
                    filteredItems.add(item);
                }
            }
        }
        filteredItems.sort(Comparator.comparing(MenuItem::getParentLabel));
        return filteredItems;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public List<MenuCategory> getMenuCategories() {
        return menuCategories;
    }
}