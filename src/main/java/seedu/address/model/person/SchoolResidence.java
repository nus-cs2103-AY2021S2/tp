package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;

public class SchoolResidence {

    private static final List<String> listResidences = Arrays.asList("PGPH", "PGPR", "KE7H", "SH", "KRH", "TH", "EH",
            "RH", "RVRC", "YNC", "TC", "CAPT", "RC4", "USP", "UTR");

    public static final String MESSAGE_CONSTRAINTS = "The residence entered should be one of the following: \n"
            + listResidences.toString();

    public final String value;

    /**
     * Constructs an {@code SchoolResidence}.
     *
     * @param schoolResidence A valid SchoolResidence.
     */
    public SchoolResidence(String schoolResidence) {
        requireNonNull(schoolResidence);
        checkArgument(isValidResidence(schoolResidence), MESSAGE_CONSTRAINTS);
        value = schoolResidence;
    }

    /**
     * Returns true if the given string is a valid residence.
     *
     * @param test the string whose format is to be checked.
     * @return true if test is a valid residence, false otherwise.
     */
    public static boolean isValidResidence(String test) {
        boolean result = listResidences.contains(test.toUpperCase())
                || test.equalsIgnoreCase("Does not live on campus");
        return result;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SchoolResidence // instanceof handles nulls
                && value.equals(((SchoolResidence) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
