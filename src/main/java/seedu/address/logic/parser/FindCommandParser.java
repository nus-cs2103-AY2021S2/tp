package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRESSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.Optional;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.garment.AttributesContainsKeywordsPredicate;
import seedu.address.model.garment.Size;

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

    //maybe can separate to each prefix, but that sorta forces each prefix to be present
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
        Optional<String> dressCode = argMultimap.getValue(PREFIX_DRESSCODE);
        if (dressCode.isPresent() && isValidDressCode(dressCode)) {
            isValidSyntax = true;
            keywords = argMultimap.getValue(PREFIX_DRESSCODE).get().split("\\s+");
            isNotEmpty = isNotEmpty && isNotEmpty(keywords);
        }
        if (argMultimap.getValue(PREFIX_COLOUR).isPresent()) {
            isValidSyntax = true;
            keywords = argMultimap.getValue(PREFIX_COLOUR).get().split("\\s+");
            isNotEmpty = isNotEmpty && isNotEmpty(keywords);
        }
        Optional<String> size = argMultimap.getValue(PREFIX_SIZE);
        if (argMultimap.getValue(PREFIX_SIZE).isPresent() && isValidSize(size)) {
            isValidSyntax = true;
            keywords = argMultimap.getValue(PREFIX_SIZE).get().split("\\s+");
            isNotEmpty = isNotEmpty && isNotEmpty(keywords);
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            isValidSyntax = true;
            keywords = argMultimap.getValue(PREFIX_DESCRIPTION).get().split("\\s+");
            isNotEmpty = isNotEmpty && isNotEmpty(keywords);
        }
        Optional<String> type = argMultimap.getValue(PREFIX_TYPE);
        if (type.isPresent() && isValidType(type)) {
            isValidSyntax = true;
            keywords = argMultimap.getValue(PREFIX_TYPE).get().split("\\s+");
            isNotEmpty = isNotEmpty && isNotEmpty(keywords);
        }
        return isValidSyntax && isNotEmpty;
    }

    /**
     * Returns true if the all query dress codes are valid
     */
    public static boolean isValidDressCode(Optional<String> dressCode) {
        boolean isValid = true;
        String[] dressCodes = dressCode.get().split(" ");
        for (String d : dressCodes) {
            if (!d.equalsIgnoreCase("casual")
                    && !d.equalsIgnoreCase("formal")
                    && !d.equalsIgnoreCase("active")) {
                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * Returns true if the all query types are valid
     */
    public static boolean isValidType(Optional<String> type) {
        boolean isValid = true;
        String[] types = type.get().split(" ");
        for (String t : types) {
            if (!t.equalsIgnoreCase("upper")
                    && !t.equalsIgnoreCase("lower")
                    && !t.equalsIgnoreCase("footwear")) {
                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * Returns true if the all query sizes are valid
     */
    public static boolean isValidSize(Optional<String> size) {
        boolean isValid = true;
        String[] sizes = size.get().split(" ");
        for (String s : sizes) {
            if (!Size.isValidSize(s)) {
                isValid = false;
            }
        }
        return isValid;
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
