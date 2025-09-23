package entity;

import helper.Helper;

public class Trader {
   private int maxLoad;
   private int speedDay;
   private int many;

    public Trader(int maxLoad, int speedDay, int many) {
        this.maxLoad = maxLoad;
        this.speedDay = 3;
        this.many = Helper.getRandom(100,200);
    }

    public int getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(int maxLoad) {
        this.maxLoad = maxLoad;
    }

    public int getSpeedDay() {
        return speedDay;
    }

    public void setSpeedDay(int speedDay) {
        this.speedDay = speedDay;
    }

    public int getMany() {
        return many;
    }

    public void setMany(int many) {
        this.many = many;
    }
}
