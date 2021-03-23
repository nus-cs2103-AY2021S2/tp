package seedu.student.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.student.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.student.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.student.commons.core.GuiSettings;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.Model;
import seedu.student.model.ReadOnlyStudentBook;
import seedu.student.model.ReadOnlyUserPrefs;
import seedu.student.model.StudentBook;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.Student;
import seedu.student.testutil.AppointmentBuilder;
import seedu.student.testutil.StudentBuilder;

public class AddAppointmentCommandTest {

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null));
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        Student validStudent = new StudentBuilder().build();
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded(validStudent);
        Appointment validAppointment = new AppointmentBuilder()
                .withMatric(validStudent.getMatriculationNumber().toString()).build();

        CommandResult commandResult = new AddAppointmentCommand(validAppointment).execute(modelStub);

        assertEquals(String.format(AddAppointmentCommand.MESSAGE_SUCCESS, validAppointment),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAppointment), modelStub.appointmentsAdded);
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() {
        Student validStudent = new StudentBuilder().build();
        Appointment validAppointment = new AppointmentBuilder()
                .withMatric(validStudent.getMatriculationNumber().toString()).build();

        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(validAppointment);
        ModelStub modelStub = new ModelStubWithStudentAndAppointment(validStudent, validAppointment);

        assertThrows(CommandException.class,
                AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT, () -> addAppointmentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Appointment appt1 = new AppointmentBuilder().withMatric("A1234567X").build();
        Appointment appt2 = new AppointmentBuilder().withMatric("A7654321Y").build();
        AddAppointmentCommand addAppt1Command = new AddAppointmentCommand(appt1);
        AddAppointmentCommand addAppt2Command = new AddAppointmentCommand(appt2);

        // same object -> returns true
        assertTrue(addAppt1Command.equals(addAppt1Command));

        // same values -> returns true
        AddAppointmentCommand addAppt1CommandCopy = new AddAppointmentCommand(appt1);
        assertTrue(addAppt1Command.equals(addAppt1CommandCopy));

        // different types -> returns false
        assertFalse(addAppt1Command.equals(1));

        // null -> returns false
        assertFalse(addAppt1Command.equals(null));

        // different person -> returns false
        assertFalse(addAppt1Command.equals(addAppt2Command));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getStudentBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudentBookFilePath(Path studentBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudentBook(ReadOnlyStudentBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyStudentBook getStudentBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOverlappingAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single appointment.
     */
    private class ModelStubWithStudentAndAppointment extends ModelStub {
        private final Student student;
        private final Appointment appointment;

        ModelStubWithStudentAndAppointment(Student student, Appointment appointment) {
            requireAllNonNull(student, appointment);
            this.student = student;
            this.appointment = appointment;
        }

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return this.student.isSameStudent(student);
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return this.appointment.isSameAppointment(appointment);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingAppointmentAdded extends ModelStub {
        final ArrayList<Student> students = new ArrayList<>();
        final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();

        public ModelStubAcceptingAppointmentAdded(Student student) {
            students.add(student);
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentsAdded.stream().anyMatch(appointment::isSameAppointment);
        }

        @Override
        public boolean hasOverlappingAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentsAdded.stream().anyMatch(appointment::doesTimeOverlap);
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            appointmentsAdded.add(appointment);
        }

        @Override
        public ReadOnlyStudentBook getStudentBook() {
            return new StudentBook();
        }
    }

}
