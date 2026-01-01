package christmas.domain;

public enum MenuBoard {

    MUSHROOM_SOUP("양송이수프", 6_000, DishType.APPETIZER),
    TAPAS("타파스", 5_500, DishType.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, DishType.APPETIZER),

    T_BONE_STEAK("티본스테이크", 55_000, DishType.MAIN),
    BBQ_RIBS("바비큐립", 54_000, DishType.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, DishType.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, DishType.MAIN),

    CHOCO_CAKE("초코케이크", 15_000, DishType.DESSERT),
    ICE_CREAM("아이스크림", 5_000, DishType.DESSERT),

    ZERO_COLA("제로콜라", 3_000, DishType.BEVERAGE),
    RED_WINE("레드와인", 60_000, DishType.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, DishType.BEVERAGE),
    ;

    private final String name;
    private final int price;
    private final DishType dishType;

    MenuBoard(String name, int price, DishType dishType) {
        this.name = name;
        this.price = price;
        this.dishType = dishType;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public DishType getDishType() {
        return dishType;
    }

    public boolean isDessert() {
        return dishType == DishType.DESSERT;
    }

    public boolean isMain() {
        return dishType == DishType.MAIN;
    }

    public boolean isBeverage() {
        return dishType == DishType.BEVERAGE;
    }
}
