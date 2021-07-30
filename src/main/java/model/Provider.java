package model;

import ca.CarAd;

import java.util.List;

public class Provider {
    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<CarAd> getAds(String searchString) {
        return strategy.getAds(searchString);
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
