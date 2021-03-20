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
import seedu.address.model.garment.ColourContainsKeywordsPredicate;
import seedu.address.model.garment.ContainsKeywordsPredicate;
import seedu.address.model.garment.DescriptionContainsKeywordsPredicate;
import seedu.address.model.garment.DressCodeContainsKeywordsPredicate;
import seedu.address.model.garment.NameContainsKeywordsPredicate;
import seedu.address.model.garment.SizeContainsKeywordsPredicate;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION);

        ContainsKeywordsPredicate containsKeywordsPredicate;
        String[] keywords;

        //potential issue if finding by more than 1 attribute, looks at 1st thing in here, if multiple of the same
        // attribute will look at last one, eg find n/x n/y will find n/y, not both
        //another issue when finding a description with >1 word causes bug
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
            containsKeywordsPredicate =
                    new NameContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (argMultimap.getValue(PREFIX_DRESSCODE).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_DRESSCODE).get().split("\\s+");
            containsKeywordsPredicate =
                    new DressCodeContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (argMultimap.getValue(PREFIX_COLOUR).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_COLOUR).get().split("\\s+");
            containsKeywordsPredicate =
                    new ColourContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (argMultimap.getValue(PREFIX_SIZE).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_SIZE).get().split("\\s+");
            containsKeywordsPredicate =
                    new SizeContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_DESCRIPTION).get().split("\\s+");
            containsKeywordsPredicate =
                    new DescriptionContainsKeywordsPredicate(Arrays.asList(keywords));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        if (keywords[0].equals("")) { //Empty arguments
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(containsKeywordsPredicate);
    }
}
