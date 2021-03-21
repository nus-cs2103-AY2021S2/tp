package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

import seedu.address.model.Event;
import seedu.address.model.module.Description;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person's birthday in the RemindMe.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthday(String)}
 */
public class Birthday extends Event {

    public static final String MESSAGE_CONSTRAINTS =
            "Birthdays should be in the form of DD/MM/YYYY";

    //todo change regex to this: ^(1[0-2]|0[1-9])\/(3[01]|[12][0-9]|0[1-9])\/[0-9]{4}$
    public static final String VALIDATION_REGEX = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";


    private final LocalDate birthday;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthday A valid birthday.
     */
    public Birthday(String birthday) {
        super(new Description("someone bday"), LocalDate.parse(birthday).atStartOfDay(), new Tag("bday"));
        requireNonNull(birthday);
        checkArgument(isValidBirthday(birthday), VALIDATION_REGEX);
        this.birthday = LocalDate.parse(birthday);
    }

    /**
     * Returns true if a given string is a valid birthday.
     */
    public static boolean isValidBirthday(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if birthday lies on a certain date of any year.
     * @param date to check with.
     * @return true if date and birthday lies on same date, else false.
     */
    public boolean isDate(LocalDate date) {
        return birthday.getMonth() == date.getMonth()
            && birthday.getDayOfMonth() == date.getDayOfMonth();
    }

    @Override
    public String toString() {
        return this.birthday.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && this.birthday.equals(((Birthday) other).birthday)); // state check
    }

    @Override
    public int hashCode() {
        return birthday.hashCode();
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

}
