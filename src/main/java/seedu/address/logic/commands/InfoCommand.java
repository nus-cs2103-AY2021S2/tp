package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.model.Model;
import seedu.address.storage.JsonModule;

/**
 * Finds and list module information to the user.
 */

public class InfoCommand extends  Command{
    public static final String COMMAND_WORD = "info";

    public static final String MESSAGE_SUCCESS = "Listed all modules";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all modules information or a specified one "
            + "Parameters: NONE or "
            + PREFIX_MODULE_CODE + "MODULE CODE " + "\n"
            + "Example: " + COMMAND_WORD + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CODE + "CS2030 ";

    public static final String MESSAGE_NOT_FOUND = "No such module in the module list";

    public static final String MESSAGE_FOUND = "Specified module information displayed";

    private String moduleCode;


    /**
     * Creates a infocommand to find the specified {@code moduleCode} and list its information
     * @param moduleCode the modulecode of the module information to be listed
     */
    public InfoCommand (String moduleCode){
        this.moduleCode = moduleCode;
    }

    /**
     * Creates an infocommand to list all module infomation
     */
    public InfoCommand() {
        this.moduleCode = "";
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        JsonModule[] informationOfModules = model.getAddressBook().getModuleInfo();

        if(noArgument()) {
            StringBuilder str = new StringBuilder();
            for(int i = 0; i < informationOfModules.length; i++) {
                str.append(informationOfModules[i].toString());
                str.append("\n\n");
            }
            return new CommandResult(str.toString() + MESSAGE_SUCCESS);
        } else {
            return findMatchingModule(informationOfModules);
        }

    }

    /**
     * Checks if infocommand has been given a module code to find
     * @return true if modulecode specified false otherwise
     */
    private boolean noArgument() {
        return moduleCode.equals("");
    }

    /**
     * finds the module code from the array of module information
     * @param informationOfModules the array of module information to find the module fomr
     * @return the module information if found otherwise a module not found message
     */
    private CommandResult findMatchingModule(JsonModule[] informationOfModules) {
        for(int i = 0; i < informationOfModules.length; i++) {
            if(informationOfModules[i].module_code.equals(this.moduleCode)) {
                return new CommandResult(informationOfModules[i].toString() + MESSAGE_FOUND);
            }
        }
        return new CommandResult(MESSAGE_NOT_FOUND);
    }
}

