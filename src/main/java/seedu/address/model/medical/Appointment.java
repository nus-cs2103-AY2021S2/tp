package seedu.address.model.medical;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.medical.DateFormat.DATE_FORMAT_DISPLAY;
import static seedu.address.model.medical.DateFormat.DATE_FORMAT_STORAGE;

import java.time.LocalDateTime;

import seedu.address.model.person.Person;

/**
 * Represents a Appointment of a Patient.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment {

    public static final String MESSAGE_CONSTRAINTS_MIN_DATE = "Date must be in the future";
    public static final String MESSAGE_CONSTRAINTS_DATE_FORMAT = "Date format: "
            + "DDMMYYYYhhmm or DDMMhhmm. If the year is omitted, the current year is"
            + " assumed.";

    private Person person;
    private String zoomMeetingUrl;
    private LocalDateTime date;

    /**
     * Every field must be present and not null.
     */
    public Appointment(Person person, LocalDateTime date) {
        requireAllNonNull(person, date);
        this.person = person;
        this.date = date;
    }

    /**
     * Every field must be present and not null.
     */
    public Appointment(LocalDateTime date) {
        requireNonNull(date);
        this.date = date;
    }

    public Person getPerson() {
        return person;
    }

    public String getZoomMeetingUrl() {
        return zoomMeetingUrl;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public int hashCode() {
        return person.hashCode() + date.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return otherAppointment.getPerson().equals(getPerson())
                && otherAppointment.getDate().equals(getDate());
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

    public Appointment setPerson(Person p) {
        return new Appointment(p, this.date);
    }

}
