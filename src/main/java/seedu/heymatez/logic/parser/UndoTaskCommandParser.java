package seedu.heymatez.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.heymatez.commons.core.index.Index;
import seedu.heymatez.logic.commands.UndoTaskCommand;
import seedu.heymatez.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UndoTaskCommand object
 */
public class UndoTaskCommandParser implements Parser<UndoTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UndoTaskCommand
     * and returns an UndoTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UndoTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoTaskCommand.MESSAGE_USAGE), pe);
        }

        return new UndoTaskCommand(index);
    }


}
