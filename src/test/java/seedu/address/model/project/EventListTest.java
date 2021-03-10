package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventListTest {

    @Test
    public void constructor_empty_createEmptyEventList() {
        EventList emptyEventList = new EventList();
        assertTrue(emptyEventList.getEvents().isEmpty());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventList(null));
    }

}
