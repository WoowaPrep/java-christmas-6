package christmas.domain.event;

import christmas.domain.menu.DishType;
import christmas.domain.menu.MenuBoard;
import christmas.domain.menu.Menus;
import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;

public class Discount {

    private static final int EVENT_THRESH_HOLD = 10_000;

    private static final int BASIC_DISCOUNT = 1_000;
    private static final int SPECIAL_DISCOUNT = 1_000;
    private static final int DAILY_INCREMENT = 100;

    private static final int DISCOUNT_PER_DISSERT = 2_023;
    private static final int DISCOUNT_PER_MAIN = 2_023;

    private static final int CHAMPAGNE_GIFT_PRICE = 25_000;

    private static final int CHRISTMAS_D_DAY = 25;
    private static final int WEEK_PERIOD = 7;
    private static final int FRIDAY = 1;
    private static final int SATURDAY = 2;
    private static final int SUNDAY = 3;

    public static int calculateAllDiscount(Menus menus, LocalDate date) {
        if (validateTotalOrder(menus)) {
            return 0;
        }

        return  calculateDdayDiscount(menus, date) +
                calculateWeekdayDiscount(menus, date) +
                calculateWeekendDiscount(menus, date) +
                calculateSpecialDiscount(menus, date);
    }

    public static int calculateDdayDiscount(Menus menus, LocalDate date) {
        if (validateTotalOrder(menus)) {
            return 0;
        }

        int dayOfMonth = date.getDayOfMonth();
        if (dayOfMonth > CHRISTMAS_D_DAY) {
            return 0;
        }

        return BASIC_DISCOUNT + DAILY_INCREMENT * (dayOfMonth - 1);
    }

    public static int calculateWeekdayDiscount(Menus menus, LocalDate date) {
        if (validateTotalOrder(menus)) {
            return 0;
        }

        int dayModWeek = date.getDayOfMonth() % WEEK_PERIOD;
        if (dayModWeek == FRIDAY || dayModWeek == SATURDAY) {
            return 0;
        }

        Map<String, Integer> countByMenu = menus.getMenus();
        int totalDiscount = 0;

        for (Entry<String, Integer> entry : countByMenu.entrySet()) {
            String menuName = entry.getKey();
            Integer count = entry.getValue();
            MenuBoard menu = MenuBoard.of(menuName);
            if (menu.getDishType() == DishType.DESSERT) {
                totalDiscount += DISCOUNT_PER_DISSERT * count;
            }
        }
        return totalDiscount;
    }

    public static int calculateWeekendDiscount(Menus menus, LocalDate date) {
        if (validateTotalOrder(menus)) {
            return 0;
        }

        int dayModWeek = date.getDayOfMonth() % WEEK_PERIOD;
        if (dayModWeek != FRIDAY && dayModWeek != SATURDAY) {
            return 0;
        }

        Map<String, Integer> countByMenu = menus.getMenus();
        int totalDiscount = 0;

        for (Entry<String, Integer> entry : countByMenu.entrySet()) {
            String menuName = entry.getKey();
            Integer count = entry.getValue();
            MenuBoard menu = MenuBoard.of(menuName);
            if (menu.getDishType() == DishType.MAIN) {
                totalDiscount += DISCOUNT_PER_MAIN * count;
            }
        }
        return totalDiscount;
    }

    public static int calculateSpecialDiscount(Menus menus, LocalDate date) {
        if (validateTotalOrder(menus)) {
            return 0;
        }

        int dayOfMonth = date.getDayOfMonth();
        if (dayOfMonth != CHRISTMAS_D_DAY && dayOfMonth % WEEK_PERIOD != SUNDAY) {
            return 0;
        }
        return SPECIAL_DISCOUNT;
    }

    public static int calculateGiftAmount(Menus menus) {
        if (validateTotalOrder(menus)) {
            return 0;
        }

        if (menus.hasGift()) {
            return CHAMPAGNE_GIFT_PRICE;
        }
        return 0;
    }

    public static boolean validateTotalOrder(Menus menus) {
        int totalOrder = menus.calculateTotalOrderAmount();
        if (totalOrder < EVENT_THRESH_HOLD) {
            return true;
        }
        return false;
    }
}
