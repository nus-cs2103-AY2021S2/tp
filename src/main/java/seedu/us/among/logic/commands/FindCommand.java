package seedu.us.among.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.us.among.commons.core.Messages;
import seedu.us.among.model.Model;
import seedu.us.among.model.endpoint.Endpoint;

/**
 * Finds and lists all API endpoints whose fields contain any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all API endpoints whose fields contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " get facebook google";

    private final Predicate<Endpoint> predicate;

    public FindCommand(Predicate<Endpoint> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEndpointList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ENDPOINTS_LISTED_OVERVIEW, model.getFilteredEndpointList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
