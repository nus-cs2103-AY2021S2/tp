package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddShortcutCommand;
import seedu.address.logic.commands.BatchCommand;
import seedu.address.logic.commands.ClearShortcutCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteShortcutCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditShortcutCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListShortcutCommand;
import seedu.address.logic.commands.LockCommand;
import seedu.address.logic.commands.MeetCommand;
import seedu.address.logic.commands.PolicyCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.UnlockCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.shortcut.ShortcutLibrary;

/**
 * Parses user input.
 */
public class ClientBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private static HashMap<String, String> shortcuts;

    public ClientBookParser() {
        shortcuts = new HashMap<>();
    }

    public ClientBookParser(ShortcutLibrary shortcutLibrary) {
        shortcuts = shortcutLibrary.getShortcuts();
    }

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

        case BatchCommand.COMMAND_WORD:
            return new BatchCommandParser().parse(arguments);

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case LockCommand.COMMAND_WORD:
            return new LockCommandParser().parse(arguments);

        case UnlockCommand.COMMAND_WORD:
            return new UnlockCommandParser().parse(arguments);

        case PolicyCommand.COMMAND_WORD:
            return new PolicyCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case MeetCommand.COMMAND_WORD:
            return new MeetCommandParser().parse(arguments);

        case AddShortcutCommand.COMMAND_WORD:
            return new AddShortcutCommandParser().parse(arguments);

        case DeleteShortcutCommand.COMMAND_WORD:
            return new DeleteShortcutCommandParser().parse(arguments);

        case EditShortcutCommand.COMMAND_WORD:
            return new EditShortcutCommandParser().parse(arguments);

        case ListShortcutCommand.COMMAND_WORD:
            return new ListShortcutCommand();

        case ClearShortcutCommand.COMMAND_WORD:
            return new ClearShortcutCommand();

        default:
            if (shortcuts.containsKey(commandWord)) {
                return this.parseCommand(shortcuts.get(commandWord));
            } else {
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        }
    }

}
