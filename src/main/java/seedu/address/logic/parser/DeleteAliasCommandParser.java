package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteAliasCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.alias.Alias;

/**
 * Parses input arguments and creates a new DeleteAliasCommand object
 */
public class DeleteAliasCommandParser implements Parser<DeleteAliasCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAliasCommand
     * and returns a DeleteAliasCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteAliasCommand parse(String args) throws ParseException {
        try {
            Alias alias = ParserUtil.parseAlias(args);
            return new DeleteAliasCommand(alias);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAliasCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAliasCommand
     * and returns true if arguments are valid to be aliased.
     */
    @Override
    public boolean isValidCommandToAlias(String userInput) {
        if (userInput.trim().isEmpty()) {
            return true;
        }

        try {
            ParserUtil.parseAlias(userInput);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

}
