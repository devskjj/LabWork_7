package enums;

import java.util.ArrayList;
import java.util.List;

public enum GoodsType {
    MEET("Мясо"),
    DRIED_FRUITS("Сухофрукты"),
    CORN("Зерно"),
    FLOUR("Мука"),
    FABRICS("Ткани"),
    COLOR("Краска");

    private String goodsType;

    GoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsType() {
        return goodsType;
    }

    @Override
    public String toString() {
        return goodsType;
    }
}

