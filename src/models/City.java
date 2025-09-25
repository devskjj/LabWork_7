package models;

import helper.Helper;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private int distance;

    public City() {
        this.name = getCities().get(Helper.getRandom(0, getCities().size() - 1));
        this.distance = Helper.getRandom(15, 40);
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    private List<String> getCities() {
        List<String> city = new ArrayList<>();
        city.add("Bishkek");
        city.add("Moscow");
        city.add("London");
        city.add("Karakol");
        city.add("Tokyo");
        city.add("Almaty");
        return city;
    }
}
