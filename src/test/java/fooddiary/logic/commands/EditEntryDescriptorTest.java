package fooddiary.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fooddiary.logic.commands.EditCommand.EditEntryDescriptor;
import fooddiary.testutil.EditEntryDescriptorBuilder;


public class EditEntryDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditEntryDescriptor descriptorWithSameValues =
                new EditCommand.EditEntryDescriptor(CommandTestUtil.DESC_AMY);
        assertTrue(CommandTestUtil.DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(CommandTestUtil.DESC_AMY.equals(CommandTestUtil.DESC_AMY));

        // null -> returns false
        assertFalse(CommandTestUtil.DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(CommandTestUtil.DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(CommandTestUtil.DESC_AMY.equals(CommandTestUtil.DESC_BOB));

        // different name -> returns false
        EditEntryDescriptor editedAmy = new EditEntryDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withName(CommandTestUtil.VALID_NAME_BOB).build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different rating -> returns false
        editedAmy = new EditEntryDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withRating(CommandTestUtil.VALID_RATING_BOB).build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different review -> returns false
        editedAmy = new EditEntryDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withReview(CommandTestUtil.VALID_REVIEW_BOB).build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditEntryDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withAddress(CommandTestUtil.VALID_ADDRESS_BOB).build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditEntryDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withTags(CommandTestUtil.VALID_TAG_WESTERN).build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));
    }
}
