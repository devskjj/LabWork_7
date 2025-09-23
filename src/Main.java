import enums.QualityOfGoods;
import models.Goods;

public class Main {
    public static void main(String[] args) {
        Goods good = new Goods();
        System.out.println(good);
        System.out.println(good.getFinalPrice());

        good.setQuality(QualityOfGoods.HALF_DAMAGED);
        System.out.println(good);
        System.out.println(good.getFinalPrice());

        good.decreaseQuality();
        System.out.println(good);
        System.out.println(good.getFinalPrice());

        good.decreaseQuality();
        System.out.println(good);
        System.out.println(good.getFinalPrice());

        good.decreaseQuality();
        System.out.println(good);
        System.out.println(good.getFinalPrice());

        good.setQuality(QualityOfGoods.NORMAL);
        System.out.println(good);
        System.out.println(good.getFinalPrice());

    }
}