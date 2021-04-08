package seedu.address.logic.parser.room;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.room.DeleteRoomCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteRoomCommand object
 */
public class DeleteRoomCommandParser implements Parser<DeleteRoomCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteRoomCommand
     * and returns a DeleteRoomCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteRoomCommand parse(String userInput) throws ParseException {
        Index index = ParserUtil.parseIndex(userInput);

        assert index != null;

        return new DeleteRoomCommand(index);
    }
}
