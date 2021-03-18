package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.customer.Phone;
import seedu.address.model.order.OrderDate;
import seedu.address.model.order.Quantity;

/**
 * Parses input arguments and creates a new AddOrderCommand object
 */
public class AddOrderCommandParser implements Parser<AddOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddOrderCommand
     * and returns an AddOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOrderCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CHEESE_TYPE, PREFIX_PHONE, PREFIX_QUANTITY, PREFIX_ORDER_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_CHEESE_TYPE, PREFIX_PHONE, PREFIX_QUANTITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE));
        }

        CheeseType cheeseType = ParserUtil.parseCheeseType(argMultimap.getValue(PREFIX_CHEESE_TYPE).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Quantity quantity = ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get());
        OrderDate orderDate;

        if (argMultimap.getValue(PREFIX_ORDER_DATE).isPresent()) {
            orderDate = ParserUtil.parseOrderDate(argMultimap.getValue(PREFIX_ORDER_DATE).get());
        } else {
            orderDate = OrderDate.now();
        }

        return new AddOrderCommand(cheeseType, phone, quantity, orderDate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
