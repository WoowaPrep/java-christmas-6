package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String PLANNER_INTRODUCTION_MESSAGE =
            "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String VISIT_DATE_INPUT_MESSAGE =
            "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public String readVisitDate() {
        System.out.println(PLANNER_INTRODUCTION_MESSAGE);
        System.out.println(VISIT_DATE_INPUT_MESSAGE);
        return Console.readLine();
    }
}
