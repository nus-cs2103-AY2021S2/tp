package seedu.address.model.person;

import java.util.Arrays;

public enum PersonSortOption {
    NAME("NAME"),
    EMAIL("EMAIL"),
    PHONE("EMAIL"),
    ADDRESS("ADDRESS");

    private String sortString;

    PersonSortOption(String sortString) {this.sortString = sortString;}

    public String getValue() { return sortString; }
}
