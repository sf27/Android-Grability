package com.grability.elio.grabilitytest.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by elio on 12/26/16.
 */

public class AppImage extends RealmObject {
    public String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
