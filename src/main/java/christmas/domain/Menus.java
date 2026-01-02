package christmas.domain;

import java.util.Map;
import java.util.Map.Entry;

public class Menus {

    private Map<String, Integer> menus;

    public Menus(Map<String, Integer> menus) {
        this.menus = menus;
    }

    public Map<String, Integer> getMenus() {
        return menus;
    }

    public long calculateTotalOrderAmount() {
        long totalAmount = 0;
        for (Entry<String, Integer> entry : menus.entrySet()) {
            String menuName = entry.getKey();
            Integer count = entry.getValue();
            MenuBoard menu = MenuBoard.of(menuName);
            totalAmount += (long) menu.getPrice() * count;
        }
        return totalAmount;
    }
}
