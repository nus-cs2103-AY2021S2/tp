package seedu.address.model.flashcard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortOptionsTest {
    @Test
    public void validOption() {
        assertTrue(SortOptions.isValidOption("priority a"));
        assertTrue(SortOptions.isValidOption("priority d"));
        assertTrue(SortOptions.isValidOption("question a"));
        assertTrue(SortOptions.isValidOption("question d"));
    }

    @Test
    public void invalidOption() {
        assertFalse(SortOptions.isValidOption(""));
        assertFalse(SortOptions.isValidOption(" "));
        assertFalse(SortOptions.isValidOption("priority"));
    }
}
