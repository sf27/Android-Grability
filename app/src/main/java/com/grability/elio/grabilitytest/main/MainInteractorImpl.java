package com.grability.elio.grabilitytest.main;

import android.content.Context;

import com.grability.elio.grabilitytest.domain.NetworkUtils;

public class MainInteractorImpl implements MainInteractor {

    private LocalRepository localRepository;
    private MainRepository networkRepository;
    private Context context;

    public MainInteractorImpl(
            Context context, LocalRepository localRepository, MainRepository networkRepository) {
        this.localRepository = localRepository;
        this.networkRepository = networkRepository;
        this.context = context;
    }

    @Override
    public void loadApps() {
        if (NetworkUtils.haveNetworkConnection(context)) {
            networkRepository.loadApps();
        } else {
            localRepository.loadApps();
        }
    }

    @Override
    public void getAppById(String id) {
        localRepository.getAppById(id);
    }
}