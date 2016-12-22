package com.grability.elio.grabilitytest.main;

public class MainInteractorImpl implements MainInteractor {

    private MainRepository mainRepository;

    public MainInteractorImpl(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public void loadApps() {
        mainRepository.loadApps();
    }
}