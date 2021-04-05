package seedu.partyplanet.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Contains tests for {@code InputHistory}.
 */
public class InputHistoryTest {

    private InputHistory inputHistory;

    @BeforeEach
    public void setUp() {
        // InputHistory contains [a, b, c]
        inputHistory = new InputHistory();
        inputHistory.add("a");
        inputHistory.add("b");
        inputHistory.add("c");
    }

    @Test
    public void execute_previousTest_success() {
        assertEquals("c", inputHistory.getPrevious());
        assertEquals("b", inputHistory.getPrevious());
        assertEquals("a", inputHistory.getPrevious());
        // Once reach start, should always be a
        assertEquals("a", inputHistory.getPrevious());
        assertEquals("a", inputHistory.getPrevious());
    }

    @Test
    public void execute_nextTest_success() {
        inputHistory.getPrevious();
        inputHistory.getPrevious();
        inputHistory.getPrevious();

        assertEquals("b", inputHistory.getNext());
        assertEquals("c", inputHistory.getNext());
        assertEquals("", inputHistory.getNext());
        // Once reach latest, should always be blank
        assertEquals("", inputHistory.getNext());
        assertEquals("", inputHistory.getNext());
    }

    @Test
    public void execute_previousTestEmptyList_success() {
        inputHistory = new InputHistory();
        assertEquals("", inputHistory.getPrevious());
    }

    @Test
    public void execute_nextTestEmptyList_success() {
        inputHistory = new InputHistory();
        assertEquals("", inputHistory.getNext());
    }

    @Test
    public void check_doNotAddIfSameAsMostRecent() {
        assertEquals(3, inputHistory.size());

        inputHistory.add("duplicate");
        assertEquals(4, inputHistory.size());

        inputHistory.add("duplicate");
        inputHistory.add("duplicate");
        inputHistory.add("duplicate");
        assertEquals(4, inputHistory.size()); // duplicates not added

    }

    @Test
    public void testMaxSize() {
        inputHistory = new InputHistory();
        assertEquals(0, inputHistory.size());

        for (int c = 0; c < 20; c++) {
            inputHistory.add(c + "");
        }

        assertEquals(20, inputHistory.size());
        inputHistory.add("oneMore");
        assertEquals(20, inputHistory.size()); // Size unchanged
    }
}
