package com.grability.elio.grabilitytest.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by elio on 12/26/16.
 */

public class Category extends RealmObject {
    @PrimaryKey
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
