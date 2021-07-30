package model;

import ca.CarAd;

import java.util.List;

public interface Strategy {
    List<CarAd> getAds(String searchString);
}
