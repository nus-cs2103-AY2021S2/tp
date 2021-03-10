package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class VaccinationStatus {

    public static final String MESSAGE_CONSTRAINTS = "Vaccination status should only be of the format 'vaccinated' "
            + "or 'not vaccinated' ";

    public final String value;

    /**
     * Constructs an {@code VaccinationStatus}.
     *
     * @param vaccinationStatus A valid VaccinationStatus.
     */
    public VaccinationStatus(String vaccinationStatus) {
        requireNonNull(vaccinationStatus);
        checkArgument(isValidStatus(vaccinationStatus), MESSAGE_CONSTRAINTS);
        value = vaccinationStatus;
    }

    /**
     * Returns true if the given string is a valid status.
     *
     * @param test the string whose format is to be checked.
     * @return true if test is a valid statis, false otherwise.
     */
    public static boolean isValidStatus(String test) {
        boolean result = test.equalsIgnoreCase("not vaccinated") ||
                test.equalsIgnoreCase("vaccinated");
        return result;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VaccinationStatus // instanceof handles nulls
                && value.equals(((VaccinationStatus) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
