package seedu.address.model.meeting;

public enum MeetingSortDirection {
    ASC("ASC"),
    DESC("DESC");

    private final String sortString;

    MeetingSortDirection(String sortString) {
        this.sortString = sortString;
    }
    public String getValue() {
        return sortString;
    }
}
