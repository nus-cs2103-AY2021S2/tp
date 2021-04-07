package seedu.address.model.grade;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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
        assertFalse(GradeEnum.isValidGrade("A")); // does not contain a number character after alphabet
        assertFalse(GradeEnum.isValidGrade("A+")); // contains non-alphanumeric characters
        assertFalse(GradeEnum.isValidGrade("A3")); // wrong number after corresponding alphabet
        assertFalse(GradeEnum.isValidGrade("a")); // contains alphabetic character in lower case
        assertFalse(GradeEnum.isValidGrade("AB")); // contains multiple alphabetic characters

        // valid grade [A1][A1][B3][B4][C5][C6][D7][E8][F9]
        assertTrue(GradeEnum.isValidGrade("A1"));
        assertTrue(GradeEnum.isValidGrade("A2"));
        assertTrue(GradeEnum.isValidGrade("B3"));
        assertTrue(GradeEnum.isValidGrade("B4"));
        assertTrue(GradeEnum.isValidGrade("C5"));
        assertTrue(GradeEnum.isValidGrade("C5"));
        assertTrue(GradeEnum.isValidGrade("D7"));
        assertTrue(GradeEnum.isValidGrade("E8"));
        assertTrue(GradeEnum.isValidGrade("F9"));
    }
}
