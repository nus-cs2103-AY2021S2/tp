package seedu.address.model.attribute;

/**
 * An enumeration of Attributes that a person object can have
 */
public enum Attribute {
    POLICY_ID, EMAIL, ADDRESS, PHONE, MEETING;

    public static final String MESSAGE_CONSTRAINTS = "Attribute should be specified by -ATTRIBUTE,"
            + " where ATTRIBUTE should be policy, phone, email, meeting or address";
}
