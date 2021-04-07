package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.GradeBook;
import seedu.address.model.grade.Grade;

/**
 * A utility class containing a list of {@code Grade} objects to be used in tests.
 */
public class TypicalGrades {
    public static final Grade MATHS_GRADE = new GradeBuilder()
            .withSubject("Mathematics")
            .withGradedItem("Midterm Exam")
            .withGrade("A1").build();
    public static final Grade SCIENCE_GRADE = new GradeBuilder()
            .withSubject("Science")
            .withGradedItem("Lab 1")
            .withGrade("B3").build();
    public static final Grade ENGLISH_GRADE = new GradeBuilder()
            .withSubject("English")
            .withGradedItem("Final")
            .withGrade("C5").build();

    // Manually added
    public static final Grade PHYSICS_GRADE = new GradeBuilder()
            .withSubject("Physics")
            .withGradedItem("Quiz")
            .withGrade("D7").build();
    public static final Grade HISTORY_GRADE = new GradeBuilder()
            .withSubject("History")
            .withGradedItem("CA1")
            .withGrade("F9").build();

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
        return new ArrayList<>(Arrays.asList(MATHS_GRADE, SCIENCE_GRADE, ENGLISH_GRADE));
    }
}
