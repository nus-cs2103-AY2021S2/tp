package seedu.address.logic.parser.filterparser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.logic.commands.filtercommands.AddPersonFilterCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.filter.NameFilter;
import seedu.address.model.filter.PersonFilter;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new AddPersonFilterCommand object
 */
public class AddPersonFilterCommandParser implements Parser<AddPersonFilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPersonFilterCommand
     * and returns an AddPersonFilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPersonFilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_GENDER, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_TAG, PREFIX_SUBJECT_NAME, PREFIX_RATE, PREFIX_EDUCATION_LEVEL,
                        PREFIX_YEAR, PREFIX_QUALIFICATION);

        Set<Predicate<Name>> nameFilters = new LinkedHashSet<Predicate<Name>>(
                argMultimap.getAllValues(PREFIX_NAME).stream()
                        .map(NameFilter::new)
                        .collect(Collectors.toList()));

        // TODO: Filter other attributes
        // TODO: Check if no arguments
        // TODO: Throw ParseException
        // TODO: Maybe switch to using ParserUtil

        PersonFilter personFilter = new PersonFilter(nameFilters);

        return new AddPersonFilterCommand(personFilter);
    }
}
