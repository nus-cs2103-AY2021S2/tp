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

    public static final String MESSAGE_USAGE_1 = COMMAND_WORD + ": Find endpoints containing the search word/s through "
            + "all fields.\n"
            + "Parameters: [KEYWORD]\n"
            + "All parameters are optional, but least one parameter to edit must be provided.\n"
            + "Example: " + COMMAND_WORD + " GET github";

    public static final String MESSAGE_USAGE_2 = COMMAND_WORD + " (precise search)" + ": Find endpoints containing the "
            + "search word/s based on the prefix.\n"
            + "Parameters: -x [METHOD] -u [URL] -d [DATA] -h [HEADER] -t [TAG]\n"
            + "All parameters are optional, but least one parameter to edit must be provided.\n"
            + "Example: " + COMMAND_WORD + " -x GET -u google";

    public static final String MESSAGE_USAGE = MESSAGE_USAGE_1 + "\n\n" + MESSAGE_USAGE_2;

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
