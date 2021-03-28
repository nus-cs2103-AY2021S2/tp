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

    private LocalTime currTime = LocalTime.now();
    private String aliceStartHour = Integer.toString(currTime.getHour() + 4);
    private String aliceStartTime = aliceStartHour + ":00";

    private String bensonStartHour = Integer.toString(currTime.getHour() + 1);
    private String bensonStartTime = bensonStartHour + ":00";

    private String carlStartHour = Integer.toString(currTime.getHour() - 4);
    private String carlStartTime = carlStartHour + ":00";


    // past week 6 days ago
    public final Appointment aliceAppointment = new AppointmentBuilder()
            .withMatric(TypicalStudents.ALICE.getMatriculationNumber().toString())
            .withStartTime(aliceStartTime).withDate(aliceDate).build();

    // upcoming week same day but after current time
    public final Appointment bensonAppointment = new AppointmentBuilder()
            .withMatric(TypicalStudents.BENSON.getMatriculationNumber().toString())
            .withStartTime(bensonStartTime).withDate(bensonDate).build();

    // upcoming week 6 days later
    public final Appointment carlAppointment = new AppointmentBuilder()
            .withMatric(TypicalStudents.CARL.getMatriculationNumber().toString())
            .withStartTime(carlStartTime).withDate(carlDate).build();

    public List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(aliceAppointment, bensonAppointment, carlAppointment));
    }

    @Test
    public void execute_typicalStudents_successful() {
        for (Appointment appt : getTypicalAppointments()) {
            expectedModel.addAppointment(appt);
        }

        String expectedOutput = "Number of upcoming appointments in the next week: 2" + "\n"
                + "Number of appointments in the past week: 1";
        StatsApptCommand statsApptCommand = new StatsApptCommand();
        assertCommandSuccess(statsApptCommand, expectedModel, expectedOutput, expectedModel);
    }
}
