package seedu.flashback.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashback.logic.commands.CommandTestUtil.DESC_ATP;
import static seedu.flashback.logic.commands.CommandTestUtil.DESC_EINSTEIN;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_ANSWER_OCTOPUS;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_CATEGORY_OCTOPUS;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_PRIORITY_OCTOPUS;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_QUESTION_OCTOPUS;
import static seedu.flashback.logic.commands.CommandTestUtil.VALID_TAG_EQUATION;

import org.junit.jupiter.api.Test;

import seedu.flashback.logic.commands.EditCommand.EditCardDescriptor;
import seedu.flashback.testutil.EditCardDescriptorBuilder;

public class EditFlashcardDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditCardDescriptor descriptorWithSameValues = new EditCardDescriptor(DESC_EINSTEIN);
        assertTrue(DESC_EINSTEIN.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_EINSTEIN.equals(DESC_EINSTEIN));

        // null -> returns false
        assertFalse(DESC_EINSTEIN.equals(null));

        // different types -> returns false
        assertFalse(DESC_EINSTEIN.equals(5));

        // different values -> returns false
        assertFalse(DESC_EINSTEIN.equals(DESC_ATP));

        // different question -> returns false
        EditCommand.EditCardDescriptor editedEinstein = new EditCardDescriptorBuilder(DESC_EINSTEIN)
                .withQuestion(VALID_QUESTION_OCTOPUS).build();
        assertFalse(DESC_EINSTEIN.equals(editedEinstein));

        // different answer -> returns false
        editedEinstein = new EditCardDescriptorBuilder(DESC_EINSTEIN).withAnswer(VALID_ANSWER_OCTOPUS).build();
        assertFalse(DESC_EINSTEIN.equals(editedEinstein));

        // different category -> returns false
        editedEinstein = new EditCardDescriptorBuilder(DESC_EINSTEIN).withCategory(VALID_CATEGORY_OCTOPUS).build();
        assertFalse(DESC_EINSTEIN.equals(editedEinstein));

        // different priority -> returns false
        editedEinstein = new EditCardDescriptorBuilder(DESC_EINSTEIN).withPriority(VALID_PRIORITY_OCTOPUS).build();
        assertFalse(DESC_EINSTEIN.equals(editedEinstein));

        // different tags -> returns false
        editedEinstein = new EditCardDescriptorBuilder(DESC_EINSTEIN).withTags(VALID_TAG_EQUATION).build();
        assertFalse(DESC_EINSTEIN.equals(editedEinstein));
    }
}
