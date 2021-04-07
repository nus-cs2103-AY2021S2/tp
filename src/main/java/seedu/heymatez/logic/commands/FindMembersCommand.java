package seedu.heymatez.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.heymatez.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.heymatez.commons.core.Messages.MESSAGE_EMPTY_PERSON_LIST;

import seedu.heymatez.logic.commands.exceptions.CommandException;
import seedu.heymatez.model.Model;
import static seedu.heymatez.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import seedu.heymatez.model.person.DetailsContainsKeywordsPredicate;

/**
 * Finds and lists all persons in HeyMatez whose details contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindMembersCommand extends Command {

    public static final String COMMAND_WORD = "findMembers";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all members whose name, phone number, "
            + "email or role contain any of the specified keywords (case-insensitive) and displays them as a list.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice 97574218 rachel@example.com";

    private final DetailsContainsKeywordsPredicate predicate;

    public FindMembersCommand(DetailsContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);

        if (model.isPersonListEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_PERSON_LIST);
        }

        return new CommandResult(
                String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindMembersCommand // instanceof handles nulls
                && predicate.equals(((FindMembersCommand) other).predicate)); // state check
    }
}
