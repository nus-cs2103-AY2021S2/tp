package seedu.address.model.attribute;

public enum Attribute {
    POLICY_ID, NAME, EMAIL, ADDRESS, TAG, PHONE;

    public static final String MESSAGE_CONSTRAINTS =
            "Attribute should be specified by -ATTRIBUTE, where ATTRIBUTE should be policy, name, or email";
}
