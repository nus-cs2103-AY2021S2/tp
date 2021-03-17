package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.meetings.AddMeetingCommand;
import seedu.address.logic.commands.meetings.DeleteMeetingCommand;
import seedu.address.logic.commands.persons.AddPersonCommand;
import seedu.address.logic.commands.persons.ClearPersonCommand;
import seedu.address.logic.commands.persons.DeletePersonCommand;
import seedu.address.logic.commands.persons.EditPersonCommand;
import seedu.address.logic.commands.persons.FindGroupCommand;
import seedu.address.logic.commands.persons.FindPersonCommand;
import seedu.address.logic.commands.persons.ListPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.meetings.AddMeetingCommandParser;
import seedu.address.logic.parser.meetings.DeleteMeetingCommandParser;
import seedu.address.logic.parser.persons.AddPersonCommandParser;
import seedu.address.logic.parser.persons.DeletePersonCommandParser;
import seedu.address.logic.parser.persons.EditPersonCommandParser;
import seedu.address.logic.parser.persons.FindGroupCommandParser;
import seedu.address.logic.parser.persons.FindPersonCommandParser;

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

        case ClearPersonCommand.COMMAND_WORD:
            return new ClearPersonCommand();

        case FindPersonCommand.COMMAND_WORD:
            return new FindPersonCommandParser().parse(arguments);

        case FindGroupCommand.COMMAND_WORD:
            return new FindGroupCommandParser().parse(arguments);

        case ListPersonCommand.COMMAND_WORD:
            return new ListPersonCommand();

        //============================= Meeting ==============================
        case AddMeetingCommand.COMMAND_WORD:
            return new AddMeetingCommandParser().parse(arguments);

        case DeleteMeetingCommand.COMMAND_WORD:
            return new DeleteMeetingCommandParser().parse(arguments);

        //============================= General ==============================
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
