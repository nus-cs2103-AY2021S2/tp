package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.commons.util.DateUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class Birthday {
    public static final String MESSAGE_CONSTRAINTS =
            seedu.address.commons.util.DateUtil.MESSAGE_CONSTRAINT;

    public static final DateTimeFormatter BIRTHDAY_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public final LocalDate date;

    /**
     * Constructs a {@code Birthday}.
     * This constructor is mainly for testing purposes
     *
     * @param date A non-empty birthday
     */
    public Birthday(LocalDate date) {
        requireNonNull(date);
        this.date = date;
    }

    /**
     * Constructs a {@code Birthday}.
     * This constructor is mainly for testing purposes
     *
     * @param birthday A non-empty birthday
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidBirthday(birthday), MESSAGE_CONSTRAINTS);
        try {
            this.date = DateUtil.fromDateInput(birthday);
        } catch (ParseException e) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns true if a given string is a valid birthday.
     */
    public static boolean isValidBirthday(String test) {
        try {
            DateUtil.fromDateInput(test);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return DateUtil.toString(date, BIRTHDAY_INPUT_FORMAT);
    }

    public String toUi() {
        return DateUtil.toUi(date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && getDate().equals(((Birthday) other).getDate())); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}

