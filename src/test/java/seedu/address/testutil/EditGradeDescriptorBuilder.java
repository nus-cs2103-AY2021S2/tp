package seedu.address.testutil;

import seedu.address.logic.commands.gradecommands.EditGradeCommand;
import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeEnum;
import seedu.address.model.grade.GradedItem;
import seedu.address.model.subject.SubjectName;

public class EditGradeDescriptorBuilder {

    private EditGradeCommand.EditGradeDescriptor descriptor;

    public EditGradeDescriptorBuilder() {
        descriptor = new EditGradeCommand.EditGradeDescriptor();
    }

    public EditGradeDescriptorBuilder(EditGradeCommand.EditGradeDescriptor descriptor) {
        this.descriptor = new EditGradeCommand.EditGradeDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditGradeDescriptorBuilder} with fields containing {@code grade}'s details
     */
    public EditGradeDescriptorBuilder(Grade grade) {
        descriptor = new EditGradeCommand.EditGradeDescriptor();
        descriptor.setSubjectName(grade.getSubject());
        descriptor.setGradedItem(grade.getGradedItem());
        descriptor.setGrade(grade.getGrade());
    }

    /**
     * Sets the {@code Subject Name} of the {@code EditGradeDescriptor} that we are building.
     */
    public EditGradeDescriptorBuilder withSubject(String name) {
        descriptor.setSubjectName(new SubjectName(name));
        return this;
    }

    /**
     * Sets the {@code Graded Item} of the {@code EditGradeDescriptor} that we are building.
     */
    public EditGradeDescriptorBuilder withGradedItem(String gradedItem) {
        descriptor.setGradedItem(new GradedItem(gradedItem));
        return this;
    }

    /**
     * Sets the {@code Grade Letter} of the {@code EditGradeDescriptor} that we are building.
     */
    public EditGradeDescriptorBuilder withGrade(String gradeLetter) {
        descriptor.setGrade(GradeEnum.valueOf(gradeLetter));
        return this;
    }

    public EditGradeCommand.EditGradeDescriptor build() {
        return descriptor;
    }
}
