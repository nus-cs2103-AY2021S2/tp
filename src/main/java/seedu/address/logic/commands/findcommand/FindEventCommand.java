package seedu.address.logic.commands.findcommand;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.DescriptionContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_EVENT;

public class FindEventCommand extends FindCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":Finds all events whose description"
            + "contains any of the specified keywords (case-insensitive) and displays them as a "
            + "list with index numbers.\n"
            + "Parameters: "
            + PREFIX_GENERAL_EVENT + "KEYWORD [MORE KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GENERAL_EVENT + "Meet up";

    private final DescriptionContainsKeywordsPredicate predicate;

    public FindEventCommand(DescriptionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredEventList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW,
                model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FindEventCommand)) {
            return false;
        }
        if (object == this) {
            return true;
        }

        FindEventCommand otherEventCommand = (FindEventCommand) object;
        return predicate.equals(otherEventCommand.predicate);
    }
}
