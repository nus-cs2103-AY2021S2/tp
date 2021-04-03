package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.NoSuchElementException;

import javafx.util.Pair;
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
        try {
            Pair<Index, Index> range = ParserUtil.parseRange(args);
            return new MassDeleteCommand(range.getKey(), range.getValue());
        } catch (NoSuchElementException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MassDeleteCommand.MESSAGE_USAGE), ive);
        }
    }
}
