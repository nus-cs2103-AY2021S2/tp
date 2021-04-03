package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonTagContainsKeywordsPredicate;
import seedu.address.model.person.ReturnTruePredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG, PREFIX_ADDRESS);

        Predicate<Person> namePredicate = new ReturnTruePredicate();
        Predicate<Person> tagPredicate = new ReturnTruePredicate();
        Predicate<Person> addressPredicate = new ReturnTruePredicate();

        Optional<String> nameKeywords = argMultimap.getValue(PREFIX_NAME);
        Optional<String> tagKeywords = argMultimap.getValue(PREFIX_TAG);
        Optional<String> addressKeywords = argMultimap.getValue(PREFIX_ADDRESS);

        boolean allEmpty = nameKeywords.isEmpty() && tagKeywords.isEmpty() && addressKeywords.isEmpty();
        boolean emptyNameKeywords = nameKeywords.isPresent() && nameKeywords.get().equals("");
        boolean emptyTagKeywords = tagKeywords.isPresent() && tagKeywords.get().equals("");
        boolean emptyAddressKeywords = addressKeywords.isPresent() && addressKeywords.get().equals("");

        if (allEmpty || emptyNameKeywords || emptyTagKeywords || emptyAddressKeywords) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (nameKeywords.isPresent()) {
            String[] names = nameKeywords.get().split("\\s+");
            namePredicate = new NameContainsKeywordsPredicate(Arrays.asList(names));
        }
        if (tagKeywords.isPresent()) {
            String[] tags = tagKeywords.get().split("\\s+");
            tagPredicate = new PersonTagContainsKeywordsPredicate(Arrays.asList(tags));
        }
        if (addressKeywords.isPresent()) {
            String[] address = addressKeywords.get().split("\\s+");
            addressPredicate = new AddressContainsKeywordsPredicate(Arrays.asList(address));
        }
        return new FindCommand(namePredicate, tagPredicate, addressPredicate);
    }

}
