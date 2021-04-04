package seedu.address.model.property;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.address.model.util.DateTimeFormat;

/**
 * Represents a Property's deadline for selling.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}.
 */
public class Deadline implements Comparable<Deadline> {
    public static final String MESSAGE_CONSTRAINTS =
            "Deadline dates should be valid dates specified strictly in the format DD-MM-YYYY.\n"
            + "E.g. 01-01-2021";

    public static final String MESSAGE_INVALID_DATE = "Invalid date entered.\n"
            + "Please ensure that the date entered is valid";

    private static final String VALIDATION_REGEX = "\\d{2}-\\d{2}-\\d{4}";

    public final LocalDate deadline;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline date.
     */
    public Deadline(LocalDate deadline) {
        requireNonNull(deadline);
        this.deadline = deadline;
    }

    /**
     * Returns true if a given string is a valid date.
     *
     * @param test The string to test.
     * @return True if the given string is a valid date, otherwise false.
     */
    public static boolean isValidDeadline(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return deadline.format(DateTimeFormat.OUTPUT_DATE_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Deadline)) {
            return false;
        }
        Deadline otherDeadline = (Deadline) other;
        return deadline.equals(otherDeadline.deadline);
    }

    @Override
    public int compareTo(Deadline another) {
        return this.deadline.compareTo(another.deadline);
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }
}
