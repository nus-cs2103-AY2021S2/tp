package seedu.module.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null));
    }

    @Test
    public void constructor_invalidModule_throwsIllegalArgumentException() {
        String invalidModule = "";
        assertThrows(IllegalArgumentException.class, () -> new Module(invalidModule));
    }

    @Test
    public void isValidModule() {
        // null module
        assertThrows(NullPointerException.class, () -> Module.isValidModule(null));

        // blank module
        assertFalse(Module.isValidModule("")); // empty string
        assertFalse(Module.isValidModule(" ")); // spaces only

        // missing parts
        assertFalse(Module.isValidModule("@example.com")); // missing local part
        assertFalse(Module.isValidModule("peterjackexample.com")); // missing '@' symbol
        assertFalse(Module.isValidModule("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Module.isValidModule("peterjack@-")); // invalid domain name
        assertFalse(Module.isValidModule("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Module.isValidModule("peter jack@example.com")); // spaces in local part
        assertFalse(Module.isValidModule("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Module.isValidModule(" peterjack@example.com")); // leading space
        assertFalse(Module.isValidModule("peterjack@example.com ")); // trailing space
        assertFalse(Module.isValidModule("peterjack@@example.com")); // double '@' symbol
        assertFalse(Module.isValidModule("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Module.isValidModule("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Module.isValidModule("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Module.isValidModule("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Module.isValidModule("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Module.isValidModule("peterjack@example.com-")); // domain name ends with a hyphen

        // valid module
        assertTrue(Module.isValidModule("PeterJack_1190@example.com"));
        assertTrue(Module.isValidModule("a@bc")); // minimal
        assertTrue(Module.isValidModule("test@localhost")); // alphabets only
        assertTrue(Module.isValidModule("!#$%&'*+/=?`{|}~^.-@example.org")); // special characters local part
        assertTrue(Module.isValidModule("123@145")); // numeric local part and domain name
        assertTrue(Module.isValidModule("a1+be!@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(Module.isValidModule("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Module.isValidModule("if.you.dream.it_you.can.do.it@example.com")); // long local part
    }
}
