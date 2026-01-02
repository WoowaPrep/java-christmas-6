package christmas;

import christmas.domain.Menus;
import christmas.domain.VisitDate;
import christmas.view.InputParser;
import christmas.view.InputView;
import christmas.view.OutputView;
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

        printEventBenefitsPreview(visitDate.getDay());
        printOrderMenu(menus);
        printTotalOrderAmount(menus);

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

    private void printEventBenefitsPreview(int day) {
        outputView.printEventBenefitsPreview(day);
    }
    private void printOrderMenu(Menus menus) {
        outputView.printOrderMenu(menus);
    }

    private void printTotalOrderAmount(Menus menus) {
        long totalAmount = menus.calculateTotalOrderAmount();
        outputView.printOrderMenu(totalAmount);
    }
}
