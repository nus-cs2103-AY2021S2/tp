package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_FIRST_SESSION;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SECOND_SESSION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_SECOND_SESSION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_SECOND_SESSION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_SECOND_SESSION;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditSessionCommand.EditSessionDescriptor;
import seedu.address.testutil.EditSessionDescriptorBuilder;

public class EditSessionDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditSessionDescriptor descriptorWithSameValues = new EditSessionDescriptor(DESC_FIRST_SESSION);
        assertTrue(DESC_FIRST_SESSION.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_FIRST_SESSION.equals(DESC_FIRST_SESSION));

        // null -> returns false
        assertFalse(DESC_FIRST_SESSION.equals(null));

        // different types -> returns false
        assertFalse(DESC_FIRST_SESSION.equals(5));

        // different values -> returns false
        assertFalse(DESC_FIRST_SESSION.equals(DESC_SECOND_SESSION));


        // different day -> returns false
        EditSessionDescriptor editedFirstSession = new EditSessionDescriptorBuilder(DESC_FIRST_SESSION)
                                                    .withDay(VALID_DAY_SECOND_SESSION).build();
        assertFalse(DESC_FIRST_SESSION.equals(editedFirstSession));

        // different subject -> returns false
        editedFirstSession = new EditSessionDescriptorBuilder(DESC_FIRST_SESSION)
                                .withSubject(VALID_SUBJECT_SECOND_SESSION).build();
        assertFalse(DESC_FIRST_SESSION.equals(editedFirstSession));

        // different timeslot -> returns false
        editedFirstSession = new EditSessionDescriptorBuilder(DESC_FIRST_SESSION)
                                .withTimeslot(VALID_TIMESLOT_SECOND_SESSION).build();
        assertFalse(DESC_FIRST_SESSION.equals(editedFirstSession));

        // different tags -> returns false
        editedFirstSession = new EditSessionDescriptorBuilder(DESC_FIRST_SESSION)
                                                    .withTags(VALID_TAG_FRIEND).build();
        assertFalse(DESC_FIRST_SESSION.equals(editedFirstSession));
    }
}
