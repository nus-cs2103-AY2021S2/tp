package seedu.address.model.residence;

import org.junit.jupiter.api.Test;
import seedu.address.model.residence.Residence;
import seedu.address.model.residence.UniqueResidenceList;
import seedu.address.model.residence.exceptions.DuplicateResidenceException;
import seedu.address.model.residence.exceptions.ResidenceNotFoundException;
import seedu.address.testutil.ResidenceBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_RESIDENCE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_RESERVED;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalResidences.RESIDENCE_A;
import static seedu.address.testutil.TypicalResidences.RESIDENCE2;

public class UniqueResidenceListTest {

    private final UniqueResidenceList uniqueResidenceList = new UniqueResidenceList();

    @Test
    public void contains_nullResidence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueResidenceList.contains(null));
    }

    @Test
    public void contains_residenceNotInList_returnsFalse() {
        assertFalse(uniqueResidenceList.contains(RESIDENCE_A));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueResidenceList.add(RESIDENCE_A);
        assertTrue(uniqueResidenceList.contains(RESIDENCE_A));
    }

    @Test
    public void contains_residenceWithSameIdentityFieldsInList_returnsTrue() {
        uniqueResidenceList.add(RESIDENCE_A);
        Residence editedResidenceA = new ResidenceBuilder(RESIDENCE_A)
                .withAddress(VALID_ADDRESS_RESIDENCE2)
                .withTags(VALID_TAG_RESERVED)
                .build();
        assertTrue(uniqueResidenceList.contains(editedResidenceA));
    }

    @Test
    public void add_nullResidence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueResidenceList.add(null));
    }

    @Test
    public void add_duplicateResidence_throwsDuplicateResidenceException() {
        uniqueResidenceList.add(RESIDENCE_A);
        assertThrows(DuplicateResidenceException.class, () -> uniqueResidenceList.add(RESIDENCE_A));
    }

    @Test
    public void setResidence_nullTargetResidence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueResidenceList.setResidence(null, RESIDENCE_A));
    }

    @Test
    public void setResidence_nullEditedResidence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueResidenceList.setResidence(RESIDENCE_A, null));
    }

    @Test
    public void setResidence_targetResidenceNotInList_throwsResidenceNotFoundException() {
        assertThrows(ResidenceNotFoundException.class, () -> uniqueResidenceList.setResidence(RESIDENCE_A, RESIDENCE_A));
    }

    @Test
    public void setResidence_editedResidenceIsSameResidence_success() {
        uniqueResidenceList.add(RESIDENCE_A);
        uniqueResidenceList.setResidence(RESIDENCE_A, RESIDENCE_A);
        UniqueResidenceList expectedUniquePersonList = new UniqueResidenceList();
        expectedUniquePersonList.add(RESIDENCE_A);
        assertEquals(expectedUniquePersonList, uniqueResidenceList);
    }

    @Test
    public void setResidence_editedResidenceHasSameIdentity_success() {
        uniqueResidenceList.add(RESIDENCE_A);
        Residence editedResidenceA = new ResidenceBuilder(RESIDENCE_A)
                .withAddress(VALID_ADDRESS_RESIDENCE2)
                .withTags(VALID_TAG_RESERVED)
                .build();
        uniqueResidenceList.setResidence(RESIDENCE_A, editedResidenceA);
        UniqueResidenceList expectedUniquePersonList = new UniqueResidenceList();
        expectedUniquePersonList.add(editedResidenceA);
        assertEquals(expectedUniquePersonList, uniqueResidenceList);
    }

    @Test
    public void setResidence_editedResidenceHasDifferentIdentity_success() {
        uniqueResidenceList.add(RESIDENCE_A);
        uniqueResidenceList.setResidence(RESIDENCE_A, RESIDENCE2);
        UniqueResidenceList expectedUniquePersonList = new UniqueResidenceList();
        expectedUniquePersonList.add(RESIDENCE2);
        assertEquals(expectedUniquePersonList, uniqueResidenceList);
    }

    @Test
    public void setResidence_editedResidenceHasNonUniqueIdentity_throwsDuplicateResidenceException() {
        uniqueResidenceList.add(RESIDENCE_A);
        uniqueResidenceList.add(RESIDENCE2);
        assertThrows(DuplicateResidenceException.class, () -> uniqueResidenceList.setResidence(RESIDENCE_A, RESIDENCE2));
    }

    @Test
    public void remove_nullResidence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueResidenceList.remove(null));
    }

    @Test
    public void remove_residenceDoesNotExist_throwsResidenceNotFoundException() {
        assertThrows(ResidenceNotFoundException.class, () -> uniqueResidenceList.remove(RESIDENCE_A));
    }

    @Test
    public void remove_existingResidence_removesResidence() {
        uniqueResidenceList.add(RESIDENCE_A);
        uniqueResidenceList.remove(RESIDENCE_A);
        UniqueResidenceList expectedUniquePersonList = new UniqueResidenceList();
        assertEquals(expectedUniquePersonList, uniqueResidenceList);
    }

    @Test
    public void setResidences_nullUniqueResidenceList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueResidenceList.setResidences((UniqueResidenceList) null));
    }

    @Test
    public void setResidences_uniqueResidenceList_replacesOwnListWithProvidedUniqueResidenceList() {
        uniqueResidenceList.add(RESIDENCE_A);
        UniqueResidenceList expectedUniqueResidenceList = new UniqueResidenceList();
        expectedUniqueResidenceList.add(RESIDENCE2);
        uniqueResidenceList.setResidences(expectedUniqueResidenceList);
        assertEquals(expectedUniqueResidenceList, uniqueResidenceList);
    }

    @Test
    public void setResidences_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueResidenceList.setResidences((List<Residence>) null));
    }

    @Test
    public void setResidences_list_replacesOwnListWithProvidedList() {
        uniqueResidenceList.add(RESIDENCE_A);
        List<Residence> personList = Collections.singletonList(RESIDENCE2);
        uniqueResidenceList.setResidences(personList);
        UniqueResidenceList expectedUniqueResidenceList = new UniqueResidenceList();
        expectedUniqueResidenceList.add(RESIDENCE2);
        assertEquals(expectedUniqueResidenceList, uniqueResidenceList);
    }

    @Test
    public void setResidences_listWithDuplicateResidences_throwsDuplicateResidenceException() {
        List<Residence> listWithDuplicatePersons = Arrays.asList(RESIDENCE_A, RESIDENCE_A);
        assertThrows(DuplicateResidenceException.class, () ->
                uniqueResidenceList.setResidences(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueResidenceList.asUnmodifiableObservableList().remove(0));
    }
}
