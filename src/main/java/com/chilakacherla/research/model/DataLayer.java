package com.chilakacherla.research.model;

public enum DataLayer {
    LAYER_1_OFFICIAL("Layer 1 — Official Data"),
    LAYER_2_PUBLIC("Layer 2 — Public Data"),
    LAYER_3_COMMUNITY("Layer 3 — Community Data");

    private final String label;

    DataLayer(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
