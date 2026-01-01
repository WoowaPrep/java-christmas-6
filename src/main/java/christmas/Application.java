package christmas;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        try {
            new ChristmasPromotion().open();
        } finally {
            Console.close();
        }
    }
}
