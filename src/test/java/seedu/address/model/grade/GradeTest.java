package seedu.address.model.grade;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADED_ITEM_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_NAME_SCIENCE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGrades.MATHS_GRADE;
import static seedu.address.testutil.TypicalGrades.SCIENCE_GRADE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GradeBuilder;

public class GradeTest {

    @Test
    public void isValidSubject() {
        // null subject
        assertThrows(NullPointerException.class, () -> Grade.isValidSubject(null));

        // invalid subject
        assertFalse(Grade.isValidSubject("")); // empty string
        assertFalse(Grade.isValidSubject(" ")); // spaces only
        assertFalse(Grade.isValidSubject("^")); // only non-alphanumeric characters
        assertFalse(Grade.isValidSubject("maths*")); // contains non-alphanumeric characters
        // max number of characters is 20 but 21 here
        assertFalse(Grade.isValidSubject("aaaaaaaaaaaaaaaaaaaaa"));

        // valid subject
        assertTrue(Grade.isValidSubject("mathematics")); // alphabets only
        assertTrue(Grade.isValidSubject("12345")); // numbers only
        assertTrue(Grade.isValidSubject("sec3 maths")); // alphanumeric characters
        assertTrue(Grade.isValidSubject("Capital Final")); // with capital letters
        assertTrue(Grade.isValidSubject("aaaaaaaaaaaaaaaaaaaa")); // contains 20 characters
    }

    @Test
    public void isSameGrade() {
        // same object -> returns true
        assertTrue(MATHS_GRADE.isSameGrade(MATHS_GRADE));

        // null -> returns false
        assertFalse(MATHS_GRADE.isSameGrade(null));

        // same subject name and graded item, different grade -> returns true
        Grade editedMaths = new GradeBuilder(MATHS_GRADE)
                .withGrade(VALID_GRADE_SCIENCE).build();
        assertTrue(MATHS_GRADE.isSameGrade(editedMaths));

        // different subject name, all other attributes same -> returns false
        editedMaths = new GradeBuilder(MATHS_GRADE)
                .withSubject(VALID_SUBJECT_NAME_SCIENCE).build();
        assertFalse(MATHS_GRADE.isSameGrade(editedMaths));

        // different graded item, all other attributes same -> returns false
        editedMaths = new GradeBuilder(MATHS_GRADE)
                .withGradedItem(VALID_GRADED_ITEM_SCIENCE).build();
        assertFalse(MATHS_GRADE.isSameGrade(editedMaths));

        // subject differs in case, all other attributes same -> returns true
        Grade editedScience = new GradeBuilder(SCIENCE_GRADE)
                .withSubject(VALID_SUBJECT_NAME_SCIENCE.toLowerCase()).build();
        assertTrue(SCIENCE_GRADE.isSameGrade(editedScience));

        // graded item differs in case, all other attributes same -> returns true
        editedScience = new GradeBuilder(SCIENCE_GRADE)
                .withGradedItem(VALID_GRADED_ITEM_SCIENCE.toLowerCase()).build();
        assertTrue(SCIENCE_GRADE.isSameGrade(editedScience));

        // subject has trailing spaces, all other attributes same -> returns false
        String subjectWithTrailingSpaces = VALID_SUBJECT_NAME_SCIENCE + " ";
        editedScience = new GradeBuilder(SCIENCE_GRADE)
                .withSubject(subjectWithTrailingSpaces).build();
        assertFalse(SCIENCE_GRADE.isSameGrade(editedScience));

        // graded item has trailing spaces, all other attributes same -> returns false
        String itemWithTrailingSpaces = VALID_GRADED_ITEM_SCIENCE + " ";
        editedScience = new GradeBuilder(SCIENCE_GRADE)
                .withGradedItem(itemWithTrailingSpaces).build();
        assertFalse(SCIENCE_GRADE.isSameGrade(editedScience));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Grade mathsCopy = new GradeBuilder(MATHS_GRADE).build();
        assertTrue(MATHS_GRADE.equals(mathsCopy));

        // same object -> returns true
        assertTrue(MATHS_GRADE.equals(MATHS_GRADE));

        // null -> returns false
        assertFalse(MATHS_GRADE.equals(null));

        // different type -> returns false
        assertFalse(MATHS_GRADE.equals(5));

        // different person -> returns false
        assertFalse(MATHS_GRADE.equals(SCIENCE_GRADE));

        // different subject name -> returns false
        Grade editedMaths = new GradeBuilder(MATHS_GRADE)
                .withSubject(VALID_SUBJECT_NAME_SCIENCE).build();
        assertFalse(MATHS_GRADE.equals(editedMaths));

        // different graded item -> returns false
        editedMaths = new GradeBuilder(MATHS_GRADE)
                .withGradedItem(VALID_GRADED_ITEM_SCIENCE).build();
        assertFalse(MATHS_GRADE.equals(editedMaths));

        // different grade -> returns false
        editedMaths = new GradeBuilder(MATHS_GRADE)
                .withGrade(VALID_GRADE_SCIENCE).build();
        assertFalse(MATHS_GRADE.equals(editedMaths));
    }
}
