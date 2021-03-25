package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.SortingFlag;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all tasks according to one of three"
            + "specified parameters: date & time, module code, or priority tag.\n";

    private final SortingFlag sortingFlag;

    public SortCommand(SortingFlag sortingFlag) {
        this.sortingFlag = sortingFlag;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // placeholder
        return new CommandResult("");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand); // instanceof handles null
    }
}
