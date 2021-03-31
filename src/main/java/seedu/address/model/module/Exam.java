package seedu.address.model.module;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.Event;
import seedu.address.model.tag.Tag;

public class Exam extends Event {
    public static final String MESSAGE_CONSTRAINTS = "Exam date must be formatted "
        + "to a valid DD/MM/YYYY HHmm";
    public static final DateTimeFormatter EXAM_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public static final String EXAM_HEADER = "Exam is on: ";
    public final LocalDateTime examDate;

    /**
     * Constructs an {@code Exam} with {@code examDate} as input representing the date of the Exam.
     */
    public Exam(LocalDateTime examDate, Tag tag) {
        super(new Description("exam"), examDate, tag);
        assert examDate != null;
        this.examDate = examDate;
    }

    /**
     * Checks whether {@code Exam} is on {@code localDateTime}.
     */
    public boolean isAt(LocalDateTime localDateTime) {
        assert examDate != null;
        return examDate == localDateTime;
    }

    /**
     * Sets the date of the exam to the given {@code date}.
     */
    public Exam setDate(LocalDateTime newDate) {
        return new Exam(newDate, tag);
    }

    /**
     * Returns a string representation of {@code Exam} with date of format YYYY-MM-DD HH:TT.
     */
    @Override
    public String toString() {
        return EXAM_HEADER + EXAM_DATE_FORMATTER.format(examDate);
    }

    /**
     * Returns true if {@code other} is the same instance or is instance of Exam and has the same
     * date.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Exam // instanceof handles nulls
                && examDate.equals(((Exam) other).examDate)
                && tag.equals(((Exam) other).tag)); // state check
    }

    /**
     * Returns the hashcode representation of {@code Exam}.
     */
    @Override
    public int hashCode() {
        return examDate.hashCode();
    }

}
