package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditContactCommand.EditContactDescriptor;
import seedu.address.testutil.EditContactDescriptorBuilder;

public class EditContactDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditContactDescriptor descriptorWithSameValues = new EditContactDescriptor(CONTACT_DESC_AMY);
        assertTrue(CONTACT_DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(CONTACT_DESC_AMY.equals(CONTACT_DESC_AMY));

        // null -> returns false
        assertFalse(CONTACT_DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(CONTACT_DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(CONTACT_DESC_AMY.equals(CONTACT_DESC_BOB));

        // different contact name -> returns false
        EditContactDescriptor editedAmy = new EditContactDescriptorBuilder(CONTACT_DESC_AMY)
                .withContactName(VALID_NAME_BOB).build();
        assertFalse(CONTACT_DESC_AMY.equals(editedAmy));

        // different contact phone -> returns false
        editedAmy = new EditContactDescriptorBuilder(CONTACT_DESC_AMY).withContactPhone(VALID_PHONE_BOB).build();
        assertFalse(CONTACT_DESC_AMY.equals(editedAmy));

        // different contact email -> returns false
        editedAmy = new EditContactDescriptorBuilder(CONTACT_DESC_AMY).withContactEmail(VALID_EMAIL_BOB).build();
        assertFalse(CONTACT_DESC_AMY.equals(editedAmy));

        // different contact tags -> returns false
        editedAmy = new EditContactDescriptorBuilder(CONTACT_DESC_AMY).withContactTags(VALID_TAG_HUSBAND).build();
        assertFalse(CONTACT_DESC_AMY.equals(editedAmy));
    }
}
