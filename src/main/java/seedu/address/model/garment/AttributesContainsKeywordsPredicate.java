package seedu.address.model.garment;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRESSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;

public class AttributesContainsKeywordsPredicate implements Predicate<Garment> {

    private final ArgumentMultimap argMultimap;

    public AttributesContainsKeywordsPredicate(ArgumentMultimap argMultimap) {
        this.argMultimap = argMultimap;
    }

    @Override
    public boolean test(Garment garment) {
        String[] keywords = {""};
        boolean testResult = true;

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
            testResult = testResult && new NameContainsKeywordsPredicate(Arrays.asList(keywords)).test(garment);
        }
        if (argMultimap.getValue(PREFIX_DRESSCODE).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_DRESSCODE).get().split("\\s+");
            testResult = testResult && new DressCodeContainsKeywordsPredicate(Arrays.asList(keywords)).test(garment);
        }
        if (argMultimap.getValue(PREFIX_COLOUR).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_COLOUR).get().split("\\s+");
            testResult = testResult && new ColourContainsKeywordsPredicate(Arrays.asList(keywords)).test(garment);
        }
        if (argMultimap.getValue(PREFIX_SIZE).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_SIZE).get().split("\\s+");
            testResult = testResult && new SizeContainsKeywordsPredicate(Arrays.asList(keywords)).test(garment);
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_DESCRIPTION).get().split("\\s+");
            testResult = testResult && new DescriptionContainsKeywordsPredicate(Arrays.asList(keywords)).test(garment);
        }
        if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_TYPE).get().split("\\s+");
            testResult = testResult && new TypeContainsKeywordsPredicate(Arrays.asList(keywords)).test(garment);
        }
        return testResult;
    }

    /**
     * Returns true if the prefix has a value in this predicate
     */
    public boolean isPrefixValuePresent(Prefix prefix) {
        return argMultimap.getValue(prefix).isPresent();
    }

    //can give issues when never do the above check
    /**
     * Returns the prefix value in this predicate
     */
    public String getPrefixValue(Prefix prefix) {
        return argMultimap.getValue(prefix).get();
    }

    /**
     * Checks if a prefix is present and if so, returns the prefix value. Otherwise return an empty string
     */
    public String ifPrefixPresentGetValue(Prefix prefix) {
        String result = "";
        if (isPrefixValuePresent(prefix)) {
            result = result + prefix.getPrefix() + ": " + getPrefixValue(prefix) + "\n";
        }
        return result;
    }

    /**
     * Returns a list of the descriptions in this predicate
     */
    public List<String> getDescriptionValue() {
        return argMultimap.getAllValues(PREFIX_DESCRIPTION);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttributesContainsKeywordsPredicate // instanceof handles nulls
                && argMultimap.equals(((AttributesContainsKeywordsPredicate) other).argMultimap)); // state check
    }
}
