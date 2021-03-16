package seedu.address.model.plan;

/*
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
 */
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_COMPUTER_NETWORKING_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_COMPUTER_NETWORKING_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPlans.COMPUTER_NETWORKING;
import static seedu.address.testutil.TypicalPlans.SOFTWARE_ENGINEERING;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PlanBuilder;

public class PlanTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Plan plan = new PlanBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> plan.getTags().remove(0));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Plan softwareEngineeringCopy = new PlanBuilder(SOFTWARE_ENGINEERING).build();
        assertTrue(SOFTWARE_ENGINEERING.equals(softwareEngineeringCopy));

        // same object -> returns true
        assertTrue(SOFTWARE_ENGINEERING.equals(SOFTWARE_ENGINEERING));

        // null -> returns false
        assertFalse(SOFTWARE_ENGINEERING.equals(null));

        // different type -> returns false
        assertFalse(SOFTWARE_ENGINEERING.equals(5));

        // different plan -> returns false
        assertFalse(SOFTWARE_ENGINEERING.equals(COMPUTER_NETWORKING));

        // different description -> returns false
        Plan editedSoftwareEngineering = new PlanBuilder(SOFTWARE_ENGINEERING)
                .withDescription(VALID_PLAN_COMPUTER_NETWORKING_DESCRIPTION).build();
        assertFalse(SOFTWARE_ENGINEERING.equals(editedSoftwareEngineering));

        // different tags -> returns false
        editedSoftwareEngineering = new PlanBuilder(SOFTWARE_ENGINEERING)
                .withTags(VALID_PLAN_COMPUTER_NETWORKING_TAG).build();
        assertFalse(SOFTWARE_ENGINEERING.equals(editedSoftwareEngineering));
    }
}
