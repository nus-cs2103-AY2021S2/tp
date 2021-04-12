package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddContactCommand;
import seedu.address.logic.commands.AddEntryCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ClearOverdueEntryCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteContactCommand;
import seedu.address.logic.commands.DeleteEntryCommand;
import seedu.address.logic.commands.EditContactCommand;
import seedu.address.logic.commands.EditEntryCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterContactCommand;
import seedu.address.logic.commands.FilterEntryCommand;
import seedu.address.logic.commands.FindContactCommand;
import seedu.address.logic.commands.FindEntryCommand;
import seedu.address.logic.commands.FreeCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListContactCommand;
import seedu.address.logic.commands.ListEntryCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TeachingAssistantParser {

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

        case AddContactCommand.COMMAND_WORD:
            return new AddContactCommandParser().parse(arguments);

        case AddEntryCommand.COMMAND_WORD:
            return new AddEntryCommandParser().parse(arguments);

        case EditContactCommand.COMMAND_WORD:
            return new EditContactCommandParser().parse(arguments);

        case EditEntryCommand.COMMAND_WORD:
            return new EditEntryCommandParser().parse(arguments);

        case DeleteEntryCommand.COMMAND_WORD:
            return new DeleteEntryCommandParser().parse(arguments);

        case DeleteContactCommand.COMMAND_WORD:
            return new DeleteContactCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ClearOverdueEntryCommand.COMMAND_WORD:
            return new ClearOverdueEntryCommand();

        case FilterContactCommand.COMMAND_WORD:
            return new FilterContactCommandParser().parse(arguments);

        case FilterEntryCommand.COMMAND_WORD:
            return new FilterEntryCommandParser().parse(arguments);

        case FindContactCommand.COMMAND_WORD:
            return new FindContactCommandParser().parse(arguments);

        case FindEntryCommand.COMMAND_WORD:
            return new FindEntryCommandParser().parse(arguments);

        case ListContactCommand.COMMAND_WORD:
            return new ListContactCommand();

        case ListEntryCommand.COMMAND_WORD:
            return new ListEntryCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case FreeCommand.COMMAND_WORD:
            return new FreeCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
