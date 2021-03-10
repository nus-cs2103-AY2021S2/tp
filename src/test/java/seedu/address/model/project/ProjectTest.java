package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalProjects.CS1101S;
import static seedu.address.testutil.TypicalProjects.CS2103T_PROJECT;

import org.junit.jupiter.api.Test;

public class ProjectTest {

    @Test
    public void isSameProject() {
        // same object -> returns true
        assertTrue(CS1101S.isSameProject(CS1101S));

        // null -> returns false
        assertFalse(CS1101S.isSameProject(null));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(CS1101S.equals(CS1101S));

        // null -> returns false
        assertFalse(CS1101S.equals(null));

        // different type -> returns false
        assertFalse(CS1101S.equals(5));

        // different project -> returns false
        assertFalse(CS1101S.equals(CS2103T_PROJECT));
    }
}
