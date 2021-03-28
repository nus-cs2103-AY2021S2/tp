package seedu.student.logic.commands.statscommands;

import static seedu.student.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.student.testutil.TypicalStudents.getTypicalStudentBook;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.student.model.Model;
import seedu.student.model.ModelManager;
import seedu.student.model.UserPrefs;
import seedu.student.model.appointment.Appointment;
import seedu.student.testutil.AppointmentBuilder;
import seedu.student.testutil.TypicalStudents;

public class StatsApptCommandTest {

    private Model expectedModel = new ModelManager(getTypicalStudentBook(), new UserPrefs());

    private LocalDate currDate = LocalDate.now();
    private LocalDate aliceDate = currDate.minusWeeks(1).plusDays(1);
    private LocalDate bensonDate = currDate;
    private LocalDate carlDate = currDate.plusWeeks(1).minusDays(1);
    private LocalDate danielDate = currDate.plusWeeks(1);
    private LocalDate elleDate = currDate.minusWeeks(1);
    private LocalDate fionaDate = currDate.plusWeeks(2);
    private LocalDate georgeDate = currDate.minusWeeks(2);

    private LocalTime currTime = LocalTime.now();
    private String generalTime = Integer.toString(currTime.getHour()) + ":00";
    private String aliceStartHour = Integer.toString(currTime.getHour() + 4);
    private String aliceStartTime = aliceStartHour + ":00";

    private String bensonStartHour = Integer.toString(currTime.getHour() + 1);
    private String bensonStartTime = bensonStartHour + ":00";

    private String carlStartHour = Integer.toString(currTime.getHour() - 4);
    private String carlStartTime = carlStartHour + ":00";


    // past week 6 days ago (included in past)
    public final Appointment aliceAppointment = new AppointmentBuilder()
            .withMatric(TypicalStudents.ALICE.getMatriculationNumber().toString())
            .withStartTime(aliceStartTime).withDate(aliceDate).build();

    // upcoming week same day but after current time (included in upcoming)
    public final Appointment bensonAppointment = new AppointmentBuilder()
            .withMatric(TypicalStudents.BENSON.getMatriculationNumber().toString())
            .withStartTime(bensonStartTime).withDate(bensonDate).build();

    // upcoming week 6 days later (included in upcoming)
    public final Appointment carlAppointment = new AppointmentBuilder()
            .withMatric(TypicalStudents.CARL.getMatriculationNumber().toString())
            .withStartTime(carlStartTime).withDate(carlDate).build();

    // upcoming week 7 days later (not included)
    public final Appointment danielAppointment = new AppointmentBuilder()
            .withMatric(TypicalStudents.DANIEL.getMatriculationNumber().toString())
            .withStartTime(generalTime).withDate(danielDate).build();

    // past week 7 days later (not included)
    public final Appointment elleAppointment = new AppointmentBuilder()
            .withMatric(TypicalStudents.ELLE.getMatriculationNumber().toString())
            .withStartTime(generalTime).withDate(elleDate).build();

    // 2 weeks later (not included)
    public final Appointment fionaAppointment = new AppointmentBuilder()
            .withMatric(TypicalStudents.FIONA.getMatriculationNumber().toString())
            .withStartTime(generalTime).withDate(fionaDate).build();

    // 2 weeks before (not included)
    public final Appointment georgeAppointment = new AppointmentBuilder()
            .withMatric(TypicalStudents.GEORGE.getMatriculationNumber().toString())
            .withStartTime(generalTime).withDate(georgeDate).build();

    public List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(aliceAppointment, bensonAppointment, carlAppointment,
                danielAppointment, elleAppointment, fionaAppointment, georgeAppointment));
    }

    @Test
    public void execute_typicalAppointmentsWithAppointments_successful() {
        for (Appointment appt : getTypicalAppointments()) {
            expectedModel.addAppointment(appt);
        }

        String expectedOutput = "Number of upcoming appointments in the next week: 2" + "\n"
                + "Number of appointments in the past week: 1";
        StatsApptCommand statsApptCommand = new StatsApptCommand();
        assertCommandSuccess(statsApptCommand, expectedModel, expectedOutput, expectedModel);
    }

    @Test
    public void execute_typicalAppointmentsEmpty_successful() {
        String expectedOutput = "Number of upcoming appointments in the next week: 0" + "\n"
                + "Number of appointments in the past week: 0";
        StatsApptCommand statsApptCommand = new StatsApptCommand();
        assertCommandSuccess(statsApptCommand, expectedModel, expectedOutput, expectedModel);
    }
}
