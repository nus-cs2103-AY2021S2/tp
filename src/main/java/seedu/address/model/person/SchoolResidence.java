package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SchoolResidence {

    public enum ResidenceAbbreviation {
        PGPH, PGPR, KE7H, SH, KRH, TH, EH, RH, RVRC, YNC, TC, CAPT, RC4, USP, UTR, DOES_NOT_LIVE_ON_CAMPUS;
    }

    public static final List<ResidenceAbbreviation> LIST_RESIDENCES = Arrays.asList(
            ResidenceAbbreviation.PGPH, ResidenceAbbreviation.PGPR, ResidenceAbbreviation.KE7H,
            ResidenceAbbreviation.SH, ResidenceAbbreviation.KRH, ResidenceAbbreviation.TH, ResidenceAbbreviation.EH,
            ResidenceAbbreviation.RH, ResidenceAbbreviation.RVRC, ResidenceAbbreviation.YNC, ResidenceAbbreviation.TC,
            ResidenceAbbreviation.CAPT, ResidenceAbbreviation.RC4, ResidenceAbbreviation.USP, ResidenceAbbreviation.UTR
    );

    public static final String MESSAGE_CONSTRAINTS = "The residence entered should be one of the following: \n"
            + getResidenceAbbreviation().toString();

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
        try {
            boolean result = test.equalsIgnoreCase("Does not live on campus")
                    || LIST_RESIDENCES.contains(ResidenceAbbreviation.valueOf(test.toUpperCase()));
            return result;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static String getStringResidences() {
        return String.join(",", getResidenceAbbreviation());
    }

    public static List<String> getResidenceAbbreviation() {
        String[] residenceArray = Stream.of(SchoolResidence.ResidenceAbbreviation.values())
                .map(SchoolResidence.ResidenceAbbreviation::name).toArray(String[]::new);
        return Arrays.asList(residenceArray);
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
