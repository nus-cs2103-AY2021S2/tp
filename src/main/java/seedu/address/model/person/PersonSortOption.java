package seedu.address.model.person;

public enum PersonSortOption {
    NAME("NAME"),
    EMAIL("EMAIL"),
    PHONE("PHONE"),
    ADDRESS("ADDRESS");

    private String sortString;

    PersonSortOption(String sortString) {this.sortString = sortString;}

    public String getValue() { return sortString; }
}
