package seedu.address.logic.commands.deletecommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Exam;
import seedu.address.model.module.ExamList;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteExamCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the assignment identified by the index in ExamList of the module\n"
            + "Parameters: Index (must be a int value)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS2103T"
            + PREFIX_ASSIGNMENT + "1";

    public static final String MESSAGE_DELETE_EXAM_SUCCESS = "Deleted Exam: %1$s";

    private final Title moduleTitle;

    private final Index examIndex;

    /**
     * creates new DeleteAssignmentCommand object
     */
    public DeleteExamCommand(Title moduleTitle, Index examIndex) {
        requireAllNonNull(moduleTitle, examIndex);
        this.moduleTitle = moduleTitle;
        this.examIndex = examIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();
        //check for Title
        Module moduleToCheck = new Module(moduleTitle);
        if (!listContainsModule(lastShownList, moduleToCheck)) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_TITLE);
        }
        int indexOfModule = getIndex(lastShownList, moduleToCheck);
        Module moduleToGet = lastShownList.get(indexOfModule);
        ExamList examList = moduleToGet.getExams();
        if (examIndex.getZeroBased() >= examList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
        }
        Exam assignmentToDelete = moduleToGet.getExam(examIndex.getZeroBased());
        moduleToGet.deleteExam(examIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_DELETE_EXAM_SUCCESS, assignmentToDelete));
    }

    /**
     * Checks whether the module title matches any in the moduleList
     */
    public boolean listContainsModule(List<Module> moduleList, Module moduleCheck) {
        boolean hasSameModule = false;
        for (int i = 0; i < moduleList.size(); i++) {
            if (moduleCheck.isSameModule(moduleList.get(i))) {
                hasSameModule = true;
            }
        }
        return hasSameModule;
    }

    /**
     * Gets Index of module in moduleList with same title as input module
     */
    public int getIndex(List<Module> moduleList, Module moduleCheck) {
        int index = 0;
        for (int i = 0; i < moduleList.size(); i++) {
            if (moduleCheck.isSameModule(moduleList.get(i))) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteExamCommand // instanceof handles nulls
                && moduleTitle.equals(((DeleteExamCommand) other).moduleTitle)
                && examIndex.equals(((DeleteExamCommand) other).examIndex)); // state check
    }

}
