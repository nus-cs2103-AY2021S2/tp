package seedu.address.logic.commands.deletecommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.AssignmentList;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteAssignmentCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the assignment identified by the index in Assignment list of the module\n"
            + "Parameters: Index (must be a int value)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS2103T"
            + PREFIX_ASSIGNMENT + "1";

    public static final String MESSAGE_DELETE_ASSIGNMENT_SUCCESS = "Deleted Assignment: %1$s";

    private final Title moduleTitle;

    private final Index assignmentIndex;

    /**
     * creates new DeleteAssignmentCommand object
     * @param moduleTitle
     * @param assignmentIndex
     */
    public DeleteAssignmentCommand(Title moduleTitle, Index assignmentIndex) {
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
        Assignment assignmentToDelete = moduleToGet.getAssignment(assignmentIndex.getZeroBased());
        moduleToGet.deleteAssignment(assignmentIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_DELETE_ASSIGNMENT_SUCCESS, assignmentToDelete));
    }

    /**
     * Checks whether the module title matches any in the moduleList
     * @param moduleList
     * @param moduleCheck
     * @return
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
     * @param moduleList
     * @param moduleCheck
     * @return
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
                || (other instanceof DeleteAssignmentCommand // instanceof handles nulls
                && moduleTitle.equals(((DeleteAssignmentCommand) other).moduleTitle)
                && assignmentIndex.equals(((DeleteAssignmentCommand) other).assignmentIndex)); // state check
    }

}
