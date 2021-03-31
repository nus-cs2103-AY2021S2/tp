package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.module.Exam;
import seedu.address.model.tag.Tag;

public class ExamBuilder {
    public static final LocalDateTime DEFAULT_EXAM_DATE = LocalDateTime.of(2021, 04, 24, 9, 00);
    public static final String DEFAULT_TAG = "tag";

    private LocalDateTime examDate;
    private Tag tag;

    /**
     * Creates a {@code ExamBuilder} with default details;
     */
    public ExamBuilder() {
        this.examDate = DEFAULT_EXAM_DATE;
        this.tag = new Tag(DEFAULT_TAG);
    }

    /**
     * Sets the date of the {@code Exam} that we are building.
     */
    public ExamBuilder withExamDate(LocalDateTime examDate) {
        this.examDate = examDate;
        return this;
    }

    /**
     * Sets the {@code Tag} of the {@code Exam} that we are building.
     */
    public ExamBuilder withTag(String tag) {
        this.tag = new Tag(tag);
        return this;
    }

    public Exam build() {
        return new Exam(examDate, tag);
    }
}
