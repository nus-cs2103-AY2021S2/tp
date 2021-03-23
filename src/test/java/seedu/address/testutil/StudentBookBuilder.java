package seedu.address.testutil;

import seedu.address.model.StudentBook;
import seedu.address.model.student.Student;

/**
 * A utility class to help with building Studentbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withStudent("John", "Doe").build();}
 */
public class StudentBookBuilder {

    private StudentBook studentBook;

    public StudentBookBuilder() {
        studentBook = new StudentBook();
    }

    public StudentBookBuilder(StudentBook studentBook) {
        this.studentBook = studentBook;
    }

    /**
     * Adds a new {@code Student} to the {@code AddressBook} that we are building.
     */
    public StudentBookBuilder withStudent(Student student) {
        studentBook.addStudent(student);
        return this;
    }

    public StudentBook build() {
        return studentBook;
    }
}
