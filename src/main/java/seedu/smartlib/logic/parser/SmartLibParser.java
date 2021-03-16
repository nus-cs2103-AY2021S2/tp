package seedu.smartlib.logic.parser;

import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartlib.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.smartlib.logic.commands.AddBookCommand;
import seedu.smartlib.logic.commands.AddReaderCommand;
import seedu.smartlib.logic.commands.BorrowCommand;
import seedu.smartlib.logic.commands.ClearCommand;
import seedu.smartlib.logic.commands.Command;
import seedu.smartlib.logic.commands.DeleteBookCommand;
import seedu.smartlib.logic.commands.DeleteReaderCommand;
import seedu.smartlib.logic.commands.EditCommand;
import seedu.smartlib.logic.commands.ExitCommand;
import seedu.smartlib.logic.commands.FindReaderCommand;
import seedu.smartlib.logic.commands.HelpCommand;
import seedu.smartlib.logic.commands.ListBookCommand;
import seedu.smartlib.logic.commands.ListReaderCommand;
import seedu.smartlib.logic.commands.ReturnCommand;
import seedu.smartlib.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class SmartLibParser {

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

        case AddReaderCommand.COMMAND_WORD:
            return new AddReaderCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteReaderCommand.COMMAND_WORD:
            return new DeleteReaderCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindReaderCommand.COMMAND_WORD:
            return new FindReaderCommandParser().parse(arguments);

        case ListReaderCommand.COMMAND_WORD:
            return new ListReaderCommand();

        case AddBookCommand.COMMAND_WORD:
            return new AddBookCommandParser().parse(arguments);

        case DeleteBookCommand.COMMAND_WORD:
            return new DeleteBookCommandParser().parse(arguments);

        case ListBookCommand.COMMAND_WORD:
            return new ListBookCommand();

        case BorrowCommand.COMMAND_WORD:
            return new BorrowCommandParser().parse(arguments);

        case ReturnCommand.COMMAND_WORD:
            return new ReturnCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
