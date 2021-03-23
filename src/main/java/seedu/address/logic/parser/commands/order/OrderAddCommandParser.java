package seedu.address.logic.parser.commands.order;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.core.Pair;
import seedu.address.logic.commands.order.OrderAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.commands.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class OrderAddCommandParser implements Parser<OrderAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public OrderAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATETIME, PREFIX_DISH, PREFIX_QUANTITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATETIME, PREFIX_DISH, PREFIX_QUANTITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, OrderAddCommand.MESSAGE_USAGE));
        }

        String customer = argMultimap.getValue(PREFIX_NAME).get().trim();
        String datetime = argMultimap.getValue(PREFIX_DATETIME).get().trim(); // not used in v1.2
        List<String> dishNums = argMultimap.getAllValues(PREFIX_DISH);
        List<String> dishQuants = argMultimap.getAllValues(PREFIX_QUANTITY);

        List<Pair<Integer, Integer>> dishQuantityList = new ArrayList<>();

        for (int idx = 0; idx < dishNums.size(); idx++) {
            Pair<Integer, Integer> orderComponent =
                    new Pair<>(Integer.parseInt(dishNums.get(idx)),
                            Integer.parseInt(dishQuants.get(idx)));
            dishQuantityList.add(orderComponent);
        }

        return new OrderAddCommand(datetime, customer, dishQuantityList);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
