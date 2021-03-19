package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyContainsKeywordsPredicate;
import seedu.address.model.property.PropertyPredicateList;

/**
 * Finds and lists all properties in address book containing any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPropertyCommand extends Command {

    public static final String COMMAND_WORD = "find property";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all properties containing any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " hdb jurong";

    private final PropertyPredicateList predicates;

    public FindPropertyCommand(PropertyPredicateList predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPropertyList(predicates);

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
                && predicates.equals(((FindPropertyCommand) other).predicates)); // state check
    }
}
