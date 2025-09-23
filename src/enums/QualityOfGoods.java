package enums;

import models.Goods;

public enum QualityOfGoods {
    NORMAL(1.2) {
        @Override
        public void decreaseQuality(Goods good) {
            good.setQuality(SLIGHTLY_DAMAGED);
        }
    },
    SLIGHTLY_DAMAGED(0.95) {
        @Override
        public void decreaseQuality(Goods good) {
            good.setQuality(HALF_DAMAGED);
        }
    },
    HALF_DAMAGED(0.55) {
        @Override
        public void decreaseQuality(Goods good) {
            good.setQuality(ALMOST_FULL_DAMAGED);
        }
    },
    ALMOST_FULL_DAMAGED(0.25) {
        @Override
        public void decreaseQuality(Goods good) {
            good.setQuality(TOTALLY_DAMAGED);
        }
    },
    TOTALLY_DAMAGED(0.1) {
        @Override
        public void decreaseQuality(Goods good) {
            System.out.println("Это худшее качество, ниже некуда.");
            good.setQuality(TOTALLY_DAMAGED);
        }
    };

    private double rate;

    QualityOfGoods(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public abstract void decreaseQuality(Goods good);


}
