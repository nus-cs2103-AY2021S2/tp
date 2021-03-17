package seedu.address.model.module;

import org.junit.jupiter.api.Test;
import seedu.address.model.tag.Tag;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_2;

public class ExamListTest {
    private ExamList EXAM_LIST_1 = new ExamList();
    private ExamList EXAM_LIST_2 = new ExamList();
    private LocalDateTime DATE_1 = LocalDateTime.parse(VALID_EXAM_DATETIME_1,
            Exam.EXAM_DATE_FORMATTER);
    private LocalDateTime DATE_2 = LocalDateTime.parse(VALID_EXAM_DATETIME_2,
            Exam.EXAM_DATE_FORMATTER);
    private Exam EXAM_1 = new Exam(DATE_1, new Tag("mod1"));
    private Exam EXAM_2 = new Exam(DATE_2, new Tag("mod2"));

    @Test
    public void add() {
        // empty list add one exam -> returns true
        EXAM_LIST_1.add(EXAM_1);
        EXAM_LIST_2.add(EXAM_2);
        assertTrue(EXAM_LIST_1.size() == 1);
        assertTrue(EXAM_LIST_2.size() == 1);

        // size of list is 2
        EXAM_LIST_1.add(EXAM_2);
        assertTrue(EXAM_LIST_1.size() == 2);
    }

    @Test
    public void equals() {
        EXAM_LIST_1.add(EXAM_1);
        EXAM_LIST_1.add(EXAM_2);

        List<Exam> exams = new ArrayList<>();
        exams.add(EXAM_2);
        exams.add(EXAM_1);
        EXAM_LIST_2 = new ExamList(exams);

        // same object -> returns true
        assertTrue(EXAM_LIST_1.equals(EXAM_LIST_1));

        // null -> returns false
        assertFalse(EXAM_LIST_1.equals(null));

        // same attributes -> returns true
        ExamList copyExamList1 = new ExamList();
        copyExamList1.add(EXAM_1);
        copyExamList1.add(EXAM_2);
        assertTrue(EXAM_LIST_1.equals(copyExamList1));

        // different lists -> returns false
        assertFalse(EXAM_LIST_1.equals(EXAM_LIST_2));

        // different sequence -> returns false
        copyExamList1 = new ExamList();
        copyExamList1.add(EXAM_2);
        copyExamList1.add(EXAM_1);
        assertFalse(EXAM_LIST_1.equals(copyExamList1));

        // different size -> returns false
        copyExamList1.add(EXAM_1);
        assertFalse(EXAM_LIST_1.equals(copyExamList1));

        // different instances -> return false
        assertFalse(EXAM_LIST_1.equals(1));
    }

    @Test
    public void contains() {
        // empty list -> returns false
        assertFalse(EXAM_LIST_1.contains(EXAM_1));

        // null -> returns false
        assertFalse(EXAM_LIST_1.contains(null));

        // list contains exam -> returns true
        EXAM_LIST_1.add(EXAM_1);
        assertTrue(EXAM_LIST_1.contains(EXAM_1));

        // list doesn't contain exam -> returns false
        assertFalse(EXAM_LIST_1.contains(EXAM_2));

        // list with multiple exams
        EXAM_LIST_1.add(EXAM_2);

        // list contains exams
        assertTrue(EXAM_LIST_1.contains(EXAM_1));
        assertTrue(EXAM_LIST_1.contains(EXAM_2));
    }

    @Test
    public void getIndex() {
        // empty list -> returns -1
        assertTrue(EXAM_LIST_1.getIndex(EXAM_1) == -1);

        // null -> returns -1
        EXAM_LIST_1.add(EXAM_1);
        assertTrue(EXAM_LIST_1.getIndex(null) == -1);

        // has exam -> returns 0
        assertTrue(EXAM_LIST_1.getIndex(EXAM_1) == 0);

        // doesn't have exam -> returns -1
        assertTrue(EXAM_LIST_1.getIndex(EXAM_2) == -1);

        // multiple exams
        EXAM_LIST_1.add(EXAM_2);

        // has exam at index 0 -> returns 0
        assertTrue(EXAM_LIST_1.getIndex(EXAM_1) == 0);

        // has exam at index 1 -> return 1
        assertTrue(EXAM_LIST_1.getIndex(EXAM_2) == 1);
    }

