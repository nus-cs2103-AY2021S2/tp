package seedu.student.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.student.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SchoolResidence {

    public enum ResidenceAbbreviation {
        PGPH, PGPR, KE7H, SH, KRH, TH, EH, RH, RVRC, YNC, TC, CAPT, RC4, USP, UTR, DOES_NOT_LIVE_ON_CAMPUS;

        @Override
        public String toString() {
            return this == DOES_NOT_LIVE_ON_CAMPUS ? "DOES NOT LIVE ON CAMPUS" : super.toString();
        }
    }

    public static final List<ResidenceAbbreviation> LIST_RESIDENCES = Arrays.asList(
            ResidenceAbbreviation.PGPH, ResidenceAbbreviation.PGPR, ResidenceAbbreviation.KE7H,
            ResidenceAbbreviation.SH, ResidenceAbbreviation.KRH, ResidenceAbbreviation.TH, ResidenceAbbreviation.EH,
            ResidenceAbbreviation.RH, ResidenceAbbreviation.RVRC, ResidenceAbbreviation.YNC, ResidenceAbbreviation.TC,
            ResidenceAbbreviation.CAPT, ResidenceAbbreviation.RC4, ResidenceAbbreviation.USP, ResidenceAbbreviation.UTR,
            ResidenceAbbreviation.DOES_NOT_LIVE_ON_CAMPUS
    );

    public static final String MESSAGE_CONSTRAINTS = "The residence entered should be one of the following: \n"
            + getResidenceAbbreviation().toString();

    public final ResidenceAbbreviation residenceAbbreviation;

    /**
     * Constructs an {@code SchoolResidence}.
     *
     * @param schoolResidence A valid SchoolResidence.
     */
    public SchoolResidence(String schoolResidence) {
        requireNonNull(schoolResidence);
        checkArgument(isValidResidence(schoolResidence), MESSAGE_CONSTRAINTS);
        // only takes in DOES_NOT_LIVE_ON_CAMPUS
        residenceAbbreviation = ResidenceAbbreviation.valueOf(schoolResidence);
    }

    /**
     * Returns true if the given string is a valid residence.
     *
     * @param test the string whose format is to be checked.
     * @return true if test is a valid residence, false otherwise.
     */
    public static boolean isValidResidence(String test) {
        try {
            if (test == null) {
                throw new NullPointerException();
            }
            return getResidenceAbbreviation().contains(test);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static String getStringResidences() {
        return String.join(",", getResidenceAbbreviation());
    }

    public static List<String> getResidenceAbbreviation() { // DOES_NOT_LIVE_ON_CAMPUS
        String[] residenceArray = Stream.of(SchoolResidence.ResidenceAbbreviation.values())
                .map(SchoolResidence.ResidenceAbbreviation::name).toArray(String[]::new);
        return Arrays.asList(residenceArray);
    }

    @Override
    public String toString() {
        return this.residenceAbbreviation.toString(); // "DOES NOT LIVE ON CAMPUS"
    }

    public String toSavingString() {
        return this.residenceAbbreviation.name();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SchoolResidence // instanceof handles nulls
                && residenceAbbreviation == ((SchoolResidence) other).residenceAbbreviation); // state check
    }

    @Override
    public int hashCode() {
        return residenceAbbreviation.hashCode();
    }
}
