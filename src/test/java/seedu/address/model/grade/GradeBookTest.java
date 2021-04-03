package seedu.address.model.grade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.GradeBook;

public class GradeBookTest {

    private final GradeBook gradeBook = new GradeBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), gradeBook.getGradeList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gradeBook.resetData(null));
    }
}
