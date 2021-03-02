package seedu.address.model.common;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private LocalDate date;

    /**
     * Constructs an {@code Date}.
     *
     * @param dateString A valid date.
     */
    public Date(String dateString) {
        requireNonNull(date);
        date = LocalDate.parse(dateString, dateFormatter);
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the date in a string.
     */
    public String toString() {
        return this.date.format(dateFormatter);
    }
}
