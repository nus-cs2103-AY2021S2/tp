package seedu.partyplanet.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class PrefixTest {

    @Test
    public void invalidOrDuplicatePrefixes() {
        Prefix abc = new Prefix("_test_abc");
        try {
            Prefix abc2 = new Prefix("_test_abc");
        } catch (AssertionError e) {
            return;
        }
        throw new AssertionError("Duplicate prefixes should not be allowed.");
    }

    @Test
    public void equalsMethod() {
        Prefix aaa = new Prefix("_test_aaa");

        assertEquals(aaa, aaa);

        assertNotEquals(aaa, "_test_aaa");
        assertNotEquals(aaa, new Prefix("_test_aab"));
    }
}
