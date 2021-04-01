package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteShortcutCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteShortcutCommand object
 */
public class DeleteShortcutCommandParser implements Parser<DeleteShortcutCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteShortcutCommand
     * and returns an DeleteShortcutCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteShortcutCommand parse(String args) throws ParseException {
        try {
            String shortcutName = ParserUtil.formatShortcutName(args);
            return new DeleteShortcutCommand(shortcutName);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteShortcutCommand.MESSAGE_USAGE), pe);
        }
    }
}
