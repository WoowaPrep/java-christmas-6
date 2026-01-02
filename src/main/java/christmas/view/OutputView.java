package christmas.view;

import christmas.domain.Badge;
import christmas.domain.Discount;
import christmas.domain.Menus;
import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    private static final String NONE = "없음";

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
    private static final String TOTAL_BENEFIT_TITLE = "<총혜택 금액>";
    private static final String PAYMENT_AMOUNT_TITLE = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE_TITLE = "<12월 이벤트 배지>";

    private static final String MENU_COUNT_FORMAT = "%s %d개" + NEW_LINE;
    private static final String TOTAL_ORDER_AMOUNT_FORMAT = "%,d원" + NEW_LINE;
    private static final String ONE_CHAMPAGNE = "샴페인 1개" + NEW_LINE;
    private static final String TOTAL_BENEFIT_AMOUNT = "%,d원" + NEW_LINE;
    private static final String PAYMENT_AMOUNT = "%,d원" + NEW_LINE;

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

        printNone();
        printNewLine();
    }

    public void printBenefitsHistoryTitle() {
        System.out.println(BENEFITS_HISTORY_TITLE);
    }

    public boolean printDdayDiscount(LocalDate date) {
        int totalDiscount = Discount.calculateDdayDiscount(date);
        if (totalDiscount == 0) {
            return false;
        }

        System.out.printf(CHRISTMAS_D_DAY_DISCOUNT, -totalDiscount);
        return true;
    }

    public boolean printWeekdayDiscount(Menus menus, LocalDate date) {
        int totalDiscount = Discount.calculateWeekdayDiscount(menus, date);
        if (totalDiscount == 0) {
            return false;
        }

        System.out.printf(WEEKDAY_DISCOUNT, -totalDiscount);
        return true;
    }

    public boolean printWeekendDiscount(Menus menus, LocalDate date) {
        int totalDiscount = Discount.calculateWeekendDiscount(menus, date);
        if (totalDiscount == 0) {
            return false;
        }

        System.out.printf(WEEKEND_DISCOUNT, -totalDiscount);
        return true;
    }

    public boolean printSpecialDiscount(LocalDate date) {
        int totalDiscount = Discount.calculateSpecialDiscount(date);
        if (totalDiscount == 0) {
            return false;
        }

        System.out.printf(SPECIAL_DISCOUNT, -totalDiscount);
        return true;
    }

    public boolean printGiftDiscount(Menus menus) {
        int totalDiscount = Discount.calculateGiftAmount(menus);
        if (totalDiscount == 0) {
            return false;
        }

        System.out.printf(GIFT_EVENT_DISCOUNT, -totalDiscount);
        return true;
    }

    public void printTotalBenefitAmount(int totalAmount) {
        System.out.println(TOTAL_BENEFIT_TITLE);
        System.out.printf(TOTAL_BENEFIT_AMOUNT, -totalAmount);
        printNewLine();
    }

    public void printPaymentAmount(int paymentAmount) {
        System.out.println(PAYMENT_AMOUNT_TITLE);
        System.out.printf(PAYMENT_AMOUNT, paymentAmount);
    }

    public void printEventBadge(int total) {
        Badge badge = Badge.getBadge(total);
        System.out.println(EVENT_BADGE_TITLE);
        System.out.println(badge.getName());
    }

    public void printNone() {
        System.out.println(NONE);
    }

    public void printNewLine() {
        System.out.println();
    }
}
