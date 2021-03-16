package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_RESIDENCE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_REPAIR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalResidences.RESIDENCE1;
import static seedu.address.testutil.TypicalResidences.getTypicalResidenceTracker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.residence.Residence;
import seedu.address.testutil.ResidenceBuilder;

public class ResidenceTrackerTest {

    private final ResidenceTracker residenceTracker = new ResidenceTracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), residenceTracker.getResidenceList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> residenceTracker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        ResidenceTracker newData = getTypicalResidenceTracker();
        residenceTracker.resetData(newData);
        assertEquals(newData, residenceTracker);
    }

    @Test
    public void resetData_withDuplicateResidences_throwsDuplicateResidenceException() {
        // Two persons with the same identity fields
        Residence editedR1 = new ResidenceBuilder(RESIDENCE1).withAddress(VALID_ADDRESS_RESIDENCE2)
                .withTags(VALID_TAG_REPAIR).build();
        List<Residence> newResidences = Arrays.asList(RESIDENCE1, editedR1);
        ResidenceTrackerStub newData = new ResidenceTrackerStub(newResidences);

        assertThrows(DuplicatePersonException.class, () -> residenceTracker.resetData(newData));
    }

    @Test
    public void hasResidence_nullResidence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> residenceTracker.hasResidence(null));
    }

    @Test
    public void hasResidence_residenceNotInResidenceTracker_returnsFalse() {
        assertFalse(residenceTracker.hasResidence(RESIDENCE1));
    }

    @Test
    public void hasResidence_residenceInResidenceTracker_returnsTrue() {
        residenceTracker.addResidence(RESIDENCE1);
        assertTrue(residenceTracker.hasResidence(RESIDENCE1));
    }

    @Test
    public void hasResidence_residenceWithSameIdentityFieldsInResidenceTracker_returnsTrue() {
        residenceTracker.addResidence(RESIDENCE1);
        Residence editedR1 = new ResidenceBuilder(RESIDENCE1).withAddress(VALID_ADDRESS_RESIDENCE2)
                .withTags(VALID_TAG_REPAIR).build();
        assertTrue(residenceTracker.hasResidence(editedR1));
    }

    @Test
    public void getResidenceList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> residenceTracker.getResidenceList().remove(0));
    }

    /**
     * A stub ReadOnlyResidenceTracker whose list of residence can violate interface constraints.
     */
    private static class ResidenceTrackerStub implements ReadOnlyResidenceTracker {
        private final ObservableList<Residence> residences = FXCollections.observableArrayList();

        ResidenceTrackerStub(Collection<Residence> residences) {
            this.residences.setAll(residences);
        }

        @Override
        public ObservableList<Residence> getResidenceList() {
            return residences;
        }
    }

}
