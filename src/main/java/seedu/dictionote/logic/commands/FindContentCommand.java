package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.dictionary.ContentContainsKeywordsPredicate;

/**
 * Finds and lists all persons in dictionote book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindContentCommand extends Command {

    public static final String COMMAND_WORD = "findcontent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all relevant content which contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " programming language";

    private final ContentContainsKeywordsPredicate predicate;

    /**
     * Creates a new {@code FindContentCommand} with a predicate (i.e., conditions):
     * which references to the main content in the Dictionary.
     * @param predicate The predicate to be evaluated against the Dictionary's main content.
     */
    public FindContentCommand(ContentContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContentList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_CONTENTS_LISTED_OVERVIEW, model.getFilteredContentList().size()),
            UiAction.OPEN, UiActionOption.DICTIONARY_LIST);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindContentCommand // instanceof handles nulls
                && predicate.equals(((FindContentCommand) other).predicate)); // state check
    }
}
