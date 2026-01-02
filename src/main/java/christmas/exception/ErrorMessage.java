package christmas.exception;

public enum ErrorMessage {

    EMPTY_INPUT("입력값이 비어있습니다. 다시 입력해 주세요."),

    DAY_NOT_NUMERIC("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    DAY_OUT_OF_RANGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),

    INVALID_MENU_FORMAT("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_MENU_COUNT("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    DUPLICATED_MENU("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    NOT_EXIST_MENU("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    EXCEED_DISH_COUNT("총 음식 개수가 20개 넘는 주문은 받을 수 없습니다. 다시 입력해 주세요."),
    ;

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
