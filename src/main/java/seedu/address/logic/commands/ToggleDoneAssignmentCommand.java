package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.AssignmentList;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;

public class ToggleDoneAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_DONE_TOGGLE_SUCCESS = "Assignment updated done status: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": toggles the done status of the assignment identified by the index in AssignmentList of the module\n"
            + "Parameters: Index (must be a int value)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS2103T"
            + PREFIX_ASSIGNMENT + "1";

    private final Title moduleTitle;

    private final Index assignmentIndex;

    /**
     * creates new DeleteAssignmentCommand object
     */
    public ToggleDoneAssignmentCommand(Title moduleTitle, Index assignmentIndex) {
        requireAllNonNull(moduleTitle, assignmentIndex);
        this.moduleTitle = moduleTitle;
        this.assignmentIndex = assignmentIndex;
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
        AssignmentList assignmentList = moduleToGet.getAssignments();
        if (assignmentIndex.getZeroBased() >= assignmentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
        }
        Assignment assignmentToToggleDoneStatus = moduleToGet.getAssignment(assignmentIndex.getZeroBased());
        moduleToGet.toggleAssignmentDoneStatus(assignmentIndex.getZeroBased());

        return new CommandResult(String.format(MESSAGE_DONE_TOGGLE_SUCCESS, assignmentToToggleDoneStatus));
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
                || (other instanceof ToggleDoneAssignmentCommand // instanceof handles nulls
                && moduleTitle.equals(((ToggleDoneAssignmentCommand) other).moduleTitle)
                && assignmentIndex.equals(((ToggleDoneAssignmentCommand) other).assignmentIndex)); // state check
    }


}
