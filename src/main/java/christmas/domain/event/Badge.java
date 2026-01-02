package christmas.domain;

public enum Badge {

    SANTA("산타"),
    TREE("트리"),
    STAR("별"),
    NONE("없음"),
    ;

    private final String name;

    Badge(String name) {
        this.name = name;
    }

    public static Badge getBadge(int amount) {
        if (amount >= 20_000) return Badge.SANTA;
        if (amount >= 10_000) return Badge.TREE;
        if (amount >= 5_000) return Badge.SANTA;
        return Badge.NONE;
    }

    public String getName() {
        return name;
    }
}
