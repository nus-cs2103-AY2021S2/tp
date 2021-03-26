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

    private final SubjectName subject;
    private final GradedItem gradedItem;
    private final GradeEnum grade;

    /**
     * Every field must be present and not null.
     */
    public Grade(SubjectName subject, GradedItem gradedItem, GradeEnum grade) {
        requireAllNonNull(subject, gradedItem, grade);
        this.subject = subject;
        this.gradedItem = gradedItem;
        this.grade = grade;
    }

    public SubjectName getSubject() {
        assert(this.subject != null);
        return this.subject;
    }

    public GradedItem getGradedItem() {
        assert(this.gradedItem != null);
        return this.gradedItem;
    }

    public GradeEnum getGrade() {
        assert(this.grade != null);
        return this.grade;
    }

    public static boolean isValidGrade(String test) {
        return GradeEnum.isValidGrade(test);
    }

    public static boolean isValidGradedItem(String test) {
        return GradedItem.isValidGradedItem(test);
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
                && Objects.equals(gradedItem, that.gradedItem);
        //&& Objects.equals(grade, that.grade);
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
