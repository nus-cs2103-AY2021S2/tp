package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.booking.commons.core.Messages;
import seedu.booking.model.Model;
import seedu.booking.model.booking.PersonContainsTagPredicate;

/**
 * Finds and lists persons in the system whose venue corresponds to the tag name given as argument
 * Tag name matching is case insensitive.
 */
public class FindPersonByTagCommand extends Command {

    public static final String COMMAND_WORD = "find_person_by_tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons with the tag "
            + "the specified tag name (case-insensitive) and displays them as a list.\n"
            + "Parameters: t/tag\n"
            + "Example: " + COMMAND_WORD + " t/Central";

    private final PersonContainsTagPredicate predicate;

    public FindPersonByTagCommand(PersonContainsTagPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        if (model.getFilteredPersonList().size() == 0) {
            return new CommandResult(Messages.MESSAGE_BOOKING_FILTER_FAILED);
        } else {
            return new CommandResult(
                    Messages.MESSAGE_BOOKING_TAG_FILTERED + predicate.getTagName());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPersonByTagCommand // instanceof handles nulls
                && predicate.equals(((FindPersonByTagCommand) other).predicate)); // state check
    }
}
