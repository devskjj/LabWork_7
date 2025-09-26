package models;

import enums.Types;
import helper.Helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TavernRumors {
    public static void showInfo(Trader trader, int distanceRemaining, int distanceTraveled) {
        List<Types> traderTypes = getTypesFromListGoodsOfTrader(trader);
        if (traderTypes.isEmpty()) {
            Helper.print("Вы не имеете товаров!");
            return;
        }

        City newCity;
        do {
            newCity = new City();
        } while (newCity.getName().equals(trader.getNewDestinationCity().getName())
                || newCity.getName().equals(trader.getCurrentCity().getName()));

        List<Types> specialGoodsList = new ArrayList<>(newCity.getSpecialGoods());
        Collections.shuffle(specialGoodsList);
        List<String> goodsName = new ArrayList<>();

        int count = Math.min(specialGoodsList.size(), Helper.getRandom(1, 3));
        for (int i = 0; i < count; i++) {
            goodsName.add(specialGoodsList.get(i).getValue());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < goodsName.size(); i++) {
            sb.append(goodsName.get(i));
            if (i < goodsName.size() - 1) {
                sb.append(", ");
            }
        }
        String goodsStr = sb.toString();

        Helper.print("В таверне дошел слух, что в городе " + newCity.getName() +
                " покупают по высоким ценам товар: " + goodsStr);
        calculateAndPrintRiskInfo(trader, distanceRemaining, newCity, distanceTraveled);

        Scanner scanner = new Scanner(System.in);
        String answer;

        while (true) {
            Helper.print("Хотите ли вы изменить маршрут на " + newCity.getName() + "? (Y/N): ");
            answer = scanner.nextLine();

            if (answer == null) {
                Helper.print("Ошибка ввода. Пожалуйста, повторите попытку.");
                continue;
            }

            answer = answer.trim().toLowerCase();
            if (answer.equals("y")) {
                Helper.print("Маршрут изменен на город " + newCity.getName());
                trader.setNewDestinationCity(newCity);
                trader.setChangeCity(true);
                break;
            } else if (answer.equals("n")) {
                Helper.print("Вы решаете продолжить движение к первоначальному городу.");
                break;
            } else {
                Helper.print("Пожалуйста, введите 'Y' или 'N'.");
            }
        }
    }

    private static void calculateAndPrintRiskInfo(Trader trader, int distanceRemaining, City newCity, int distanceTraveled) {
        int baseSpeed = trader.getSpeedDay();
        double chanceBandits = 0.15;
        int daysNow = distanceRemaining / baseSpeed;
        int daysNew = newCity.getDistance() / baseSpeed;

        double currentGoodLoss = trader.riskBanditsLoss(daysNow, chanceBandits);
        double newGoodLoss = trader.riskBanditsLoss(daysNew, chanceBandits);

        double profitNow = trader.newProfit(trader.getNewDestinationCity());
        double profitNew = trader.newProfit(newCity);

        double goodsPriceLoss = trader.riskGoodsPriceLoss(currentGoodLoss);
        double goodsPriceLossNew = trader.riskGoodsPriceLoss(newGoodLoss);

        double totalCurrent = profitNow - goodsPriceLoss;
        double totalNew = profitNew - goodsPriceLossNew;

        Helper.print("Текущий путь в город: " + trader.getNewDestinationCity().getName() + ", расстояние: " + distanceRemaining);
        Helper.print("Потенциальная прибыль: " + String.format("%.2f", profitNow));
        Helper.print("Возможные потери товара: " + String.format("%.2f", currentGoodLoss));
        Helper.print("Возможные убытки: " + String.format("%.2f", goodsPriceLoss));
        Helper.print("Ожидаемая прибыль в итоге: " + String.format("%.2f", totalCurrent));

        Helper.print("-----------------------");

        Helper.print("Новый город: " + newCity.getName() + ", расстояние: " + ((distanceTraveled / 4) + (newCity.getDistance() * 2 / 3)));
        Helper.print("Потенциальная прибыль: " + String.format("%.2f", profitNew));
        Helper.print("Возможные потери товара: " + String.format("%.2f", newGoodLoss));
        Helper.print("Возможные убытки: " + String.format("%.2f", goodsPriceLossNew));
        Helper.print("Ожидаемая прибыль в итоге: " + String.format("%.2f", totalNew));
    }

    private static List<Types> getTypesFromListGoodsOfTrader(Trader trader) {
        List<Types> uniqueTypes = new ArrayList<>();
        for (Goods good : trader.getPurchasedGoods()) {
            Types type = good.getType();
            if (!uniqueTypes.contains(type)) {
                uniqueTypes.add(type);
            }
        }
        return uniqueTypes;
    }
}
