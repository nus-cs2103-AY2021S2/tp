package seedu.address.model.task;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Repeatable {

    public static final String MESSAGE_CONSTRAINTS_DESCRIPTION = "Description can take any values, and it should "
            + "not be blank";

    /*
     * The first character of the description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String DESCRIPTION_VALIDATION_REGEX = "[^\\s].*";

    protected String description;
    protected Boolean isWeekly;
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor for Repeatable.
     * @param description Description of the Repeatable.
     * @param date Date of the Repeatable.
     * @param time Time of the Repeatable.
     */
    public Repeatable(String description, LocalDate date, LocalTime time) {
        requireAllNonNull(description, date, time);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS_DESCRIPTION);
        this.description = description;
        this.date = date;
        this.time = time;
        this.isWeekly = false;
    }

    /**
     * Constructor for Repeatable.
     * @param description Description of the Repeatable.
     * @param date Date of the Repeatable.
     * @param time Time of the Repeatable.
     * @param isWeekly isWeekly Status of the Repeatable
     */
    public Repeatable(String description, LocalDate date, LocalTime time, Boolean isWeekly) {
        requireAllNonNull(description, date, time, isWeekly);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS_DESCRIPTION);
        this.description = description;
        this.date = date;
        this.time = time;
        this.isWeekly = isWeekly;
    }

    /**
     * Returns the Repeatable description.
     * @return A String representing the Repeatable description.
     */
    public String getDescription() {
        assert this.description != null;
        return this.description;
    }

    /**
     * Returns the date of the Repeatable.
     * @return A LocalDate representing the Repeatable's date.
     */
    public LocalDate getDate() {
        assert this.date != null : "date should not be null!";
        return this.date;
    }

    /**
     * Returns the time of the Repeatable.
     * @return A LocalTime representing the Repeatable's time.
     */
    public LocalTime getTime() {
        assert this.time != null : "time should not be null!";
        return this.time;
    }

    /**
     * Returns the isWeekly status of the Repeatable.
     * @return A Boolean representing the Repeatable's isWeekly status.
     */
    public abstract Boolean getIsWeekly();

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(DESCRIPTION_VALIDATION_REGEX);
    }

    /**
     * Checks if an instance of a Repeatable is equal to another Object.
     * @param other Object to be compared with.
     * @return True if both objects are equal. Else return false.
     */
    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract int hashCode();

    /**
     * Returns a String representation of the Repeatable.
     * @return A String representation of the Repeatable.
     */
    @Override
    public abstract String toString();
}
