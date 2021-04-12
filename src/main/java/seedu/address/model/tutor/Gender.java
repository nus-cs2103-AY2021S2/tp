package seedu.address.model.tutor;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Tutor's gender in the TutorBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender {

    public static final String MESSAGE_CONSTRAINTS =
            "Genders should be male, female or others";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */

    public final String personGender;

    /**
     * Constructs a {@code Name}.
     *
     * @param gender A valid gender.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        personGender = gender;
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidGender(String s) {
        String uppercaseString = s.toUpperCase();
        return uppercaseString.equals("MALE") || uppercaseString.equals("FEMALE") || uppercaseString.equals("OTHERS");
    }


    @Override
    public String toString() {
        return personGender;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && personGender.equals(((Gender) other).personGender)); // state check
    }

    @Override
    public int hashCode() {
        return personGender.hashCode();
    }
}
