package seedu.plan.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.plan.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.plan.commons.core.LogsCenter;
import seedu.plan.model.Model;
import seedu.plan.storage.JsonModule;



/**
 * Finds and list module information to the user.
 */

public class InfoCommand extends Command {
    public static final String COMMAND_WORD = "info";
    public static final String COMMAND_WORD_SINGLE_MODULE = "info_single";
    public static final String MESSAGE_SUCCESS = "Listed all modules";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all modules information or a specified one "
            + "Parameters: NONE or "
            + PREFIX_MODULE_CODE + "MODULE CODE " + "\n"
            + "Example: " + COMMAND_WORD + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CODE + "CS2030 ";

    public static final String MESSAGE_NOT_FOUND = "No such module in the module list";

    public static final String MESSAGE_FOUND = "Specified module information displayed";

    public static final Logger LOGGER = LogsCenter.getLogger(InfoCommand.class);

    private String moduleCode;


    /**
     * Creates a infocommand to find the specified {@code moduleCode} and list its information
     * @param moduleCode the modulecode of the module information to be listed
     */
    public InfoCommand (String moduleCode) {
        this.moduleCode = moduleCode.toUpperCase();
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
        LOGGER.log(Level.INFO, "----------------[EXECUTE][START][INFOCOMMAND]");
        JsonModule[] informationOfModules = model.getPlans().getModuleInfo();
        assert informationOfModules != null : "Module.json is not found";

        JsonModule foundModule = findMatchingModule(informationOfModules);
        if (noArgument()) {
            LOGGER.log(Level.INFO, "[EXECUTION SUCCESS][DISPLAY ALL MODULE INFO]");
            model.setCurrentCommand(COMMAND_WORD);
            return new CommandResult(MESSAGE_SUCCESS);
        } else if (foundModule == null) {
            LOGGER.log(Level.INFO, "[EXECUTION SUCCESS][MODULE NOT FOUND]");
            return new CommandResult(MESSAGE_NOT_FOUND);
        } else {
            LOGGER.log(Level.INFO, "[FOUND MODULE INFORMATION]");
            model.setCurrentCommand(COMMAND_WORD_SINGLE_MODULE);
            LOGGER.log(Level.INFO, "[CURRENT COMMAND SET]");
            model.getPlans().setFoundModule(foundModule);
            LOGGER.log(Level.INFO, "[EXECUTION SUCCESS][MODULE FOUND]");
            return new CommandResult(MESSAGE_FOUND);
        }

    }

    /**
     * Checks if infocommand has been given a module code to find
     * @return true if modulecode specified false otherwise
     */
    public boolean noArgument() {
        return moduleCode.equals("");
    }

    /**
     * finds the module code from the array of module information
     * @param informationOfModules the array of module information to find the module from
     * @return the module information if found otherwise a module not found message
     */
    public JsonModule findMatchingModule(JsonModule[] informationOfModules) {
        assert informationOfModules != null : "Module information not found";
        LOGGER.log(Level.INFO, "[LOOKING FOR MODULE INFORMATION]");
        for (int i = 0; i < informationOfModules.length; i++) {
            if (informationOfModules[i].getModuleCode().equals(this.moduleCode)) {
                return informationOfModules[i];
            }
        }
        LOGGER.log(Level.INFO, "[SEARCH FOR MODULE INFORMATION COMPLETE]");
        return null;
    }

    public String getModuleCode() {
        return this.moduleCode;
    }
}

