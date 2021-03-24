package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FavouriteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class FavouriteCommandParser implements Parser<FavouriteCommand> {

    @Override
    public FavouriteCommand parse(String args) throws ParseException {

        try {
            Index index = ParserUtil.parseIndex(args);
            return new FavouriteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FavouriteCommand.MESSAGE_USAGE));
        }
    }
}
