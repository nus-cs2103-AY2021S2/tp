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
import seedu.address.model.description.Description;
import seedu.address.model.garment.AttributesContainsKeywordsPredicate;
import seedu.address.model.garment.Colour;
import seedu.address.model.garment.DressCode;
import seedu.address.model.garment.Name;
import seedu.address.model.garment.Size;
import seedu.address.model.garment.Type;

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
        Optional<String> nameOpt = argMultimap.getValue(PREFIX_NAME);
        if (nameOpt.isPresent() && isValidName(nameOpt.get())) {
            isValidSyntax = true;
            keywords = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
            isNotEmpty = isNotEmpty && isNotEmpty(keywords);
        }
        Optional<String> dressCodeOpt = argMultimap.getValue(PREFIX_DRESSCODE);
        if (dressCodeOpt.isPresent() && isValidDressCode(dressCodeOpt.get())) {
            isValidSyntax = true;
            keywords = argMultimap.getValue(PREFIX_DRESSCODE).get().split("\\s+");
            isNotEmpty = isNotEmpty && isNotEmpty(keywords);
        }
        Optional<String> colourOpt = argMultimap.getValue(PREFIX_COLOUR);
        if (colourOpt.isPresent() && isValidColour(colourOpt.get())) {
            isValidSyntax = true;
            keywords = argMultimap.getValue(PREFIX_COLOUR).get().split("\\s+");
            isNotEmpty = isNotEmpty && isNotEmpty(keywords);
        }
        Optional<String> sizeOpt = argMultimap.getValue(PREFIX_SIZE);
        if (sizeOpt.isPresent() && isValidSize(sizeOpt.get())) {
            isValidSyntax = true;
            keywords = argMultimap.getValue(PREFIX_SIZE).get().split("\\s+");
            isNotEmpty = isNotEmpty && isNotEmpty(keywords);
        }
        Optional<String> descriptionOpt = argMultimap.getValue(PREFIX_DESCRIPTION);
        if (descriptionOpt.isPresent() && isValidDescription(descriptionOpt.get())) {
            isValidSyntax = true;
            keywords = argMultimap.getValue(PREFIX_DESCRIPTION).get().split("\\s+");
            isNotEmpty = isNotEmpty && isNotEmpty(keywords);
        }
        Optional<String> typeOpt = argMultimap.getValue(PREFIX_TYPE);
        if (typeOpt.isPresent() && isValidType(typeOpt.get())) {
            isValidSyntax = true;
            keywords = argMultimap.getValue(PREFIX_TYPE).get().split("\\s+");
            isNotEmpty = isNotEmpty && isNotEmpty(keywords);
        }
        return isValidSyntax && isNotEmpty;
    }

    /**
     * Returns true if the all query dress codes are valid
     */
    public static boolean isValidDressCode(String dressCode) {
        boolean isValid = true;
        String[] dressCodes = dressCode.split(" ");
        for (String d : dressCodes) {
            if (!DressCode.isValidDressCode(d)) {
                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * Returns true if the all query types are valid
     */
    public static boolean isValidType(String type) {
        boolean isValid = true;
        String[] types = type.split(" ");
        for (String t : types) {
            if (!Type.isValidType(t)) {
                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * Returns true if the all query sizes are valid
     */
    public static boolean isValidSize(String size) {
        boolean isValid = true;
        String[] sizes = size.split(" ");
        for (String s : sizes) {
            if (!Size.isValidSize(s)) {
                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * Returns true if the all query names are valid
     */
    public static boolean isValidName(String name) {
        boolean isValid = true;
        String[] names = name.split(" ");
        for (String n : names) {
            if (!Name.isValidName(n)) {
                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * Returns true if the all query colours are valid
     */
    public static boolean isValidColour(String colour) {
        boolean isValid = true;
        String[] colours = colour.split(" ");
        for (String c : colours) {
            if (!Colour.isValidColour(c)) {
                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * Returns true if the all query descriptions are valid
     */
    public static boolean isValidDescription(String description) {
        boolean isValid = true;
        String[] descriptions = description.split(" ");
        for (String d : descriptions) {
            if (!Description.isValidDescriptionName(d)) {
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
