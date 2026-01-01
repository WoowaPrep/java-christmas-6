package christmas.exception;

public enum ErrorMessage {

    EMPTY_INPUT("입력값이 비어있습니다."),
    DAY_OUT_OF_RANGE("유효한 날짜 범위가 아닙니다."),
    DAY_NOT_NUMERIC("날짜는 숫자만 입력 가능합니다."),
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
