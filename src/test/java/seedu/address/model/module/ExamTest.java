package seedu.address.model.module;

import org.junit.jupiter.api.Test;
import seedu.address.model.tag.Tag;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_2;

public class ExamTest {
    private LocalDateTime date1 = LocalDateTime.parse(VALID_EXAM_DATETIME_1,
            Exam.EXAM_DATE_FORMATTER);
    private LocalDateTime date2 = LocalDateTime.parse(VALID_EXAM_DATETIME_2,
            Exam.EXAM_DATE_FORMATTER);
    private Tag tag1 = new Tag("Mod1");
    private Tag tag2 = new Tag("Mod2");
    private Exam exam1 = new Exam(date1, tag1);
    private Exam exam2 = new Exam(date2, tag2);
    private Exam exam3 = new Exam(date1, tag2);
    private Exam exam4 = new Exam(date2, tag1);

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(exam1.equals(exam1));

        // null -> returns false
        assertFalse(exam1.equals(null));

        // different instance -> returns false
        assertFalse(exam1.equals(1));

        // same attributes -> returns true
        Exam copyExam2 = new Exam(date2, tag2);
        assertTrue(exam2.equals(copyExam2));

        // different date -> returns false
        assertFalse(exam2.equals(exam3));

        // different tag -> returns false
        assertFalse(exam2.equals(exam4));
    }

    @Test
    public void isAt() {
        // same date -> returns true
        assertTrue(exam1.isAt(date1));

        // different date -> returns false
        assertFalse(exam1.isAt(date2));
        assertFalse(exam2.isAt(date1));

        // different day -> returns false
        LocalDateTime edittedDate = LocalDateTime.parse("15/05/2021 1300",
                Exam.EXAM_DATE_FORMATTER);
        assertFalse(exam3.isAt(edittedDate));

        // different month -> returns false
        edittedDate = LocalDateTime.parse("03/11/2021 1300", Exam.EXAM_DATE_FORMATTER);
        assertFalse(exam1.isAt(edittedDate));

        // different year -> returns false
        edittedDate = LocalDateTime.parse("03/05/2020 1300", Exam.EXAM_DATE_FORMATTER);
        assertFalse(exam1.isAt(edittedDate));

        // different hour -> returns false
        edittedDate = LocalDateTime.parse("03/05/2021 0100", Exam.EXAM_DATE_FORMATTER);
        assertFalse(exam3.isAt(edittedDate));

        // different minutes -> returns false
        edittedDate = LocalDateTime.parse("03/05/2021 1359", Exam.EXAM_DATE_FORMATTER);
        assertFalse(exam3.isAt(edittedDate));
    }

    @Test
    public void testToString() {
        // same object -> returns true
        assertEquals(exam1.toString(), exam1.toString());

        // same attributes -> returns true
        String expectedString = Exam.EXAM_HEADER + Exam.EXAM_DATE_FORMATTER.format(date1);
        assertEquals(expectedString, exam1.toString());

        // different header -> returns false
        expectedString = "HI " + Exam.EXAM_DATE_FORMATTER.format(date2);
        assertNotEquals(expectedString, exam2.toString());

        // different date -> returns false
        expectedString = Exam.EXAM_HEADER + Exam.EXAM_DATE_FORMATTER.format(date2);
        assertNotEquals(expectedString, exam1.toString());
    }
}
