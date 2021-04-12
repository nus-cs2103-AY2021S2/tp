package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRESSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.garment.AttributesContainsKeywordsPredicate;

/**
 * Finds and lists all garments in wardrobe whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all garments whose names, colours, sizes, "
            + "dresscodes or descriptions contain any of the specified\n"
            + "keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: n/KEYWORD [MORE_KEYWORDS] or s/KEYWORD [MORE_KEYWORDS] ... for each attribute.\n"
            + "Example: " + COMMAND_WORD + " n/alice bob charlie\n"
            + "All [KEYWORD] must be non-empty"
            + "For dress code, [KEYWORD] must be either casual, CASUAL, formal, FORMAL, active, or ACTIVE\n"
            + "For type, [KEYWORD] must be upper, lower, or footwear.\n"
            + "For size, [KEYWORD] must be a positive integer less or equal to 200\n"
            + "For name, [KEYWORD] must be less than or equal to 40 characters\n"
            + "For description, [KEYWORD] must be less than or equal to 15 characters\n"
            + "For colour, [KEYWORD] must be  either red, orange, yellow, green, blue, pink, grey, white,\n"
            + "purple, brown, beige, or black.";


    private final AttributesContainsKeywordsPredicate predicates;

    public FindCommand(AttributesContainsKeywordsPredicate predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredGarmentList(predicates);
        return new CommandResult(showMessage());
    }

    //is this the correct class to have this method? Listcommand also has the message in its class tho
    /**
     * Returns the message to be shown
     */
    public String showMessage() {
        String result = "Showing garments that match the following:";
        if (predicates.isPrefixValuePresent(PREFIX_NAME)) {
            result = result + "\nname: " + predicates.getPrefixValue(PREFIX_NAME);
        }
        if (predicates.isPrefixValuePresent(PREFIX_SIZE)) {
            result = result + "\nsize: " + predicates.getPrefixValue(PREFIX_SIZE);
        }
        if (predicates.isPrefixValuePresent(PREFIX_COLOUR)) {
            result = result + "\ncolour: " + predicates.getPrefixValue(PREFIX_COLOUR);
        }
        if (predicates.isPrefixValuePresent(PREFIX_DRESSCODE)) {
            result = result + "\ndresscode: " + predicates.getPrefixValue(PREFIX_DRESSCODE);
        }
        if (predicates.isPrefixValuePresent(PREFIX_TYPE)) {
            result = result + "\ntype: " + predicates.getPrefixValue(PREFIX_TYPE);
        }
        List<String> predicateDesc = predicates.getDescriptionValue();
        boolean firstDesc = true;
        for (String desc : predicateDesc) {
            if (firstDesc) {
                result = result + "\ndescriptions: ";
                firstDesc = false;
            }
            result = result + desc + " ";
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicates.equals(((FindCommand) other).predicates)); // state check
    }
    //create a tostring
    /*public String toString() {
        return ;
    }*/
}
