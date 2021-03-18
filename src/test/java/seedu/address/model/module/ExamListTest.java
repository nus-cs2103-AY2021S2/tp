package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class ExamListTest {
    private ExamList examList1 = new ExamList();
    private ExamList examList2 = new ExamList();
    private LocalDateTime date1 = LocalDateTime.parse(VALID_EXAM_DATETIME_1,
            Exam.EXAM_DATE_FORMATTER);
    private LocalDateTime date2 = LocalDateTime.parse(VALID_EXAM_DATETIME_2,
            Exam.EXAM_DATE_FORMATTER);
    private Exam exam1 = new Exam(date1, new Tag("mod1"));
    private Exam exam2 = new Exam(date2, new Tag("mod2"));

    @Test
    public void add() {
        // empty list add one exam -> returns true
        examList1.add(exam1);
        examList2.add(exam2);
        assertTrue(examList1.size() == 1);
        assertTrue(examList2.size() == 1);

        // size of list is 2
        examList1.add(exam2);
        assertTrue(examList1.size() == 2);
    }

    @Test
    public void equals() {
        examList1.add(exam1);
        examList1.add(exam2);

        List<Exam> exams = new ArrayList<>();
        exams.add(exam2);
        exams.add(exam1);
        examList2 = new ExamList(exams);

        // same object -> returns true
        assertTrue(examList1.equals(examList1));

        // null -> returns false
        assertFalse(examList1.equals(null));

        // same attributes -> returns true
        ExamList copyExamList1 = new ExamList();
        copyExamList1.add(exam1);
        copyExamList1.add(exam2);
        assertTrue(examList1.equals(copyExamList1));

        // different lists -> returns false
        assertFalse(examList1.equals(examList2));

        // different sequence -> returns false
        copyExamList1 = new ExamList();
        copyExamList1.add(exam2);
        copyExamList1.add(exam1);
        assertFalse(examList1.equals(copyExamList1));

        // different size -> returns false
        copyExamList1.add(exam1);
        assertFalse(examList1.equals(copyExamList1));

        // different instances -> return false
        assertFalse(examList1.equals(1));
    }

    @Test
    public void contains() {
        // empty list -> returns false
        assertFalse(examList1.contains(exam1));

        // null -> returns false
        assertFalse(examList1.contains(null));

        // list contains exam -> returns true
        examList1.add(exam1);
        assertTrue(examList1.contains(exam1));

        // list doesn't contain exam -> returns false
        assertFalse(examList1.contains(exam2));

        // list with multiple exams
        examList1.add(exam2);

        // list contains exams
        assertTrue(examList1.contains(exam1));
        assertTrue(examList1.contains(exam2));
    }

    @Test
    public void getIndex() {
        // empty list -> returns -1
        assertTrue(examList1.getIndex(exam1) == -1);

        // null -> returns -1
        examList1.add(exam1);
        assertTrue(examList1.getIndex(null) == -1);

        // has exam -> returns 0
        assertTrue(examList1.getIndex(exam1) == 0);

        // doesn't have exam -> returns -1
        assertTrue(examList1.getIndex(exam2) == -1);

        // multiple exams
        examList1.add(exam2);

        // has exam at index 0 -> returns 0
        assertTrue(examList1.getIndex(exam1) == 0);

        // has exam at index 1 -> return 1
        assertTrue(examList1.getIndex(exam2) == 1);
    }

    @Test
    public void deleteAt() {
        examList1.add(exam1);

        // remove exam at index 0 -> returns true
        assertTrue(examList1.deleteAt(0).equals(exam1));
        assertTrue(examList1.size() == 0);

        // remove exam at index 1 -> returns true
        examList1.add(exam1);
        examList1.add(exam2);
        assertTrue(examList1.deleteAt(1).equals(exam2));
        assertTrue(examList1.size() == 1);
    }

    @Test
    public void delete() {
        examList1.add(exam1);
        examList1.add(exam2);

        // remove exam at index 1 -> returns true
        assertTrue(examList1.delete(exam2).equals(exam2));

        // remove exam at index 0 -> returns true
        assertTrue(examList1.delete(exam1).equals(exam1));
    }

    @Test
    public void find() {
        // empty list -> returns true
        assertTrue(examList1.find(date1).equals(new ExamList()));

        // null -> returns true
        assertTrue(examList1.find(null).equals(new ExamList()));

        // list has the exam with the date and time -> returns true
        examList1.add(exam1);
        assertTrue(examList1.find(date1).equals(examList1));

        // list had no exam with the date and time -> returns false
        assertFalse(examList1.find(date2).equals(examList1));

        // list has multiple exams with the date time -> returns true
        examList1.add(exam1);
        examList1.add(exam2);
        examList2.add(exam1);
        examList2.add(exam1);
        assertTrue(examList1.find(date1).equals(examList2));
    }

    @Test
    public void get() {
        examList1.add(exam1);

        // exam at index 0 -> returns true
        assertTrue(examList1.get(0).equals(exam1));

        // exam at index 1 -> returns true
        examList1.add(exam2);
        assertTrue(examList1.get(1).equals(exam2));
    }

    @Test
    public void hasNoExam() {
        // empty list -> return true
        assertTrue(examList1.hasNoExam());

        // non-empty list -> returns false
        examList1.add(exam2);
        assertFalse(examList1.hasNoExam());
    }

    @Test
    public void getExam() {
        List<Exam> exams = new ArrayList<>();

        // empty list -> returns true
        assertTrue(examList1.getExams().equals(exams));

        // has one exam -> returns true
        examList1.add(exam1);
        exams.add(exam1);
        assertTrue(examList1.getExams().equals(exams));

        // has two exams -> returns true
        examList1.add(exam2);
        exams.add(exam2);
        assertTrue(examList1.getExams().equals(exams));
    }

    @Test
    public void getExamAt() {
        examList1.add(exam1);
        examList1.add(exam2);

        // exam at index 0 -> returns true
        assertTrue(examList1.getExamAt(0).equals(exam1));

        // exam at index 1 -> returns true
        assertTrue(examList1.getExamAt(1).equals(exam2));
    }

    @Test
    public void testToString() {
        // empty list -> returns true
        assertTrue(examList1.toString().equals(ExamList.NO_EXAMS_OUTPUT));

        // non-empty list -> returns true
        examList1.add(exam1);
        examList1.add(exam2);
        String output = "Exams: \n"
                + "1. " + exam1.toString() + "\n"
                + "2. " + exam2.toString() + "\n";
        assertTrue(examList1.toString().equals(output));
    }
}
