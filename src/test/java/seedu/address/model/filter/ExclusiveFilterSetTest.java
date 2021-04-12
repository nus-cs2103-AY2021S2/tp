package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalFilters;

public class ExclusiveFilterSetTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExclusiveFilterSet<String>(null));
    }

    @Test
    public void hasAny() {
        // EP 1: Fully contains
        assertTrue(TypicalFilters.EXCLUSIVE_FILTERSET_AB.hasAny(TypicalFilters.EXCLUSIVE_FILTERSET_AB));
        assertTrue(TypicalFilters.EXCLUSIVE_FILTERSET_AB.hasAny(TypicalFilters.EXCLUSIVE_FILTERSET_A));
        assertTrue(TypicalFilters.EXCLUSIVE_FILTERSET_AB.hasAny(TypicalFilters.EXCLUSIVE_FILTERSET_B));

        // EP 2: Partially contains
        assertTrue(TypicalFilters.EXCLUSIVE_FILTERSET_A.hasAny(TypicalFilters.EXCLUSIVE_FILTERSET_AB));
        assertTrue(TypicalFilters.EXCLUSIVE_FILTERSET_B.hasAny(TypicalFilters.EXCLUSIVE_FILTERSET_AB));

        // EP 3: Disjoint
        assertFalse(TypicalFilters.EXCLUSIVE_FILTERSET_A.hasAny(TypicalFilters.EXCLUSIVE_FILTERSET_B));
        assertFalse(TypicalFilters.EXCLUSIVE_FILTERSET_B.hasAny(TypicalFilters.EXCLUSIVE_FILTERSET_A));
    }

    @Test
    public void hasAll() {
        // EP 1: Has All
        assertTrue(TypicalFilters.EXCLUSIVE_FILTERSET_ABC.hasAll(TypicalFilters.EXCLUSIVE_FILTERSET_ABC));
        assertTrue(TypicalFilters.EXCLUSIVE_FILTERSET_ABC.hasAll(TypicalFilters.EXCLUSIVE_FILTERSET_AB));
        assertTrue(TypicalFilters.EXCLUSIVE_FILTERSET_ABC.hasAll(TypicalFilters.EXCLUSIVE_FILTERSET_A));
        assertTrue(TypicalFilters.EXCLUSIVE_FILTERSET_AB.hasAll(TypicalFilters.EXCLUSIVE_FILTERSET_B));

        // EP 2: Has Some
        assertFalse(TypicalFilters.EXCLUSIVE_FILTERSET_AB.hasAll(TypicalFilters.EXCLUSIVE_FILTERSET_AC));
        assertFalse(TypicalFilters.EXCLUSIVE_FILTERSET_A.hasAll(TypicalFilters.EXCLUSIVE_FILTERSET_AB));

        // EP 3: Disjoint
        assertFalse(TypicalFilters.EXCLUSIVE_FILTERSET_A.hasAll(TypicalFilters.EXCLUSIVE_FILTERSET_B));
        assertFalse(TypicalFilters.EXCLUSIVE_FILTERSET_B.hasAll(TypicalFilters.EXCLUSIVE_FILTERSET_A));
    }

    @Test
    public void add() {
        FilterSet<String> filterSet = new ExclusiveFilterSet<>();

        // EP 1: Empty
        filterSet.add(TypicalFilters.FILTER_A);
        assertEquals(TypicalFilters.EXCLUSIVE_FILTERSET_A, filterSet);

        // EP 2: Not Empty
        filterSet.add(TypicalFilters.FILTER_B);
        assertEquals(TypicalFilters.EXCLUSIVE_FILTERSET_AB, filterSet);

        filterSet.add(TypicalFilters.FILTER_C);
        assertEquals(TypicalFilters.EXCLUSIVE_FILTERSET_ABC, filterSet);

        // EP 3: Duplicate
        filterSet.add(TypicalFilters.FILTER_C);
        assertEquals(TypicalFilters.EXCLUSIVE_FILTERSET_ABC, filterSet);

        // EP 4: null
        assertThrows(NullPointerException.class, () -> filterSet.add(null));
    }

    @Test
    public void remove() {
        FilterSet<String> filterSet = new ExclusiveFilterSet<>(
                TypicalFilters.EXCLUSIVE_FILTERSET_ABC.getFilters());

        // EP 1: Has Multiple
        filterSet.remove(TypicalFilters.FILTER_C);
        assertEquals(TypicalFilters.EXCLUSIVE_FILTERSET_AB, filterSet);

        filterSet.remove(TypicalFilters.FILTER_B);
        assertEquals(TypicalFilters.EXCLUSIVE_FILTERSET_A, filterSet);

        // EP 2: Has One
        filterSet.remove(TypicalFilters.FILTER_A);
        assertEquals(TypicalFilters.EXCLUSIVE_FILTERSET_EMPTY, filterSet);

        // EP 3: Empty
        filterSet.remove(TypicalFilters.FILTER_A);
        assertEquals(TypicalFilters.EXCLUSIVE_FILTERSET_EMPTY, filterSet);

        // EP 4: null
        filterSet.remove(null);
        assertEquals(TypicalFilters.EXCLUSIVE_FILTERSET_EMPTY, filterSet);
    }

    @Test
    public void addAll() {
        FilterSet<String> filterSet = new ExclusiveFilterSet<>();

        // EP 1: Empty
        filterSet.addAll(TypicalFilters.SET_C);
        assertEquals(TypicalFilters.EXCLUSIVE_FILTERSET_C, filterSet);

        // EP 2: Not Empty
        filterSet.addAll(TypicalFilters.EXCLUSIVE_FILTERSET_AB);
        assertEquals(TypicalFilters.EXCLUSIVE_FILTERSET_ABC, filterSet);

        // EP 3: Duplicate
        filterSet.addAll(TypicalFilters.SET_B);
        assertEquals(TypicalFilters.EXCLUSIVE_FILTERSET_ABC, filterSet);

        // EP 4: null
        assertThrows(NullPointerException.class, () -> filterSet.addAll((FilterSet<String>) null));
    }

    @Test
    public void removeAll() {
        FilterSet<String> filterSet = new ExclusiveFilterSet<>(
                TypicalFilters.EXCLUSIVE_FILTERSET_ABC.getFilters());

        // EP 1: Has Multiple
        filterSet.removeAll(TypicalFilters.EXCLUSIVE_FILTERSET_AB);
        assertEquals(TypicalFilters.EXCLUSIVE_FILTERSET_C, filterSet);

        // EP 2: Has One
        filterSet.removeAll(TypicalFilters.SET_C);
        assertEquals(TypicalFilters.EXCLUSIVE_FILTERSET_EMPTY, filterSet);

        // EP 3: Empty
        filterSet.removeAll(TypicalFilters.SET_A);
        assertEquals(TypicalFilters.EXCLUSIVE_FILTERSET_EMPTY, filterSet);

        // EP 4: null
        assertThrows(NullPointerException.class, () -> filterSet.removeAll((FilterSet<String>) null));
    }

    @Test
    public void test() {
        // EP 1: One filter match
        assertTrue(TypicalFilters.EXCLUSIVE_FILTERSET_A.test(TypicalFilters.FILTERABLE_A));

        // EP 2: Multiple filters match
        assertTrue(TypicalFilters.EXCLUSIVE_FILTERSET_AB.test(
                TypicalFilters.FILTERABLE_A + TypicalFilters.FILTERABLE_B));
        assertTrue(TypicalFilters.EXCLUSIVE_FILTERSET_AB.test(
                    TypicalFilters.FILTERABLE_A + TypicalFilters.FILTERABLE_B + TypicalFilters.FILTERABLE_C));

        // EP 3: One filter no match
        assertFalse(TypicalFilters.EXCLUSIVE_FILTERSET_A.test(TypicalFilters.FILTERABLE_B));

        // EP 4: Multiple filters no match
        assertFalse(TypicalFilters.EXCLUSIVE_FILTERSET_AB.test(TypicalFilters.FILTERABLE_A));
        assertFalse(TypicalFilters.EXCLUSIVE_FILTERSET_ABC.test(
                TypicalFilters.FILTERABLE_A + TypicalFilters.FILTERABLE_B));
        assertFalse(TypicalFilters.EXCLUSIVE_FILTERSET_AB.test(TypicalFilters.FILTERABLE_C));

        // EP 5: null
        assertThrows(NullPointerException.class, () -> TypicalFilters.EXCLUSIVE_FILTERSET_A.test(null));
    }
}
