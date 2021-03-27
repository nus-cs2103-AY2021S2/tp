package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_INDEX;

import java.util.NoSuchElementException;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MassDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class MassDeleteCommandParser implements Parser<MassDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MassDeleteCommand
     * and returns a MassDeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MassDeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_START_INDEX, PREFIX_END_INDEX);
        try {
            Index startIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_START_INDEX).get());
            Index endIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_END_INDEX).get());
            return new MassDeleteCommand(startIndex, endIndex);
        } catch (NoSuchElementException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MassDeleteCommand.MESSAGE_USAGE), ive);
        } catch (ParseException parseException) {
            throw new ParseException(String.format(MassDeleteCommand.MESSAGE_INVALID_START_INDEX,
                    MassDeleteCommand.MESSAGE_USAGE), parseException);
        }
    }
}
