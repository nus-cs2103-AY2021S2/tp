package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.EmailContactCommand;
import seedu.dictionote.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EmailContactCommand object
 */
public class EmailContactCommandParser implements Parser<EmailContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EmailContactCommand
     * and returns an EmailContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EmailContactCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new EmailContactCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EmailContactCommand.MESSAGE_USAGE), pe);
        }
    }

}
