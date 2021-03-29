package seedu.address.model.meeting;

public enum MeetingSortOption {
    NAME("NAME"),
    START("START"),
    END("END"),
    PRIORITY("PRIORITY"),
    DESCRIPTION("DESCRIPTION");

    private final String sortString;

    MeetingSortOption(String sortString) {this.sortString = sortString;}

    public String getValue() { return sortString; }
}
