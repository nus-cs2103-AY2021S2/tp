package seedu.address.logic.parser;

public class FilterKeywordChecker {

    private String keyword;
    private String attributeType;

    /**
     * Constructs an {@code FilterKeyWordChecker} and detects the prefix
     *
     * @param keyword a user filter search input.
     */
    public FilterKeywordChecker(String keyword) {
        this.keyword = keyword;

        if (keyword.length() < 2) {
            this.attributeType = "invalid";
        } else if (keyword.substring(0, 2).equals("a/")) {
            this.attributeType = "address";
        } else if (keyword.substring(0, 2).equals("g/")) {
            this.attributeType = "gender";
        } else if (keyword.substring(0, 2).equals("t/")) {
            this.attributeType = "tag";
        } else if (keyword.substring(0, 2).equals("i/")) {
            this.attributeType = "plan";
        } else if (keyword.length() < 4 && !keyword.equals("age/")) {
            this.attributeType = "invalid";
        } else if (keyword.substring(0, 4).equals("age/")) {
            this.attributeType = "age";
        } else {
            this.attributeType = "invalid";
        }
    }

    /**
     * Returns the {@code String} value of keyword after checking the attribute type of the filter query
     */
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

    /**
     * Returns the {@code String} value of attribute type of the query
     */
    public String getAttributeType() {
        return this.attributeType;
    }

    /**
     * Returns true if the attribute type is address (prefix: a/), otherwise false
     */
    public boolean isAddress() {
        return this.attributeType.equals("address");
    }

    /**
     * Returns true if the attribute type is gender (prefix: g/), otherwise false
     */
    public boolean isGender() {
        return this.attributeType.equals("gender");
    }

    /**
     * Returns true if the attribute type is tag (prefix: t/), otherwise false
     */
    public boolean isTag() {
        return this.attributeType.equals("tag");
    }

    /**
     * Returns true if the attribute type is age (prefix: age/), otherwise false
     */
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

    /**
     * Returns true if the attribute type is insurance plan (prefix: i/), otherwise false
     */
    public boolean isPlan() {
        return this.attributeType.equals("plan");
    }

    /**
     * Returns true if the attribute type neither of the above, and therefore "invalid", otherwise true
     */
    public boolean isInvalid() {
        return this.attributeType.equals("invalid");
    }


}
