//@@author
package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_MONDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPTIME_MORNING;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditPassengerDescriptorBuilder;

public class EditPassengerDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditPassengerDescriptor descriptorWithSameValues =
                new EditCommand.EditPassengerDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditCommand.EditPassengerDescriptor editedAmy = new EditPassengerDescriptorBuilder(DESC_AMY)
                .withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPassengerDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditPassengerDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tripDay -> returns false
        editedAmy = new EditPassengerDescriptorBuilder(DESC_AMY).withTripDay(VALID_TRIPDAY_MONDAY).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tripTime -> returns false
        editedAmy = new EditPassengerDescriptorBuilder(DESC_AMY).withTripTime(VALID_TRIPTIME_MORNING).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditPassengerDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HR).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
