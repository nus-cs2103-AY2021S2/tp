//@@author ZhangAnli
package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_ENTITIES_LISTED_OVERVIEW;
import static dog.pawbook.model.Model.COMPARATOR_ID_ASCENDING_ORDER;
import static java.util.Objects.requireNonNull;

import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.NameContainsKeywordsPredicate;

/**
 * Finds and lists all owners in database whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all entities which contains the specified keywords. \n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    /**
     * Constructs a Find Command object.
     *
     * @param predicate that checks if entity name contains any keywords.
     */
    public FindCommand(NameContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);

        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredEntityList(predicate);
        model.sortEntities(COMPARATOR_ID_ASCENDING_ORDER);

        return new CommandResult(String.format(MESSAGE_ENTITIES_LISTED_OVERVIEW, model.getFilteredEntityList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
