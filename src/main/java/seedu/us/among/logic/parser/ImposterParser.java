package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.us.among.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.us.among.logic.commands.AddCommand;
import seedu.us.among.logic.commands.ClearCommand;
import seedu.us.among.logic.commands.Command;
import seedu.us.among.logic.commands.EditCommand;
import seedu.us.among.logic.commands.ExitCommand;
import seedu.us.among.logic.commands.FindCommand;
import seedu.us.among.logic.commands.HelpCommand;
import seedu.us.among.logic.commands.ListCommand;
import seedu.us.among.logic.commands.RemoveCommand;
import seedu.us.among.logic.commands.RunCommand;
import seedu.us.among.logic.commands.SendCommand;
import seedu.us.among.logic.commands.ShowCommand;
import seedu.us.among.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ImposterParser {

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
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim().toLowerCase());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case RemoveCommand.COMMAND_WORD:
            return new RemoveCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case SendCommand.COMMAND_WORD:
            return new SendCommandParser().parse(arguments);

        case RunCommand.COMMAND_WORD:
            return new RunCommandParser().parse(arguments);

        case ShowCommand.COMMAND_WORD:
            return new ShowCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
