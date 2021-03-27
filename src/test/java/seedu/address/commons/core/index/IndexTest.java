package seedu.address.commons.core.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IndexTest {

    @Test
    public void createOneBasedIndex() {
        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromOneBased(-2));
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromOneBased(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromOneBased(0));

        // check equality using the same base
        assertEquals(1, Index.fromOneBased(1).getOneBased());
        assertEquals(5, Index.fromOneBased(5).getOneBased());
        assertEquals(123, Index.fromOneBased(123).getOneBased());

        // convert from one-based index to zero-based index
        assertEquals(0, Index.fromOneBased(1).getZeroBased());
        assertEquals(4, Index.fromOneBased(5).getZeroBased());
        assertEquals(124, Index.fromOneBased(125).getZeroBased());
    }

    @Test
    public void createZeroBasedIndex() {
        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromZeroBased(-3));
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromZeroBased(-2));
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromZeroBased(-1));

        // check equality using the same base
        assertEquals(0, Index.fromZeroBased(0).getZeroBased());
        assertEquals(5, Index.fromZeroBased(5).getZeroBased());
        assertEquals(123, Index.fromZeroBased(123).getZeroBased());

        // convert from zero-based index to one-based index
        assertEquals(1, Index.fromZeroBased(0).getOneBased());
        assertEquals(6, Index.fromZeroBased(5).getOneBased());
        assertEquals(126, Index.fromZeroBased(125).getOneBased());
    }

    @Test
    public void equals() {
        final Index fifthPersonIndexOneBased = Index.fromOneBased(5);
        final Index fifthPersonIndexZeroBased = Index.fromZeroBased(4);

        // same values -> returns true
        assertTrue(fifthPersonIndexOneBased.equals(Index.fromOneBased(5)));
        assertTrue(fifthPersonIndexOneBased.equals(Index.fromZeroBased(4)));
        assertTrue(fifthPersonIndexZeroBased.equals(Index.fromOneBased(5)));
        assertTrue(fifthPersonIndexZeroBased.equals(Index.fromZeroBased(4)));
        assertTrue(fifthPersonIndexOneBased.equals(fifthPersonIndexZeroBased));

        // same object -> returns true
        assertTrue(fifthPersonIndexOneBased.equals(fifthPersonIndexOneBased));
        assertTrue(fifthPersonIndexZeroBased.equals(fifthPersonIndexZeroBased));

        // null -> returns false
        assertFalse(fifthPersonIndexOneBased.equals(null));
        assertFalse(fifthPersonIndexZeroBased.equals(null));

        // different types -> returns false
        assertFalse(fifthPersonIndexOneBased.equals(5.0f));
        assertFalse(fifthPersonIndexOneBased.equals(5L));
        assertFalse(fifthPersonIndexZeroBased.equals(5.0f));
        assertFalse(fifthPersonIndexZeroBased.equals(5L));

        // different index -> returns false
        assertFalse(fifthPersonIndexOneBased.equals(Index.fromOneBased(1)));
        assertFalse(fifthPersonIndexOneBased.equals(Index.fromZeroBased(1)));
    }
}
