package seedu.address.logic.commands.doctor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DR_LEONARD;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DR_SHELDON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DR_SHELDON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TALL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.doctor.EditDoctorCommand.EditDoctorDescriptor;
import seedu.address.testutil.EditDoctorDescriptorBuilder;

public class EditDoctorDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditDoctorDescriptor descriptorWithSameValues = new EditDoctorDescriptor(DESC_DR_LEONARD);
        assertTrue(DESC_DR_LEONARD.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_DR_LEONARD.equals(DESC_DR_LEONARD));

        // null -> returns false
        assertFalse(DESC_DR_LEONARD.equals(null));

        // different types -> returns false
        assertFalse(DESC_DR_LEONARD.equals(5));

        // different values -> returns false
        assertFalse(DESC_DR_LEONARD.equals(DESC_DR_SHELDON));

        // different name -> returns false
        EditDoctorDescriptor editedLeonard = new EditDoctorDescriptorBuilder(DESC_DR_LEONARD)
                .withName(VALID_NAME_DR_SHELDON).build();
        assertFalse(DESC_DR_LEONARD.equals(editedLeonard));

        // different tags -> returns false
        editedLeonard = new EditDoctorDescriptorBuilder(DESC_DR_LEONARD).withTags(VALID_TAG_TALL).build();
        assertFalse(DESC_DR_LEONARD.equals(editedLeonard));
    }
}
