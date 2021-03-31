package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.CalendarCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ToggleDoneAssignmentCommand;
import seedu.address.logic.commands.addcommand.AddCommand;
import seedu.address.logic.commands.clearcommand.ClearCommand;
import seedu.address.logic.commands.deletecommand.DeleteCommand;
import seedu.address.logic.commands.editcommand.EditCommand;
import seedu.address.logic.commands.findcommand.FindCommand;
import seedu.address.logic.parser.addcommandparser.AddCommandParser;
import seedu.address.logic.parser.deletecommandparser.DeleteCommandParser;
import seedu.address.logic.parser.editcommandparser.EditCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.findcommandparser.FindCommandParser;



/**
 * Parses user input.
 */
public class RemindMeParser {

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

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parseCommand(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parseCommand(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parseCommand(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommandParser().parse(arguments);

        case ToggleDoneAssignmentCommand.COMMAND_WORD:
            return new ToggleDoneAssignmentCommandParser().parse(arguments);

        case CalendarCommand.COMMAND_WORD:
        case CalendarCommand.COMMAND_CHAR:
            return new CalendarCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parseCommand(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
        case ExitCommand.COMMAND_CHAR:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
        case HelpCommand.COMMAND_CHAR:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
