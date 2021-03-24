package seedu.address.logic.commands.editcommand;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.time.LocalDateTime;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;

public class EditExamCommand extends EditCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an exam in RemindMe."
            + "Parameters: "
            + PREFIX_MODULE + " MODULE TITLE "
            + PREFIX_EXAM + " EXAM INDEX "
            + PREFIX_DATE + " NEW DATE"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS2103T"
            + PREFIX_EXAM + "1"
            + PREFIX_DATE + "22/03/2021 2359";

    public static final String MESSAGE_SUCCESS = "Exam edited: %1$s";
    public static final String MESSAGE_NO_MODULE = "This module does not exists in RemindMe";
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
        return other == this
                || (other instanceof EditAssignmentCommand)
                && module.equals(((EditExamCommand) other).module)
                && toEditIndex == ((EditExamCommand) other).toEditIndex
                && edit.equals(((EditExamCommand) other).edit);
    }
}

