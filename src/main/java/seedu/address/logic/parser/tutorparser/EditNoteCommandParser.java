package seedu.address.logic.parser.tutorparser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tutorcommands.EditNoteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Notes;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.tutorcommands.EditCommand.EditPersonDescriptor;

public class EditNoteCommandParser implements Parser<EditNoteCommand> {

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

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setNotes(notes);

        return new EditNoteCommand(index, editPersonDescriptor);
    }
}
