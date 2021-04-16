package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_COMPANY,
                        PREFIX_JOB_TITLE, PREFIX_ADDRESS, PREFIX_REMARK, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_COMPANY, PREFIX_JOB_TITLE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Company company = ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get());
        JobTitle jobTitle = ParserUtil.parseJobTitle(argMultimap.getValue(PREFIX_JOB_TITLE).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Remark remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).orElse(""));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(name, phone, email, company, jobTitle, address, remark, tagList);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns true if arguments are valid to be aliased. Only the argument in the
     * last prefix is allowed to be empty in order to be valid for aliasing, and all
     * other prefixes than the last should be valid input values.
     */
    @Override
    public boolean isValidCommandToAlias(String userInput) {
        if (userInput.trim().isEmpty()) {
            return true;
        }
        assert !userInput.trim().isEmpty();

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                userInput, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_COMPANY, PREFIX_JOB_TITLE,
                PREFIX_ADDRESS, PREFIX_TAG, PREFIX_REMARK);

        // Returns false if preamble exist, which should not be for AddCommand
        if (!argMultimap.getPreamble().isEmpty()) {
            return false;
        }

        // Checks if only the last prefix argument is empty, and all other prefixes have valid input values
        final Prefix lastPrefix = ArgumentTokenizer.getLastPrefix(
                userInput, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_COMPANY, PREFIX_JOB_TITLE,
                PREFIX_ADDRESS, PREFIX_TAG, PREFIX_REMARK).get();

        return ParserUtil.validatePersonAliasArgs(argMultimap, lastPrefix);
    }

}
