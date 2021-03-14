package seedu.address.model.medical;

import seedu.address.model.person.Person;

import java.time.LocalDateTime;

import static seedu.address.model.medical.DateFormat.DATE_FORMAT_DISPLAY;
import static seedu.address.model.medical.DateFormat.DATE_FORMAT_STORAGE;

public class Appointment {
    Person person;
    String zoomMeetingURL;

    LocalDateTime date;

    public static final String MESSAGE_CONSTRAINTS_MIN_DATE = "Date must be in the future";
    public static final String MESSAGE_CONSTRAINTS_DATE_FORMAT =
            "Date format: DDMMYYYYhhmm " +
                "or DDMMhhmm. If the year is omitted, the current year is" +
                "assumed.";

    public Appointment(Person person, LocalDateTime date) {
        this.person = person;
        this.date = date;
    }

    public Appointment(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public int hashCode() {
        return person.hashCode() + date.hashCode();
    }

    @Override
    public String toString() {
        return getDateDisplay() + " - " + person.getName();
    }

    // for storage into JSON
    public String getDateStorage() {
        return date.format(DATE_FORMAT_STORAGE);
    }
    // for displaying, doesnt have year
    public String getDateDisplay() {
        return date.format(DATE_FORMAT_DISPLAY);
    }
}
