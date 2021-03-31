package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class SortEventCommand extends Command {
    public static final String COMMAND_WORD = "sort_event";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts event based on given parameter.\n"
            + "Supported parameters: \"name\", \"start_time\", \"end_time\", \"start_date\", \"end_date\" \n"
            + "Example: " + COMMAND_WORD + " name";

    public static final String MESSAGE_SORT_TASK_SUCCESS = "Sorted Events";

    private final String comparingVar;


    public SortEventCommand(String comparingVar) {
        this.comparingVar = comparingVar;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortEvents(comparingVar);
        return new CommandResult(MESSAGE_SORT_TASK_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortEventCommand // instanceof handles nulls
                && comparingVar.equals(((SortEventCommand) other).comparingVar)); // state check
    }
}
