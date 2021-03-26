package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLEAN_STATUS_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESIDENCE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.residence.BookingList;
import seedu.address.model.residence.Residence;
import seedu.address.model.residence.ResidenceAddress;
import seedu.address.model.residence.ResidenceName;
import seedu.address.model.tag.CleanStatusTag;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_RESIDENCE_ADDRESS,
                        PREFIX_BOOKING_DETAILS, PREFIX_CLEAN_STATUS_TAG, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_RESIDENCE_ADDRESS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        ResidenceName name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        ResidenceAddress address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_RESIDENCE_ADDRESS).get());

        BookingList bookingList;
        if (argMultimap.getAllValues(PREFIX_BOOKING_DETAILS).size() > 0) {
            bookingList = ParserUtil.parseBookingTime(
                    argMultimap.getValue(PREFIX_BOOKING_DETAILS).get());
        } else {
            bookingList = new BookingList();
        }

        CleanStatusTag cleanStatusTag;
        if (argMultimap.getAllValues(PREFIX_CLEAN_STATUS_TAG).size() > 0) {
            cleanStatusTag = ParserUtil.parseCleanStatusTag(
                    argMultimap.getValue(PREFIX_CLEAN_STATUS_TAG).get());
        } else {
            cleanStatusTag = new CleanStatusTag();
        }
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Residence residence = new Residence(name, address, bookingList, cleanStatusTag, tagList);

        return new AddCommand(residence);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
