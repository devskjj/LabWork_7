package models;

import helper.Helper;

import java.util.*;

public class RoadsideTavern {
    public static void runRoadsideTavernEvent(Trader trader) {
        Scanner scanner = new Scanner(System.in);

        Helper.print("Остановиться в трактире? (Y/N): ");
        String answer = scanner.nextLine().trim().toLowerCase();
        while (!answer.equals("y") && !answer.equals("n")) {
            Helper.print("Некорректный ввод. Введите Y/N: ");
            answer = scanner.nextLine().trim().toLowerCase();
        }

        if (answer.equalsIgnoreCase("N")) {
            Helper.print("Вы решили не останавливаться и продолжаете путь.");
            trader.setSpeedDay(3);
            return;
        }
        if (answer.equalsIgnoreCase("Y")) {
            Helper.print("Вы останавливаетесь в трактире.");
            trader.setSpeedDay(0);
        }

        Runnable payCostOfInn = new Runnable() {
            public void run() {
                double costs = trader.getMany() * 0.2;
                trader.setMany(trader.getMany() - costs);
                Helper.print("Траты на еду и ночлег: " + costs + ". Осталось денег: " + trader.getMany());
            }
        };

        Map<String, Runnable> actions = new HashMap<>();

        actions.put("1", new Runnable() {
            @Override
            public void run() {
                if (trader.getPurchasedGoods().isEmpty()) {
                    Helper.print("У вас нет товаров для продажи.");
                } else {
                    int i = Helper.getRandom(trader.getPurchasedGoods().size() - 1);
                    Goods soldGood = trader.getPurchasedGoods().remove(i);
                    double price = soldGood.getFinalPrice();
                    Helper.print("Продан товар: " + soldGood + " за " + price);
                    trader.setMany(trader.getMany() + price);
                    trader.setMaxLoad(trader.getMaxLoad() + soldGood.getWeight());
                }
            }
        });

        actions.put("2", new Runnable() {
            @Override
            public void run() {
                List<Goods> newGoods = Helper.getRandomGoods(7);
                Helper.print("Доступные товары из таверны...");
                Helper.printAllGoods(newGoods);
                int countGoods = trader.getPurchasedGoods().size();
                Helper.print("Попытка купить товар");

                for (int i = 0; i < newGoods.size(); i++) {
                    if (trader.isEnoughToBuy(newGoods.get(i))) {
                        trader.buy(newGoods.get(i));
                        Helper.print("Товар успешно куплен!");
                        Helper.print(newGoods.get(i).toString());
                    }

                }if(countGoods==trader.getPurchasedGoods().size()){
                    Helper.print("Не удалось купить товар: недостаточно денег или места.");
                    Helper.print("Денег осталось: " + trader.getMany());
                    Helper.print("Место в телеге осталось: " + trader.getMaxLoad());
                }

                Helper.print();
                Helper.print("Товары в телеге: ");
                Helper.printAllGoods(trader.getPurchasedGoods());
            }
        });

        while (true) {
            Helper.print("Товары в телеге продавца: ");
            Helper.printAllGoods(trader.getPurchasedGoods());
            Helper.print("Денег осталось: " + trader.getMany());
            Helper.print("Место в телеге осталось: " + trader.getMaxLoad());
            Helper.print("Купить/Продать товар можно 1 раз случайным образом.");
            Helper.print("----------------------------");
            Helper.print("Хотите продать/купить товар?");
            Helper.print("1 - Продать");
            Helper.print("2 - Купить");
            Helper.print("0 - Нет (оплатить ночлег и завершить день)");
            Helper.print("----------------------------");

            String choice = scanner.nextLine().trim();
            if (choice.equals("0")) {
                break;
            }

            Runnable action = actions.get(choice);
            if (action != null) {
                action.run();
                break;
            } else {
                Helper.print("Неверный ввод. Попробуйте снова.");
            }
        }
        payCostOfInn.run();
    }
}
