package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRESSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.garment.ContainsKeywordsPredicate;
import seedu.address.model.garment.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(trimmedArgs, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION);

        ContainsKeywordsPredicate containsKeywordsPredicate = null;
        String[] keywords;

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
            containsKeywordsPredicate = new NameContainsKeywordsPredicate(Arrays.asList(keywords));
        }
        /*if (argMultimap.getValue(PREFIX_SIZE).isPresent()) {
            editGarmentDescriptor.setSize(ParserUtil.parseSize(argMultimap.getValue(PREFIX_SIZE).get()));
        }
        if (argMultimap.getValue(PREFIX_COLOUR).isPresent()) {
            editGarmentDescriptor.setColour(ParserUtil.parseColour(argMultimap.getValue(PREFIX_COLOUR).get()));
        }*/
        if (argMultimap.getValue(PREFIX_DRESSCODE).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
            containsKeywordsPredicate = new NameContainsKeywordsPredicate(Arrays.asList(keywords));
        }

        //String[] keywords = trimmedArgs.split("\\s+");

        return new FindCommand(containsKeywordsPredicate);
    }

}
