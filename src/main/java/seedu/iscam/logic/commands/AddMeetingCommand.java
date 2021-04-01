package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_ON;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.model.Model;
import seedu.iscam.model.meeting.Meeting;

/**
 * Adds a meeting to the iscam book.
 */
public class AddMeetingCommand extends Command {

    public static final String COMMAND_WORD = "addmeet";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a meeting with a client to the iScam Book. \n"
            + "Parameters: "
            + PREFIX_CLIENT + "CLIENT_NAME "
            + PREFIX_ON + "DATE TIME "
            + PREFIX_LOCATION + "LOCATION "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "(optional) " + PREFIX_TAG + "TAG(s) \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CLIENT + "John Doe "
            + PREFIX_ON + "12-10-2021 10:00 "
            + PREFIX_LOCATION + "Starbucks, Tampines Hub "
            + PREFIX_DESCRIPTION + "Discuss insurance policy "
            + PREFIX_TAG + "Urgent "
            + PREFIX_TAG + "Premium";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";
    public static final String MESSAGE_MEETING_CONFLICT = "A meeting with this date and time already exists in the "
            + "iscam book ";

    private final Meeting toAdd;

    /**
     * Creates an AddMeetingCommand to add the specified {@code Meeting}
     */
    public AddMeetingCommand(Meeting meeting) {
        requireNonNull(meeting);
        toAdd = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasMeeting(toAdd)) {
            throw new CommandException(MESSAGE_MEETING_CONFLICT);
        }

        model.addMeeting(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof AddMeetingCommand && toAdd.equals(((AddMeetingCommand) other).toAdd));
    }
}
