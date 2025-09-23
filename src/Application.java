import entity.City;
import entity.Event;
import entity.Goods;
import entity.Quality;
import enums.GoodsType;
import helper.Helper;

public class Application {
    public static void run(){
        City city = new City();//сразу будет создаваться рандомный город с рандомным растоянием
        System.out.println(city.getName());
        System.out.println(city.getDistance());
        Event event = new Event();
        System.out.println(event.getEvent().get(Helper.getRandom(0, 8)));//Будет вытаскивать рандомные события

        Goods goods = new Goods(10, GoodsType.COLOR,new Quality("Нормальное",1),20);
        System.out.println(goods);
    }
}
