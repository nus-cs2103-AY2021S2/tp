package seedu.address.logic.commands.deletecommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;

/**
 * Deletes a person identified using it's displayed index from the remindMe.
 */
public class DeleteModuleCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the module identified by the title used list of modules\n"
            + "Parameters: Title (must be a string value)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + " CS2103T";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";

    private final Title moduleTitle;

    public DeleteModuleCommand(Title moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        //check for Title
        Module moduleToCheck = new Module(moduleTitle);
        System.out.println(lastShownList);
        System.out.println(moduleToCheck.toString());
        if (!listContainsModule(lastShownList, moduleToCheck)) {
            System.out.println("Nope");
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_TITLE);
        }
        int indexOfModule = getIndex(lastShownList, moduleToCheck);
        System.out.println("index: " + indexOfModule);
        Module moduleToDelete = lastShownList.get(indexOfModule);
        model.deleteModule(moduleToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete));
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
                || (other instanceof DeleteModuleCommand // instanceof handles nulls
                && moduleTitle.equals(((DeleteModuleCommand) other).moduleTitle)); // state check
    }
}
