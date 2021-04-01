package seedu.iscam.logic.parser.clientcommands;

import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.iscam.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.iscam.logic.commands.AddCommand;
import seedu.iscam.logic.commands.ClearCommand;
import seedu.iscam.logic.commands.Command;
import seedu.iscam.logic.commands.DeleteCommand;
import seedu.iscam.logic.commands.EditCommand;
import seedu.iscam.logic.commands.ExitCommand;
import seedu.iscam.logic.commands.FindCommand;
import seedu.iscam.logic.commands.FindPlansCommand;
import seedu.iscam.logic.commands.HelpCommand;
import seedu.iscam.logic.commands.ListCommand;
import seedu.iscam.logic.commands.ShowCommand;
import seedu.iscam.logic.parser.BookParser;
import seedu.iscam.logic.parser.ShowCommandParser;
import seedu.iscam.logic.parser.exceptions.ParseException;
import seedu.iscam.logic.parser.exceptions.ParseFormatException;

/**
 * Parses user input.
 */
public class ClientBookParser implements BookParser {

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
            throw new ParseFormatException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FindPlansCommand.COMMAND_WORD:
            return new FindPlansCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ShowCommand.COMMAND_WORD:
            return new ShowCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
