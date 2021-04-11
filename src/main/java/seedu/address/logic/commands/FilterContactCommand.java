package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.contact.ContactTagsContainKeywordsPredicate;

public class FilterContactCommand extends Command {
    public static final String COMMAND_WORD = "cfilter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all contacts that have the tags of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " student english";

    private final ContactTagsContainKeywordsPredicate predicate;

    public FilterContactCommand(ContactTagsContainKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW, model.getFilteredContactList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterContactCommand // instanceof handles nulls
                && predicate.equals(((FilterContactCommand) other).predicate)); // state check
    }
}
