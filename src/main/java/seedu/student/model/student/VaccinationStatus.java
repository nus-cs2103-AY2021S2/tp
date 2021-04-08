package seedu.student.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.student.commons.util.AppUtil.checkArgument;

public class VaccinationStatus {

    public enum VaccinationStatusAbbreviation {
        VACCINATED, UNVACCINATED
    }

    public static final String MESSAGE_CONSTRAINTS = "Vaccination status should only be of the format 'vaccinated' "
            + "or 'unvaccinated' ";

    public final VaccinationStatusAbbreviation status; // enum for Vaccination status
    public final String textUI; // string to display on the GUI

    /**
     * Constructs an {@code VaccinationStatus}.
     *
     * @param vaccinationStatus A valid VaccinationStatus.
     */
    public VaccinationStatus(String vaccinationStatus) {
        requireNonNull(vaccinationStatus);
        checkArgument(isValidStatus(vaccinationStatus), MESSAGE_CONSTRAINTS);
        if (vaccinationStatus.equals("vaccinated")) {
            status = VaccinationStatusAbbreviation.VACCINATED;
            textUI = vaccinationStatus;
        } else {
            status = VaccinationStatusAbbreviation.UNVACCINATED;
            textUI = vaccinationStatus;
        }
    }

    /**
     * Returns true if the given string is a valid status.
     *
     * @param test the string whose format is to be checked.
     * @return true if test is a valid statis, false otherwise.
     */
    public static boolean isValidStatus(String test) {
        test = test.toUpperCase();
        try {
            boolean result = VaccinationStatusAbbreviation.valueOf(test) == VaccinationStatusAbbreviation.UNVACCINATED
                    || VaccinationStatusAbbreviation.valueOf(test) == VaccinationStatusAbbreviation.VACCINATED;
            return result;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return status.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VaccinationStatus // instanceof handles nulls
                && status == ((VaccinationStatus) other).status); // state check
    }

}
