package seedu.address.logic.parser;

import seedu.address.commons.core.identifier.Identifier;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            Identifier identifier = ParserUtil.parseIdentifier(args);
            return new DeleteCommand(identifier);
        } catch (ParseException pe) {
            if (pe.getMessage().equals(ParserUtil.MESSAGE_ADDITIONAL_ARTEFACTS)
                    || pe.getMessage().equals(ParserUtil.MESSAGE_EMPTY_IDENTIFIER)) {
                throw new ParseException(pe.getMessage()
                        + DeleteCommand.MESSAGE_USAGE);
            }
            throw new ParseException(pe.getMessage());
        }
    }

}
