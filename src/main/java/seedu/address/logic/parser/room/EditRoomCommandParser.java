package seedu.address.logic.parser.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_TYPE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.room.EditRoomCommand;
import seedu.address.logic.commands.room.EditRoomCommand.EditRoomDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditRoomCommand object
 */
public class EditRoomCommandParser implements Parser<EditRoomCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditRoomCommand
     * and returns an EditRoomCommand object for execution.
     *
     * @param userInput The command {@code String} entered by the user.
     * @return The parsed {@code EditRoomCommand}.
     * @throws ParseException If the user input does not conform the expected format.
     * @throws NullPointerException If {@code userInput} is null.
     */
    @Override
    public EditRoomCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_ROOM_NUMBER, PREFIX_ROOM_TYPE, PREFIX_ROOM_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            assert index != null;
        } catch (IllegalArgumentException iex) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditRoomCommand.MESSAGE_USAGE), iex);
        }


        EditRoomDescriptor editRoomDescriptor = new EditRoomDescriptor();
        if (argMultimap.getValue(PREFIX_ROOM_NUMBER).isPresent()) {
            editRoomDescriptor
                    .setRoomNumber(ParserUtil.parseRoomNumber(argMultimap.getValue(PREFIX_ROOM_NUMBER).get()));
        }
        if (argMultimap.getValue(PREFIX_ROOM_TYPE).isPresent()) {
            editRoomDescriptor
                    .setRoomType(ParserUtil.parseRoomType(argMultimap.getValue(PREFIX_ROOM_TYPE).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_ROOM_TAG)).ifPresent(editRoomDescriptor::setTags);

        if (!editRoomDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditRoomCommand.MESSAGE_NOT_EDITED);
        }

        return new EditRoomCommand(index, editRoomDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
