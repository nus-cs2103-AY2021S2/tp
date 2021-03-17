package seedu.address.model.alias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAliases.ADD_ALIAS;
import static seedu.address.testutil.TypicalAliases.ADD_ALIAS_STRING;
import static seedu.address.testutil.TypicalAliases.INVALID_ALIAS_STRING;

import org.junit.jupiter.api.Test;

public class AliasTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Alias(null));
    }

    @Test
    public void constructor_invalidAlias_throwsIllegalArgumentException() {
        // empty alias
        assertThrows(IllegalArgumentException.class, () -> new Alias(""));

        // alias with whitespace in between
        assertThrows(IllegalArgumentException.class, () -> new Alias(INVALID_ALIAS_STRING));
    }

    @Test
    public void isValidAlias() {
        // null name
        assertThrows(NullPointerException.class, () -> Alias.isValidAlias(null));

        // alias with alphabets
        assertTrue(Alias.isValidAlias("abc"));

        // alias with numbers
        assertTrue(Alias.isValidAlias("123"));

        // alias with symbols
        assertTrue(Alias.isValidAlias("!@#"));

        // alias with alphabets and numbers
        assertTrue(Alias.isValidAlias("abc123"));

        // alias with alphabet and symbols
        assertTrue(Alias.isValidAlias("abc!@#"));

        // alias with numbers and symbols
        assertTrue(Alias.isValidAlias("123!@#"));

        // alias with alphabets, numbers and symbols
        assertTrue(Alias.isValidAlias("abc123!@#"));
    }

    @Test
    public void isSameAlias() {
        assertEquals(ADD_ALIAS, new Alias(ADD_ALIAS_STRING));
    }
}
