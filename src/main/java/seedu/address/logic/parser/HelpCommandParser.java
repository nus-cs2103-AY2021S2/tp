package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class HelpCommandParser implements Parser {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public Command parse(String args) throws ParseException {
        String trimmedArgs = args.trim().toLowerCase();
        if (trimmedArgs.isEmpty()) {
            return new HelpCommand();
        }

        String[] specifiedCommands = trimmedArgs.split("\\s+");

        if (specifiedCommands.length > 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        // isValidCommand(trimmedArgs);

        return new HelpCommand(trimmedArgs);
    }

    private void isValidCommand(String trimmedArgs) throws ParseException {
        switch (trimmedArgs) {
        case AddCommand.COMMAND_WORD:
        case EditCommand.COMMAND_WORD:
        case DeleteCommand.COMMAND_WORD:
        case ClearCommand.COMMAND_WORD:
        case FindCommand.COMMAND_WORD:
        case ListCommand.COMMAND_WORD:
        case ExitCommand.COMMAND_WORD:
        case HelpCommand.COMMAND_WORD:
            return;
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
