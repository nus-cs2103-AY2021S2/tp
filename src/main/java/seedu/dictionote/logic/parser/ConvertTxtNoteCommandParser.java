package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.ConvertTxtNoteCommand;
import seedu.dictionote.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ConvertTxtNoteCommand object.
 */
public class ConvertTxtNoteCommandParser implements Parser<ConvertTxtNoteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ConvertTxtNoteCommand
     * and returns a ConvertTxtNoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ConvertTxtNoteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ConvertTxtNoteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            ConvertTxtNoteCommand.MESSAGE_USAGE), pe);
        }
    }
}
