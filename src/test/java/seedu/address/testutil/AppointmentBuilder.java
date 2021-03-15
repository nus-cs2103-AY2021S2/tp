package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.MatriculationNumber;

/**
 * A utility class to help with building Person objects.
 */
public class AppointmentBuilder {

    public static final String DEFAULT_MATRIC = "A0192376F";
    public static final String DEFAULT_DATE = "2021-01-01";
    public static final String DEFAULT_START_TIME = "10:00";
    public static final String DEFAULT_END_TIME = "10:30";

    private MatriculationNumber matriculationNumber;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public AppointmentBuilder() {
        matriculationNumber = new MatriculationNumber(DEFAULT_MATRIC);
        date = LocalDate.parse(DEFAULT_DATE);
        startTime = LocalTime.parse(DEFAULT_START_TIME);
        endTime = LocalTime.parse(DEFAULT_END_TIME);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        matriculationNumber = appointmentToCopy.getMatriculationNumber();
        date = appointmentToCopy.getDate();
        startTime = appointmentToCopy.getStartTime();
        endTime = appointmentToCopy.getEndTime();
    }

    /**
     *Sets the {@code MatriculationNumber} of the {@code Appointment} that we are building
     */
    public AppointmentBuilder withMatric(String matric) {
        this.matriculationNumber = new MatriculationNumber(matric);
        return this;
    }

    /**
     *Sets the {@code date} of the {@code Appointment} that we are building
     */
    public AppointmentBuilder withDate(String date) {
        this.date = LocalDate.parse(date);
        return this;
    }

    /**
     *Sets the {@code startTime} of the {@code Appointment} that we are building
     */
    public AppointmentBuilder withStartTime(String startTime) {
        this.startTime = LocalTime.parse(startTime);
        return this;
    }

    /**
     *Sets the {@code endTime} of the {@code Appointment} that we are building
     */
    public AppointmentBuilder withEndTime(String endTime) {
        this.endTime = LocalTime.parse(endTime);
        return this;
    }

    /**
     * Builds a new Person object.
     *
     * @return a person object.
     */
    public Appointment build() {
        return new Appointment(matriculationNumber, date, startTime, endTime);
    }

}
