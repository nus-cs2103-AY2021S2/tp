package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.testutil.StudentBuilder;

public class StudentBookTest {

    private final StudentBook studentBook = new StudentBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), studentBook.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> studentBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyStudentBook_replacesData() {
        StudentBook newData = getTypicalStudentBook();
        studentBook.resetData(newData);
        assertEquals(newData, studentBook);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        StudentBookStub newData = new StudentBookStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> studentBook.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> studentBook.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInStudentBook_returnsFalse() {
        assertFalse(studentBook.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInStudentBook_returnsTrue() {
        studentBook.addStudent(ALICE);
        assertTrue(studentBook.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInStudentBook_returnsTrue() {
        studentBook.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .build();
        assertTrue(studentBook.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> studentBook.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyStudentBook whose students list can violate interface constraints.
     */
    private static class StudentBookStub implements ReadOnlyStudentBook {
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        StudentBookStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<Appointment> getAppointmentList() {
            return appointments;
        }
    }

}
