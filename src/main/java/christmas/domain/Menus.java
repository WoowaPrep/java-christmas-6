package christmas.domain;

import java.util.Map;
import java.util.Map.Entry;

public class Menus {

    private static final int GIFT_THRESHOLD = 120_000;

    private Map<String, Integer> menus;

    public Menus(Map<String, Integer> menus) {
        this.menus = menus;
    }

    public Map<String, Integer> getMenus() {
        return menus;
    }

    public int calculateTotalOrderAmount() {
        int totalAmount = 0;
        for (Entry<String, Integer> entry : menus.entrySet()) {
            String menuName = entry.getKey();
            Integer count = entry.getValue();
            MenuBoard menu = MenuBoard.of(menuName);
            totalAmount += menu.getPrice() * count;
        }
        return totalAmount;
    }

    public boolean hasGift() {
        if (calculateTotalOrderAmount() >= GIFT_THRESHOLD) {
            return true;
        }
        return false;
    }
}
