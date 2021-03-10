package seedu.address.model;

import java.time.LocalDateTime;

import seedu.address.model.person.Email;

/**
 * Temporary stub class to test the UI interface.
 */
public class AppointmentStub {
    private final Email email;
    private final String subject;
    private final LocalDateTime dateTime;
    private final String location;

    /**
     * Default constructor for AppointmentStub
     */
    public AppointmentStub(Email email, String subject, LocalDateTime dateTime, String location) {
        this.email = email;
        this.subject = subject;
        this.dateTime = dateTime;
        this.location = location;
    }

    public Email getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getLocation() {
        return location;
    }
}
