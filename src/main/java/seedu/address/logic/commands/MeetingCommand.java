package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Meeting;

public class MeetingCommand extends Command {

    public static final String COMMAND_WORD = "meeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Records a meeting of the person identified "
            + "by the index number used in the last person listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + "d/DATE"
            + "[t/TIME]"
            + "desc/DESCRIPTION"
            + "Example: " + COMMAND_WORD + " 1 "
            + "d/12-12-2021"
            + "t/1240"
            + "desc/We went to the beach!";

    private final Index index;
    private final Meeting meeting;

    public MeetingCommand(Index index, Meeting meeting) {
        requireAllNonNull(index, meeting);

        this.index = index;
        this.meeting = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("woots it worked");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MeetingCommand)) {
            return false;
        }

        MeetingCommand e = (MeetingCommand) other;
        return index.equals(e.index) && meeting.equals(e.meeting);
    }
}
