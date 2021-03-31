package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteDateCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteDateCommandParser implements Parser<DeleteDateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteDateCommand
     * and returns a DeleteDateCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DeleteDateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteDateCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDateCommand.MESSAGE_USAGE));
        }

        Index dateIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());

        return new DeleteDateCommand(index, dateIndex);
    }
}
