package seedu.heymatez.logic.parser;

import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.heymatez.logic.commands.DeleteMemberCommand;
import seedu.heymatez.logic.parser.exceptions.ParseException;
import seedu.heymatez.model.person.Name;

/**
 * Parses input arguments and creates a new DeleteMemberCommand object
 */
public class DeleteMemberCommandParser implements Parser<DeleteMemberCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteMemberCommand
     * and returns a DeleteMemberCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteMemberCommand parse(String args) throws ParseException {
        try {
            Name name = ParserUtil.parseName(args);
            return new DeleteMemberCommand(name);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMemberCommand.MESSAGE_USAGE), pe);
        }
    }

}
