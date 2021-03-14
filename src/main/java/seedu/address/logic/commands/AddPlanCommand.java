package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.plan.Plan;

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
            + PREFIX_TAG + "good prospects";

    public static final String MESSAGE_SUCCESS = "New plan added: Plan %1$s";
    public static final String MESSAGE_DUPLICATE_PLAN = "This plan already exists";

    private final Plan toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Plan}
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
