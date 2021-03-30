package seedu.student.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.MatriculationNumber;

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
     *Sets the {@code date} of the {@code Appointment} that we are building
     */
    public AppointmentBuilder withDate(LocalDate date) {
        this.date = date;
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
     *Sets the {@code startTime} of the {@code Appointment} that we are building
     */
    public AppointmentBuilder withStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Builds a new Appointment object.
     *
     * @return a appointment object.
     */
    public Appointment build() {
        return new Appointment(matriculationNumber, date, startTime);
    }

    /**
     * Builds a new Appointment based on Alice's details.
     * This is necessary as the editAppt command requires a specific appointment.
     * @return an appointment object based on Alice's details
     */
    public Appointment buildAlice() {
        return new AppointmentBuilder().withMatric(TypicalStudents.ALICE.getMatriculationNumber().value)
                .withStartTime("09:00").build();
    }

}
