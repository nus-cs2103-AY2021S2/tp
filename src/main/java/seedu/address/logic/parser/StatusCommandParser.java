package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.StatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new StatusCommand object
 */
public class StatusCommandParser implements Parser<StatusCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the StatusCommand
     * and returns an StatusCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public StatusCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatusCommand.MESSAGE_USAGE));
        }

        String[] keyword = trimmedArgs.split("\\s+");
        if (keyword.length < 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatusCommand.MESSAGE_USAGE));
        }
        String status = keyword[0];
        if (!(status.equals("clean") || status.equals("unclean"))) {
            throw new ParseException(StatusCommand.MESSAGE_ERROR_STATUS);
        }
        ArrayList<Index> indexArray = new ArrayList<>();
        for (int i = 1; i < keyword.length; i++) {
            indexArray.add(ParserUtil.parseIndex(keyword[i]));
        }

        return new StatusCommand(indexArray, status);
    }

}
