package seedu.address.testutil;

import seedu.address.model.AppointmentBook;
import seedu.address.model.GradeBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.grade.Grade;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class containing a list of {@code Grade} objects to be used in tests.
 */
public class TypicalGrades {
    public static final Grade MATHS_GRADE = new GradeBuilder()
            .withSubject("Mathematics")
            .withGradedItem("Midterm Exam")
            .withGrade("A").build();
    public static final Grade SCIENCE_GRADE = new GradeBuilder()
            .withSubject("Science")
            .withGradedItem("Lab 1")
            .withGrade("B+").build();
    public static final Grade ENGLISH_GRADE = new GradeBuilder()
            .withSubject("English")
            .withGradedItem("Final")
            .withGrade("85").build();

    private TypicalGrades() {
    } // prevents instantiation

    /**
     * Returns an {@code GradeBook} with all the typical grades.
     */
    public static GradeBook getTypicalGradeBook() {
        GradeBook gb = new GradeBook();
        for (Grade grade : getTypicalGrades()) {
            gb.addGrade(grade);
        }
        return gb;
    }

    public static List<Grade> getTypicalGrades() {
        return new ArrayList<>(Arrays.asList(MATHS_GRADE, SCIENCE_GRADE));
    }
}
