package christmas.view;

import christmas.domain.MenuBoard;
import christmas.exception.ChristmasException;
import christmas.exception.ErrorMessage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InputParser {

    public static final String COMMA_DELIMITER = ",";
    public static final String HYPHEN_DELIMITER = "-";
    public static final String NUMERIC_REGEX = "\\d+";
    public static final int ONE = 1;
    public static final int VISIT_START_DAY = 1;
    public static final int VISIT_END_DAY = 31;
    public static final int SEPARATE_COUNT = 2;
    public static final int TOTAL_DISH_LIMIT = 20;

    public static Integer parseVisitDate(String input) {
        validateVisitDate(input);
        return Integer.parseInt(input);
    }

    public static Map<String, Integer> parseMenus(String input) {
        Map<String, Integer> countByMenu = new HashMap<>();

        String[] countMenuPairs = input.split(COMMA_DELIMITER);
        int totalCount = 0;
        for (String countMenuPair : countMenuPairs) {
            String[] countMenu = countMenuPair.split(HYPHEN_DELIMITER);
            validateMenuPairFormat(countMenu);

            String menu = countMenu[0];
            String count = countMenu[1];

            validateNotExistMenu(menu);
            validateDuplicateMenu(countByMenu, menu);
            countByMenu.put(validateMenu(menu), validateCount(count));
            totalCount += Integer.parseInt(count);
            validateTotalOrder(totalCount);
        }

        return countByMenu;
    }

    private static void validateVisitDate(String input) {
        validateEmpty(input);
        validateNumeric(input);
        validateRange(input);
    }

    private static void validateTotalOrder(int count) {
        if (count > TOTAL_DISH_LIMIT) {
            throw ChristmasException.from(ErrorMessage.EXCEED_DISH_COUNT);
        }
    }

    private static void validateNotExistMenu(String input) {
        if (Arrays.stream(MenuBoard.values())
                .noneMatch(menuBoard -> menuBoard.getName().equals(input))) {
            throw ChristmasException.from(ErrorMessage.NOT_EXIST_MENU);
        }
    }

    private static void validateDuplicateMenu(Map<String, Integer> countByMenu, String input) {
        if (countByMenu.containsKey(input)) {
            throw ChristmasException.from(ErrorMessage.DUPLICATED_MENU);
        }
    }

    private static String validateMenu(String input) {
        validateEmpty(input);
        return input;
    }

    private static Integer validateCount(String input) {
        validateEmpty(input);
        validateCountNumeric(input);
        validatePositive(input);
        return Integer.parseInt(input);
    }

    private static void validateCountNumeric(String input) {
        if (!input.matches(NUMERIC_REGEX)) {
            throw ChristmasException.from(ErrorMessage.INVALID_MENU_COUNT);
        }
    }

    private static void validateMenuPairFormat(String[] countMenu) {
        if (countMenu.length != SEPARATE_COUNT) {
            throw ChristmasException.from(ErrorMessage.INVALID_MENU_FORMAT);
        }
    }

    private static void validateEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw ChristmasException.from(ErrorMessage.EMPTY_INPUT);
        }
    }

    private static void validateNumeric(String input) {
        if (!input.matches(NUMERIC_REGEX)) {
            throw ChristmasException.from(ErrorMessage.DAY_NOT_NUMERIC);
        }
    }

    private static void validatePositive(String input) {
        if (Integer.parseInt(input) < ONE) {
            throw ChristmasException.from(ErrorMessage.INVALID_MENU_COUNT);
        }
    }

    private static void validateRange(String input) {
        int day = Integer.parseInt(input);
        if (VISIT_START_DAY > day || day > VISIT_END_DAY) {
            throw ChristmasException.from(ErrorMessage.DAY_OUT_OF_RANGE);
        }
    }
}
