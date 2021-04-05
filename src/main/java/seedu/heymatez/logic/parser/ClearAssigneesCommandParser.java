package seedu.heymatez.logic.parser;

import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.heymatez.logic.parser.ParserUtil.MESSAGE_NON_POSITIVE_INDEX;

import seedu.heymatez.commons.core.index.Index;
import seedu.heymatez.logic.commands.ClearAssigneesCommand;
import seedu.heymatez.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new ClearAssigneesCommand object
 */
public class ClearAssigneesCommandParser implements Parser<ClearAssigneesCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClearAssigneesCommand
     * and returns a ClearAssigneesCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearAssigneesCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ClearAssigneesCommand(index);
        } catch (ParseException pe) {
            if (pe.getMessage().equals(MESSAGE_NON_POSITIVE_INDEX)) {
                throw new ParseException(MESSAGE_INVALID_TASK_DISPLAYED_INDEX, pe);
            }
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearAssigneesCommand.MESSAGE_USAGE), pe);
        }
    }
}
