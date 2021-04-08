package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.DESC_PROGRAM_A;
import static dog.pawbook.logic.commands.CommandTestUtil.DESC_PROGRAM_B;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_PROGRAM_B;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SESSION_PROGRAM_B;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_DOGS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.EditProgramCommand.EditProgramDescriptor;
import dog.pawbook.testutil.EditProgramDescriptorBuilder;

public class EditProgramDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditProgramDescriptor descriptorWithSameValues = new EditProgramDescriptor(DESC_PROGRAM_A);
        assertTrue(DESC_PROGRAM_A.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_PROGRAM_A.equals(DESC_PROGRAM_A));

        // null -> returns false
        assertFalse(DESC_PROGRAM_A.equals(null));

        // different types -> returns false
        assertFalse(DESC_PROGRAM_A.equals(5));

        // different values -> returns false
        assertFalse(DESC_PROGRAM_A.equals(DESC_PROGRAM_B));

        // different name -> returns false
        EditProgramDescriptor editedProgram1 = new EditProgramDescriptorBuilder(DESC_PROGRAM_A)
                .withName(VALID_NAME_PROGRAM_B).build();
        assertFalse(DESC_PROGRAM_A.equals(editedProgram1));

        // different session -> returns false
        editedProgram1 = new EditProgramDescriptorBuilder(DESC_PROGRAM_A).withSessions(VALID_SESSION_PROGRAM_B).build();
        assertFalse(DESC_PROGRAM_A.equals(editedProgram1));

        // different tags -> returns false
        editedProgram1 = new EditProgramDescriptorBuilder(DESC_PROGRAM_A).withTags(VALID_TAG_DOGS).build();
        assertFalse(DESC_PROGRAM_A.equals(editedProgram1));
    }
}
