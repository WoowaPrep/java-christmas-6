package christmas.view;

import christmas.domain.Menus;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    private static final String ORDER_MENU_TITLE = "<주문 메뉴>";
    private static final String MENU_COUNT_FORMAT = "%s %d개";

    public void printOrderMenu(Menus menus) {
        System.out.println(ORDER_MENU_TITLE);
        Map<String, Integer> countByMenu = menus.getMenus();
        for (Entry<String, Integer> entry : countByMenu.entrySet()) {
            String menuName = entry.getKey();
            Integer count = entry.getValue();
            System.out.printf(MENU_COUNT_FORMAT, menuName, count);
        }
        System.out.println();
    }


}
