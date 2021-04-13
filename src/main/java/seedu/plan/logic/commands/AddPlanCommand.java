package seedu.plan.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.plan.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.plan.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.plan.logic.commands.exceptions.CommandException;
import seedu.plan.model.Model;
import seedu.plan.model.plan.Plan;

/**
 * Adds a plan to the address book.
 */
public class AddPlanCommand extends Command {

    public static final String COMMAND_WORD = "addp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a plan. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Software Engineering Route "
            + PREFIX_TAG + "fun "
            + PREFIX_TAG + "interesting";

    public static final String MESSAGE_SUCCESS = "New plan added: Plan %1$s";
    public static final String MESSAGE_DUPLICATE_PLAN = "This plan already exists";

    private final Plan toAdd;

    /**
     * Creates an AddPlanCommand to add the specified {@code Plan}
     */
    public AddPlanCommand(Plan plan) {
        requireNonNull(plan);
        toAdd = plan;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPlan(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PLAN);
        }
        model.setCurrentCommand(COMMAND_WORD);
        model.addPlan(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredPlanList().size() + toAdd.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPlanCommand // instanceof handles nulls
                && toAdd.equals(((AddPlanCommand) other).toAdd));
    }
}
