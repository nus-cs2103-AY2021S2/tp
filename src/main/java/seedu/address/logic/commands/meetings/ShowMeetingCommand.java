package seedu.address.logic.commands.meetings;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.connection.PersonInMeetingPredicate;
import seedu.address.model.meeting.Meeting;

public class ShowMeetingCommand extends Command {

    public static final String COMMAND_WORD = "showm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons who are in the specified "
            + "meeting and displays them as a list with index numbers.\n"
            + "Parameters: MEETING_INDEX...\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index index;

    public ShowMeetingCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meeting = lastShownList.get(index.getZeroBased());
        PersonInMeetingPredicate predicate = new PersonInMeetingPredicate(meeting);

        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowMeetingCommand // instanceof handles nulls
                && this.index.equals(((ShowMeetingCommand) other).index)); // state check
    }
}
