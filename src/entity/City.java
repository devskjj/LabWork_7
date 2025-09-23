package entity;

import helper.Helper;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private int distance;

    public City() {
        this.name = getCities().get(Helper.getRandom(0,getCities().size()-1));
        this.distance = Helper.getRandom(50,100);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    private List<String> getCities(){
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
