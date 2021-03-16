package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date {
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be in the format yyyy-mm-dd";
    public LocalDate value;

    public Date(LocalDate date) {
        requireNonNull(date);
        value = date;
    }

    public static boolean isValidDate(String dateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE; //for LocalDate
        try {
            dateTimeFormatter.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public LocalDate getDate() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Date
                && value.equals(((Date) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
