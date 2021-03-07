package seedu.address.model.module;

import java.time.LocalDateTime;

/**
 * Test class for Exam.
 */
public class ExamTest {
    /**
     * Checks that {@code Exam} instance can be initialised.
     */
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 11, 23, 30);
        Exam exam = new Exam(localDateTime);
        System.out.println(exam);
    }
}
