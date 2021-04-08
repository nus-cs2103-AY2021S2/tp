package seedu.address.logic.parser.deletecommandparser;

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
     * Parses the given {@code String} of arguments in the context of the DeleteGeneralEventCommand
     * and returns a DeleteGeneralEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteGeneralEventCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GENERAL_EVENT);

        Index index = ParserUtil.parseGeneralEventIndex(argMultimap.getValue(PREFIX_GENERAL_EVENT).get());

        return new DeleteGeneralEventCommand(index);
    }
}
