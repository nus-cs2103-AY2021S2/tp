package seedu.address.model.medical;

import seedu.address.model.person.Person;

import java.time.LocalDateTime;

public class Appointment {
    Person person;
    String zoomMeetingURL;
    LocalDateTime date;
}
