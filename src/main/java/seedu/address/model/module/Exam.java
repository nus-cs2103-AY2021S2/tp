package seedu.address.model.module;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.Event;
import seedu.address.model.tag.Tag;

public class Exam extends Event {
    // todo change message constraints
    public static final String MESSAGE_CONSTRAINTS = "Exam names should be numerical and not "
            + "empty. It should follow a valid DD/MM/YYYY HHMM";
    public static final String VALIDATION_REGEX = "^[0-9]{4}-[01-12]{2}-[00-31]{2}T[00-23]{2}:[00-59]{2}$";
    public static final DateTimeFormatter EXAM_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public final LocalDateTime examDate;

    /**
     * Constructs an {@code Exam} with {@code examDate} as input representing the date of the Exam.
     */
    public Exam(LocalDateTime examDate, Tag tag) {
        //checkArgument(isValidExam(examDate.toString()), MESSAGE_CONSTRAINTS);
        super(new Description("exam"), examDate, tag);
        this.examDate = examDate;
    }

    /**
     * Returns true if a given string is a valid assignment.
     */
    //todo I cant get the regex to work. Do we need this? Since localdatetime catches issues with
    // wrong dates and time format.
    public static boolean isValidExam(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean isAt(LocalDateTime localDateTime) {
        return examDate == localDateTime;
    }

    /**
     * Returns a string representation of {@code Exam} with date of format YYYY-MM-DD HH:TT.
     */
    @Override
    public String toString() {
        return "Exam is on: " + EXAM_DATE_FORMATTER.format(examDate);
    }

    /**
     * Returns true if {@code other} is the same instance or is instance of Exam and has the same
     * date.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Exam // instanceof handles nulls
                && examDate.equals(((Exam) other).examDate)); // state check
    }

    /**
     * Returns the hashcode representation of {@code Exam}.
     */
    @Override
    public int hashCode() {
        return examDate.hashCode();
    }

}
