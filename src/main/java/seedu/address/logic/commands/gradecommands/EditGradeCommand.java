package seedu.address.logic.commands.gradecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADED_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GRADE;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeEnum;
import seedu.address.model.grade.GradedItem;
import seedu.address.model.subject.SubjectName;

public class EditGradeCommand extends Command {
    public static final String COMMAND_WORD = "edit_grade";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the grade identified "
            + "by the index number used in the displayed grade list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_SUBJECT_NAME + "SUBJECT] "
            + "[" + PREFIX_GRADED_ITEM + "GRADED ITEM] "
            + "[" + PREFIX_GRADE + "GRADE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_SUBJECT_NAME + "Science "
            + PREFIX_GRADED_ITEM + "Midterm"
            + PREFIX_GRADE + "A";

    public static final String MESSAGE_EDIT_GRADE_SUCCESS = "Edited Grade: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_GRADE = "This grade already exists.";

    private final Index index;
    private final EditGradeCommand.EditGradeDescriptor editGradeDescriptor;

    /**
     * @param index of the appointment in the filtered appointment list to edit
     */
    public EditGradeCommand(
            Index index,
            EditGradeCommand.EditGradeDescriptor editGradeDescriptor) {
        requireNonNull(index);
        requireNonNull(editGradeDescriptor);

        this.index = index;
        this.editGradeDescriptor = editGradeDescriptor;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredGradeList(PREDICATE_SHOW_ALL_GRADE);
        return new CommandResult(MESSAGE_EDIT_GRADE_SUCCESS);
    }

    /**
     * Creates and returns a {@code Grade} with the details of {@code gradeToEdit}
     * edited with {@code editGradeDescriptor}.
     */
    private static Grade createEditedGrade(
            Grade gradeToEdit,
            EditGradeCommand.EditGradeDescriptor editGradeDescriptor) {
        assert gradeToEdit != null;

        SubjectName updatedSubjectName = editGradeDescriptor.getSubjectName()
                .orElse(gradeToEdit.getSubject());
        GradedItem updatedGradedItem =
                editGradeDescriptor.getGradedItem().orElse(gradeToEdit.getGradedItem());
        GradeEnum updatedGrade =
                editGradeDescriptor.getGrade().orElse(gradeToEdit.getGrade());

        return new Grade(updatedSubjectName, updatedGradedItem, updatedGrade);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditGradeCommand)) {
            return false;
        }

        // state check
        EditGradeCommand e = (EditGradeCommand) other;
        return index.equals(e.index)
                && editGradeDescriptor.equals(e.editGradeDescriptor);
    }


    /**
     * Stores the details to edit the grade with. Each non-empty field value will replace the
     * corresponding field value of the grade.
     */
    public static class EditGradeDescriptor {
        private SubjectName subjectName;
        private GradedItem gradedItem;
        private GradeEnum grade;

        public EditGradeDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditGradeDescriptor(EditGradeCommand.EditGradeDescriptor toCopy) {
            setSubjectName(toCopy.subjectName);
            setGradedItem(toCopy.gradedItem);
            setGrade(toCopy.grade);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(subjectName, gradedItem, grade);
        }

        public void setSubjectName(SubjectName subjectName) {
            this.subjectName = subjectName;
        }

        public Optional<SubjectName> getSubjectName() {
            return Optional.ofNullable(subjectName);
        }

        public void setGradedItem(GradedItem gradedItem) {
            this.gradedItem = gradedItem;
        }

        public Optional<GradedItem> getGradedItem() {
            return Optional.ofNullable(this.gradedItem);
        }

        public void setGrade(GradeEnum grade) {
            this.grade = grade;
        }

        public Optional<GradeEnum> getGrade() {
            return Optional.ofNullable(this.grade);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditGradeCommand.EditGradeDescriptor)) {
                return false;
            }

            // state check
            EditGradeCommand.EditGradeDescriptor e = (EditGradeCommand.EditGradeDescriptor) other;

            return getSubjectName().equals(e.getSubjectName())
                    && getGradedItem().equals(e.getGradedItem())
                    && getGrade().equals(e.getGrade());
        }
    }
}
