package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2107;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2107;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CS2107;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_CS2107;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditEventDescriptor;
import seedu.address.testutil.EditEventDescriptorBuilder;

public class EditEventDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEventDescriptor descriptorWithSameValues = new EditEventDescriptor(DESC_CS2030);
        assertTrue(DESC_CS2030.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CS2030.equals(DESC_CS2030));

        // null -> returns false
        assertFalse(DESC_CS2030.equals(null));

        // different types -> returns false
        assertFalse(DESC_CS2030.equals(5));

        // different values -> returns false
        assertFalse(DESC_CS2030.equals(DESC_CS2107));

        // different name -> returns false
        EditEventDescriptor editedCS2030 = new EditEventDescriptorBuilder(DESC_CS2030)
                .withName(VALID_NAME_CS2107).build();
        assertFalse(DESC_CS2030.equals(editedCS2030));

        // different description -> returns false
        editedCS2030 = new EditEventDescriptorBuilder(DESC_CS2030).withDescription(VALID_DESCRIPTION_CS2107).build();
        assertFalse(DESC_CS2030.equals(editedCS2030));

        // different eventStatus -> returns false
        editedCS2030 = new EditEventDescriptorBuilder(DESC_CS2030).withEventStatus(VALID_STATUS_CS2107).build();
        assertFalse(DESC_CS2030.equals(editedCS2030));

        // Commented out for v1.2
        //        // different tags -> returns false
        //        editedAmy = new EditEventDescriptorBuilder(DESC_CS2030).withTags(VALID_TAG_HUSBAND).build();
        //        assertFalse(DESC_CS2030.equals(editedAmy));
    }
}
