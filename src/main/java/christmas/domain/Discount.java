package christmas.domain;

import java.util.Map;
import java.util.Map.Entry;

public class Discount {

    private static final int BASIC_DISCOUNT = 1_000;
    private static final int SPECIAL_DISCOUNT = 1_000;
    private static final int DAILY_INCREMENT = 100;

    private static final int DISCOUNT_PER_DISSERT = 2_023;
    private static final int DISCOUNT_PER_MAIN = 2_023;

    private static final int CHAMPAGNE_GIFT_PRICE = 25_000;

    public static int calculateDdayDiscount(int dayOfMonth) {
        return BASIC_DISCOUNT + DAILY_INCREMENT * (dayOfMonth - 1);
    }

    public static int calculateWeekdayDiscount(Menus menus) {
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

    public static int calculateWeekendDiscount(Menus menus) {
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

    public static int calculateSpecialDiscount() {
        return SPECIAL_DISCOUNT;
    }

    public static int calculateGiftDiscount(Menus menus) {
        if (menus.hasGift()) {
            return CHAMPAGNE_GIFT_PRICE;
        }
        return 0;
    }
}
