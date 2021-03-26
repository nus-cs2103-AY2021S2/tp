package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_ASKING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.add.AddPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.name.Name;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Property;
import seedu.address.model.property.Type;
import seedu.address.model.property.client.AskingPrice;
import seedu.address.model.property.client.Client;
import seedu.address.model.property.client.Contact;
import seedu.address.model.property.client.Email;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddPropertyCommand object.
 */
public class AddPropertyCommandParser implements Parser<AddPropertyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPropertyCommand
     * and returns an AddPropertyCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public AddPropertyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TYPE, PREFIX_ADDRESS, PREFIX_POSTAL,
                        PREFIX_DEADLINE, PREFIX_REMARK, PREFIX_CLIENT_NAME, PREFIX_CLIENT_CONTACT,
                        PREFIX_CLIENT_EMAIL, PREFIX_CLIENT_ASKING_PRICE, PREFIX_TAGS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TYPE, PREFIX_ADDRESS, PREFIX_POSTAL, PREFIX_DEADLINE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Type type = ParserUtil.parsePropertyType(argMultimap.getValue(PREFIX_TYPE).get());
        Address address = ParserUtil.parsePropertyAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        PostalCode postal = ParserUtil.parsePropertyPostal(argMultimap.getValue(PREFIX_POSTAL).get());
        Deadline deadline = ParserUtil.parsePropertyDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getValue(PREFIX_TAGS).orElse(null));
        Remark remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).orElse(null));

        if (!anyClientPrefixesPresent(argMultimap, PREFIX_CLIENT_NAME, PREFIX_CLIENT_CONTACT, PREFIX_CLIENT_EMAIL,
                PREFIX_CLIENT_ASKING_PRICE)) {
            Property property = new Property(name, type, address, postal, deadline, remark, tagList);
            return new AddPropertyCommand(property);
        }

        Name clientName = ParserUtil.parseName(argMultimap.getValue(PREFIX_CLIENT_NAME).orElse(null));
        Contact clientContact =
                ParserUtil.parseClientContact(argMultimap.getValue(PREFIX_CLIENT_CONTACT).orElse(null));
        Email clientEmail = ParserUtil.parseClientEmail(argMultimap.getValue(PREFIX_CLIENT_EMAIL).orElse(null));
        AskingPrice clientAskingPrice =
                ParserUtil.parseClientAskingPrice(argMultimap.getValue(PREFIX_CLIENT_ASKING_PRICE).orElse(null));
        Client client = new Client(clientName, clientContact, clientEmail, clientAskingPrice);

        Property property = new Property(name, type, address, postal, deadline, remark, client, tagList);
        return new AddPropertyCommand(property);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if any of the client prefixes are present in the given {@code ArgumentMultimap}.
     */
    private static boolean anyClientPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
