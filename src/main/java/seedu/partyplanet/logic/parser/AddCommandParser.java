package seedu.partyplanet.logic.parser;

import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.partyplanet.logic.commands.AddCommand;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.person.Address;
import seedu.partyplanet.model.person.Birthday;
import seedu.partyplanet.model.person.Email;
import seedu.partyplanet.model.person.Name;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.model.person.Phone;
import seedu.partyplanet.model.person.Remark;
import seedu.partyplanet.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_BIRTHDAY, PREFIX_ADDRESS, PREFIX_REMARK, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).orElse(Phone.EMPTY_PHONE_STRING));
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).orElse(Email.EMPTY_EMAIL_STRING));
        Birthday birthday =
                ParserUtil.parseBirthday(argMultimap.getValue(PREFIX_BIRTHDAY).orElse(Birthday.EMPTY_DATE_STRING));
        Address address =
                ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).orElse(Address.EMPTY_ADDRESS_STRING));
        Remark remark =
            ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).orElse(Remark.EMPTY_REMARK_STRING));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(name, phone, email, birthday, address, remark, tagList);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
