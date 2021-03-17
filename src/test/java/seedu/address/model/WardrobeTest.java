package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DRESSCODE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGarments.ALICE;
import static seedu.address.testutil.TypicalGarments.getTypicalWardrobe;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.garment.Garment;
import seedu.address.model.garment.exceptions.DuplicateGarmentException;
import seedu.address.testutil.GarmentBuilder;

public class WardrobeTest {

    private final Wardrobe wardrobe = new Wardrobe();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), wardrobe.getGarmentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wardrobe.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyWardrobe_replacesData() {
        Wardrobe newData = getTypicalWardrobe();
        wardrobe.resetData(newData);
        assertEquals(newData, wardrobe);
    }

    @Test
    public void resetData_withDuplicateGarments_throwsDuplicateGarmentException() {
        // Two garments with the same identity fields
        Garment editedAlice = new GarmentBuilder(ALICE)
                .withDressCode(VALID_DRESSCODE_BOB)
                .withDescriptions(VALID_DESCRIPTION_HUSBAND)
                .build();
        List<Garment> newGarments = Arrays.asList(ALICE, editedAlice);
        WardrobeStub newData = new WardrobeStub(newGarments);

        assertThrows(DuplicateGarmentException.class, () -> wardrobe.resetData(newData));
    }

    @Test
    public void hasGarment_nullGarment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wardrobe.hasGarment(null));
    }

    @Test
    public void hasGarment_garmentNotInWardrobe_returnsFalse() {
        assertFalse(wardrobe.hasGarment(ALICE));
    }

    @Test
    public void hasGarment_garmentInWardrobe_returnsTrue() {
        wardrobe.addGarment(ALICE);
        assertTrue(wardrobe.hasGarment(ALICE));
    }

    @Test
    public void hasGarment_garmentWithSameIdentityFieldsInWardrobe_returnsTrue() {
        wardrobe.addGarment(ALICE);
        Garment editedAlice = new GarmentBuilder(ALICE)
                .withDressCode(VALID_DRESSCODE_BOB)
                .withDescriptions(VALID_DESCRIPTION_HUSBAND)
                .build();
        assertTrue(wardrobe.hasGarment(editedAlice));
    }

    @Test
    public void getGarmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> wardrobe.getGarmentList().remove(0));
    }

    /**
     * A stub ReadOnlyWardrobe whose garments list can violate interface constraints.
     */
    private static class WardrobeStub implements ReadOnlyWardrobe {
        private final ObservableList<Garment> garments = FXCollections.observableArrayList();

        WardrobeStub(Collection<Garment> garments) {
            this.garments.setAll(garments);
        }

        @Override
        public ObservableList<Garment> getGarmentList() {
            return garments;
        }
    }

}
