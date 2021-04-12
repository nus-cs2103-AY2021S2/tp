package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BlacklistTest {

    @Test
    public void equals() {
        // default blacklist status is false
        Blacklist blacklist = new Blacklist();

        // same object -> returns true
        assertTrue(blacklist.equals(blacklist));

        // same values -> returns true
        Blacklist blacklistCopy = new Blacklist(blacklist.isBlacklisted);
        assertTrue(blacklist.equals(blacklistCopy));

        // different types -> returns false
        assertFalse(blacklist.equals(1));

        // null -> returns false
        assertFalse(blacklist.equals(null));

        // different blacklist status -> returns false
        Blacklist differentBlacklist = new Blacklist(true);
        assertFalse(blacklist.equals(differentBlacklist));
    }
}
