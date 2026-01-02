package christmas.domain.event;

public enum Badge {

    SANTA("산타"),
    TREE("트리"),
    STAR("별"),
    NONE("없음"),
    ;

    private static final int SANTA_BADGE_THRESHOLD = 20_000;
    private static final int TREE_BADGE_THRESHOLD = 10_000;
    private static final int STAR_BADGE_THRESHOLD = 5_000;

    private final String name;

    Badge(String name) {
        this.name = name;
    }

    public static Badge getBadge(int amount) {
        if (amount >= SANTA_BADGE_THRESHOLD) return Badge.SANTA;
        if (amount >= TREE_BADGE_THRESHOLD) return Badge.TREE;
        if (amount >= STAR_BADGE_THRESHOLD) return Badge.SANTA;
        return Badge.NONE;
    }

    public String getName() {
        return name;
    }
}
