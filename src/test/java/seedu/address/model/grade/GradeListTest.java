package seedu.address.model.grade;

import org.junit.jupiter.api.Test;
import seedu.address.model.grade.exceptions.DuplicateGradeException;
import seedu.address.model.grade.exceptions.GradeNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGrades.getTypicalGrades;

public class GradeListTest {

    private GradeList gradeList = new GradeList();
    private List<Grade> typicalGrades = getTypicalGrades();

    @Test
    public void execute_addDuplicateGrade_throwsDuplicateGradeException() {
        gradeList.add(typicalGrades.get(0));
        assertThrows(DuplicateGradeException.class, () -> gradeList.add(
                typicalGrades.get(0)));

    }

    @Test
    public void execute_replaceMissingGrade_throwsGradeNotFoundException() {
        assertThrows(GradeNotFoundException.class, () -> gradeList
                .setGrade(typicalGrades.get(1), typicalGrades.get(0)));
    }

    @Test
    public void equals() {
        assertFalse(gradeList.contains(typicalGrades.get(0)));

        Grade grade1 = typicalGrades.get(0);
        GradeList gradeList1 = new GradeList();
        GradeList gradeList2 = new GradeList();
        gradeList1.add(grade1);
        gradeList2.add(grade1);
        assertEquals(gradeList1, gradeList2);

        gradeList1.remove(0);
        assertFalse(gradeList1.contains(grade1));
    }
}
