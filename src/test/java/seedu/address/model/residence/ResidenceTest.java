package seedu.address.model.residence;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_RESIDENCE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_RESIDENCE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNCLEAN_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalResidences.RESIDENCE1;
import static seedu.address.testutil.TypicalResidences.RESIDENCE2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ResidenceBuilder;

public class ResidenceTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Residence residence = new ResidenceBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> residence.getTags().remove(0));
    }

    @Test
    public void isSameResidence() {
        // same object -> returns true
        assertTrue(RESIDENCE1.isSameResidence(RESIDENCE1));

        // null -> returns false
        assertFalse(RESIDENCE1.isSameResidence(null));

        // same name, all other attributes different -> returns true
        Residence editedR1 = new ResidenceBuilder(RESIDENCE1).withAddress(VALID_ADDRESS_RESIDENCE2)
                .withCleanStatusTag("n").withTags(VALID_TAG_REPAIR).build();
        //.withBookingDetails()
        assertTrue(RESIDENCE1.isSameResidence(editedR1));

        // different name, all other attributes same -> returns false
        editedR1 = new ResidenceBuilder(RESIDENCE1).withName("new name").build();
        assertFalse(RESIDENCE1.isSameResidence(editedR1));

        // name differs in case, all other attributes same -> returns false
        Residence editedR2 = new ResidenceBuilder(RESIDENCE2).withName(VALID_NAME_RESIDENCE2.toUpperCase()).build();
        assertFalse(RESIDENCE2.isSameResidence(editedR2));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_RESIDENCE2 + " ";
        editedR2 = new ResidenceBuilder(RESIDENCE2).withName(nameWithTrailingSpaces).build();
        assertFalse(RESIDENCE2.isSameResidence(editedR2));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Residence r1Copy = new ResidenceBuilder(RESIDENCE1).build();
        assertTrue(RESIDENCE1.equals(r1Copy));

        // same object -> returns true
        assertTrue(RESIDENCE1.equals(r1Copy));

        // null -> returns false
        assertFalse(RESIDENCE1.equals(null));

        // different type -> returns false
        assertFalse(RESIDENCE1.equals(5));

        // different residence -> returns false
        assertFalse(RESIDENCE1.equals(RESIDENCE2));

        // different name -> returns false
        Residence editedR1 = new ResidenceBuilder(RESIDENCE1).withName(VALID_NAME_RESIDENCE2).build();
        assertFalse(RESIDENCE1.equals(editedR1));

        // different clean status tag -> returns false
        editedR1 = new ResidenceBuilder(RESIDENCE1).withCleanStatusTag(VALID_UNCLEAN_TAG).build();
        assertFalse(RESIDENCE1.equals(editedR1));

        // different address -> returns false
        editedR1 = new ResidenceBuilder(RESIDENCE1).withAddress(VALID_ADDRESS_RESIDENCE2).build();
        assertFalse(RESIDENCE1.equals(editedR1));

        // different tags -> returns false
        editedR1 = new ResidenceBuilder(RESIDENCE1).withTags(VALID_TAG_REPAIR).build();
        assertFalse(RESIDENCE1.equals(editedR1));
    }
}
