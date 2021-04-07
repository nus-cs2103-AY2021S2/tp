package seedu.address.logic.commands.editcommand;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;

public class EditExamCommand extends EditCommand {

    public static final String MESSAGE_USAGE =
            "Missing necessary prefixes: m/, e/, and on/\n"
            + "Exam: edit m/TITLE e/INDEX on/EXAM DATE\n"
            + "Example: edit m/MOD1 e/1 on/03/02/2021 2359";

    public static final String MESSAGE_SUCCESS = "Exam edited: %1$s";
    public static final String MESSAGE_NO_MODULE = "This module does not exist in RemindMe";
    public static final String MESSAGE_NO_EXAM = "This module does not contain any exam at this index.";
    public static final String MESSAGE_NO_CHANGE = "The input given does not change anything!";
    public static final String MESSAGE_NO_VALID_CHANGES = "Please input a valid edit.";
    public static final String MESSAGE_DUPLICATE_EXAM = "This exam already exists in RemindMe.";

    private final Module module;
    private final int toEditIndex;
    private final LocalDateTime edit;

    /**
     * Creates an EditModuleCommand to edit the specified {@code Module}.
     */
    public EditExamCommand(Module module, int index, LocalDateTime date) {
        requireNonNull(module);
        this.module = module;
        toEditIndex = index;
        edit = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(module)) {
            throw new CommandException(MESSAGE_NO_MODULE);
        }

        if (!model.hasExam(module, toEditIndex)) {
            throw new CommandException(MESSAGE_NO_EXAM);
        }

        Module targetMod = model.getModule(module);
        Exam target = targetMod.getExam(toEditIndex - 1);

        boolean hasSameDate = target.examDate.equals(edit);

        if (hasSameDate) {
            throw new CommandException(MESSAGE_NO_CHANGE);
        }

        if (isNull(edit)) {
            throw new CommandException(MESSAGE_NO_VALID_CHANGES);
        }

        target = target.setDate(edit);
        if (model.hasExam(module, target)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXAM);
        }

        model.editExam(module, toEditIndex, edit);

        Module editedMod = model.getModule(module);
        Exam edited = editedMod.getExam(toEditIndex - 1);

        return new CommandResult(String.format(MESSAGE_SUCCESS, edited));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof EditExamCommand)) {
            return false;
        }
        boolean sameEdit;
        if (isNull(edit)) {
            sameEdit = isNull(((EditExamCommand) other).edit);
        } else {
            sameEdit = edit.equals(((EditExamCommand) other).edit);
        }
        return other == this
                || other instanceof EditExamCommand
                && module.equals(((EditExamCommand) other).module)
                && toEditIndex == ((EditExamCommand) other).toEditIndex
                && sameEdit;
    }
}

