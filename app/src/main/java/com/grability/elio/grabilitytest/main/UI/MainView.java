package com.grability.elio.grabilitytest.main.UI;

import com.grability.elio.grabilitytest.api.ApiClient;

import java.util.List;

public interface MainView {

    void downloadError(String error);

    void showData(List<ApiClient.Entry> entries);
}
