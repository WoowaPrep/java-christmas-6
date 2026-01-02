package christmas.view;

import christmas.domain.Discount;
import christmas.domain.Menus;
import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    private static final String NONE = "없음" + NEW_LINE;

    private static final int CHRISTMAS_D_DAY = 25;

    private static final int WEEK_PERIOD = 7;
    private static final int FRIDAY = 1;
    private static final int SATURDAY = 2;
    private static final int SUNDAY = 3;

    private static final String CHRISTMAS_D_DAY_DISCOUNT = "크리스마스 디데이 할인: %,d원" + NEW_LINE;
    private static final String WEEKDAY_DISCOUNT = "평일 할인: %,d원" + NEW_LINE;
    private static final String WEEKEND_DISCOUNT = "주말 할인: %,d원" + NEW_LINE;
    private static final String SPECIAL_DISCOUNT = "특별 할인: %,d원" + NEW_LINE;
    private static final String GIFT_EVENT_DISCOUNT = "증정 이벤트: %,d원" + NEW_LINE;

    private static final String EVENT_BENEFITS_PREVIEW =
            "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" + NEW_LINE;
    private static final String ORDER_MENU_TITLE = "<주문 메뉴>";
    private static final String TOTAL_ORDER_AMOUNT_TITLE = "<할인 전 총주문 금액>";
    private static final String GIFT_EVENT_TITLE = "<증정 메뉴>";
    private static final String BENEFITS_HISTORY_TITLE = "<혜택 내역>";
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

    public void printBenefitsHistoryTitle() {
        System.out.println(BENEFITS_HISTORY_TITLE);
    }

    public void printDdayDiscount(LocalDate date) {
        int dayOfMonth = date.getDayOfMonth();
        if (dayOfMonth > CHRISTMAS_D_DAY) {
            return;
        }

        int totalDiscount = Discount.calculateDdayDiscount(dayOfMonth);
        if (totalDiscount > 0) {
            System.out.printf(CHRISTMAS_D_DAY_DISCOUNT, -totalDiscount);
        }
    }

    public void printWeekdayDiscount(Menus menus, LocalDate date) {
        int dayModWeek = date.getDayOfMonth() % WEEK_PERIOD;
        if (dayModWeek == FRIDAY || dayModWeek == SATURDAY) {
            return;
        }

        int totalDiscount = Discount.calculateWeekdayDiscount(menus);
        if (totalDiscount > 0) {
            System.out.printf(WEEKDAY_DISCOUNT, -totalDiscount);
        }
    }

    public void printWeekendDiscount(Menus menus, LocalDate date) {
        int dayModWeek = date.getDayOfMonth() % WEEK_PERIOD;
        if (dayModWeek != FRIDAY && dayModWeek != SATURDAY) {
            return;
        }

        int totalDiscount = Discount.calculateWeekendDiscount(menus);
        if (totalDiscount > 0) {
            System.out.printf(WEEKEND_DISCOUNT, -totalDiscount);
        }
    }

    public void printSpecialDiscount(LocalDate date) {
        int dayOfMonth = date.getDayOfMonth();
        if (dayOfMonth != CHRISTMAS_D_DAY && dayOfMonth % 7 != SUNDAY) {
            return;
        }

        System.out.printf(SPECIAL_DISCOUNT, -Discount.calculateSpecialDiscount());
    }

    public void printGiftDiscount(Menus menus) {
        int totalDiscount = Discount.calculateGiftDiscount(menus);
        if (totalDiscount > 0) {
            System.out.printf(GIFT_EVENT_DISCOUNT, -totalDiscount);
        }
    }
}
