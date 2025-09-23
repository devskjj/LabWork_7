package enums;

public enum TypeOfGoods {
    MEAT("Мясо"),
    DRYFRUITS("Сухофрукты"),
    GRAIN("Зерно"),
    FLOUR("Мука"),
    FABRICS("Ткани"),
    PAINT("Краска");

    private String value;

    TypeOfGoods(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
