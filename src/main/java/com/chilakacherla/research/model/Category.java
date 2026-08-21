package com.chilakacherla.research.model;

public enum Category {
    IDENTITY("Village Identity"),
    GOVERNMENT("Government Information"),
    EDUCATION("Education Data"),
    AGRICULTURE("Agriculture Research"),
    MARKET("Market & Agriculture Contribution"),
    FUNDS("Government Funds & Development"),
    SCHEMES("Government Schemes"),
    JOBS("Jobs Research"),
    NEWS("News Research"),
    HISTORY("Village History"),
    GEOGRAPHY("Geographical Research"),
    PLACES("Public Places Directory"),
    PEOPLE("Village People Research"),
    ELECTION("Voter / Election Data");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
