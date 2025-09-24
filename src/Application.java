import models.Goods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {
    public static void runApplication() {
        List<Goods> randomGoods = getRandomGoods(7);
        printGoods(randomGoods);
    }

    private static List<Goods> getRandomGoods(int count) {
        List<Goods> goods = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            goods.add(new Goods());
        }
        Collections.shuffle(goods);
        return goods;
    }

    private static void printGoods(List<Goods> list) {
        for (Goods goods : list) {
            System.out.println(goods);
        }
    }
}
