package seedu.address.model.medical;

import seedu.address.model.person.Person;

import java.time.LocalDateTime;

public class Appointment {
    public static final String MESSAGE_CONSTRAINTS = "TODO APPT MESSAGE CONSTRAINTS ";

    Person person;
    String zoomMeetingURL;
    LocalDateTime date;

    public Appointment(Person person, LocalDateTime date) {
        this.person = person;
        this.date = date;
    }
}
