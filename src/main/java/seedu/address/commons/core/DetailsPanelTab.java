package seedu.address.commons.core;

public enum DetailsPanelTab {
    UPCOMING_EVENTS("Upcoming Events"),
    PERSON_DETAILS("Details"),
    STREAKS("Streaks");

    private final String label;

    DetailsPanelTab(final String label) {
        this.label = label;
    }

    /**
     * Returns the DetailsPanelTab enum representation of the given {@code String}
     */
    public static DetailsPanelTab fromString(String label) {
        for (DetailsPanelTab tab : DetailsPanelTab.values()) {
            if (tab.label.equalsIgnoreCase(label)) {
                return tab;
            }
        }
        throw new IllegalArgumentException("No tab with text " + label + " found");
    }

    @Override
    public String toString() {
        return label;
    }
}
