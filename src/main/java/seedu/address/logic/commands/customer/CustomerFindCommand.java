package seedu.address.logic.commands.customer;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class CustomerFindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: n/[KEYWORD] (MORE_KEYWORDS)...\n"
            + "Example: " + COMMAND_WORD + " n/alice bob charlie";

    private final Predicate<Person> predicate;

    public CustomerFindCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ITEMS_LISTED_OVERVIEW,
                        model.getFilteredPersonList().size(),
                        Messages.ITEM_PERSONS),
                CommandResult.CRtype.PERSON);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CustomerFindCommand // instanceof handles nulls
                && predicate.equals(((CustomerFindCommand) other).predicate)); // state check
    }
}
