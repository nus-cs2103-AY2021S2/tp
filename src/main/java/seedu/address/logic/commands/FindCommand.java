package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.garment.AttributesContainsKeywordsPredicate;
import seedu.address.model.garment.ContainsKeywordsPredicate;
import seedu.address.model.garment.Garment;

import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.List;

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

    /*private final ContainsKeywordsPredicate predicate;

    public FindCommand(ContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }*/

    /*private final List<ContainsKeywordsPredicate> predicateList;

    public FindCommand(List<ContainsKeywordsPredicate> predicateList) {
        this.predicateList = predicateList;
    }*/

    private final AttributesContainsKeywordsPredicate predicates;

    public FindCommand(AttributesContainsKeywordsPredicate predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        //model.update is what affects whats being shown
        //somehow need to pass in either a list of predicates
        //or pass in a combined pred.
        //model.updateFilteredGarmentList(predicate);

        //model.updateFilteredGarmentList(predicateList);

        model.updateFilteredGarmentList(predicates);
        //command res is just a string feedback
        return new CommandResult(
                String.format(Messages.MESSAGE_GARMENTS_LISTED_OVERVIEW, model.getFilteredGarmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicates.equals(((FindCommand) other).predicates)); // state check
    }
}
