package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.iscam.model.Model;
import seedu.iscam.model.util.clientbook.ClientBook;
import seedu.iscam.model.util.meetingbook.MeetingBook;


/**
 * Clears the iscam book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "All clients and meetings have been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setClientBook(new ClientBook());
        model.setMeetingBook(new MeetingBook());
        model.setDetailedClient(null);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
