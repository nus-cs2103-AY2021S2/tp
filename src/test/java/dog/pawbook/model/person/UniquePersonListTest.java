package dog.pawbook.model.owner;

import static dog.pawbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalOwners.ALICE;
import static dog.pawbook.testutil.TypicalOwners.BOB;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import dog.pawbook.model.owner.exceptions.DuplicateOwnerException;
import dog.pawbook.model.owner.exceptions.OwnerNotFoundException;
import dog.pawbook.testutil.OwnerBuilder;

public class UniqueOwnerListTest {

    private final UniqueOwnerList uniqueOwnerList = new UniqueOwnerList();

    @Test
    public void contains_nullOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOwnerList.contains(null));
    }

    @Test
    public void contains_ownerNotInList_returnsFalse() {
        assertFalse(uniqueOwnerList.contains(ALICE));
    }

    @Test
    public void contains_ownerInList_returnsTrue() {
        uniqueOwnerList.add(ALICE);
        assertTrue(uniqueOwnerList.contains(ALICE));
    }

    @Test
    public void contains_ownerWithSameIdentityFieldsInList_returnsTrue() {
        uniqueOwnerList.add(ALICE);
        Owner editedAlice = new OwnerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueOwnerList.contains(editedAlice));
    }

    @Test
    public void add_nullOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOwnerList.add(null));
    }

    @Test
    public void add_duplicateOwner_throwsDuplicateOwnerException() {
        uniqueOwnerList.add(ALICE);
        assertThrows(DuplicateOwnerException.class, () -> uniqueOwnerList.add(ALICE));
    }

    @Test
    public void setOwner_nullTargetOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOwnerList.setOwner(null, ALICE));
    }

    @Test
    public void setOwner_nullEditedOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOwnerList.setOwner(ALICE, null));
    }

    @Test
    public void setOwner_targetOwnerNotInList_throwsOwnerNotFoundException() {
        assertThrows(OwnerNotFoundException.class, () -> uniqueOwnerList.setOwner(ALICE, ALICE));
    }

    @Test
    public void setOwner_editedOwnerIsSameOwner_success() {
        uniqueOwnerList.add(ALICE);
        uniqueOwnerList.setOwner(ALICE, ALICE);
        UniqueOwnerList expectedUniqueOwnerList = new UniqueOwnerList();
        expectedUniqueOwnerList.add(ALICE);
        assertEquals(expectedUniqueOwnerList, uniqueOwnerList);
    }

    @Test
    public void setOwner_editedOwnerHasSameIdentity_success() {
        uniqueOwnerList.add(ALICE);
        Owner editedAlice = new OwnerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueOwnerList.setOwner(ALICE, editedAlice);
        UniqueOwnerList expectedUniqueOwnerList = new UniqueOwnerList();
        expectedUniqueOwnerList.add(editedAlice);
        assertEquals(expectedUniqueOwnerList, uniqueOwnerList);
    }

    @Test
    public void setOwner_editedOwnerHasDifferentIdentity_success() {
        uniqueOwnerList.add(ALICE);
        uniqueOwnerList.setOwner(ALICE, BOB);
        UniqueOwnerList expectedUniqueOwnerList = new UniqueOwnerList();
        expectedUniqueOwnerList.add(BOB);
        assertEquals(expectedUniqueOwnerList, uniqueOwnerList);
    }

    @Test
    public void setOwner_editedOwnerHasNonUniqueIdentity_throwsDuplicateOwnerException() {
        uniqueOwnerList.add(ALICE);
        uniqueOwnerList.add(BOB);
        assertThrows(DuplicateOwnerException.class, () -> uniqueOwnerList.setOwner(ALICE, BOB));
    }

    @Test
    public void remove_nullOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOwnerList.remove(null));
    }

    @Test
    public void remove_ownerDoesNotExist_throwsOwnerNotFoundException() {
        assertThrows(OwnerNotFoundException.class, () -> uniqueOwnerList.remove(ALICE));
    }

    @Test
    public void remove_existingOwner_removesOwner() {
        uniqueOwnerList.add(ALICE);
        uniqueOwnerList.remove(ALICE);
        UniqueOwnerList expectedUniqueOwnerList = new UniqueOwnerList();
        assertEquals(expectedUniqueOwnerList, uniqueOwnerList);
    }

    @Test
    public void setOwners_nullUniqueOwnerList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOwnerList.setOwners((UniqueOwnerList) null));
    }

    @Test
    public void setOwners_uniqueOwnerList_replacesOwnListWithProvidedUniqueOwnerList() {
        uniqueOwnerList.add(ALICE);
        UniqueOwnerList expectedUniqueOwnerList = new UniqueOwnerList();
        expectedUniqueOwnerList.add(BOB);
        uniqueOwnerList.setOwners(expectedUniqueOwnerList);
        assertEquals(expectedUniqueOwnerList, uniqueOwnerList);
    }

    @Test
    public void setOwners_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOwnerList.setOwners((List<Owner>) null));
    }

    @Test
    public void setOwners_list_replacesOwnListWithProvidedList() {
        uniqueOwnerList.add(ALICE);
        List<Owner> ownerList = Collections.singletonList(BOB);
        uniqueOwnerList.setOwners(ownerList);
        UniqueOwnerList expectedUniqueOwnerList = new UniqueOwnerList();
        expectedUniqueOwnerList.add(BOB);
        assertEquals(expectedUniqueOwnerList, uniqueOwnerList);
    }

    @Test
    public void setOwners_listWithDuplicateOwners_throwsDuplicateOwnerException() {
        List<Owner> listWithDuplicateOwners = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateOwnerException.class, () -> uniqueOwnerList.setOwners(listWithDuplicateOwners));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueOwnerList.asUnmodifiableObservableList().remove(0));
    }
}
