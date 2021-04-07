package fooddiary.logic.parser;

import static fooddiary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fooddiary.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fooddiary.logic.commands.AddCommand;
import fooddiary.logic.commands.AddOnCommand;
import fooddiary.logic.commands.ClearCommand;
import fooddiary.logic.commands.Command;
import fooddiary.logic.commands.DeleteCommand;
import fooddiary.logic.commands.EditCommand;
import fooddiary.logic.commands.ExitCommand;
import fooddiary.logic.commands.FindAllCommand;
import fooddiary.logic.commands.FindCommand;
import fooddiary.logic.commands.HelpCommand;
import fooddiary.logic.commands.ListCommand;
import fooddiary.logic.commands.ReviseCommand;
import fooddiary.logic.commands.ViewCommand;
import fooddiary.logic.parser.exceptions.ParseException;


/**
 * Parses user input.
 */
public class FoodDiaryParser {

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
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case ReviseCommand.COMMAND_WORD:
            return new ReviseCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FindAllCommand.COMMAND_WORD:
            return new FindAllCommandParser().parse(arguments);

        case AddOnCommand.COMMAND_WORD:
            return new AddOnCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
