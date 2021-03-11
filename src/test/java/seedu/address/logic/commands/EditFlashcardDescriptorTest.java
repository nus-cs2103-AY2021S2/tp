package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ATP;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EINSTEIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_OCTOPUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_OCTOPUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_OCTOPUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_OCTOPUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EQUATION;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditFlashcardDescriptor;
import seedu.address.testutil.EditFlashcardDescriptorBuilder;

public class EditFlashcardDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditFlashcardDescriptor descriptorWithSameValues = new EditFlashcardDescriptor(DESC_EINSTEIN);
        assertTrue(DESC_EINSTEIN.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_EINSTEIN.equals(DESC_EINSTEIN));

        // null -> returns false
        assertFalse(DESC_EINSTEIN.equals(null));

        // different types -> returns false
        assertFalse(DESC_EINSTEIN.equals(5));

        // different values -> returns false
        assertFalse(DESC_EINSTEIN.equals(DESC_ATP));

        // different name -> returns false
        EditCommand.EditFlashcardDescriptor editedAmy = new EditFlashcardDescriptorBuilder(DESC_EINSTEIN)
                .withQuestion(VALID_QUESTION_OCTOPUS).build();
        assertFalse(DESC_EINSTEIN.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditFlashcardDescriptorBuilder(DESC_EINSTEIN).withAnswer(VALID_ANSWER_OCTOPUS).build();
        assertFalse(DESC_EINSTEIN.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditFlashcardDescriptorBuilder(DESC_EINSTEIN).withCategory(VALID_CATEGORY_OCTOPUS).build();
        assertFalse(DESC_EINSTEIN.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditFlashcardDescriptorBuilder(DESC_EINSTEIN).withPriority(VALID_PRIORITY_OCTOPUS).build();
        assertFalse(DESC_EINSTEIN.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditFlashcardDescriptorBuilder(DESC_EINSTEIN).withTags(VALID_TAG_EQUATION).build();
        assertFalse(DESC_EINSTEIN.equals(editedAmy));
    }
}
