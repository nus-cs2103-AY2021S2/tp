package seedu.taskify.logic.parser;

import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskify.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.taskify.logic.commands.util.DeleteMultipleCommandUtil.hasMultipleValidIndex;
import static seedu.taskify.logic.commands.util.DeleteMultipleCommandUtil.isDeletingTasksByStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.taskify.logic.commands.AddCommand;
import seedu.taskify.logic.commands.ClearCommand;
import seedu.taskify.logic.commands.Command;
import seedu.taskify.logic.commands.CompletedCommand;
import seedu.taskify.logic.commands.DeleteCommand;
import seedu.taskify.logic.commands.EditCommand;
import seedu.taskify.logic.commands.ExitCommand;
import seedu.taskify.logic.commands.ExpiredCommand;
import seedu.taskify.logic.commands.FindCommand;
import seedu.taskify.logic.commands.HelpCommand;
import seedu.taskify.logic.commands.HomeCommand;
import seedu.taskify.logic.commands.ListCommand;
import seedu.taskify.logic.commands.SortCommand;
import seedu.taskify.logic.commands.TagSearchCommand;
import seedu.taskify.logic.commands.UncompletedCommand;
import seedu.taskify.logic.commands.ViewCommand;
import seedu.taskify.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TaskifyParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform to the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        assert userInput != null : "userInput should not be empty";
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
            if (isDeletingTasksByStatus(arguments) || hasMultipleValidIndex(arguments)) {
                return new DeleteMultipleCommandParser().parse(arguments);
            } else {
                return new DeleteCommandParser().parse(arguments);
            }
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case TagSearchCommand.COMMAND_WORD:
            return new TagSearchCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommand();

        case HomeCommand.COMMAND_WORD:
            return new HomeCommand();

        case ExpiredCommand.COMMAND_WORD:
            return new ExpiredCommand();

        case CompletedCommand.COMMAND_WORD:
            return new CompletedCommand();

        case UncompletedCommand.COMMAND_WORD:
            return new UncompletedCommand();

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);


        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
