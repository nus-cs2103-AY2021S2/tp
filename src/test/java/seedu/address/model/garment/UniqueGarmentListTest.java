package seedu.address.model.garment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DRESSCODE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGarments.ALICE;
import static seedu.address.testutil.TypicalGarments.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.garment.exceptions.DuplicateGarmentException;
import seedu.address.model.garment.exceptions.GarmentNotFoundException;
import seedu.address.testutil.GarmentBuilder;

public class UniqueGarmentListTest {

    private final UniqueGarmentList uniqueGarmentList = new UniqueGarmentList();

    @Test
    public void contains_nullGarment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGarmentList.contains(null));
    }

    @Test
    public void contains_garmentNotInList_returnsFalse() {
        assertFalse(uniqueGarmentList.contains(ALICE));
    }

    @Test
    public void contains_garmentInList_returnsTrue() {
        uniqueGarmentList.add(ALICE);
        assertTrue(uniqueGarmentList.contains(ALICE));
    }

    @Test
    public void contains_garmentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueGarmentList.add(ALICE);
        Garment editedAlice = new GarmentBuilder(ALICE)
                .withDressCode(VALID_DRESSCODE_BOB)
                .withDescriptions(VALID_DESCRIPTION_HUSBAND)
                .build();
        assertTrue(uniqueGarmentList.contains(editedAlice));
    }

    @Test
    public void add_nullGarment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGarmentList.add(null));
    }

    @Test
    public void add_duplicateGarment_throwsDuplicateGarmentException() {
        uniqueGarmentList.add(ALICE);
        assertThrows(DuplicateGarmentException.class, () -> uniqueGarmentList.add(ALICE));
    }

    @Test
    public void setGarment_nullTargetGarment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGarmentList.setGarment(null, ALICE));
    }

    @Test
    public void setGarment_nullEditedGarment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGarmentList.setGarment(ALICE, null));
    }

    @Test
    public void setGarment_targetGarmentNotInList_throwsGarmentNotFoundException() {
        assertThrows(GarmentNotFoundException.class, () -> uniqueGarmentList.setGarment(ALICE, ALICE));
    }

    @Test
    public void setGarment_editedGarmentIsSameGarment_success() {
        uniqueGarmentList.add(ALICE);
        uniqueGarmentList.setGarment(ALICE, ALICE);
        UniqueGarmentList expectedUniqueGarmentList = new UniqueGarmentList();
        expectedUniqueGarmentList.add(ALICE);
        assertEquals(expectedUniqueGarmentList, uniqueGarmentList);
    }

    @Test
    public void setGarment_editedGarmentHasSameIdentity_success() {
        uniqueGarmentList.add(ALICE);
        Garment editedAlice = new GarmentBuilder(ALICE)
                .withDressCode(VALID_DRESSCODE_BOB)
                .withDescriptions(VALID_DESCRIPTION_HUSBAND)
                .build();
        uniqueGarmentList.setGarment(ALICE, editedAlice);
        UniqueGarmentList expectedUniqueGarmentList = new UniqueGarmentList();
        expectedUniqueGarmentList.add(editedAlice);
        assertEquals(expectedUniqueGarmentList, uniqueGarmentList);
    }

    @Test
    public void setGarment_editedGarmentHasDifferentIdentity_success() {
        uniqueGarmentList.add(ALICE);
        uniqueGarmentList.setGarment(ALICE, BOB);
        UniqueGarmentList expectedUniqueGarmentList = new UniqueGarmentList();
        expectedUniqueGarmentList.add(BOB);
        assertEquals(expectedUniqueGarmentList, uniqueGarmentList);
    }

    @Test
    public void setGarment_editedGarmentHasNonUniqueIdentity_throwsDuplicateGarmentException() {
        uniqueGarmentList.add(ALICE);
        uniqueGarmentList.add(BOB);
        assertThrows(DuplicateGarmentException.class, () -> uniqueGarmentList.setGarment(ALICE, BOB));
    }

    @Test
    public void remove_nullGarment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGarmentList.remove(null));
    }

    @Test
    public void remove_garmentDoesNotExist_throwsGarmentNotFoundException() {
        assertThrows(GarmentNotFoundException.class, () -> uniqueGarmentList.remove(ALICE));
    }

    @Test
    public void remove_existingGarment_removesGarment() {
        uniqueGarmentList.add(ALICE);
        uniqueGarmentList.remove(ALICE);
        UniqueGarmentList expectedUniqueGarmentList = new UniqueGarmentList();
        assertEquals(expectedUniqueGarmentList, uniqueGarmentList);
    }

    @Test
    public void setGarments_nullUniqueGarmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGarmentList.setGarments((UniqueGarmentList) null));
    }

    @Test
    public void setGarments_uniqueGarmentList_replacesOwnListWithProvidedUniqueGarmentList() {
        uniqueGarmentList.add(ALICE);
        UniqueGarmentList expectedUniqueGarmentList = new UniqueGarmentList();
        expectedUniqueGarmentList.add(BOB);
        uniqueGarmentList.setGarments(expectedUniqueGarmentList);
        assertEquals(expectedUniqueGarmentList, uniqueGarmentList);
    }

    @Test
    public void setGarments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGarmentList.setGarments((List<Garment>) null));
    }

    @Test
    public void setGarments_list_replacesOwnListWithProvidedList() {
        uniqueGarmentList.add(ALICE);
        List<Garment> garmentList = Collections.singletonList(BOB);
        uniqueGarmentList.setGarments(garmentList);
        UniqueGarmentList expectedUniqueGarmentList = new UniqueGarmentList();
        expectedUniqueGarmentList.add(BOB);
        assertEquals(expectedUniqueGarmentList, uniqueGarmentList);
    }

    @Test
    public void setGarments_listWithDuplicateGarments_throwsDuplicateGarmentException() {
        List<Garment> listWithDuplicateGarments = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateGarmentException.class, () -> uniqueGarmentList.setGarments(listWithDuplicateGarments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueGarmentList.asUnmodifiableObservableList().remove(0));
    }
}
