package seedu.address.logic.parser.room;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.room.FindRoomCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.room.RoomNumberOrTagsContainsKeywordsPredicate;

public class FindRoomCommandParser implements Parser<FindRoomCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindRoomCommand
     * and returns a FindRoomCommand object for execution.
     *
     * @param userInput The command {@code String} entered by the user.
     * @return The parsed {@code FindRoomCommand}.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindRoomCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRoomCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindRoomCommand(new RoomNumberOrTagsContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
