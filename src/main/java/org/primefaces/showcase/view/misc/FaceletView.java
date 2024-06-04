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
package org.primefaces.showcase.view.misc;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.View;
import jakarta.faces.application.StateManager;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIOutput;
import jakarta.faces.component.html.HtmlBody;
import jakarta.faces.component.html.HtmlCommandButton;
import jakarta.faces.component.html.HtmlDoctype;
import jakarta.faces.component.html.HtmlForm;
import jakarta.faces.component.html.HtmlOutputText;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.facelets.Facelet;

@View("/facelet.xhtml")
@ApplicationScoped
public class FaceletView extends Facelet {

    @Override
    public void apply(FacesContext facesContext, UIComponent parent) {
        if (!facesContext.getAttributes().containsKey(StateManager.IS_BUILDING_INITIAL_STATE)) {
            return;
        }

        var components = new ComponentBuilder(facesContext);
        var rootChildren = parent.getChildren();

        var htmlDoctype = new HtmlDoctype();
        htmlDoctype.setRootElement("html");
        rootChildren.add(htmlDoctype);

        var htmlTag = new UIOutput();
        htmlTag.setValue("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        htmlTag.getAttributes().put("escape", false);
        rootChildren.add(htmlTag);

        HtmlBody body = components.create(HtmlBody.COMPONENT_TYPE);
        rootChildren.add(body);

        HtmlForm form = components.create(HtmlForm.COMPONENT_TYPE);
        form.setId("form");
        body.getChildren().add(form);

        HtmlOutputText message = components.create(HtmlOutputText.COMPONENT_TYPE);
        message.setId("message");

        HtmlCommandButton actionButton = components.create(HtmlCommandButton.COMPONENT_TYPE);
        actionButton.setId("button");
        actionButton.addActionListener(
                e -> message.setValue("Hello, World! Welcome to Faces 4.0 on Jakarta EE 10"));
        actionButton.setValue("Greet");

        form.getChildren().add(actionButton);

        parent.getChildren().add(message);

        htmlTag = new UIOutput();
        htmlTag.setValue("</html>");
        htmlTag.getAttributes().put("escape", false);
        rootChildren.add(htmlTag);
    }

    private static class ComponentBuilder {
        FacesContext facesContext;

        ComponentBuilder(FacesContext facesContext) {
            this.facesContext = facesContext;
        }

        @SuppressWarnings("unchecked")
        <T> T create(String componentType) {
            try {
                return (T) facesContext.getApplication().createComponent(componentType);
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("Component type " + componentType + " is not valid.", e);
            }
        }
    }
}