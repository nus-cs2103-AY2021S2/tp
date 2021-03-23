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
                new EditCommand.EditEntryDescriptor(CommandTestUtil.DESC_A);
        assertTrue(CommandTestUtil.DESC_A.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(CommandTestUtil.DESC_A.equals(CommandTestUtil.DESC_A));

        // null -> returns false
        assertFalse(CommandTestUtil.DESC_A.equals(null));

        // different types -> returns false
        assertFalse(CommandTestUtil.DESC_A.equals(5));

        // different values -> returns false
        assertFalse(CommandTestUtil.DESC_A.equals(CommandTestUtil.DESC_B));

        // different name -> returns false
        EditEntryDescriptor editedEntryA = new EditEntryDescriptorBuilder(CommandTestUtil.DESC_A)
                .withName(CommandTestUtil.VALID_NAME_B).build();
        assertFalse(CommandTestUtil.DESC_A.equals(editedEntryA));

        // different rating -> returns false
        editedEntryA = new EditEntryDescriptorBuilder(CommandTestUtil.DESC_A)
                .withRating(CommandTestUtil.VALID_RATING_B).build();
        assertFalse(CommandTestUtil.DESC_A.equals(editedEntryA));

        // different price -> returns false
        editedEntryA = new EditEntryDescriptorBuilder(CommandTestUtil.DESC_A)
                .withPrice(CommandTestUtil.VALID_PRICE_B).build();
        assertFalse(CommandTestUtil.DESC_A.equals(editedEntryA));

        // different review -> returns false
        editedEntryA = new EditEntryDescriptorBuilder(CommandTestUtil.DESC_A)
                .withReview(CommandTestUtil.VALID_REVIEW_B).build();
        assertFalse(CommandTestUtil.DESC_A.equals(editedEntryA));

        // different address -> returns false
        editedEntryA = new EditEntryDescriptorBuilder(CommandTestUtil.DESC_A)
                .withAddress(CommandTestUtil.VALID_ADDRESS_B).build();
        assertFalse(CommandTestUtil.DESC_A.equals(editedEntryA));

        // different tags -> returns false
        editedEntryA = new EditEntryDescriptorBuilder(CommandTestUtil.DESC_A)
                .withTags(CommandTestUtil.VALID_TAG_WESTERN).build();
        assertFalse(CommandTestUtil.DESC_A.equals(editedEntryA));
    }
}
