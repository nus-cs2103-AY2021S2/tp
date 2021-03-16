package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCustomerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.predicates.CustomerAddressPredicate;
import seedu.address.model.customer.predicates.CustomerEmailPredicate;
import seedu.address.model.customer.predicates.CustomerNamePredicate;
import seedu.address.model.customer.predicates.CustomerPhonePredicate;
import seedu.address.model.util.predicate.CompositeFieldPredicateBuilder;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCustomerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCustomerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        if (!areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCustomerCommand.MESSAGE_USAGE));
        }

        CompositeFieldPredicateBuilder<Customer> pBuilder = new CompositeFieldPredicateBuilder<>();
        Optional<String> nameArg = argMultimap.getValue(PREFIX_NAME);
        if (nameArg.isPresent()) {
            List<String> nameKeywords = splitToKeywordsList(nameArg.get());
            pBuilder.compose(new CustomerNamePredicate(nameKeywords));
        }

        Optional<String> phoneArg = argMultimap.getValue(PREFIX_PHONE);
        if (phoneArg.isPresent()) {
            List<String> phoneKeywords = splitToKeywordsList(phoneArg.get());
            pBuilder.compose(new CustomerPhonePredicate(phoneKeywords));
        }

        Optional<String> emailArg = argMultimap.getValue(PREFIX_EMAIL);
        if (emailArg.isPresent()) {
            List<String> emailKeywords = splitToKeywordsList(emailArg.get());
            pBuilder.compose(new CustomerEmailPredicate(emailKeywords));
        }

        Optional<String> addressArg = argMultimap.getValue(PREFIX_ADDRESS);
        if (addressArg.isPresent()) {
            List<String> addressKeywords = splitToKeywordsList(addressArg.get());
            pBuilder.compose(new CustomerAddressPredicate(addressKeywords));
        }

        return new FindCustomerCommand(pBuilder.build());
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static List<String> splitToKeywordsList(String keywords) {
        return Arrays.asList(keywords.trim().split("\\s+"));
    }

}
