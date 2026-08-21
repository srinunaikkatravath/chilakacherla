package com.chilakacherla.research.model;

public enum TrustLevel {
    LEVEL_1_OFFICIAL(1, "Level 1 — Official Government"),
    LEVEL_2_INSTITUTION(2, "Level 2 — Recognized Institution"),
    LEVEL_3_NEWS(3, "Level 3 — Reputed News"),
    LEVEL_4_DIRECTORY(4, "Level 4 — Public Directory & Maps"),
    LEVEL_5_COMMUNITY(5, "Level 5 — Community Submitted");

    private final int level;
    private final String description;

    TrustLevel(int level, String description) {
        this.level = level;
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }
}
