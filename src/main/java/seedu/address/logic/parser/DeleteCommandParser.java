package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;



/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
        String[] indexStrings = trimmedArgs.split("\\s+");
        assert indexStrings.length > 0 : "DeleteCommand indexes are empty";
        try {
            List<Index> indexes = stringArrayToIndexList(indexStrings);
            return new DeleteCommand(indexes);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
        // try {
        //     Index index = ParserUtil.parseIndex(args);
        //     return new DeleteCommand(index);
        // } catch (ParseException pe) {
        //     throw new ParseException(
        //             String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        // }
    }

    private List<Index> stringArrayToIndexList(String[] indexStrings) throws ParseException {
        List<Index> indexes = new ArrayList<>();
        for (String indexString : indexStrings) {
            indexes.add(ParserUtil.parseIndex(indexString));
        }
        return indexes;
    }
}
