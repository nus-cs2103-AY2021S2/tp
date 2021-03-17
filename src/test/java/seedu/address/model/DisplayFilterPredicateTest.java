package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;

class DisplayFilterPredicateTest {

    private ArgumentMultimap addressMultimap;
    private ArgumentMultimap fullMultimap;

    @BeforeEach
    public void initializeArguments() {
        addressMultimap = new ArgumentMultimap();
        addressMultimap.put(PREFIX_ADDRESS, "");

        fullMultimap = new ArgumentMultimap();
        fullMultimap.put(PREFIX_NAME, "");
        fullMultimap.put(PREFIX_ADDRESS, "");
        fullMultimap.put(PREFIX_EMAIL, "");
        fullMultimap.put(PREFIX_TAG, "");
        fullMultimap.put(PREFIX_PHONE, "");
        fullMultimap.put(PREFIX_REMARK, "");
    }

    @Test
    public void test_predicateEqualsCheck_allEquals() {
        DisplayFilterPredicate emptyArgs = new DisplayFilterPredicate();
        DisplayFilterPredicate defaultArgs = new DisplayFilterPredicate(new ArgumentMultimap());
        DisplayFilterPredicate addressArgs = new DisplayFilterPredicate(addressMultimap);
        DisplayFilterPredicate dupeAddressArgs = new DisplayFilterPredicate(addressMultimap);
        DisplayFilterPredicate fullArgs = new DisplayFilterPredicate(fullMultimap);

        assertEquals(emptyArgs, defaultArgs);
        assertEquals(emptyArgs, fullArgs);
        assertEquals(emptyArgs, defaultArgs);
        assertEquals(addressArgs, addressArgs);
        assertEquals(addressArgs, dupeAddressArgs);
    }

    @Test
    public void test_predicateEqualsCheck_allNotEquals() {
        DisplayFilterPredicate emptyArgs = new DisplayFilterPredicate();
        DisplayFilterPredicate addressArgs = new DisplayFilterPredicate(addressMultimap);
        DisplayFilterPredicate fullArgs = new DisplayFilterPredicate(fullMultimap);

        assertNotEquals(emptyArgs, addressArgs);
        assertNotEquals(addressArgs, fullArgs);
        assertNotEquals(1, emptyArgs);
        assertNotEquals(1, addressArgs);
        assertNotEquals(1, fullArgs);
        assertNotEquals(null, emptyArgs);
        assertNotEquals(null, addressArgs);
        assertNotEquals(null, fullArgs);
    }

    @Test
    public void test_defaultArgs_allTrue() {
        DisplayFilterPredicate displayFilterPredicate = new DisplayFilterPredicate();

        assertTrue(displayFilterPredicate.test(PREFIX_ADDRESS));
        assertTrue(displayFilterPredicate.test(PREFIX_NAME));
        assertTrue(displayFilterPredicate.test(PREFIX_EMAIL));
        assertTrue(displayFilterPredicate.test(PREFIX_TAG));
        assertTrue(displayFilterPredicate.test(PREFIX_PHONE));
        assertTrue(displayFilterPredicate.test(PREFIX_REMARK));
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
        assertTrue(displayFilterPredicate.test(PREFIX_REMARK));
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
        assertFalse(displayFilterPredicate.test(PREFIX_REMARK));
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
        assertFalse(displayFilterPredicate.test(PREFIX_REMARK));
    }

    @Test
    public void test_argMapFilledAllTrue_success() {
        DisplayFilterPredicate displayFilterPredicate = new DisplayFilterPredicate(fullMultimap);
        assertTrue(displayFilterPredicate.test(PREFIX_ADDRESS));
        assertTrue(displayFilterPredicate.test(PREFIX_NAME));
        assertTrue(displayFilterPredicate.test(PREFIX_EMAIL));
        assertTrue(displayFilterPredicate.test(PREFIX_TAG));
        assertTrue(displayFilterPredicate.test(PREFIX_PHONE));
        assertTrue(displayFilterPredicate.test(PREFIX_REMARK));
    }
}
