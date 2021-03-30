package seedu.budgetbaby.ablogic.parser;

import static seedu.budgetbaby.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.budgetbaby.ablogic.commands.AddCommand;
import seedu.budgetbaby.abmodel.person.Address;
import seedu.budgetbaby.abmodel.person.Email;
import seedu.budgetbaby.abmodel.person.Name;
import seedu.budgetbaby.abmodel.person.Person;
import seedu.budgetbaby.abmodel.person.Phone;
import seedu.budgetbaby.logic.parser.ArgumentMultimap;
import seedu.budgetbaby.logic.parser.ArgumentTokenizer;
import seedu.budgetbaby.logic.parser.Parser;
import seedu.budgetbaby.logic.parser.ParserUtil;
import seedu.budgetbaby.logic.parser.Prefix;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.record.Category;

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
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_CATEGORY);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Category> categoryList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_CATEGORY));

        Person person = new Person(name, phone, email, address, categoryList);

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
