package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.ClearAssigneesCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteMemberCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.DoneTaskCommand;
import seedu.address.logic.commands.EditMemberCommand;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindByPriorityCommand;
import seedu.address.logic.commands.FindMemberTasksCommand;
import seedu.address.logic.commands.FindMembersCommand;
import seedu.address.logic.commands.FindTasksBeforeCommand;
import seedu.address.logic.commands.FindTasksCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.UndoTaskCommand;
import seedu.address.logic.commands.ViewMembersCommand;
import seedu.address.logic.commands.ViewTasksCommand;
import seedu.address.logic.commands.ViewUnassignedTasksCommand;
import seedu.address.logic.commands.ViewUncompletedTasksCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class HeyMatezParser {

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

        case AddMemberCommand.COMMAND_WORD:
            return new AddMemberCommandParser().parse(arguments);

        case AddTaskCommand.COMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments);

        case ClearAssigneesCommand.COMMAND_WORD:
            return new ClearAssigneesCommandParser().parse(arguments);

        case EditMemberCommand.COMMAND_WORD:
            return new EditMemberCommandParser().parse(arguments);

        case EditTaskCommand.COMMAND_WORD:
            return new EditTaskCommandParser().parse(arguments);

        case DeleteMemberCommand.COMMAND_WORD:
            return new DeleteMemberCommandParser().parse(arguments);

        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommandParser().parse(arguments);

        case DoneTaskCommand.COMMAND_WORD:
            return new DoneTaskCommandParser().parse(arguments);

        case UndoTaskCommand.COMMAND_WORD:
            return new UndoTaskCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindMembersCommand.COMMAND_WORD:
            return new FindMemberCommandParser().parse(arguments);

        case FindMemberTasksCommand.COMMAND_WORD:
            return new FindMemberTasksCommandParser().parse(arguments);

        case FindByPriorityCommand.COMMAND_WORD:
            return new FindByPriorityCommandParser().parse(arguments);

        case FindTasksCommand.COMMAND_WORD:
            return new FindTasksCommandParser().parse(arguments);

        case FindTasksBeforeCommand.COMMAND_WORD:
            return new FindTasksBeforeCommandParser().parse(arguments);

        case ViewMembersCommand.COMMAND_WORD:
            return new ViewMembersCommand();

        case ViewTasksCommand.COMMAND_WORD:
            return new ViewTasksCommand();

        case ViewUnassignedTasksCommand.COMMAND_WORD:
            return new ViewUnassignedTasksCommand();

        case ViewUncompletedTasksCommand.COMMAND_WORD:
            return new ViewUncompletedTasksCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
