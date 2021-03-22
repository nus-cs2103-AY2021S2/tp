package seedu.address.model.user;

/**
 * Represents a User's gender.
 */
public class Gender {
    public static final String MESSAGE_CONSTRAINTS =
            "Gender should be either M (for Male) or F (for female)!";
    private static final String VALIDATION_REGEX = "^M$|^F$";
    public final String gender;

    /**
     * Initializes the gender class.
     * @param gender Gender to be input
     */
    public Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidGender(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.gender;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Gender)) {
            return false;
        }
        Gender otherGender = (Gender) other;
        return otherGender.getGender().equals(getGender());
    }

}
