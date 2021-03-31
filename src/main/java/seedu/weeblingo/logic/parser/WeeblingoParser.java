package seedu.weeblingo.logic.parser;

import static seedu.weeblingo.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.weeblingo.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.weeblingo.logic.commands.CheckCommand;
import seedu.weeblingo.logic.commands.Command;
import seedu.weeblingo.logic.commands.DeleteCommand;
import seedu.weeblingo.logic.commands.EndCommand;
import seedu.weeblingo.logic.commands.ExitCommand;
import seedu.weeblingo.logic.commands.HelpCommand;
import seedu.weeblingo.logic.commands.LearnCommand;
import seedu.weeblingo.logic.commands.NextCommand;
import seedu.weeblingo.logic.commands.QuizCommand;
import seedu.weeblingo.logic.commands.StartCommand;
import seedu.weeblingo.logic.commands.TagCommand;
import seedu.weeblingo.logic.commands.ViewHistoryCommand;
import seedu.weeblingo.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class WeeblingoParser {

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

        case EndCommand.COMMAND_WORD:
            return new EndCommand();

        case LearnCommand.COMMAND_WORD:
            return new LearnCommand();

        case QuizCommand.COMMAND_WORD:
            return new QuizCommand();

        case StartCommand.COMMAND_WORD:
            return new StartCommandParser().parse(arguments);

        case NextCommand.COMMAND_WORD:
            return new NextCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case CheckCommand.COMMAND_WORD:
            return new CheckCommandParser().parse(arguments);

        case TagCommand.COMMAND_WORD:
            return new TagCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ViewHistoryCommand.COMMAND_WORD:
            return new ViewHistoryCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
