package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.ShowNoteCommand;
import seedu.dictionote.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ShowNoteCommand object.
 */
public class ShowNoteCommandParser implements Parser<ShowNoteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ShowNoteCommand.
     * and returns a ShowNoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ShowNoteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ShowNoteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowNoteCommand.MESSAGE_USAGE), pe);
        }
    }
}
