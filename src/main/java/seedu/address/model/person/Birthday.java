package seedu.address.model.person;

import java.time.LocalDate;

import seedu.address.commons.util.LocalDateTimeUtil;
import seedu.address.model.Event;
import seedu.address.model.module.Description;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person's birthday in the RemindMe.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthday(String)}
 */
public class Birthday extends Event {

    public static final String MESSAGE_CONSTRAINTS = "Birthdays should be in the form of DD/MM/YYYY";

    private final LocalDate birthday;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthday A valid birthday.
     */
    public Birthday(String birthday) {
        super(new Description("someone bday"),
            LocalDate.parse(birthday, LocalDateTimeUtil.DATE_FORMATTER).atStartOfDay(),
            new Tag("bday"));
        this.birthday = LocalDate.parse(birthday, LocalDateTimeUtil.DATE_FORMATTER);
    }

    /**
     * Returns true if a given string is a valid birthday.
     */
    public static boolean isValidBirthday(String test) {
        return LocalDateTimeUtil.isValidDate(test);
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
    public int getDuration() {
        return 0;
    }

    @Override
    public String toString() {
        return this.birthday.format(LocalDateTimeUtil.DATE_FORMATTER);
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
