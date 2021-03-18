package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRESSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        ContainsKeywordsPredicate containsKeywordsPredicate = null;
        //String[] keywords;

        /*String[] check = trimmedArgs.split("/");//ok works but if no / gives an issue
        String trimRest = check[1].trim();
        String[] keywords = trimRest.split("\\s+");*/

        //potential issue if finding by more than 1 attribute
        //cant have >1 word to search
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            containsKeywordsPredicate =
                    new NameContainsKeywordsPredicate(Arrays.asList(argMultimap.getValue(PREFIX_NAME).get()));
        } else if (argMultimap.getValue(PREFIX_DRESSCODE).isPresent()) {
            containsKeywordsPredicate =
                    new DressCodeContainsKeywordsPredicate(Arrays.asList(argMultimap.getValue(PREFIX_DRESSCODE).get()));
        } else if (argMultimap.getValue(PREFIX_COLOUR).isPresent()) {
            containsKeywordsPredicate =
                    new ColourContainsKeywordsPredicate(Arrays.asList(argMultimap.getValue(PREFIX_COLOUR).get()));
        } else if (argMultimap.getValue(PREFIX_SIZE).isPresent()) {
            containsKeywordsPredicate =
                    new SizeContainsKeywordsPredicate(Arrays.asList(argMultimap.getValue(PREFIX_SIZE).get()));
        } else if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            containsKeywordsPredicate =
                    new DescriptionContainsKeywordsPredicate(Arrays.asList(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }

        //assume name no '/'
        /*String[] check = trimmedArgs.split("/");//ok works but if no / gives an issue
        String trimRest = check[1].trim();
        String[] keywords = trimRest.split("\\s+");*/
        /*List<String> wordList = new ArrayList<>();

        if (keywords[0] == "/n") {
            for (int i = 1; i < keywords.length; i++) {
                wordList.add(keywords[i]);
            }
        }*/

            //containsKeywordsPredicate = new NameContainsKeywordsPredicate(Arrays.asList(keywords));
        //            keywords = argMultimap.getValue(PREFIX_DRESSCODE).get().split("\\s+");
        //if containskeywords is null then throw illegal argument
        return new FindCommand(containsKeywordsPredicate);
    }

}
