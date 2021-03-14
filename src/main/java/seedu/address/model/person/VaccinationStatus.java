package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class VaccinationStatus {

    public enum VacStatusEnum {
        VACCINATED, NOT_VACCINATED
    }

    public static final String MESSAGE_CONSTRAINTS = "Vaccination status should only be of the format 'vaccinated' "
            + "or 'not vaccinated' ";

    public final VacStatusEnum status;
    public final String textUI;

    /**
     * Constructs an {@code VaccinationStatus}.
     *
     * @param vaccinationStatus A valid VaccinationStatus.
     */
    public VaccinationStatus(String vaccinationStatus) {
        requireNonNull(vaccinationStatus);
        checkArgument(isValidStatus(vaccinationStatus), MESSAGE_CONSTRAINTS);
        if (vaccinationStatus.equals("vaccinated")) {
            status = VacStatusEnum.VACCINATED;
            textUI = vaccinationStatus;
        } else {
            status = VacStatusEnum.NOT_VACCINATED;
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
        test = test.replaceAll(" ", "_").toUpperCase();
        try {
            boolean result = VacStatusEnum.valueOf(test) == VacStatusEnum.NOT_VACCINATED
                    || VacStatusEnum.valueOf(test) == VacStatusEnum.VACCINATED;
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

    @Override
    public int hashCode() {
        return status.hashCode();
    }
}