    @Test
    public void deleteAt() {
        EXAM_LIST_1.add(EXAM_1);

        // remove exam at index 0 -> returns true
        assertTrue(EXAM_LIST_1.deleteAt(0).equals(EXAM_1));
        assertTrue(EXAM_LIST_1.size() == 0);

        // remove exam at index 1 -> returns true
        EXAM_LIST_1.add(EXAM_1);
        EXAM_LIST_1.add(EXAM_2);
        assertTrue(EXAM_LIST_1.deleteAt(1).equals(EXAM_2));
        assertTrue(EXAM_LIST_1.size() == 1);
    }

    @Test
    public void delete() {
        EXAM_LIST_1.add(EXAM_1);
        EXAM_LIST_1.add(EXAM_2);

        // remove exam at index 1 -> returns true
        assertTrue(EXAM_LIST_1.delete(EXAM_2).equals(EXAM_2));

        // remove exam at index 0 -> returns true
        assertTrue(EXAM_LIST_1.delete(EXAM_1).equals(EXAM_1));
    }

    @Test
    public void find() {
        // empty list -> returns true
        assertTrue(EXAM_LIST_1.find(DATE_1).equals(new ExamList()));

        // null -> returns true
        assertTrue(EXAM_LIST_1.find(null).equals(new ExamList()));

        // list has the exam with the date and time -> returns true
        EXAM_LIST_1.add(EXAM_1);
        assertTrue(EXAM_LIST_1.find(DATE_1).equals(EXAM_LIST_1));

        // list had no exam with the date and time -> returns false
        assertFalse(EXAM_LIST_1.find(DATE_2).equals(EXAM_LIST_1));

        // list has multiple exams with the date time -> returns true
        EXAM_LIST_1.add(EXAM_1);
        EXAM_LIST_1.add(EXAM_2);
        EXAM_LIST_2.add(EXAM_1);
        EXAM_LIST_2.add(EXAM_1);
        assertTrue(EXAM_LIST_1.find(DATE_1).equals(EXAM_LIST_2));
    }

    @Test
    public void get() {
        EXAM_LIST_1.add(EXAM_1);

        // exam at index 0 -> returns true
        assertTrue(EXAM_LIST_1.get(0).equals(EXAM_1));

        // exam at index 1 -> returns true
        EXAM_LIST_1.add(EXAM_2);
        assertTrue(EXAM_LIST_1.get(1).equals(EXAM_2));
    }

    @Test
    public void hasNoExam() {
        // empty list -> return true
        assertTrue(EXAM_LIST_1.hasNoExam());

        // non-empty list -> returns false
        EXAM_LIST_1.add(EXAM_2);
        assertFalse(EXAM_LIST_1.hasNoExam());
    }

    @Test
    public void getExam() {
        List<Exam> exams = new ArrayList<>();

        // empty list -> returns true
        assertTrue(EXAM_LIST_1.getExams().equals(exams));

        // has one exam -> returns true
        EXAM_LIST_1.add(EXAM_1);
        exams.add(EXAM_1);
        assertTrue(EXAM_LIST_1.getExams().equals(exams));

        // has two exams -> returns true
        EXAM_LIST_1.add(EXAM_2);
        exams.add(EXAM_2);
        assertTrue(EXAM_LIST_1.getExams().equals(exams));
    }

    @Test
    public void getExamAt() {
        EXAM_LIST_1.add(EXAM_1);
        EXAM_LIST_1.add(EXAM_2);

        // exam at index 0 -> returns true
        assertTrue(EXAM_LIST_1.getExamAt(0).equals(EXAM_1));

        // exam at index 1 -> returns true
        assertTrue(EXAM_LIST_1.getExamAt(1).equals(EXAM_2));
    }

    @Test
    public void testToString() {
       // empty list -> returns true
       assertTrue(EXAM_LIST_1.toString().equals(ExamList.NO_EXAMS_OUTPUT));

       // non-empty list -> returns true
        EXAM_LIST_1.add(EXAM_1);
        EXAM_LIST_1.add(EXAM_2);
        String output = "Exams: \n"
                + "1. " + EXAM_1.toString() + "\n"
                + "2. " + EXAM_2.toString() + "\n";
        assertTrue(EXAM_LIST_1.toString().equals(output));
    }
}
