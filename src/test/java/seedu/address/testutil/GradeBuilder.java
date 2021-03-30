package seedu.address.testutil;

import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeEnum;
import seedu.address.model.grade.GradedItem;
import seedu.address.model.subject.SubjectName;

/**
 * A utility class to help with building Grade objects.
 */
public class GradeBuilder {

    public static final String DEFAULT_SUBJECT = "Mathematics";
    public static final String DEFAULT_GRADED_ITEM = "Final";
    public static final String DEFAULT_GRADE = "A";

    private SubjectName subjectName;
    private GradedItem gradedItem;
    private GradeEnum grade;

    /**
     * Creates a {@code GradeBuilder} with the default details.
     */
    public GradeBuilder() {
        subjectName = new SubjectName(DEFAULT_SUBJECT);
        gradedItem = new GradedItem(DEFAULT_GRADED_ITEM);
        grade = GradeEnum.valueOf(DEFAULT_GRADE);
    }

    /**
     * Initializes the GradeBuilder with the data of {@code gradeToCopy}.
     */
    public GradeBuilder(Grade gradeToCopy) {
        subjectName = gradeToCopy.getSubject();
        gradedItem = gradeToCopy.getGradedItem();
        grade = gradeToCopy.getGrade();
    }

    /**
     * Sets the {@code Subject} of the {@code Grade} that we are building.
     */
    public GradeBuilder withSubject(String subject) {
        this.subjectName = new SubjectName(subject);
        return this;
    }

    /**
     * Sets the {@code GradedItem} of the {@code Grade} that we are building.
     */
    public GradeBuilder withGradedItem(String gradedItem) {
        this.gradedItem = new GradedItem(gradedItem);
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code Grade} that we are building.
     */
    public GradeBuilder withGrade(String grade) {
        this.grade = GradeEnum.valueOf(grade);
        return this;
    }

    public Grade build() {
        return new Grade(subjectName, gradedItem, grade);
    }
}
