package seedu.address.model.appointment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.subject.SubjectName;

/**
 * Appointment class to store appointment objects to represent tutee and tutor relations
 */
public class Appointment {

    private final Email email;
    private final SubjectName subject;
    private final LocalDateTime dateTime;
    private final Address location;

    private final String formatter = "dd MM yyyy";

    /**
     * Primary constructor for appointment class.
     * @param email Email of tutor.
     * @param subject Subject tutor is teaching to tutee.
     * @param dateTime LocalDateTime
     * @param location Location of teaching venue
     */
    public Appointment(Email email, SubjectName subject, LocalDateTime dateTime,
                       Address location) {
        this.email = email;
        this.subject = subject;
        this.dateTime = dateTime;
        this.location = location;
    }

    public Email getEmail() {
        return email;
    }

    public SubjectName getSubject() {
        return subject;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Address getLocation() {
        return location;
    }

    /**
     * Helper method to parse date time.
     * @param date Date in string
     * @param time 24 hr time in integer
     * @return LocalDateTime for given date and time
     */
    private LocalDateTime parseDateTime(String date, int time) {
        String[] tempArray = date.split("\\s+");
        List<Integer> dateList =
                Arrays.stream(tempArray).map(Integer::parseInt).collect(Collectors.toList());
        int hour = time / 100;
        int min = time % 100;

        LocalDateTime dateAndTime = LocalDateTime.of(dateList.get(1), dateList.get(2),
                dateList.get(3), hour, min);
        return dateAndTime;
    }

    @Override
    public String toString() {
        return String.format("Appointment with Tutor (%s) at %s", this.email.value,
                LocalDateTime.parse(this.dateTime.toString(),
                        DateTimeFormatter.ofPattern(formatter)));
    }

    /**
     * Returns true if both appointment have the same datetime.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }

        return otherAppointment != null
                && this.dateTime.isEqual(otherAppointment.dateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Appointment that = (Appointment) o;
        return Objects.equals(email, that.email) && Objects.equals(subject, that.subject)
                && Objects.equals(dateTime, that.dateTime) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, subject, dateTime, location);
    }
}
