package christmas;

import christmas.domain.Discount;
import christmas.domain.Menus;
import christmas.domain.VisitDate;
import christmas.view.InputParser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.Map;

public class ChristmasPromotion {

    private InputView inputView;
    private OutputView outputView;

    public ChristmasPromotion() {
        this(new InputView(), new OutputView());
    }

    public ChristmasPromotion(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void open() {
        VisitDate visitDate = readVisitDate();
        Menus menus = readMenus();

        printBenefitHistory(menus, visitDate);

        printTotalBenefitAmount(menus, visitDate);
    }

    private VisitDate readVisitDate() {
        String visitDateInput = inputView.readVisitDate();
        Integer visitDate = InputParser.parseVisitDate(visitDateInput);
        return new VisitDate(visitDate);
    }

    private Menus readMenus() {
        String menusInput = inputView.readMenus();
        Map<String, Integer> countByMenu = InputParser.parseMenus(menusInput);
        return new Menus(countByMenu);
    }

    private void printBenefitHistory(Menus menus, VisitDate visitDate) {
        printEventPreview(visitDate.getDay());
        printOrderMenu(menus);
        printTotalOrderAmount(menus);
        printGiftEvent(menus);
        printBenefits(menus, visitDate);
    }

    private void printTotalBenefitAmount(Menus menus, VisitDate day) {
        LocalDate date = LocalDate.of(2023, 12, day.getDay());
        int totalBenefitAmount =
                Discount.calculateDdayDiscount(date) +
                Discount.calculateWeekdayDiscount(menus, date) +
                Discount.calculateWeekendDiscount(menus, date) +
                Discount.calculateSpecialDiscount(date) +
                Discount.calculateGiftDiscount(menus);

        outputView.printTotalBenefitAmount(totalBenefitAmount);
    }

    private void printEventPreview(int day) {
        outputView.printEventPreview(day);
    }
    private void printOrderMenu(Menus menus) {
        outputView.printOrderMenu(menus);
    }

    private void printTotalOrderAmount(Menus menus) {
        long totalAmount = menus.calculateTotalOrderAmount();
        outputView.printOrderMenu(totalAmount);
    }

    private void printGiftEvent(Menus menus) {
        if (menus.hasGift()) {
            outputView.printGiftEvent(true);
            return;
        }
        outputView.printGiftEvent(false);
    }

    private void printBenefits(Menus menus, VisitDate day) {
        LocalDate visitDate = LocalDate.of(2023, 12, day.getDay());
        outputView.printBenefitsHistoryTitle();

        boolean hasDiscount = false;
        if (outputView.printDdayDiscount(visitDate)) hasDiscount = true;
        if (outputView.printWeekdayDiscount(menus, visitDate)) hasDiscount = true;
        if (outputView.printWeekendDiscount(menus, visitDate)) hasDiscount = true;
        if (outputView.printSpecialDiscount(visitDate)) hasDiscount = true;
        if (outputView.printGiftDiscount(menus)) hasDiscount = true;

        if (!hasDiscount) {
            outputView.printNone();
        }
        outputView.printNewLine();
    }
}
