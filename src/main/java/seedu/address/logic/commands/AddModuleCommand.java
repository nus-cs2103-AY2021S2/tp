package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAN_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.plan.Module;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.plan.Semester;
import seedu.address.model.plan.Plan;

public class AddModuleCommand extends Command{
    public static final String COMMAND_WORD = "addm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a module to this semester. "
            + "Parameters: "
            + PREFIX_PLAN_NUMBER + "PLAN NUMBER "
            + PREFIX_SEM_NUMBER + "SEM NUMBER "
            + PREFIX_MODULE_CODE + "MODULE CODE"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PLAN_NUMBER + "1 "
            + PREFIX_SEM_NUMBER + "2 "
            + PREFIX_MODULE_CODE + "1010";

    public static final String MESSAGE_SUCCESS = "New module added to \"Plan %1$s\": %2$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exist in this current semester";

    private final Module toAdd;
    private final Index semNumber;
    private final Index planNumber;


    public AddModuleCommand(Module module, Index semNumber, Index planNumber) {
        this.toAdd = module;
        this.planNumber = planNumber;
        this.semNumber = semNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Plan> lastShownPlan = model.getFilteredPersonList();

        if (planNumber.getZeroBased() >= lastShownPlan.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (model.hasModule(planNumber.getZeroBased(), semNumber.getZeroBased(), toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.addModule(planNumber.getZeroBased(), semNumber.getZeroBased(), toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, planNumber.getOneBased(), toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddModuleCommand // instanceof handles nulls
                && toAdd.equals(((AddModuleCommand) other).toAdd));
    }
}
