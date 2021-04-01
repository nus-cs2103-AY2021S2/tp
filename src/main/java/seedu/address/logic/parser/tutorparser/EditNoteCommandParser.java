package seedu.address.logic.parser.tutorparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tutorcommands.EditCommand;
import seedu.address.logic.commands.tutorcommands.EditNoteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutor.Notes;

/**
 * Parses input arguments and creates a new EditNoteCommand object
 */
public class EditNoteCommandParser implements Parser<EditNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditNoteCommand
     * and returns an EditNoteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditNoteCommand parse(String userInput) throws ParseException {

        String strippedUserInput = userInput.strip();

        String[] split = strippedUserInput.split(" ", 2);

        if (split.length < 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteCommand.MESSAGE_USAGE));
        }

        String indexString = split[0];
        String noteString = split[1];

        Index index;
        try {
            index = ParserUtil.parseIndex(indexString);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteCommand.MESSAGE_USAGE), pe);
        }

        Notes notes;
        try {
            notes = ParserUtil.parseNotes(noteString);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteCommand.MESSAGE_USAGE), pe);
        }

        EditCommand.EditTutorDescriptor editTutorDescriptor = new EditCommand.EditTutorDescriptor();
        editTutorDescriptor.setNotes(notes);

        return new EditNoteCommand(index, editTutorDescriptor);
    }
}
