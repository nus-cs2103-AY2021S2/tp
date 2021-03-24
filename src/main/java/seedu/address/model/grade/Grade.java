package seedu.address.model.grade;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.subject.SubjectName;
import seedu.address.model.tag.Filterable;

/**
 * Represents a Grade in Tutor Tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Grade implements Filterable {
    public static final String MESSAGE_CONSTRAINTS =
            "Each Grade must contain subject name, exam name and grade.";

    /*
     * The first character of the gradedItem must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    /*
     * The first character of the grade must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * The grade must be
     */
    public static final String VALIDATION_GRADE = "[\\p{Alnum}][-+]?";

    private final SubjectName subject;
    private final String gradedItem;
    private final String grade;

    /**
     * Every field must be present and not null.
     */
    public Grade(SubjectName subject, String gradedItem, String grade) {
        requireAllNonNull(subject, gradedItem, grade);
        this.subject = subject;
        this.gradedItem = gradedItem;
        this.grade = grade;
    }

    public SubjectName getSubject() {
        assert(this.subject != null);
        return this.subject;
    }

    public String getGradedItem() {
        assert(this.gradedItem != null);
        return this.gradedItem;
    }

    public String getGrade() {
        assert(this.grade != null);
        return this.grade;
    }

    public static boolean isValidGradedItem(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static boolean isValidGrade(String test) {
        return test.matches(VALIDATION_GRADE);
    }

    @Override
    public String toString() {
        return subject.name + " - " + gradedItem + " - " + grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Grade that = (Grade) o;
        return Objects.equals(subject, that.subject)
                && Objects.equals(gradedItem, that.gradedItem)
                && Objects.equals(grade, that.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, gradedItem, grade);
    }

    @Override
    public boolean filter(String s) {
        return subject.filter(s);
    }

}
