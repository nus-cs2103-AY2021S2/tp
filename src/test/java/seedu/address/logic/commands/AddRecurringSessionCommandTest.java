package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
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
        public Student getStudentWithName(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSession(Name name, Session session) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSession(Name name, Index sessionIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRecurringSession(Name name, Index sessionIndex, SessionDate sessionDate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasName(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSession(Session session) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOverlappingSession(Session session) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOverlappingSession(RecurringSession recurringSession) {
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
        public Predicate<Student> getFilteredStudentListPredicate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public double getFee(LocalDateTime startPeriod, LocalDateTime endPeriod) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single student with a single recurring session.
     */
    private class ModelStubWithRecurringSession extends AddRecurringSessionCommandTest.ModelStub {
        private final Student student;

        ModelStubWithRecurringSession(RecurringSession recurringSession) {
            requireNonNull(recurringSession);
            this.student = new StudentBuilder().build();
            List<Session> list = new ArrayList<>();
            list.add(new RecurringSessionBuilder().build());
            student.addSessions(list);
        }

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
    private class ModelStubAcceptingRecurringSessionAdded extends AddRecurringSessionCommandTest.ModelStub {
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
