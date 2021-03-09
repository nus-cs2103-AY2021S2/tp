package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROTEINS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CARBOS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FATS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.UpdateFoodItemCommand;
import seedu.address.logic.commands.UpdateFoodItemCommand.EditFoodDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

public class UpdateFoodItemCommandParser implements Parser<UpdateFoodItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the Update Command
     * and returns an UpdateFoodItemCommand object for execution.
     * @param args arguments passed in
     * @return an UpdateFoodItemCommand instance
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateFoodItemCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PROTEINS, PREFIX_CARBOS, PREFIX_FATS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditFoodDescriptor editFoodDescriptor = new EditFoodDescriptor();
        if(argMultimap.getValue(PREFIX_NAME).isPresent()) {
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
}
