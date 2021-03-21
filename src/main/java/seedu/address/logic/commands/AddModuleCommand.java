package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAN_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEM_NUMBER;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.plan.Module;
import seedu.address.storage.JsonModule;

public class AddModuleCommand extends Command {
    public static final String COMMAND_WORD = "addm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a module to this semester. "
            + "Parameters: "
            + PREFIX_PLAN_NUMBER + "PLAN NUMBER "
            + PREFIX_SEM_NUMBER + "SEM NUMBER "
            + PREFIX_MODULE_CODE + "MODULE CODE"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PLAN_NUMBER + "1 "
            + PREFIX_SEM_NUMBER + "2 "
            + PREFIX_MODULE_CODE + "CS1010S";

    public static final String MESSAGE_SUCCESS = "New module added to Plan %1$s\t Semester: %2$s\t Module Code: %3$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exist in this current semester";
    public static final String MESSAGE_INVALID_MODULE_CODE = "The module code provided is not recognised.";
    public static final String MESSAGE_INVALID_GRADE = "The grade provided is invalid.";

    private final Index planIndex;
    private final Index semIndex;
    private final String moduleCode;
    private final String grade;

    /**
     * Create a addmodulecommand object with plan index, sem index and module code
     * grade is set to "null"
     */
    public AddModuleCommand(Index planIndex, Index semIndex, String moduleCode) {
        //this.planIndex = planIndex;
        //this.semIndex = semIndex;
        //this.moduleCode = moduleCode;
        this(planIndex, semIndex, moduleCode, "null");
    }

    /**
     * Create a addmodulecommand object with plan index, sem index and module code and grade
     */
    public AddModuleCommand(Index planIndex, Index semIndex, String moduleCode, String grade) {
        this.planIndex = planIndex;
        this.semIndex = semIndex;
        this.moduleCode = moduleCode;
        this.grade = grade;
    }

    /**
     * Returns a command result from add module command.
     *
     * @param model USER'S MODEL.
     * @return add module command result.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // 1. Read in all modules
        JsonModule[] informationOfModules = model.getPlans().getModuleInfo();
        // 2. Check if the moduleCode is valid
        Module matchingModule = findMatchingModule(informationOfModules, moduleCode);
        // 3. Use ModelManager::addModule(int, int, Module)
        if (matchingModule == null) {
            throw new CommandException(MESSAGE_INVALID_MODULE_CODE);
        }
        // 4. Use modelManager::hasModule to check if it already exists.
        if (model.hasModule(planIndex.getZeroBased(), semIndex.getZeroBased(), matchingModule)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        if (this.grade.equals("null")) {
            model.addModule(planIndex.getZeroBased(), semIndex.getZeroBased(), matchingModule);
        } else {
            if (!model.isValidGrade(grade)) {
                throw new CommandException(MESSAGE_INVALID_GRADE);
            }
            Module moduleWithGrade = matchingModule.setGrade(this.grade);
            model.addModule(planIndex.getZeroBased(), semIndex.getZeroBased(), moduleWithGrade);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, planIndex.toString(), semIndex.toString(), moduleCode));
    }

    /**
     * Check if two addmodule commands are equal
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof AddModuleCommand) {
            AddModuleCommand o = (AddModuleCommand) other;
            boolean isSamePlanNumber = this.planIndex.equals(o.planIndex);
            boolean isSameSemNumber = this.semIndex.equals(o.semIndex);
            boolean isSameModuleCode = this.moduleCode.equals(o.moduleCode);
            return (isSameModuleCode && isSameSemNumber && isSamePlanNumber);
        } else {
            return false;
        }
    }

    /**
     * Returns true if the given module code is found in the provided JsonModule[].
     *
     * @param informationOfModules The array of module information to search for a module in.
     * @return True if the given module code is found in the JsonModule array and false otherwise.
     */
    private Module findMatchingModule (JsonModule[] informationOfModules, String moduleCode) {
        for (int i = 0; i < informationOfModules.length; i++) {
            if (informationOfModules[i].getModuleCode().equals(moduleCode)) {
                // TODO: Change this line so that it returns the module that was found
                JsonModule moduleFound = informationOfModules[i];
                String moduleTitle = moduleFound.getModuleTitle();
                String code = moduleFound.getModuleCode();
                int moduleCredit = Integer.parseInt(moduleFound.getNumMc());
                return new Module(moduleTitle, code, moduleCredit);
            }
        }
        return null;
    }
}
