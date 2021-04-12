package seedu.address.logic.parser.commands.order;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.order.OrderAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.commands.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object.
 */
public class OrderAddCommandParser implements Parser<OrderAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @SuppressWarnings("checkstyle:Indentation")
    public OrderAddCommand parse(String args) throws ParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATETIME, PREFIX_DISH, PREFIX_QUANTITY);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATETIME, PREFIX_DISH, PREFIX_QUANTITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, OrderAddCommand.MESSAGE_USAGE));
        }

        Integer customerId = ParserUtil.parseNonNegativeInt(argMultimap.getValue(PREFIX_NAME).get());
        String strDateTime = argMultimap.getValue(PREFIX_DATETIME).get();
        ParserUtil.validateDateTime(strDateTime);

        List<String> dishNumbers = argMultimap.getAllValues(PREFIX_DISH);
        List<String> dishQuantities = argMultimap.getAllValues(PREFIX_QUANTITY);
        ParserUtil.validateListLengths(dishNumbers, dishQuantities);

        List<Pair<Index, Integer>> dishQuantityList = new ArrayList<>();

        for (int i = 0; i < dishNumbers.size(); i++) {
            Pair<Index, Integer> orderComponent =
                    new Pair<>(ParserUtil.parseIndex(dishNumbers.get(i)),
                            ParserUtil.parseNonNegativeInt(dishQuantities.get(i)));
            dishQuantityList.add(orderComponent);
        }

        try {
            LocalDateTime dateTime = LocalDateTime.parse(strDateTime, dateTimeFormatter);
            return new OrderAddCommand(dateTime, customerId, dishQuantityList);
        } catch (DateTimeParseException e) {
            throw new ParseException(ParserUtil.MESSAGE_INVALID_DATETIME);
        }

    }
}
