package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CS2030;
import static seedu.address.testutil.TypicalEvents.CS1010S;
import static seedu.address.testutil.TypicalEvents.CS2030;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class EventTest {

    @Test
    public void isSameEvent() {
        // same object -> returns true
        assertTrue(CS2030.isSameEvent(CS2030));

        // null -> returns false
        assertFalse(CS1010S.isSameEvent(null));

        // same name, all other attrs different
        // -> returns true
        Event editedCS1010S = new EventBuilder(CS1010S).withStatus(EventStatus.TODO)
                .withDescription("This is not the actual description").build();
        assertTrue(CS1010S.isSameEvent(editedCS1010S));

        // different name, all other attrs same -> false
        editedCS1010S = new EventBuilder(CS1010S).withName("CS2030").build();
        assertFalse(CS1010S.isSameEvent(editedCS1010S));

        // name differs in case, all other attrs same -> returns false
        Event editedCS2030 = new EventBuilder(CS2030)
                .withName(VALID_NAME_CS2030.toLowerCase()).build();
        assertTrue(CS2030.isSameEvent(editedCS2030));

        // name has trailing spaces, all other attrs same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_CS2030 + " ";
        editedCS2030 = new EventBuilder(CS2030)
                .withName(nameWithTrailingSpaces).build();
        assertFalse(CS2030.isSameEvent(editedCS2030));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Event cs2030Copy = new EventBuilder(CS2030).build();
        assertTrue(CS2030.equals(cs2030Copy));

        // same object -> returns true
        assertTrue(CS2030.equals(CS2030));

        // null -> returns false
        assertFalse(CS2030.equals(null));

        // different type -> returns false
        assertFalse(CS2030.equals(5));

        // different event -> returns false
        assertFalse(CS2030.equals(CS1010S));

        // different name -> returns false
        Event editedCS2030 = new EventBuilder(CS2030).withName("abcabc").build();
        assertFalse(CS2030.equals(editedCS2030));

        // different timeStart -> return false
        /* Commented out v1.2
        editedCS2030 = new EventBuilder(CS2030).withTimeEnd("10/06/2043 09:00").build();
        assertFalse(CS2030.equals(editedCS2030));
         */

        // different timeEnd -> return false
        /* Commented out v1.2
        editedCS2030 = new EventBuilder(CS2030).withTimeStart("10/06/2043 09:00").build();
        assertFalse(CS2030.equals(editedCS2030));
         */
    }
}
