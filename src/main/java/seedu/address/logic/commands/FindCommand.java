package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.garment.ContainsKeywordsPredicate;

/**
 * Finds and lists all garments in wardrobe whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all garments whose names, colours, sizes, "
            + "dresscodes or descriptions contain any of the specified\n"
            + "keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: n/KEYWORD [MORE_KEYWORDS] or s/KEYWORD [MORE_KEYWORDS] ... for each attribute\n"
            + "Example: " + COMMAND_WORD + " n/alice bob charlie";

    private final ContainsKeywordsPredicate predicate;

    public FindCommand(ContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredGarmentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_GARMENTS_LISTED_OVERVIEW, model.getFilteredGarmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
