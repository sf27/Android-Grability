package com.grability.elio.grabilitytest.main.events;

import com.grability.elio.grabilitytest.api.ApiClient;

import java.util.List;

public class MainEvent {
    public final static int onLoadAppsSuccess = 1;
    public final static int onLoadAppsError = 2;

    private int type;
    private String error;
    private List<ApiClient.Entry> data;

    public MainEvent() {
        this.error = null;
        this.type = 0;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<ApiClient.Entry> getData() {
        return data;
    }

    public void setData(List<ApiClient.Entry> entries) {
        this.data = entries;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}