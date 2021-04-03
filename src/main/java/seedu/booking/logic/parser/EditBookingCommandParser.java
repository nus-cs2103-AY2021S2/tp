package seedu.booking.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_END;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_ID;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_START;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.booking.logic.parser.ParserUtil.parseTagsForEdit;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.booking.commons.core.index.Index;
import seedu.booking.logic.commands.EditBookingCommand;
import seedu.booking.logic.commands.EditBookingCommand.EditBookingDescriptor;
import seedu.booking.logic.commands.EditCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.Tag;
import seedu.booking.model.booking.Id;

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

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBookingCommand.MESSAGE_USAGE), pe);
        }

//        if (!arePrefixesPresent(argMultimap, PREFIX_BOOKING_ID)
//                || argMultimap.getValue(PREFIX_BOOKING_ID).isEmpty()) {
//            throw new ParseException(
//                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBookingCommand.MESSAGE_USAGE));
//        }


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
            throw new ParseException(EditBookingCommand.MESSAGE_NOT_EDITED);
        }

        return new EditBookingCommand(index, editBookingDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
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
