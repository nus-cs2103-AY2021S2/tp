package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CARBOS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FATS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROTEINS;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddFoodItemCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.food.Food;

public class AddFoodItemCommandParser implements Parser<AddFoodItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddFoodItemCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PROTEINS, PREFIX_CARBOS, PREFIX_FATS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PROTEINS, PREFIX_CARBOS, PREFIX_FATS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFoodItemCommand.MESSAGE_USAGE));
        }

        String name = ParserUtil.parseFoodName(argMultimap.getValue(PREFIX_NAME).get());
        Double carbos = ParserUtil.parseDouble(argMultimap.getValue(PREFIX_CARBOS).get());
        Double fats = ParserUtil.parseDouble(argMultimap.getValue(PREFIX_FATS).get());
        Double proteins = ParserUtil.parseDouble(argMultimap.getValue(PREFIX_PROTEINS).get());

        Food newFood = new Food(name, carbos, fats, proteins);

        return new AddFoodItemCommand(newFood);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
