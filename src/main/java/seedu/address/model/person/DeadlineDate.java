package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task's deadlineDate in the task list.
 * Guarantees: is valid as declared in {@link #isValidDeadlineDate(String)}
 */
public class DeadlineDate implements Comparable<DeadlineDate> {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadline date should: \n 1.DD-MM-YYYY,\n 2.it should exist on calendar, and\n"
                    + " 3.The year is between 2020-2099";

    public static final String VALIDATION_REGEX = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-((20)[2-9][0-9])$";
    private static final DateTimeFormatter dateDateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private LocalDate deadlineDate;

    /**
     * Constructs a {@code DeadlineDate}.
     *
     * @param deadlineDate A valid deadline date.
     */
    public DeadlineDate(String deadlineDate) {
        requireNonNull(deadlineDate);
        checkArgument(isValidDeadlineDate(deadlineDate), MESSAGE_CONSTRAINTS);
        this.deadlineDate = LocalDate.parse(deadlineDate, dateDateFormatter);
    }

    /**
     * Returns true if a given String is a valid deadline date.
     */
    public static boolean isValidDeadlineDate(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        try {
            LocalDate testingDate = LocalDate.parse(test, dateDateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return deadlineDate.format(dateDateFormatter);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineDate // instanceof handles nulls
                && deadlineDate.equals(((DeadlineDate) other).deadlineDate)); // state check
    }

    @Override
    public int hashCode() {
        return deadlineDate.hashCode();
    }

    @Override
    public int compareTo(DeadlineDate otherDeadline) {
        return this.deadlineDate.compareTo(otherDeadline.deadlineDate);
    }
}
