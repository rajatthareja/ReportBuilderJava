package com.rajatthareja.reportbuilder;

/**
 * Colors for Report http://materializecss.com/color.html
 */
public enum Color {
    BROWN("brown"),
    INDIGO("indigo"),
    CYAN("cyan"),
    PURPLE("purple"),
    GREY("grey"),
    LIME("lime");

    private final String color;

    /**
     * @param color for report
     */
    Color(final String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color.toLowerCase();
    }
}