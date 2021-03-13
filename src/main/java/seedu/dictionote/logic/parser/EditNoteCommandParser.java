package seedu.dictionote.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_CONTENT;

import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.EditNoteCommand;
import seedu.dictionote.logic.commands.EditNoteCommand.EditNoteDescriptor;
import seedu.dictionote.logic.parser.exceptions.ParseException;
import seedu.dictionote.model.note.Note;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditNoteCommandParser implements Parser<EditNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CONTENT);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteCommand.MESSAGE_USAGE),
                    pe);
        }

        EditNoteDescriptor editNoteDescriptor = new EditNoteDescriptor();
        if (argMultimap.getValue(PREFIX_CONTENT).isPresent()) {
            editNoteDescriptor.setNote(new Note(ParserUtil.parseNote(argMultimap.getValue(PREFIX_CONTENT).get())));
        }
        return new EditNoteCommand(index, editNoteDescriptor);
    }
}
