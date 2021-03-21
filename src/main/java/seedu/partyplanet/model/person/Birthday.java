package seedu.partyplanet.model.person;

import static seedu.partyplanet.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

import seedu.partyplanet.model.date.Date;

/** Represents a Person's birthday in PartyPlanet.
 * Guarantees: immutable; is always valid.
 */
public class Birthday extends Date {

    public static final String MESSAGE_CONSTRAINTS = "Birthdays should be in one of the following formats:\n"
            + Date.MESSAGE_CONSTRAINTS;
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
        checkArgument(isValidBirthdayDate(value), MESSAGE_BIRTHDAY_CONSTRAINTS);
    }

    /**
     * Constructs an empty birthday.
     */
    public Birthday() {
        super();
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
     * Returns the month value of the birthday, in the range [1-12].
     * Required for feature to filter contact birthdays by month.
     */
    public int getMonth() {
        if (isEmpty) {
            throw new IllegalArgumentException("Birthday is empty");
        }
        return month;
    }

    @Override
    public int compareTo(Date other) {
        return getMonthDayString().compareTo(other.getMonthDayString());
    }


    @Override
    public String toString() {
        return displayValue;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Birthday)) {
            return false;
        }
        if (isEmpty == Date.isEmptyDate((Birthday) other)) {
            return true;
        }
        return value.equals(((Birthday) other).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
