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
        if (gender.equals("M")) {
            gender = "Male";
        } else if (gender.equals("F")) {
            gender = "Female";
        } else if (gender.equals("N")) {
            gender = "Non-binary";
        }
        this.value = gender;
        assert this.value.equals("Male") || this.value.equals("Female") || this.value.equals("Non-binary")
                : "Invalid value for Gender";
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidGender (String test) {
        return test.equals("M") || test.equals("F") || test.equals("N")
                || test.equals("Male") || test.equals("Female") || test.equals("Non-binary");
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
