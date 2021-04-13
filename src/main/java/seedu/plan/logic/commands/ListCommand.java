package seedu.plan.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.plan.model.Model.PREDICATE_SHOW_ALL_PLANS;

import seedu.plan.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all plans";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPlanList(PREDICATE_SHOW_ALL_PLANS);
        model.setCurrentCommand(COMMAND_WORD);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
