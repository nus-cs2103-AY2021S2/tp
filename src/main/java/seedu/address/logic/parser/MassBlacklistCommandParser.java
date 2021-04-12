package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLACKLIST;

import java.util.NoSuchElementException;

import javafx.util.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MassBlacklistCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MassBlacklistCommand object.
 */
public class MassBlacklistCommandParser implements Parser<MassBlacklistCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MassBlacklistCommand
     * and returns a MassBlacklistCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public MassBlacklistCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_BLACKLIST);
        try {
            Pair<Index, Index> range = ParserUtil.parseRange(argMultimap.getPreamble());
            boolean toBlacklist = ParserUtil.parseBlacklistKeyword(argMultimap.getValue(PREFIX_BLACKLIST).get());
            return new MassBlacklistCommand(range.getKey(), range.getValue(), toBlacklist);
        } catch (ParseException | NoSuchElementException exception) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MassBlacklistCommand.MESSAGE_USAGE), exception);
        }
    }
}
