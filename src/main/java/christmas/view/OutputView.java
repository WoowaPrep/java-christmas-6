package christmas.view;

import christmas.domain.Menus;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    private static final String NONE = "없음" + NEW_LINE;

    private static final String EVENT_BENEFITS_PREVIEW =
            "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" + NEW_LINE;
    private static final String ORDER_MENU_TITLE = "<주문 메뉴>";
    private static final String TOTAL_ORDER_AMOUNT_TITLE = "<할인 전 총주문 금액>";
    private static final String GIFT_EVENT_TITLE = "<증정 메뉴>";
    private static final String MENU_COUNT_FORMAT = "%s %d개" + NEW_LINE;
    private static final String TOTAL_ORDER_AMOUNT_FORMAT = "%,d원" + NEW_LINE;
    private static final String ONE_CHAMPAGNE = "샴페인 1개" + NEW_LINE;

    public void printEventPreview(int day) {
        System.out.printf(EVENT_BENEFITS_PREVIEW, day);
        System.out.println();
    }

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

    public void printOrderMenu(long amount) {
        System.out.println(TOTAL_ORDER_AMOUNT_TITLE);
        System.out.printf(TOTAL_ORDER_AMOUNT_FORMAT, amount);
        System.out.println();
    }

    public void printGiftEvent(boolean hasGift) {
        System.out.println(GIFT_EVENT_TITLE);
        if (hasGift) {
            System.out.println(ONE_CHAMPAGNE);
            return;
        }
        System.out.println(NONE);
    }
}
