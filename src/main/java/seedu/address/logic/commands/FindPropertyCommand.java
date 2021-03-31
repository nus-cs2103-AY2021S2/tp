package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.property.PropertyPredicateList;

/**
 * Finds and lists all properties in property book containing any of the argument keywords or by price
 * given.
 * Keyword matching is case insensitive.
 */
public class FindPropertyCommand extends Command {

    public static final String COMMAND_WORD = "find property";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all properties containing any of "
            + "the specified keywords (case-insensitive) and/or by giving a price and search for properties "
            + "with asking prices above or below that amount, then displaying them as a list with index "
            + "numbers.\n"
            + "Parameters: [KEYWORD]... [pl/UPPER_PRICE_LIMIT] [pm/LOWER_PRICE_LIMIT] [t/TYPE] \n"
            + "Price limits are inclusive. \n"
            + "Housing types accepted are: HDB, Condo, and Landed. \n"
            + "Example: " + COMMAND_WORD + " jurong\n"
            + COMMAND_WORD + " pl/1000000 t/hdb";

    private final PropertyPredicateList predicates;

    public FindPropertyCommand(PropertyPredicateList predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPropertyList(predicates.combine());

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
