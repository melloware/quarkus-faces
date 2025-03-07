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
package org.primefaces.showcase.view.multimedia;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.showcase.domain.Photo;
import org.primefaces.showcase.service.PhotoService;

@Named
@RequestScoped
@RegisterForReflection(serialization = true)
public class DynamicGalleriaView implements Serializable {

    @Inject
    PhotoService service;
    private List<Photo> photos;

    public StreamedContent getPhotoAsStreamedContent() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String photoId = facesContext.getExternalContext().getRequestParameterMap().get("photoId");
        if (photoId == null) {
            photoId = getPhotos().stream().findFirst().get().getId();
        }
        String finalPhotoId = photoId;
        Photo photo = getPhotos().stream()
                .filter(p -> p.getId().equals(finalPhotoId))
                .findFirst().get();

        return DefaultStreamedContent.builder()
                .name(photo.getTitle() + ".jpg")
                .contentType("image/jpg")
                .stream(() -> facesContext.getExternalContext().getResourceAsStream("/resources/" + photo.getItemImageSrc()))
                .build();
    }

    public List<Photo> getPhotos() {
        if (photos == null) {
            photos = service.getPhotos();
        }
        return photos;
    }

    public void setService(PhotoService service) {
        this.service = service;
    }
}