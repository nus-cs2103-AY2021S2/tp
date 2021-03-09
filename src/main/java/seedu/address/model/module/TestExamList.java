package seedu.address.model.module;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestExamList {
    /**
     * Test for ExamList.
     */
    public static void main(String[] args) {
        LocalDateTime localDateTime1 = LocalDateTime.of(2021, 3, 11, 23, 30);
        Exam exam1 = new Exam(localDateTime1);
        LocalDateTime localDateTime2 = LocalDateTime.of(2021, 3, 10, 11, 30);
        Exam exam2 = new Exam(localDateTime2);
        LocalDateTime localDateTime3 = LocalDateTime.of(2021, 3, 3, 1, 21);
        Exam exam3 = new Exam(localDateTime3);
        ExamList examList1 = new ExamList();
        examList1.add(exam1);
        examList1.add(exam2);
        examList1.add(exam3);

        ArrayList<Exam> exams = new ArrayList<>();
        exams.add(exam1);
        exams.add(exam2);
        exams.add(exam3);
        ExamList examList2 = new ExamList(exams);

        System.out.println(examList1);
        System.out.println(examList2);
        System.out.println(examList1.equals(examList2));

        System.out.println(examList1);
        Exam deltedExam = examList1.deleteAt(1);
        System.out.println(examList1);
        System.out.println(deltedExam);

        System.out.println(examList2);
        examList2.sort();
        System.out.println(examList2);

    }
}
