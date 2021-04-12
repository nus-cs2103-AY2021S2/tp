package seedu.address.logic.parser.room;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

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
     * @param userInput The command {@code String} entered by the user.
     * @return The parsed {@code AddRoomCommand}.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public DeleteRoomCommand parse(String userInput) throws ParseException {

        try {
            Index index = ParserUtil.parseIndex(userInput);
            assert index != null;
            return new DeleteRoomCommand(index);
        } catch (IllegalArgumentException iex) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteRoomCommand.MESSAGE_USAGE), iex);
        }
    }
}
