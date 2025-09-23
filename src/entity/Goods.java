package entity;

import enums.GoodsType;

public class Goods {
    private int weight;
    private GoodsType goodsType;
    private Quality qualityGoods; // качество товара
    private int priceOnShop;//стоимость в пункте покупки

    public Goods(int weight, GoodsType goodsType, Quality qualityGoods, int priceOnShop) {
        this.weight = weight;
        this.goodsType = goodsType;
        this.qualityGoods = qualityGoods;
        this.priceOnShop = priceOnShop;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public Quality getQualityGoods() {
        return qualityGoods;
    }

    public void setQualityGoods(Quality qualityGoods) {
        this.qualityGoods = qualityGoods;
    }

    public int getPriceOnShop() {
        return priceOnShop;
    }

    public void setPriceOnShop(int priceOnShop) {
        this.priceOnShop = priceOnShop;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "weight=" + weight +
                ", goodsType=" + goodsType +
                ", qualityGoods=" + qualityGoods +
                ", priceOnShop=" + priceOnShop +
                '}';
    }
}
