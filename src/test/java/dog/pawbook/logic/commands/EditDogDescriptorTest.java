package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.DESC_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.DESC_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_BREED_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_DATEOFBIRTH_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_OWNERID_17;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SEX_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.EditDogCommand.EditDogDescriptor;
import dog.pawbook.testutil.EditDogDescriptorBuilder;

public class EditDogDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditDogDescriptor descriptorWithSameValues = new EditDogDescriptor(DESC_ASHER);
        assertTrue(DESC_ASHER.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ASHER.equals(DESC_ASHER));

        // null -> returns false
        assertFalse(DESC_ASHER.equals(null));

        // different types -> returns false
        assertFalse(DESC_ASHER.equals(5));

        // different values -> returns false
        assertFalse(DESC_ASHER.equals(DESC_BELL));

        // different name -> returns false
        EditDogDescriptor editedAsher = new EditDogDescriptorBuilder(DESC_ASHER).withName(VALID_NAME_BELL).build();
        assertFalse(DESC_ASHER.equals(editedAsher));

        // different breed -> returns false
        editedAsher = new EditDogDescriptorBuilder(DESC_ASHER).withBreed(VALID_BREED_BELL).build();
        assertFalse(DESC_ASHER.equals(editedAsher));

        // different dob -> returns false
        editedAsher = new EditDogDescriptorBuilder(DESC_ASHER).withDob(VALID_DATEOFBIRTH_BELL).build();
        assertFalse(DESC_ASHER.equals(editedAsher));

        // different sex -> returns false
        editedAsher = new EditDogDescriptorBuilder(DESC_ASHER).withSex(VALID_SEX_BELL).build();
        assertFalse(DESC_ASHER.equals(editedAsher));

        // different ownerId -> returns false
        editedAsher = new EditDogDescriptorBuilder(DESC_ASHER).withOwnerId(VALID_OWNERID_17).build();
        assertFalse(DESC_ASHER.equals(editedAsher));

        // different tags -> returns false
        editedAsher = new EditDogDescriptorBuilder(DESC_ASHER).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_ASHER.equals(editedAsher));
    }
}
