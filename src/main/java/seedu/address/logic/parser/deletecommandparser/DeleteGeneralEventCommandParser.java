package seedu.address.logic.parser.deletecommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_EVENT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.deletecommand.DeleteGeneralEventCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteGeneralEventCommandParser extends DeleteCommandParser implements Parser<DeleteGeneralEventCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteGeneralEventCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_GENERAL_EVENT);
            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_GENERAL_EVENT).get());
            return new DeleteGeneralEventCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGeneralEventCommand.MESSAGE_USAGE), pe);
        }
    }
}
