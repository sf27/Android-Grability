package com.grability.elio.grabilitytest.entities;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by elio on 12/26/16.
 */

public class App extends RealmObject {
    public RealmList<AppImage> images;
    public String title;
    public String description;
    public Category category;

    public RealmList<AppImage> getImages() {
        return images;
    }

    public void setImages(RealmList<AppImage> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
