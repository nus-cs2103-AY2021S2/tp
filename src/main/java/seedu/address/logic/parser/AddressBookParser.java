package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.resident.AddResidentCommand;
import seedu.address.logic.commands.resident.DeleteResidentCommand;
import seedu.address.logic.commands.resident.EditResidentCommand;
import seedu.address.logic.commands.resident.FindResidentCommand;
import seedu.address.logic.commands.resident.ListResidentCommand;
import seedu.address.logic.commands.room.AddRoomCommand;
import seedu.address.logic.commands.room.DeleteRoomCommand;
import seedu.address.logic.commands.room.EditRoomCommand;
import seedu.address.logic.commands.room.FindRoomCommand;
import seedu.address.logic.commands.room.ListRoomCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.resident.AddResidentCommandParser;
import seedu.address.logic.parser.resident.DeleteResidentCommandParser;
import seedu.address.logic.parser.resident.EditResidentCommandParser;
import seedu.address.logic.parser.resident.FindResidentCommandParser;
import seedu.address.logic.parser.room.AddRoomCommandParser;
import seedu.address.logic.parser.room.DeleteRoomCommandParser;
import seedu.address.logic.parser.room.EditRoomCommandParser;
import seedu.address.logic.parser.room.FindRoomCommandParser;

/**
 * Parses user input.
 */
public class AddressBookParser {

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

        case AddResidentCommand.COMMAND_WORD:
            return new AddResidentCommandParser().parse(arguments);

        case EditResidentCommand.COMMAND_WORD:
            return new EditResidentCommandParser().parse(arguments);

        case DeleteResidentCommand.COMMAND_WORD:
            return new DeleteResidentCommandParser().parse(arguments);

        case FindResidentCommand.COMMAND_WORD:
            return new FindResidentCommandParser().parse(arguments);

        case ListResidentCommand.COMMAND_WORD:
            return new ListResidentCommand();

        //====== Room Commands ======
        case AddRoomCommand.COMMAND_WORD:
            return new AddRoomCommandParser().parse(arguments);

        case EditRoomCommand.COMMAND_WORD:
            return new EditRoomCommandParser().parse(arguments);

        case DeleteRoomCommand.COMMAND_WORD:
            return new DeleteRoomCommandParser().parse(arguments);

        case FindRoomCommand.COMMAND_WORD:
            return new FindRoomCommandParser().parse(arguments);

        case ListRoomCommand.COMMAND_WORD:
            return new ListRoomCommand();

        //====== System Commands ======
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
