package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parse input arguments and creates a new ViewCommand Object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] indexStrings = trimmedArgs.split("\\s+");
        List<Index> indexes = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            try {
                Index index = ParserUtil.parseIndex(indexStrings[i]);
                indexes.add(index);
            } catch (ParseException | ArrayIndexOutOfBoundsException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), pe);
            }
        }
        return new ViewCommand(indexes);
    }
}
