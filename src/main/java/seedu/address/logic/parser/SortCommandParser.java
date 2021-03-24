package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIRECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_CRITERIA;

import java.util.NoSuchElementException;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_SORT_CRITERIA, PREFIX_DIRECTION);
        try {
            String sortCriteria = ParserUtil.parseCriteria(argMultimap.getValue(PREFIX_SORT_CRITERIA).get());
            boolean reverse = ParserUtil.parseIsAscending(argMultimap.getValue(PREFIX_DIRECTION).get());
            return new SortCommand(sortCriteria, reverse);
        } catch (NoSuchElementException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortCommand.MESSAGE_USAGE), ive);
        }
    }
}
