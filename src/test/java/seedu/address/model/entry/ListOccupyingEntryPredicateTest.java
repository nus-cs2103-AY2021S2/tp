package seedu.address.model.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EntryBuilder;

public class ListOccupyingEntryPredicateTest {

    @Test
    void test_intervalOccupiedFully_returnTrue() {
        EntryDate start = new EntryDate("2021-04-05 17:00");
        EntryDate end = new EntryDate("2021-04-05 19:00");
        ListOccupyingEntryPredicate predicate = new ListOccupyingEntryPredicate(start, end);
        assertTrue(predicate.test(new EntryBuilder().build()));
    }

    @Test
    void test_intervalOccupiedPartially_returnTrue() {
        EntryDate start = new EntryDate("2021-04-05 17:30");
        EntryDate end = new EntryDate("2021-04-05 19:30");
        ListOccupyingEntryPredicate predicate = new ListOccupyingEntryPredicate(start, end);
        assertTrue(predicate.test(new EntryBuilder().build()));
    }

    @Test
    void test_intervalNotOccupied_returnFalse() {
        EntryDate start = new EntryDate("2021-04-05 13:00");
        EntryDate end = new EntryDate("2021-04-05 14:00");
        ListOccupyingEntryPredicate predicate = new ListOccupyingEntryPredicate(start, end);
        assertFalse(predicate.test(new EntryBuilder().build()));
    }
}
