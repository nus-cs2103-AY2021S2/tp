package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAllCommand;
import seedu.address.logic.commands.connections.AddPersonToMeetingConnectionCommand;
import seedu.address.logic.commands.connections.DeletePersonToMeetingConnectionCommand;
import seedu.address.logic.commands.meetings.AddMeetingCommand;
import seedu.address.logic.commands.meetings.DeleteMeetingCommand;
import seedu.address.logic.commands.meetings.EditMeetingCommand;
import seedu.address.logic.commands.meetings.FindMeetingCommand;
import seedu.address.logic.commands.meetings.ListMeetingCommand;
import seedu.address.logic.commands.meetings.SetTimetableCommand;
import seedu.address.logic.commands.meetings.ShowMeetingCommand;
import seedu.address.logic.commands.meetings.SortMeetingCommand;
import seedu.address.logic.commands.meetings.UnsortMeetingCommand;
import seedu.address.logic.commands.notes.AddNoteCommand;
import seedu.address.logic.commands.notes.DeleteNoteCommand;
import seedu.address.logic.commands.persons.AddPersonCommand;
import seedu.address.logic.commands.persons.DeletePersonCommand;
import seedu.address.logic.commands.persons.EditPersonCommand;
import seedu.address.logic.commands.persons.FindGroupCommand;
import seedu.address.logic.commands.persons.FindPersonCommand;
import seedu.address.logic.commands.persons.ListPersonCommand;
import seedu.address.logic.commands.persons.SortPersonCommand;
import seedu.address.logic.commands.persons.UnsortPersonCommand;
import seedu.address.logic.commands.reminders.RefreshRemindersCommand;
import seedu.address.logic.parser.connections.AddPersonToMeetingConnectionParser;
import seedu.address.logic.parser.connections.DeletePersonToMeetingConnectionParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.meetings.AddMeetingCommandParser;
import seedu.address.logic.parser.meetings.DeleteMeetingCommandParser;
import seedu.address.logic.parser.meetings.EditMeetingCommandParser;
import seedu.address.logic.parser.meetings.FindMeetingCommandParser;
import seedu.address.logic.parser.meetings.SetTimetableCommandParser;
import seedu.address.logic.parser.meetings.ShowMeetingCommandParser;
import seedu.address.logic.parser.meetings.SortMeetingCommandParser;
import seedu.address.logic.parser.notes.AddNoteCommandParser;
import seedu.address.logic.parser.notes.DeleteNoteCommandParser;
import seedu.address.logic.parser.persons.AddPersonCommandParser;
import seedu.address.logic.parser.persons.DeletePersonCommandParser;
import seedu.address.logic.parser.persons.EditPersonCommandParser;
import seedu.address.logic.parser.persons.FindGroupCommandParser;
import seedu.address.logic.parser.persons.FindPersonCommandParser;
import seedu.address.logic.parser.persons.SortPersonCommandParser;

/**
 * Parses user input.
 */
public class MeetBuddyParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        //============================= Person ==============================
        case AddPersonCommand.COMMAND_WORD:
            return new AddPersonCommandParser().parse(arguments);

        case EditPersonCommand.COMMAND_WORD:
            return new EditPersonCommandParser().parse(arguments);

        case DeletePersonCommand.COMMAND_WORD:
            return new DeletePersonCommandParser().parse(arguments);

        case FindPersonCommand.COMMAND_WORD:
            return new FindPersonCommandParser().parse(arguments);

        case FindGroupCommand.COMMAND_WORD:
            return new FindGroupCommandParser().parse(arguments);

        case ListPersonCommand.COMMAND_WORD:
            return new ListPersonCommand();

        case SortPersonCommand.COMMAND_WORD:
            return new SortPersonCommandParser().parse(arguments);

        case UnsortPersonCommand
                    .COMMAND_WORD:
            return new UnsortPersonCommand();

        //============================= Meeting ==============================
        case AddMeetingCommand.COMMAND_WORD:
            return new AddMeetingCommandParser().parse(arguments);

        case EditMeetingCommand.COMMAND_WORD:
            return new EditMeetingCommandParser().parse(arguments);

        case DeleteMeetingCommand.COMMAND_WORD:
            return new DeleteMeetingCommandParser().parse(arguments);

        case ListMeetingCommand.COMMAND_WORD:
            return new ListMeetingCommand();

        case SortMeetingCommand.COMMAND_WORD:
            return new SortMeetingCommandParser().parse(arguments);

        case UnsortMeetingCommand.COMMAND_WORD:
            return new UnsortMeetingCommand();

        //===================== Connection =================================

        case ShowMeetingCommand.COMMAND_WORD:
            return new ShowMeetingCommandParser().parse(arguments);

        case FindMeetingCommand.COMMAND_WORD:
            return new FindMeetingCommandParser().parse(arguments);

        //======================= Note =====================================
        case AddNoteCommand.COMMAND_WORD:
            return new AddNoteCommandParser().parse(arguments);

        case DeleteNoteCommand.COMMAND_WORD:
            return new DeleteNoteCommandParser().parse(arguments);

        //======================= Timetable =====================================

        case SetTimetableCommand.COMMAND_WORD:
            return new SetTimetableCommandParser().parse(arguments);

        //============================= Meeting ==============================
        case AddPersonToMeetingConnectionCommand.COMMAND_WORD:
            return new AddPersonToMeetingConnectionParser().parse(arguments);

        case DeletePersonToMeetingConnectionCommand.COMMAND_WORD:
            return new DeletePersonToMeetingConnectionParser().parse(arguments);

        //============================= General ==============================
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ListAllCommand.COMMAND_WORD:
            return new ListAllCommand();

        case RefreshRemindersCommand.COMMAND_WORD:
            return new RefreshRemindersCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
