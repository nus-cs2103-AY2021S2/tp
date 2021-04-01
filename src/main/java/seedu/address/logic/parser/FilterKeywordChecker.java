package seedu.address.logic.parser;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.List;

import static seedu.address.commons.core.Messages.*;

public class FilterKeywordChecker {

    private String keyword;
    private String attributeType;

    public FilterKeywordChecker(String keyword) {
        this.keyword = keyword;

        if (keyword.substring(0,2).equals("a/")) {
            this.attributeType = "address";
        } else if (keyword.substring(0,2).equals("g/")) {
            this.attributeType = "gender";
        } else if (keyword.substring(0,2).equals("t/")) {
            this.attributeType = "tag";
        } else if (keyword.substring(0,4).equals("age/")) {
            this.attributeType = "age";
        } else if (keyword.substring(0,2).equals("i/")) {
            this.attributeType = "plan";
        } else {
            this.attributeType = "invalid";
        }
    }

    public String value() {
        if (isAddress() || isGender() || isTag()) {
            return this.keyword.substring(2);
        } else if (isAge()) {
            return this.keyword.substring(4);
        } else if (isPlan()) {
            return this.keyword.substring(2);
        } else {
            return null;
        }
    }

    public String getAttributeType() {
        return this.attributeType;
    }

    public boolean isAddress() {
        return this.attributeType.equals("address");
    }

    public boolean isGender() {
        return this.attributeType.equals("gender");
    }

    public boolean isTag() {
        return this.attributeType.equals("tag");
    }

    public boolean isAge() {
        try {
            String[] ageArr = this.keyword.substring(4).split("-");
            for (String age : ageArr) {
                Integer.parseInt(age);
            }
            return this.attributeType.equals("age");
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isPlan() {
        return this.attributeType.equals("plan");
    }

    public boolean isInvalid() {
        return this.attributeType.equals("invalid");
    }


}
