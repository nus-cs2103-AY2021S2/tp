package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.DESC_OBEDIENCE_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.DESC_POTTY_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_POTTY_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SESSION_POTTY_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_ALL;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.EditProgramCommand.EditProgramDescriptor;
import dog.pawbook.testutil.EditProgramDescriptorBuilder;

public class EditProgramDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditProgramDescriptor descriptorWithSameValues = new EditProgramDescriptor(DESC_OBEDIENCE_TRAINING);
        assertTrue(DESC_OBEDIENCE_TRAINING.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_OBEDIENCE_TRAINING.equals(DESC_OBEDIENCE_TRAINING));

        // null -> returns false
        assertFalse(DESC_OBEDIENCE_TRAINING.equals(null));

        // different types -> returns false
        assertFalse(DESC_OBEDIENCE_TRAINING.equals(5));

        // different values -> returns false
        assertFalse(DESC_OBEDIENCE_TRAINING.equals(DESC_POTTY_TRAINING));

        // different name -> returns false
        EditProgramDescriptor editedProgram1 = new EditProgramDescriptorBuilder(DESC_OBEDIENCE_TRAINING)
                .withName(VALID_NAME_POTTY_TRAINING).build();
        assertFalse(DESC_OBEDIENCE_TRAINING.equals(editedProgram1));

        // different session -> returns false
        editedProgram1 = new EditProgramDescriptorBuilder(DESC_OBEDIENCE_TRAINING)
                .withSessions(VALID_SESSION_POTTY_TRAINING).build();
        assertFalse(DESC_OBEDIENCE_TRAINING.equals(editedProgram1));

        // different tags -> returns false
        editedProgram1 = new EditProgramDescriptorBuilder(DESC_OBEDIENCE_TRAINING).withTags(VALID_TAG_ALL).build();
        assertFalse(DESC_OBEDIENCE_TRAINING.equals(editedProgram1));
    }
}
