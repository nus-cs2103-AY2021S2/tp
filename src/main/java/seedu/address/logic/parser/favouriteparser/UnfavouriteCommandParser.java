package seedu.address.logic.parser.favouriteparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.tutorcommands.EditCommand.EditTutorDescriptor;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.favouritecommands.UnfavouriteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnfavouriteCommand object
 */
public class UnfavouriteCommandParser implements Parser<UnfavouriteCommand> {

    @Override
    public UnfavouriteCommand parse(String userInput) throws ParseException {

        String strippedUserInput = userInput.strip();

        Index index;
        try {
            index = ParserUtil.parseIndex(userInput);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnfavouriteCommand.MESSAGE_USAGE));
        }

        EditTutorDescriptor editTutorDescriptor = new EditTutorDescriptor();
        editTutorDescriptor.setIsFavourite(false);

        return new UnfavouriteCommand(index, editTutorDescriptor);

    }
}
