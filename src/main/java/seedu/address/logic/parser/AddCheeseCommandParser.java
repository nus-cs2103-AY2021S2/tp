package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MANUFACTURE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATURITY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCheeseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.cheese.ExpiryDate;
import seedu.address.model.cheese.ManufactureDate;
import seedu.address.model.cheese.MaturityDate;

/**
 * Parses input arguments and creates a new AddCustomerCommand object
 */
public class AddCheeseCommandParser implements Parser<AddCheeseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCustomerCommand
     * and returns an AddCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCheeseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CHEESE_TYPE, PREFIX_QUANTITY,
                        PREFIX_MANUFACTURE_DATE, PREFIX_MATURITY_DATE, PREFIX_EXPIRY_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_CHEESE_TYPE, PREFIX_QUANTITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCheeseCommand.MESSAGE_USAGE));
        }

        CheeseType cheeseType = ParserUtil.parseCheeseType(argMultimap.getValue(PREFIX_CHEESE_TYPE).get());

        int quantity = ParserUtil.parseInteger(argMultimap.getValue(PREFIX_QUANTITY).get());

        ManufactureDate manufactureDate;
        if (argMultimap.getValue(PREFIX_MANUFACTURE_DATE).isPresent()) {
            manufactureDate = ParserUtil.parseManufactureDate(argMultimap.getValue(PREFIX_MANUFACTURE_DATE).get());
        } else {
            manufactureDate = ManufactureDate.now();
        }

        MaturityDate maturityDate = null;
        if (argMultimap.getValue(PREFIX_MATURITY_DATE).isPresent()) {
            maturityDate = ParserUtil.parseMaturityDate(argMultimap.getValue(PREFIX_MATURITY_DATE).get());
        }

        ExpiryDate expiryDate = null;
        if (argMultimap.getValue(PREFIX_EXPIRY_DATE).isPresent()) {
            expiryDate = ParserUtil.parseExpiryDate(argMultimap.getValue(PREFIX_EXPIRY_DATE).get());
        }

        Cheese[] cheeses = new Cheese[quantity];
        for (int i = 0; i < quantity; i++) {
            cheeses[i] = new Cheese(cheeseType, manufactureDate, maturityDate, expiryDate);
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
