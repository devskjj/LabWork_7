import entity.*;
import enums.GoodsType;
import helper.Helper;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void run(){
        City city = new City();//сразу будет создаваться рандомный город с рандомным растоянием
        System.out.println(city.getName());
        System.out.println(city.getDistance());
        Event event = new Event();
        System.out.println(event.getEvent().get(Helper.getRandom(0, 8)));//Будет вытаскивать рандомные события

        Trader trader = new Trader(200, 3, 300);
        Goods goods = new Goods(10, GoodsType.COLOR,new Quality("Нормальное",1),20);
        System.out.println(goods);

        List<Goods> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Goods(50, GoodsType.COLOR,new Quality("Нормальное",1),40));
        }

        buyGoods(list, trader); // происходит покупка и составление списка нового

        System.out.println(trader.getMany());
        System.out.println(trader.getMaxLoad());

        System.out.println("Список купленных товаров");
        for (Goods good : trader.getPurchasedGoods()) {
            System.out.println(good);
        }



    }

    private static void buyGoods (List<Goods> goods, Trader trader) { // заполнить телегу на основе листа с товарами и возможностями торговца
        for (Goods good : goods) {
           if (trader.isEnoughToBuy(good)) {
               trader.buy(good);
               System.out.println("Куплен товар: " + good);
           } else {
               System.out.println("Недостаточно денег или места в телеге.");
           }
        }
    }


}
