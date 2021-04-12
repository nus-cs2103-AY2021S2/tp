package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.dictionary.DefinitionContainsKeywordsPredicate;

/**
 * Finds and lists all persons in dictionote book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindDefinitionCommand extends Command {

    public static final String COMMAND_WORD = "finddef";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all relevant definition which contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " programming language";

    private final DefinitionContainsKeywordsPredicate predicate;

    //Todo
    public FindDefinitionCommand(DefinitionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDefinitionList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_DEFINITIONS_LISTED_OVERVIEW, model.getFilteredDefinitionList().size()),
            UiAction.OPEN, UiActionOption.DICTIONARY_LIST);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindDefinitionCommand // instanceof handles nulls
                && predicate.equals(((FindDefinitionCommand) other).predicate)); // state check
    }
}
