package seedu.booking.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_END;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_START;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.booking.commons.core.index.Index;
import seedu.booking.logic.commands.DeleteBookingCommand;
import seedu.booking.logic.commands.EditBookingCommand;
import seedu.booking.logic.commands.EditBookingCommand.EditBookingDescriptor;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.Tag;

public class EditBookingCommandParser implements Parser<EditBookingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditBookingCommand
     * and returns an EditBookingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public EditBookingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EMAIL, PREFIX_VENUE,
                        PREFIX_DESCRIPTION, PREFIX_BOOKING_START, PREFIX_BOOKING_END, PREFIX_TAG);

        Index index;

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBookingCommand.MESSAGE_USAGE));
        }

        index = ParserUtil.parseIndex(argMultimap.getPreamble());

        EditBookingDescriptor editBookingDescriptor = new EditBookingDescriptor();

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editBookingDescriptor.setBookerEmail(ParserUtil.parseEmail(
                    argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (argMultimap.getValue(PREFIX_VENUE).isPresent()) {
            editBookingDescriptor.setVenueName(ParserUtil.parseVenueName(
                    argMultimap.getValue(PREFIX_VENUE).get()));
        }

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editBookingDescriptor.setDescription(ParserUtil.parseBookingDescription(
                    argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }

        if (argMultimap.getValue(PREFIX_BOOKING_START).isPresent()) {
            editBookingDescriptor.setBookingStart(ParserUtil.parseBookingStart(
                    argMultimap.getValue(PREFIX_BOOKING_START).get()));
        }

        if (argMultimap.getValue(PREFIX_BOOKING_END).isPresent()) {
            editBookingDescriptor.setBookingEnd(ParserUtil.parseBookingEnd(
                    argMultimap.getValue(PREFIX_BOOKING_END).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editBookingDescriptor::setTags);

        if (!editBookingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditBookingCommand.MESSAGE_USAGE));
        }

        return new EditBookingCommand(index, editBookingDescriptor);
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
