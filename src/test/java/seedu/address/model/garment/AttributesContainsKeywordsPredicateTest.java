package seedu.address.model.garment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRESSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;

public class AttributesContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String argsFirst = " n/Alice Bob s/23 22 c/red black r/casual t/upper lower";
        String argsSecond = " n/Alic Bob s/23 22 c/red black r/casual t/upper lower"; //one letter missing from Alice
        ArgumentMultimap argFirstPredicate =
                ArgumentTokenizer.tokenize(argsFirst, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);
        ArgumentMultimap argSecondPredicate =
                ArgumentTokenizer.tokenize(argsSecond, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
                        PREFIX_DESCRIPTION, PREFIX_TYPE);

        AttributesContainsKeywordsPredicate firstPredicate = new AttributesContainsKeywordsPredicate(argFirstPredicate);
        AttributesContainsKeywordsPredicate secondPredicate =
                new AttributesContainsKeywordsPredicate(argSecondPredicate);

        assertTrue(firstPredicate.equals(firstPredicate));

        assertFalse(firstPredicate.equals(secondPredicate));

        AttributesContainsKeywordsPredicate firstPredicateCopy =
                new AttributesContainsKeywordsPredicate(argFirstPredicate);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        assertFalse(firstPredicate.equals(null));

        assertFalse(firstPredicate.equals(1));
    }

    //check if sameprefix in argumentmultimap works
    /*@Test
    public void c() {
        String args = " n/df gf s/34 c/re d/sd d/fg";
        String args1 = " n/df gf s/34 c/re d/sd d/fg";
        //ArgumentMultimap map =
        //        ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
        //                PREFIX_DESCRIPTION, PREFIX_TYPE);
        //ArgumentMultimap map1 =
        //        ArgumentTokenizer.tokenize(args1, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE,
        //                PREFIX_DESCRIPTION, PREFIX_TYPE);
        //assertTrue(map.isPrefixValueSame(PREFIX_NAME, map1));
        //AttributesContainsKeywordsPredicate a1 = new AttributesContainsKeywordsPredicate(map);
        //AttributesContainsKeywordsPredicate a2 = new AttributesContainsKeywordsPredicate(map1);
        //FindCommand c1 = new FindCommand(a1);
        //FindCommand c2 = new FindCommand(a2);
        FindCommand c1 = null;
        FindCommand c2 = null;
        //WardrobeParser parser = new WardrobeParser();
        try {
            c1 = new FindCommandParser().parse(args);
            c2 = new FindCommandParser().parse(args1);
        } catch(Exception e) {}
        assertTrue(c1.equals(c2));
    }*/
}
