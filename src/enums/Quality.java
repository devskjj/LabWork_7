package enums;

import models.Goods;

public enum Quality {
    NORMAL(1.2, "Нормальное") {
        @Override
        public void decreaseQuality(Goods good) {
            good.setQuality(SLIGHTLY_DAMAGED);
        }
    },
    SLIGHTLY_DAMAGED(0.95, "Слегка испорчен ") {
        @Override
        public void decreaseQuality(Goods good) {
            good.setQuality(HALF_DAMAGED);
        }
    },
    HALF_DAMAGED(0.55, "Половина испортилась") {
        @Override
        public void decreaseQuality(Goods good) {
            good.setQuality(ALMOST_FULL_DAMAGED);
        }
    },
    ALMOST_FULL_DAMAGED(0.25, "Почти весь пропал") {
        @Override
        public void decreaseQuality(Goods good) {
            good.setQuality(TOTALLY_DAMAGED);
        }
    },
    TOTALLY_DAMAGED(0.1, "Испорчен в хлам") {
        @Override
        public void decreaseQuality(Goods good) {
            System.out.println("Это худшее качество, ниже некуда.");
        }
    };

    private final double rate;
    private final String value;

    Quality(double rate, String value) {
        this.rate = rate;
        this.value = value;
    }

    public double getRate() {
        return rate;
    }

    public String getValue() {
        return value;
    }

    public abstract void decreaseQuality(Goods good);


}
