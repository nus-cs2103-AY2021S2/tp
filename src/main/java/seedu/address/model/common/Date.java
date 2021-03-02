package seedu.address.model.common;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    private LocalDate date;

    /**
     * Constructs an {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(LocalDate date) {
        requireNonNull(date);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the date in a string.
     */
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return this.date.format(dateFormatter);
    }
}
