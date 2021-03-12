package seedu.address.model.user;

/**
 * Represents a User's age.
 */
public class Age {
    public static final String MESSAGE_CONSTRAINTS =
            "Ages should only contain numbers, and it should fall between 1 and 100!";
    public static final String VALIDATION_REGEX = "\\d{1,}";
    private final int age;

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
