package seedu.address.model.grade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class GradeEnumTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> GradeEnum.valueOf(null));
    }

    @Test
    public void constructor_invalidGradeEnum_throwsIllegalArgumentException() {
        String invalidGradedItem = "";
        assertThrows(IllegalArgumentException.class, () -> GradeEnum.valueOf(invalidGradedItem));
    }

    @Test
    public void isValidGrade() {

        // invalid grade
        assertFalse(GradeEnum.isValidGrade("")); // empty string
        assertFalse(GradeEnum.isValidGrade(" ")); // spaces only
        assertFalse(GradeEnum.isValidGrade("^")); // only non-alphabetic characters
        assertFalse(GradeEnum.isValidGrade("A+")); // contains non-alphabetic characters
        assertFalse(GradeEnum.isValidGrade("A1")); // contains non-alphanumeric characters
        assertFalse(GradeEnum.isValidGrade("a")); // contains alphabetic character in lower case
        assertFalse(GradeEnum.isValidGrade("AB")); // contains multiple alphabetic characters

        // valid grade [A...F][S][U]
        assertTrue(GradeEnum.isValidGrade("A"));
        assertTrue(GradeEnum.isValidGrade("B"));
        assertTrue(GradeEnum.isValidGrade("C"));
        assertTrue(GradeEnum.isValidGrade("D"));
        assertTrue(GradeEnum.isValidGrade("E"));
        assertTrue(GradeEnum.isValidGrade("F"));
        assertTrue(GradeEnum.isValidGrade("S"));
        assertTrue(GradeEnum.isValidGrade("U"));
    }
}
