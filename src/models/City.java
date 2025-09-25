package models;

import enums.Types;
import helper.Helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class City {
    private String name;
    private int distance;
    private Set<Types> specialGoods;

    public City() {
        this.name = getCities().get(Helper.getRandom(0,getCities().size()-1));
        this.distance = Helper.getRandom(50,100);
        this.specialGoods = getSpecialGoods();
    }

    public Set<Types> getSpecialGoods() {
        Types[] types = Types.values();
        if (specialGoods == null) {
            specialGoods = new HashSet<>();
            int counter = Helper.getRandom(1,3);
            while (specialGoods.size() < counter) {
                specialGoods.add(types[Helper.getRandom(types.length - 1)]);
            }
        }
        return specialGoods;
    }

    public List<Goods> getMatchingSpecialGoods(List<Goods> trader) { // для сравнения по приезду продавца
        List<Goods> matchingGoods = new ArrayList<>();
        for (Goods good : trader) {
            if (getSpecialGoods().contains(good.getType())) {
                matchingGoods.add(good);
            }
        }
        return matchingGoods;
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
