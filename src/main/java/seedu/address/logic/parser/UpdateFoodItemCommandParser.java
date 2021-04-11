package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CARBOS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FATS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROTEINS;

import java.util.stream.Stream;

import seedu.address.logic.commands.UpdateFoodItemCommand;
import seedu.address.logic.commands.UpdateFoodItemCommand.EditFoodDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UpdateFoodItemCommand object
 */
public class UpdateFoodItemCommandParser implements Parser<UpdateFoodItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UpdateFoodItemCommand
     * and returns an UpdateFoodItemCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateFoodItemCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PROTEINS, PREFIX_CARBOS, PREFIX_FATS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UpdateFoodItemCommand.MESSAGE_NAME_MISSING));
        }

        EditFoodDescriptor editFoodDescriptor = new EditFoodDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editFoodDescriptor.setName(ParserUtil.parseFoodName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_CARBOS).isPresent()) {
            editFoodDescriptor.setCarbos(ParserUtil.parseDouble(argMultimap.getValue(PREFIX_CARBOS).get()));
        }
        if (argMultimap.getValue(PREFIX_FATS).isPresent()) {
            editFoodDescriptor.setFats(ParserUtil.parseDouble(argMultimap.getValue(PREFIX_FATS).get()));
        }
        if (argMultimap.getValue(PREFIX_PROTEINS).isPresent()) {
            editFoodDescriptor.setProteins(ParserUtil.parseDouble(argMultimap.getValue(PREFIX_PROTEINS).get()));
        }

        return new UpdateFoodItemCommand(editFoodDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
