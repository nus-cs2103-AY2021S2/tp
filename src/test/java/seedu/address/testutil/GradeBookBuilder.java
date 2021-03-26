package seedu.address.testutil;

import seedu.address.model.GradeBook;
import seedu.address.model.grade.Grade;

/**
 * A utility class to help with building GradeBook objects.
 * Example usage: <br>
 *     {@code GradeBook ab = new GradeBookBuilder().withGrade("John", "Doe").build();}
 */
public class GradeBookBuilder {

    private GradeBook gradeBook;

    public GradeBookBuilder() {
        gradeBook = new GradeBook();
    }

    public GradeBookBuilder(GradeBook gradeBook) {
        this.gradeBook = gradeBook;
    }

    /**
     * Adds a new {@code Grade} to the {@code GradeBook} that we are building.
     */
    public GradeBookBuilder withGrade(Grade grade) {
        gradeBook.addGrade(grade);
        return this;
    }

    public GradeBook build() {
        return gradeBook;
    }
}
