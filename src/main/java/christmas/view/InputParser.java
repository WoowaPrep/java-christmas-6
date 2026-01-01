package christmas.view;

import christmas.exception.ChristmasException;
import christmas.exception.ErrorMessage;

public class InputParser {

    public static Integer parseVisitDate(String input) {
        validateVisitDate(input);
        return Integer.parseInt(input);
    }

    private static void validateVisitDate(String input) {
        validateEmpty(input);
        validateNumeric(input);
        validateRange(input);
    }

    private static void validateEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw ChristmasException.from(ErrorMessage.EMPTY_INPUT);
        }
    }

    private static void validateNumeric(String input) {
        if (!input.matches("\\d+")) {
            throw ChristmasException.from(ErrorMessage.DAY_NOT_NUMERIC);
        }
    }

    private static void validateRange(String input) {
        int day = Integer.parseInt(input);
        if (1 > day || day > 31) {
            throw ChristmasException.from(ErrorMessage.DAY_OUT_OF_RANGE);
        }
    }
}
