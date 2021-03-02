package seedu.address.model.common;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    public LocalDate date;

    public Date(LocalDate date) {
        requireNonNull(date);
        this.date = date;
    }
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return this.date.format(dateFormatter);
    }
}
