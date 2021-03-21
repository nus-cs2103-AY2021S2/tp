package seedu.address.model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline {
    public static final String MESSAGE_CONSTRAINTS =
            "Deadlines should be in the format YYYY-MM-DD";

    /*
     * The first character of the title must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    public static final DateTimeFormatter unformatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");


    public final LocalDate date;

    public final String dateString;
    public final String unformattedDate;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param date A valid date.
     */
    public Deadline(String date) {
        this.unformattedDate = date;
        this.date = LocalDate.parse(date);
        this.dateString = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getUnformattedDate() {
        return unformattedDate;
    }

    public LocalDate getDeadline() {
        return this.date;
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && date.equals(((Deadline) other).date)); // state check
    }

}
