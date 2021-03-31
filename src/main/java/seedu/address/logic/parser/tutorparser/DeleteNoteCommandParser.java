package seedu.address.logic.parser.tutorparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.tutorcommands.EditCommand.EditTutorDescriptor;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tutorcommands.DeleteNoteCommand;
import seedu.address.logic.commands.tutorcommands.EditCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutor.Notes;

/**
 * Parses input arguments and creates a new DeleteNoteCommand object
 */
public class DeleteNoteCommandParser implements Parser<DeleteNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteNoteCommand
     * and returns an DeleteNoteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteNoteCommand parse(String userInput) throws ParseException {

        String strippedUserInput = userInput.strip();

        Index index;
        try {
            index = ParserUtil.parseIndex(strippedUserInput);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteNoteCommand.MESSAGE_USAGE), pe);
        }

        Notes notes = new Notes();

        EditTutorDescriptor editTutorDescriptor = new EditCommand.EditTutorDescriptor();
        editTutorDescriptor.setNotes(notes);

        return new DeleteNoteCommand(index, editTutorDescriptor);
    }
}
