package seedu.address.logic.parser.favouriteparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.tutorcommands.EditCommand.EditPersonDescriptor;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.favouritecommands.FavouriteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FavouriteCommand object
 */
public class FavouriteCommandParser implements Parser<FavouriteCommand> {

    @Override
    public FavouriteCommand parse(String userInput) throws ParseException {

        String strippedUserInput = userInput.strip();

        Index index;
        try {
            index = ParserUtil.parseIndex(strippedUserInput);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FavouriteCommand.MESSAGE_USAGE));
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setIsFavourite(true);

        return new FavouriteCommand(index, editPersonDescriptor);
    }
}
