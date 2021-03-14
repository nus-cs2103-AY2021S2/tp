package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.PropertyContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPropertyCommand extends Command {

    public static final String COMMAND_WORD = "find property";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all properties containing any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " hdb jurong";

    private final PropertyContainsKeywordsPredicate predicate;

    public FindPropertyCommand(PropertyContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPropertyList(predicate);

        int propertyListSize = model.getFilteredPropertyList().size();
        return new CommandResult(
                String.format(propertyListSize > 1
                        ? Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW
                        : Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, propertyListSize));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPropertyCommand // instanceof handles nulls
                && predicate.equals(((FindPropertyCommand) other).predicate)); // state check
    }
}
