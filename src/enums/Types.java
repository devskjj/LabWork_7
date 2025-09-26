
package enums;

public enum Types {
    MEAT("Мясо"),
    DRYFRUITS("Сухофрукты"),
    GRAIN("Зерно"),
    FLOUR("Мука"),
    FABRICS("Ткани"),
    PAINT("Краска");

    private final String value;

    Types(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
