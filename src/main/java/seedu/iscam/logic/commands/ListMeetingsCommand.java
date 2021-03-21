package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.model.Model;
import seedu.iscam.model.meeting.Meeting;

/**
 * Lists all meetings in the iscam book to the user.
 */
public class ListMeetingsCommand extends Command {

    public static final String COMMAND_WORD = "listmeet";

    public static final String MESSAGE_EMPTY_LIST = "There is no meeting in the iscam book.";

    private String stringifyList(ObservableList<Meeting> meetings) {
        String str = "";
        for (int i = 0; i < meetings.size(); i++) {
            str += meetings.get(i).toString() + "\n";
        }
        return str;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Meeting> meetings = model.getFilteredMeetingList();
        if (meetings.size() == 0) {
            throw new CommandException(MESSAGE_EMPTY_LIST);
        }
        return new CommandResult(stringifyList(meetings));
    }
}
