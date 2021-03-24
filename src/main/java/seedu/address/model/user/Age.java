package seedu.address.model.user;

/**
 * Represents a User's age.
 */
public class Age {
    public static final String MESSAGE_CONSTRAINTS =
            "Ages should only contain numbers, and it should fall between 1 and 100!";
    private static final String VALIDATION_REGEX = "^[1-9][0-9]?$|^100$";
    public final int age;

    /**
     * Initializes the age class.
     * @param age Age to be input
     */
    public Age(int age) {
        this.age = age;
    }

    public Integer getAge() {
        return this.age;
    }

    /**
     * Returns true if a given string is a valid age.
     */
    public static boolean isValidAge(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.valueOf(this.age);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Age)) {
            return false;
        }
        Age otherAge = (Age) other;
        return otherAge.getAge().equals(getAge());
    }
}
