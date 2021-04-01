package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRESSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.garment.AttributesContainsKeywordsPredicate;

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
                        PREFIX_DESCRIPTION, PREFIX_TYPE);

        if (!(isValidInput(argMultimap))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        AttributesContainsKeywordsPredicate predicates = new AttributesContainsKeywordsPredicate(argMultimap);
        return new FindCommand(predicates);
    }

    //maybe can separate to each prefix, but that sorta forces each prefic to be present
    /**
     * Returns true if the argumentMultiMap has valid prefixes and is non-empty
     */
    public boolean isValidInput(ArgumentMultimap argMultimap) {
        String[] keywords = {""};
        boolean isValidSyntax = false;
        boolean isNotEmpty = true;
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            isValidSyntax = true;
            keywords = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
            isNotEmpty = isNotEmpty && isNotEmpty(keywords);
        }
        if (argMultimap.getValue(PREFIX_DRESSCODE).isPresent()) {
            isValidSyntax = true;
            keywords = argMultimap.getValue(PREFIX_DRESSCODE).get().split("\\s+");
            isNotEmpty = isNotEmpty && isNotEmpty(keywords);
        }
        if (argMultimap.getValue(PREFIX_COLOUR).isPresent()) {
            isValidSyntax = true;
            keywords = argMultimap.getValue(PREFIX_COLOUR).get().split("\\s+");
            isNotEmpty = isNotEmpty && isNotEmpty(keywords);
        }
        if (argMultimap.getValue(PREFIX_SIZE).isPresent()) {
            isValidSyntax = true;
            keywords = argMultimap.getValue(PREFIX_SIZE).get().split("\\s+");
            isNotEmpty = isNotEmpty && isNotEmpty(keywords);
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            isValidSyntax = true;
            keywords = argMultimap.getValue(PREFIX_DESCRIPTION).get().split("\\s+");
            isNotEmpty = isNotEmpty && isNotEmpty(keywords);
        }
        if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
            isValidSyntax = true;
            keywords = argMultimap.getValue(PREFIX_TYPE).get().split("\\s+");
            isNotEmpty = isNotEmpty && isNotEmpty(keywords);
        }
        return isValidSyntax && isNotEmpty;
    }

    /**
     * Returns true if the input is non empty
     */
    public boolean isNotEmpty(String[] input) {
        if (!(input[0].equals(""))) {
            return true;
        } else {
            return false;
        }
    }
}
