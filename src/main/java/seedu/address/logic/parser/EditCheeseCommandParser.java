package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MANUFACTURE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATURITY_DATE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCheeseCommand;
import seedu.address.logic.commands.EditCheeseCommand.EditCheeseDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new EditCheeseCommand object
 */
public class EditCheeseCommandParser implements Parser<EditCheeseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCheeseCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CHEESE_TYPE,
                        PREFIX_MANUFACTURE_DATE, PREFIX_MATURITY_DATE, PREFIX_EXPIRY_DATE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCheeseCommand.MESSAGE_USAGE), pe);
        }

        EditCheeseDescriptor editCheeseDescriptor = new EditCheeseDescriptor();
        if (argMultimap.getValue(PREFIX_CHEESE_TYPE).isPresent()) {
            editCheeseDescriptor.setCheeseType(
                    ParserUtil.parseCheeseType(argMultimap.getValue(PREFIX_CHEESE_TYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_MANUFACTURE_DATE).isPresent()) {
            editCheeseDescriptor.setManufactureDate(
                    ParserUtil.parseManufactureDate(argMultimap.getValue(PREFIX_MANUFACTURE_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_MATURITY_DATE).isPresent()) {
            editCheeseDescriptor.setMaturityDate(
                    ParserUtil.parseMaturityDate(argMultimap.getValue(PREFIX_MATURITY_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_EXPIRY_DATE).isPresent()) {
            editCheeseDescriptor.setExpiryDate(
                    ParserUtil.parseExpiryDate(argMultimap.getValue(PREFIX_EXPIRY_DATE).get()));
        }

        if (!editCheeseDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCheeseCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCheeseCommand(index, editCheeseDescriptor);
    }

}
