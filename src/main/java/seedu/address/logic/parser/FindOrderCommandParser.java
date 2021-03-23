package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_COMPLETION_STATUS;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Order;
import seedu.address.model.util.predicate.CompositeFieldPredicateBuilder;

/**
 * Parses input arguments and creates a new FindOrderCommand
 */
public class FindOrderCommandParser implements Parser<FindOrderCommand> {

    /**
     * Parses the given {@code String} of arugments in the context of FindOrderCommand
     * and returns a FindOrderCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindOrderCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CHEESE_TYPE,
                PREFIX_ORDER_COMPLETION_STATUS);

        if (!areAnyPrefixesPresent(argMultimap, PREFIX_CHEESE_TYPE, PREFIX_ORDER_COMPLETION_STATUS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
        }

        CompositeFieldPredicateBuilder<Order> pBuilder = new CompositeFieldPredicateBuilder<>();

        Optional<String> cheeseTypeArg = argMultimap.getValue(PREFIX_CHEESE_TYPE);
        if (cheeseTypeArg.isPresent()) {
            pBuilder.compose(ParserUtil.parseOrderCheeseTypeKeywords(cheeseTypeArg.get()));
        }

        Optional<String> statusArg = argMultimap.getValue(PREFIX_ORDER_COMPLETION_STATUS);
        if (statusArg.isPresent()) {
            pBuilder.compose(ParserUtil.parseOrderCompletionStatusKeyword(statusArg.get()));
        }

        return new FindOrderCommand(pBuilder.build());
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
