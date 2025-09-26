import helper.Helper;

public class Main {
    public static void main(String[] args) {
        try {
            Application.runApplication();
        } catch (Exception e) {
            Helper.print(e.getMessage());
        }
    }
}