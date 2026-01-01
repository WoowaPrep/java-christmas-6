package christmas;

import christmas.domain.VisitDate;
import christmas.view.InputParser;
import christmas.view.InputView;
import christmas.view.OutputView;

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

    }

    private VisitDate readVisitDate() {
        String visitDateInput = inputView.readVisitDate();
        Integer visitDate = InputParser.parseVisitDate(visitDateInput);
        return new VisitDate(visitDate);
    }

}
