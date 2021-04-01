package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.iscam.model.Model;
import seedu.iscam.model.util.clientbook.ClientBook;
import seedu.iscam.model.util.meetingbook.MeetingBook;


/**
 * Clears the iscam book.
 */
public class ClearCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Client book has been cleared!";

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setClientBook(new ClientBook());
        model.setMeetingBook(new MeetingBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
