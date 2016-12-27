package com.grability.elio.grabilitytest.main;

import android.content.Context;

import com.grability.elio.grabilitytest.domain.NetworkUtils;

public class MainInteractorImpl implements MainInteractor {

    private MainRepository localRepository;
    private MainRepository networkRepository;
    private Context context;

    public MainInteractorImpl(
            Context context, MainRepository localRepository, MainRepository networkRepository) {
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
}