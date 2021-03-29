package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.VALID_INDEXES;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

public class EmailCommandTest {

    @Test
    public void initialize_nullIndexes_throwsException() {
        assertThrows(NullPointerException.class, () -> new EmailCommand(null));
    }

    @Test
    public void equals() {
        List<Index> validIndexes = new ArrayList<>(VALID_INDEXES);
        List<Index> validIndexesReversed = new ArrayList<>(VALID_INDEXES);
        Collections.reverse(validIndexesReversed);

        // same indexes -> equals
        assertEquals(new EmailCommand(validIndexes), new EmailCommand(validIndexes));

        // same indexes, different order -> equals
        assertEquals(new EmailCommand(validIndexes), new EmailCommand(validIndexesReversed));

        // different instance, same values -> equals
        assertEquals(new EmailCommand(), new EmailCommand());

        // different command -> not equals
        assertNotEquals(new EmailCommand(), new ListCommand());

        // different indexes -> not equals
        assertNotEquals(new EmailCommand(), new EmailCommand(validIndexes));

        // different types -> not equals
        assertNotEquals(null, new EmailCommand());
        assertNotEquals(1, new EmailCommand());
    }

}
