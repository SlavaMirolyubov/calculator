package src;

public class Main {

    public static void main(String[] args) {

        new Calculator().calculate("0+0-5*(0+10+81)");

        // 2.3-8/(35,9+2*7/(-2))-9*2
        // 5 * (10 + 81 / (5 + 4) * 9) + (8 / (9 - 5) - 11.5)
        // 2 + 9 *(17,3 - 8*3.7)-19*(18-7)
        // 0+0-5*(0+10+81)
        // скобки ошибка 2.3-8/((35,9+2*7/(-2))-9*2

    }
}
