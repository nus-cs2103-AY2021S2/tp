package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;

class DisplayFilterPredicateTest {

    @Test
    public void equals() {
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(PREFIX_ADDRESS, "");

        DisplayFilterPredicate first = new DisplayFilterPredicate();
        DisplayFilterPredicate second = new DisplayFilterPredicate(argumentMultimap);
        assertNotEquals(second, first);

        ArgumentMultimap argumentMultimapFull = new ArgumentMultimap();
        argumentMultimap.put(PREFIX_NAME, "");
        argumentMultimap.put(PREFIX_ADDRESS, "");
        argumentMultimap.put(PREFIX_EMAIL, "");
        argumentMultimap.put(PREFIX_TAG, "");
        argumentMultimap.put(PREFIX_PHONE, "");

        DisplayFilterPredicate third = new DisplayFilterPredicate(new ArgumentMultimap());
        assertEquals(first, third);
    }

    @Test
    public void test_defaultArgs_allTrue() {
        DisplayFilterPredicate displayFilterPredicate = new DisplayFilterPredicate();

        assertTrue(displayFilterPredicate.test(PREFIX_ADDRESS));
        assertTrue(displayFilterPredicate.test(PREFIX_NAME));
        assertTrue(displayFilterPredicate.test(PREFIX_EMAIL));
        assertTrue(displayFilterPredicate.test(PREFIX_TAG));
        assertTrue(displayFilterPredicate.test(PREFIX_PHONE));
    }

    @Test
    public void test_emptyArgMap_allTrue() {
        DisplayFilterPredicate displayFilterPredicate = new DisplayFilterPredicate(
                new ArgumentMultimap());

        assertTrue(displayFilterPredicate.test(PREFIX_ADDRESS));
        assertTrue(displayFilterPredicate.test(PREFIX_NAME));
        assertTrue(displayFilterPredicate.test(PREFIX_EMAIL));
        assertTrue(displayFilterPredicate.test(PREFIX_TAG));
        assertTrue(displayFilterPredicate.test(PREFIX_PHONE));
    }

    @Test
    public void test_argMapNameAlwaysTrue_success() {
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(PREFIX_ADDRESS, "");
        DisplayFilterPredicate displayFilterPredicate = new DisplayFilterPredicate(
                argumentMultimap);

        assertTrue(displayFilterPredicate.test(PREFIX_NAME));
        assertTrue(displayFilterPredicate.test(PREFIX_ADDRESS));
        assertFalse(displayFilterPredicate.test(PREFIX_EMAIL));
        assertFalse(displayFilterPredicate.test(PREFIX_TAG));
        assertFalse(displayFilterPredicate.test(PREFIX_PHONE));
    }

    @Test
    public void test_argMapNameTrueOthersFalse_success() {
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(PREFIX_NAME, "");
        DisplayFilterPredicate displayFilterPredicate = new DisplayFilterPredicate(
                argumentMultimap);

        assertTrue(displayFilterPredicate.test(PREFIX_NAME));
        assertFalse(displayFilterPredicate.test(PREFIX_ADDRESS));
        assertFalse(displayFilterPredicate.test(PREFIX_EMAIL));
        assertFalse(displayFilterPredicate.test(PREFIX_TAG));
        assertFalse(displayFilterPredicate.test(PREFIX_PHONE));
    }

    @Test
    public void test_argMapFilledAllTrue_success() {
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(PREFIX_NAME, "");
        argumentMultimap.put(PREFIX_ADDRESS, "");
        argumentMultimap.put(PREFIX_EMAIL, "");
        argumentMultimap.put(PREFIX_TAG, "");
        argumentMultimap.put(PREFIX_PHONE, "");
        DisplayFilterPredicate displayFilterPredicate = new DisplayFilterPredicate(
                argumentMultimap);

        assertTrue(displayFilterPredicate.test(PREFIX_ADDRESS));
        assertTrue(displayFilterPredicate.test(PREFIX_NAME));
        assertTrue(displayFilterPredicate.test(PREFIX_EMAIL));
        assertTrue(displayFilterPredicate.test(PREFIX_TAG));
        assertTrue(displayFilterPredicate.test(PREFIX_PHONE));
    }
}
