package seedu.module.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.module.commons.core.index.Index;
import seedu.module.commons.exceptions.IllegalIntegerException;
import seedu.module.logic.commands.NotDoneCommand;
import seedu.module.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new NotDoneCommand object
 */
public class NotDoneCommandParser implements Parser<NotDoneCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the NotDoneCommand
     * and returns an NotDoneCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NotDoneCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotDoneCommand.MESSAGE_USAGE), pe);
        } catch (IllegalIntegerException iie) {
            throw new ParseException(iie.getMessage());
        }

        return new NotDoneCommand(index);
    }
}
