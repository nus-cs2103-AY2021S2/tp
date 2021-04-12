package seedu.partyplanet.logic.parser;

import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.partyplanet.logic.commands.AddCommand;
import seedu.partyplanet.logic.commands.Command;
import seedu.partyplanet.logic.commands.DeleteCommand;
import seedu.partyplanet.logic.commands.EAddCommand;
import seedu.partyplanet.logic.commands.EDeleteCommand;
import seedu.partyplanet.logic.commands.EDoneCommand;
import seedu.partyplanet.logic.commands.EEditCommand;
import seedu.partyplanet.logic.commands.EListCommand;
import seedu.partyplanet.logic.commands.EditCommand;
import seedu.partyplanet.logic.commands.ExitCommand;
import seedu.partyplanet.logic.commands.HelpCommand;
import seedu.partyplanet.logic.commands.ListCommand;
import seedu.partyplanet.logic.commands.RedoCommand;
import seedu.partyplanet.logic.commands.ToggleThemeCommand;
import seedu.partyplanet.logic.commands.UndoCommand;
import seedu.partyplanet.logic.parser.exceptions.ParseException;

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
     * @param userInput full user input string.
     * @return the command based on the user input.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        // AddressBook Commands

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);


        // EventBook Commands

        case EAddCommand.COMMAND_WORD:
            return new EAddCommandParser().parse(arguments);

        case EEditCommand.COMMAND_WORD:
            return new EEditCommandParser().parse(arguments);

        case EDeleteCommand.COMMAND_WORD:
            return new EDeleteCommandParser().parse(arguments);

        case EDoneCommand.COMMAND_WORD:
            return new EDoneCommandParser().parse(arguments);

        case EListCommand.COMMAND_WORD:
            return new EListCommandParser().parse(arguments);

        // General Commands

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case ToggleThemeCommand.COMMAND_WORD:
            return new ToggleThemeCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
