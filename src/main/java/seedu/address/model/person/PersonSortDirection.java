package seedu.address.model.person;

public enum PersonSortDirection {
    ASC("ASC"),
    DESC("DESC");

    private final String sortString;

    PersonSortDirection(String sortString) {this.sortString = sortString;}

    public String getValue() { return sortString; }

}
