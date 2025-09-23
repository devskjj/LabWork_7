package enums;

public enum QualityOfGoods {
    NORMAL(1.2),
    SLIGHTLY_DAMAGED(0.95),
    HALF_DAMAGED(0.55),
    ALMOST_FULL_DAMAGED(0.25),
    TOTALLY_DAMAGED(0.1);

    private double rate;

    QualityOfGoods(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
