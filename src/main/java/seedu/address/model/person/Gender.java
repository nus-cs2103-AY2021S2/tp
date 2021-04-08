package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Gender {
    public static final String MESSAGE_CONSTRAINTS =
            "Gender should be either \"M\"(male) or \"F\"(female) or \"N\"(non-binary), and it should not be blank";

    public final String value;

    /**
     * Constructs a {@code Gender}.
     *
     * @param gender A valid gender.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        String genderLower = gender.toLowerCase();
        String genderValue = "Placeholder";
        if (genderLower.equals("m") || genderLower.equals("male")) {
            genderValue = "Male";
        } else if (genderLower.equals("f") || genderLower.equals("female")) {
            genderValue = "Female";
        } else if (genderLower.equals("n") || genderLower.equals("non-binary")) {
            genderValue = "Non-binary";
        }
        this.value = genderValue;
        assert this.value.equals("Male") || this.value.equals("Female") || this.value.equals("Non-binary")
                : "Invalid value for Gender";
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidGender (String test) {
        String testLower = test.toLowerCase();
        return testLower.equals("m") || testLower.equals("f") || testLower.equals("n")
                || testLower.equals("male") || testLower.equals("female") || testLower.equals("non-binary");
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && value.equals(((Gender) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
