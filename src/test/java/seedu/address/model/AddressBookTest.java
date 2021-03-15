package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BOB;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.session.Duration;
import seedu.address.model.session.Fee;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.session.Subject;
import seedu.address.model.session.exceptions.SessionException;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.testutil.StudentBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInAddressBook_returnsTrue() {
        addressBook.addStudent(ALICE);
        assertTrue(addressBook.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .build();
        assertTrue(addressBook.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getStudentList().remove(0));
    }

    @Test
    public void hasName_nameExists_returnsTrue() {
        addressBook.addStudent(ALICE);
        assertTrue(addressBook.hasName(ALICE.getName()));
    }

    @Test
    public void hasName_nameDoesNotExist_returnsFalse() {
        addressBook.addStudent(BOB);
        assertTrue(addressBook.hasName(BOB.getName()));
    }

    // The test cases here are commented out to avoid failing the storage tests
    @Test
    public void hasSession_sessionExists_returnsTrue() throws SessionException {
        Session session = new Session(new SessionDate("2020-01-01", "10:30"),
                new Duration("120"), new Subject("Math"), new Fee("100"));
        addressBook.addStudent(ALICE);
        addressBook.addSession(ALICE.getName(), session);
        assertTrue(addressBook.hasSession(ALICE.getName(), session));
    }

    @Test
    public void hasSession_sessionDoesNotExist_returnsFalse() throws SessionException {
        Session session = new Session(new SessionDate("2020-01-01", "10:30"),
                new Duration("120"), new Subject("Math"), new Fee("100"));
        Session newSession = new Session(new SessionDate("2020-01-02", "10:30"),
                new Duration("120"), new Subject("Math"), new Fee("100"));
        addressBook.addStudent(ALICE);
        addressBook.addSession(ALICE.getName(), session);
        assertFalse(addressBook.hasSession(ALICE.getName(), newSession));
    }

    @Test
    public void hasSession_sessionWithSameDateAndTimeWithDifferentFields_returnsTrue() throws SessionException {
        Session session = new Session(new SessionDate("2020-01-01", "10:30"),
                new Duration("120"), new Subject("Math"), new Fee("100"));
        Session newSession = new Session(new SessionDate("2020-01-01", "10:30"),
                new Duration("100"), new Subject("Science"), new Fee("80"));
        addressBook.addStudent(ALICE);
        addressBook.addSession(ALICE.getName(), session);
        assertTrue(addressBook.hasSession(ALICE.getName(), newSession));
    }

    /**
     * A stub ReadOnlyAddressBook whose students list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        AddressBookStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }
    }

}
