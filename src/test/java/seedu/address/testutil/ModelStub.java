package seedu.address.testutil;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
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
