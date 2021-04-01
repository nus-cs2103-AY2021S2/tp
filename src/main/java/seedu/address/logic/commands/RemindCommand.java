package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_UPCOMING_BOOKED_RESIDENCES;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;

/**
 * Lists all residences in the residence tracker with bookings starting in the next 7 days.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind";

    public static String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredResidenceList(PREDICATE_UPCOMING_BOOKED_RESIDENCES);
        return new CommandResult(
                String.format(Messages.MESSAGE_RESIDENCES_WITH_UPCOMING_BOOKINGS,
                        model.getFilteredResidenceList().size()));
    }
}
