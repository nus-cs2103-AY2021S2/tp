package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.RecurringSessionBuilder;
import seedu.address.testutil.StudentBuilder;

public class AddRecurringSessionCommandTest {

    @Test
    public void constructor_nullRecurringSession_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddRecurringSessionCommand(null,
                new Name("John")));
    }

    @Test
    public void execute_recurringSessionAcceptedByModel_addSuccessful() throws Exception {
        AddRecurringSessionCommandTest.ModelStubAcceptingRecurringSessionAdded modelStub =
                new AddRecurringSessionCommandTest.ModelStubAcceptingRecurringSessionAdded();
        RecurringSession validRecurringSession = new RecurringSessionBuilder().build();
        Name existingStudentName = new Name(StudentBuilder.DEFAULT_NAME);

        CommandResult commandResult = new AddRecurringSessionCommand(validRecurringSession, existingStudentName)
                .execute(modelStub);

        assertEquals(String.format(AddRecurringSessionCommand.MESSAGE_SUCCESS, validRecurringSession),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validRecurringSession), modelStub.oneStudent.getListOfSessions());
    }

    @Test
    public void execute_noStudentOfName_throwsCommandException() {
        RecurringSession validRecurringSession = new RecurringSessionBuilder().build();
        AddRecurringSessionCommandTest.ModelStubWithRecurringSession modelStub =
                new AddRecurringSessionCommandTest.ModelStubWithRecurringSession(validRecurringSession);
        Name nonExistingStudentName = new Name("Yohoho");
        AddRecurringSessionCommand addRecurringSessionCommand =
                new AddRecurringSessionCommand(validRecurringSession, nonExistingStudentName);

        assertThrows(CommandException.class, AddRecurringSessionCommand.STUDENT_DOES_NOT_EXIST, () ->
                addRecurringSessionCommand.execute(modelStub));
    }
    @Test
    public void execute_duplicateRecurringSession_throwsCommandException() {
        RecurringSession validRecurringSession = new RecurringSessionBuilder().build();
        AddRecurringSessionCommandTest.ModelStubWithRecurringSession modelStub =
                new AddRecurringSessionCommandTest.ModelStubWithRecurringSession(validRecurringSession);
        Name existingStudentName = new Name(StudentBuilder.DEFAULT_NAME);
        AddRecurringSessionCommand addRecurringSessionCommand =
                new AddRecurringSessionCommand(validRecurringSession, existingStudentName);

        assertThrows(CommandException.class, AddRecurringSessionCommand.SESSION_ALREADY_EXIST_ERROR, () ->
                addRecurringSessionCommand.execute(modelStub));
    }

    @Test
    public void execute_overlappingRecurringSession_throwsCommandException() {
        RecurringSession validRecurringSession = new RecurringSessionBuilder().build();
        AddRecurringSessionCommandTest.ModelStubWithRecurringSession modelStub =
                new AddRecurringSessionCommandTest.ModelStubWithRecurringSession(validRecurringSession);
        Name existingStudentName = new Name(StudentBuilder.DEFAULT_NAME);
        RecurringSession overlappingRecurringSession =
                new RecurringSessionBuilder().withSessionDate("2021-01-08", "01:00")
                .withLastSessionDate("2021-01-15", "01:00")
                .withFee("10").build();

        AddRecurringSessionCommand addRecurringSessionCommand =
                new AddRecurringSessionCommand(overlappingRecurringSession, existingStudentName);

        assertThrows(CommandException.class, AddRecurringSessionCommand.SESSION_OVERLAP, () ->
                addRecurringSessionCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Student alice = new StudentBuilder().withName("Alice").build();
        Student bob = new StudentBuilder().withName("Bob").build();
        AddRecurringSessionCommand addToAliceCommand = new AddRecurringSessionCommand(
                new RecurringSessionBuilder().build(),
                alice.getName());
        AddRecurringSessionCommand addToAliceCommand2 = new AddRecurringSessionCommand(
                new RecurringSessionBuilder().withInterval("1").build(),
                alice.getName());
        AddRecurringSessionCommand addToBobCommand = new AddRecurringSessionCommand(
                new RecurringSessionBuilder().build(),
                bob.getName());

        // same object -> returns true
        assertTrue(addToAliceCommand.equals(addToAliceCommand));

        // same values -> returns true
        AddRecurringSessionCommand addToAliceCommandCopy = new AddRecurringSessionCommand(
                new RecurringSessionBuilder().build(),
                alice.getName());
        assertTrue(addToAliceCommand.equals(addToAliceCommandCopy));

        // different types -> returns false
        assertFalse(addToAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addToAliceCommand.equals(null));

        // different student -> returns false
        assertFalse(addToAliceCommand.equals(addToBobCommand));

        // diff recurring session -> return false
        assertFalse(addToAliceCommand.equals(addToAliceCommand2));
    }

    /**
     * A Model stub that contains a single student with a single recurring session.
     */
    private class ModelStubWithRecurringSession extends ModelStub {
        private final Student student;

        ModelStubWithRecurringSession(RecurringSession recurringSession) {
            requireNonNull(recurringSession);
            this.student = new StudentBuilder().build();
            student.addSessions(new RecurringSessionBuilder().build()); }

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return this.student.isSameStudent(student);
        }

        @Override
        public boolean hasOverlappingSession(RecurringSession recurringSession) {
            for (Session recurringSessionOfStudent : this.student.getListOfSessions()) {
                if (recurringSessionOfStudent instanceof RecurringSession) {
                    return recurringSessionOfStudent.isOverlapping(recurringSession);
                }
            }
            return false;
        }

        @Override
        public boolean hasName(Name name) {
            requireNonNull(name);
            return this.student.getName().equals(name);
        }

        @Override
        public boolean hasSession(Session session) {
            requireNonNull(session);
            return this.student.getListOfSessions().get(0).equals(session);
        }
    }

    /**
     * A Model stub that always accept the session being added.
     */
    private class ModelStubAcceptingRecurringSessionAdded extends ModelStub {
        private Student oneStudent = new StudentBuilder().build();

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return oneStudent.isSameStudent(student);
        }

        @Override
        public boolean hasName(Name name) {
            requireNonNull(name);
            return oneStudent.getName().equals(name);
        }

        @Override
        public boolean hasSession(Session session) {
            requireNonNull(session);
            List<Session> sessionList = oneStudent.getListOfSessions();
            for (Session sessionInList : sessionList) {
                SessionDate sessionDate = sessionInList.getSessionDate();
                if (sessionDate.equals(session.getSessionDate())) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean hasOverlappingSession(RecurringSession recurringSession) {
            for (Session recurringSessionOfStudent : oneStudent.getListOfSessions()) {
                if (recurringSessionOfStudent instanceof RecurringSession) {
                    return recurringSessionOfStudent.isOverlapping(recurringSession);
                }
            }
            return false;
        }

        @Override
        public void addSession(Name name, Session session) {
            requireNonNull(session);
            assert(name.equals(oneStudent.getName()));
            oneStudent.addSession(session);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
