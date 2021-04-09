package chim.logic.parser;

import static chim.commons.core.Messages.MESSAGE_CHEESE_QUANTITY_EXCEED;
import static chim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static chim.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static chim.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static chim.logic.parser.CliSyntax.PREFIX_MANUFACTURE_DATE;
import static chim.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.stream.Stream;

import chim.logic.commands.AddCheeseCommand;
import chim.logic.parser.exceptions.ParseException;
import chim.model.cheese.Cheese;
import chim.model.cheese.CheeseType;
import chim.model.cheese.ExpiryDate;
import chim.model.cheese.ManufactureDate;

/**
 * Parses input arguments and creates a new AddCustomerCommand object
 */
public class AddCheeseCommandParser implements Parser<AddCheeseCommand> {

    public static final int UPPER_BOUND_LIMIT = 1000;

    /**
     * Parses the given {@code String} of arguments in the context of the AddCustomerCommand
     * and returns an AddCustomerCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCheeseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CHEESE_TYPE, PREFIX_QUANTITY, PREFIX_MANUFACTURE_DATE,
                        PREFIX_EXPIRY_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_CHEESE_TYPE, PREFIX_QUANTITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCheeseCommand.MESSAGE_USAGE));
        }

        CheeseType cheeseType = ParserUtil.parseCheeseType(argMultimap.getValue(PREFIX_CHEESE_TYPE).get());

        int quantity = ParserUtil.parseInteger(argMultimap.getValue(PREFIX_QUANTITY).get());

        if (quantity > UPPER_BOUND_LIMIT) {
            throw new ParseException(MESSAGE_CHEESE_QUANTITY_EXCEED);
        }

        ManufactureDate manufactureDate;
        if (argMultimap.getValue(PREFIX_MANUFACTURE_DATE).isPresent()) {
            manufactureDate = ParserUtil.parseManufactureDate(argMultimap.getValue(PREFIX_MANUFACTURE_DATE).get());
        } else {
            manufactureDate = ManufactureDate.now();
        }

        ExpiryDate expiryDate = null;
        if (argMultimap.getValue(PREFIX_EXPIRY_DATE).isPresent()) {
            expiryDate = ParserUtil.parseExpiryDate(argMultimap.getValue(PREFIX_EXPIRY_DATE).get());
        }

        Cheese[] cheeses = new Cheese[quantity];
        try {
            for (int i = 0; i < quantity; i++) {
                cheeses[i] = new Cheese(cheeseType, manufactureDate, expiryDate);
            }
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }

        return new AddCheeseCommand(cheeses);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
