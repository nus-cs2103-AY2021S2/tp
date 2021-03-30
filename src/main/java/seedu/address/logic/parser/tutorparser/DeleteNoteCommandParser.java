package seedu.address.logic.parser.tutorparser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tutorcommands.DeleteNoteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Notes;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.tutorcommands.EditCommand.EditPersonDescriptor;

public class DeleteNoteCommandParser implements Parser<DeleteNoteCommand> {

    @Override
    public DeleteNoteCommand parse(String userInput) throws ParseException {

        String strippedUserInput = userInput.strip();

        Index index;
        try {
            index = ParserUtil.parseIndex(strippedUserInput);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteNoteCommand.MESSAGE_USAGE), pe);
        }

        Notes notes = new Notes();

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setNotes(notes);

        return new DeleteNoteCommand(index, editPersonDescriptor);
    }
}
