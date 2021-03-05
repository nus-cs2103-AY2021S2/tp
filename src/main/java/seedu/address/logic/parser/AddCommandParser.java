package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPIRYDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.ExpiryDate;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.Location;
import seedu.address.model.item.Quantity;
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
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_QUANTITY, PREFIX_EXPIRYDATE, PREFIX_LOCATION,
                PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_LOCATION, PREFIX_QUANTITY, PREFIX_EXPIRYDATE)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        ItemName name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Quantity quantity = ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get());
        ExpiryDate expiryDate = ParserUtil.parseExpiryDate(argMultimap.getValue(PREFIX_EXPIRYDATE).get());
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Item item = new Item(name, quantity, expiryDate, location, tagList);

        return new AddCommand(item);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
