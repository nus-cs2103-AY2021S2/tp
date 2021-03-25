package seedu.partyplanet.model.person;

import static seedu.partyplanet.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

import seedu.partyplanet.model.date.Date;

/** Represents a Person's birthday in PartyPlanet.
 * Guarantees: immutable; is always valid.
 */
public class Birthday extends Date {

    public static final String MESSAGE_CONSTRAINTS = "Birthdays should be in one of the following formats:\n"
            + MESSAGE_YEAR_FORMATS + "\n" + MESSAGE_NOYEAR_FORMATS;
    public static final String MESSAGE_BIRTHDAY_CONSTRAINTS = "Birthday should not be a date in the future";
    public static final Birthday EMPTY_BIRTHDAY = new Birthday();

    /**
     * Constructs a {@code Birthday}.
     * Birthdate should not be in the future, and can optionally contain a year.
     * Some invalid dates are mapped to the nearest valid date, e.g. 29 Feb 2021 -> 28 Feb 2021.
     *
     * @param birthdate A valid birthdate.
     */
    public Birthday(String birthdate) {
        super(birthdate, false);
        checkArgument(isValidBirthdayDate(birthdate), MESSAGE_BIRTHDAY_CONSTRAINTS);
    }

    /**
     * Constructs an empty birthday.
     */
    public Birthday() {
        super();
    }

    /**
     * Returns true if a given date string is a valid date not in the future.
     */
    public static boolean isFuture(String test) {
        return isFuture(test, LocalDate.now());
    }

    /**
     * Returns true if a given date string is a valid date not after {@code reference}.
     * Exposes {@reference} date as a parameter for unit testing.
     * Note: Dates without years which are parsed successfully are always considered not from the future.
     */
    public static boolean isFuture(String test, LocalDate reference) {
        assert isValidDate(test);
        LocalDate date = parseDate(test);
        return reference.isBefore(date);
    }

    /**
     * Returns true if a given birthday string is a valid date not in the future.
     */
    public static boolean isValidBirthdayDate(String test) {
        return isValidBirthdayDate(test, LocalDate.now());
    }

    /**
     * Returns true if a given birthday string is a valid date not after {@code reference}.
     * Exposes {@reference} date as a parameter for unit testing.
     * Note: Dates without years which are parsed successfully are always considered valid.
     */
    public static boolean isValidBirthdayDate(String test, LocalDate reference) {
        return isValidDate(test) && !isFuture(test, reference);
    }

    /**
     * Returns 0 if equal, otherwise positive (negative) integer if monthDay is earlier (later).
     * Empty dates are always treated as later dates.
     */
    public int compareTo(Birthday other) {
        if (isEmpty && isEmptyDate(other)) {
            return 0;
        }
        if (isEmpty) {
            return 1;
        }
        if (isEmptyDate(other)) {
            return -1;
        }
        return date.withYear(NON_YEAR).compareTo(other.date.withYear(NON_YEAR));
    }
}
