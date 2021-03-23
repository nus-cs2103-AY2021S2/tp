package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_AMY_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_BOB_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_AMY_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_BOB_APPOINTMENT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.StudentBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Appointment ALICE_APPOINTMENT = new AppointmentBuilder()
            .withMatric(TypicalStudents.ALICE.getMatriculationNumber().toString())
            .withStartTime("10:00").withEndTime("10:30").build();
    public static final Appointment BENSON_APPOINTMENT = new AppointmentBuilder()
            .withMatric(TypicalStudents.BENSON.getMatriculationNumber().toString())
            .withStartTime("10:30").withEndTime("11:00").build();
    public static final Appointment CARL_APPOINTMENT = new AppointmentBuilder()
            .withMatric(TypicalStudents.BENSON.getMatriculationNumber().toString())
            .withStartTime("11:00").withEndTime("11:30").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Appointment AMY_APPOINTMENT = new AppointmentBuilder().withMatric(VALID_MATRIC_AMY)
            .withDate(VALID_DATE_AMY_APPOINTMENT).withStartTime(VALID_START_TIME_AMY_APPOINTMENT)
            .withEndTime(VALID_END_TIME_AMY_APPOINTMENT).build();
    public static final Appointment BOB_APPOINTMENT = new AppointmentBuilder().withMatric(VALID_MATRIC_BOB)
            .withDate(VALID_DATE_BOB_APPOINTMENT).withStartTime(VALID_START_TIME_BOB_APPOINTMENT)
            .withEndTime(VALID_END_TIME_BOB_APPOINTMENT).build();

    private TypicalAppointments() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical appointments.
     */
    public static StudentBook getTypicalStudentBook() {
        StudentBook ab = new StudentBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        for (Appointment appointment : getTypicalAppointments()) {
            ab.addAppointment(appointment);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(TypicalStudents.ALICE, TypicalStudents.BENSON, TypicalStudents.CARL));
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(ALICE_APPOINTMENT, BENSON_APPOINTMENT, CARL_APPOINTMENT));
    }
}
