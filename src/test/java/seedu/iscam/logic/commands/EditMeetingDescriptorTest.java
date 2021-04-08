package seedu.iscam.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iscam.logic.commands.CommandTestUtil.DESC_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.DESC_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_DATETIME_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_LOCATION_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_CLIENT_NAME_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_TAG_URGENT;

import org.junit.jupiter.api.Test;

import seedu.iscam.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.iscam.testutil.EditMeetingDescriptorBuilder;

public class EditMeetingDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditMeetingDescriptor descriptorWithSameValues = new EditMeetingDescriptor(DESC_CLEO);
        assertTrue(DESC_CLEO.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CLEO.equals(DESC_CLEO));

        // null -> returns false
        assertFalse(DESC_CLEO.equals(null));

        // different types -> returns false
        assertFalse(DESC_CLEO.equals(5));

        // different values -> returns false
        assertFalse(DESC_CLEO.equals(DESC_DAN));

        // different name -> returns false
        EditMeetingDescriptor editedCleo = new EditMeetingDescriptorBuilder(DESC_CLEO)
                .withClientName(VALID_CLIENT_NAME_DAN).build();
        assertFalse(DESC_CLEO.equals(editedCleo));

        // different phone -> returns false
        editedCleo = new EditMeetingDescriptorBuilder(DESC_CLEO).withDateTime(VALID_DATETIME_DAN).build();
        assertFalse(DESC_CLEO.equals(editedCleo));

        // different email -> returns false
        editedCleo = new EditMeetingDescriptorBuilder(DESC_CLEO).withLocation(VALID_LOCATION_DAN).build();
        assertFalse(DESC_CLEO.equals(editedCleo));

        // different iscam -> returns false
        editedCleo = new EditMeetingDescriptorBuilder(DESC_CLEO).withDescription(VALID_DESCRIPTION_DAN).build();
        assertFalse(DESC_CLEO.equals(editedCleo));

        // different tags -> returns false
        editedCleo = new EditMeetingDescriptorBuilder(DESC_CLEO).withTags(VALID_TAG_URGENT).build();
        assertFalse(DESC_CLEO.equals(editedCleo));
    }
}
