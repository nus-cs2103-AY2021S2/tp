package seedu.iScam.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.iScam.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.iScam.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.iScam.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.iScam.logic.parser.CliSyntax.PREFIX_ON;
import static seedu.iScam.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.iScam.logic.commands.exceptions.CommandException;
import seedu.iScam.model.Model;
import seedu.iScam.model.meeting.Meeting;

public class AddMeetingCommand extends Command {

    public static final String COMMAND_WORD = "addmeet";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a meeting with a client to the iScam book. "
            + "Parameters: "
            + PREFIX_CLIENT + "CLIENT_ID "
            + PREFIX_ON + "DATE-TIME "
            + PREFIX_LOCATION + "ADDRESS "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CLIENT + "1 "
            + PREFIX_ON + "12-10-2020 10PM "
            + PREFIX_LOCATION + "Starbucks, Tampines Hub "
            + PREFIX_DESCRIPTION + "Discuss insurance policy "
            + PREFIX_TAG + "Urgent "
            + PREFIX_TAG + "Premium";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";
    public static final String MESSAGE_MEETING_CONFLICT = "A meeting with this date and time already exists in the "
            + "iScam book ";

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
        // Throw exception if model has this meeting

        // Add meeting to model
        return new CommandResult("PLACEHOLDER ADD SUCCESS");
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof AddMeetingCommand && toAdd.equals(((AddMeetingCommand) other).toAdd));
    }
}
