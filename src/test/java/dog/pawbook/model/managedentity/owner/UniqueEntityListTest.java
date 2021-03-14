package dog.pawbook.model.managedentity.owner;

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

import dog.pawbook.model.managedentity.owner.exceptions.DuplicateOwnerException;
import dog.pawbook.model.managedentity.owner.exceptions.OwnerNotFoundException;
import dog.pawbook.testutil.OwnerBuilder;

public class UniqueEntityListTest {

    private final UniqueEntityList uniqueEntityList = new UniqueEntityList();

    @Test
    public void contains_nullOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntityList.contains(null));
    }

    @Test
    public void contains_ownerNotInList_returnsFalse() {
        assertFalse(uniqueEntityList.contains(ALICE));
    }

    @Test
    public void contains_ownerInList_returnsTrue() {
        uniqueEntityList.add(ALICE);
        assertTrue(uniqueEntityList.contains(ALICE));
    }

    @Test
    public void contains_ownerWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEntityList.add(ALICE);
        Owner editedAlice = new OwnerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueEntityList.contains(editedAlice));
    }

    @Test
    public void add_nullOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntityList.add(null));
    }

    @Test
    public void add_duplicateOwner_throwsDuplicateOwnerException() {
        uniqueEntityList.add(ALICE);
        assertThrows(DuplicateOwnerException.class, () -> uniqueEntityList.add(ALICE));
    }

    @Test
    public void setOwner_nullTargetOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntityList.setEntity(null, ALICE));
    }

    @Test
    public void setOwner_nullEditedOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntityList.setEntity(ALICE, null));
    }

    @Test
    public void setOwner_targetOwnerNotInList_throwsOwnerNotFoundException() {
        assertThrows(OwnerNotFoundException.class, () -> uniqueEntityList.setEntity(ALICE, ALICE));
    }

    @Test
    public void setOwner_editedOwnerIsSameOwner_success() {
        uniqueEntityList.add(ALICE);
        uniqueEntityList.setEntity(ALICE, ALICE);
        UniqueEntityList expectedUniqueOwnerList = new UniqueEntityList();
        expectedUniqueOwnerList.add(ALICE);
        assertEquals(expectedUniqueOwnerList, uniqueEntityList);
    }

    @Test
    public void setOwner_editedOwnerHasSameIdentity_success() {
        uniqueEntityList.add(ALICE);
        Owner editedAlice = new OwnerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueEntityList.setEntity(ALICE, editedAlice);
        UniqueEntityList expectedUniqueOwnerList = new UniqueEntityList();
        expectedUniqueOwnerList.add(editedAlice);
        assertEquals(expectedUniqueOwnerList, uniqueEntityList);
    }

    @Test
    public void setOwner_editedOwnerHasDifferentIdentity_success() {
        uniqueEntityList.add(ALICE);
        uniqueEntityList.setEntity(ALICE, BOB);
        UniqueEntityList expectedUniqueOwnerList = new UniqueEntityList();
        expectedUniqueOwnerList.add(BOB);
        assertEquals(expectedUniqueOwnerList, uniqueEntityList);
    }

    @Test
    public void setOwner_editedOwnerHasNonUniqueIdentity_throwsDuplicateOwnerException() {
        uniqueEntityList.add(ALICE);
        uniqueEntityList.add(BOB);
        assertThrows(DuplicateOwnerException.class, () -> uniqueEntityList.setEntity(ALICE, BOB));
    }

    @Test
    public void remove_nullOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntityList.remove(null));
    }

    @Test
    public void remove_ownerDoesNotExist_throwsOwnerNotFoundException() {
        assertThrows(OwnerNotFoundException.class, () -> uniqueEntityList.remove(ALICE));
    }

    @Test
    public void remove_existingOwner_removesOwner() {
        uniqueEntityList.add(ALICE);
        uniqueEntityList.remove(ALICE);
        UniqueEntityList expectedUniqueOwnerList = new UniqueEntityList();
        assertEquals(expectedUniqueOwnerList, uniqueEntityList);
    }

    @Test
    public void setOwners_nullUniqueOwnerList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntityList.setEntities((UniqueEntityList) null));
    }

    @Test
    public void setOwners_uniqueOwnerList_replacesOwnListWithProvidedUniqueOwnerList() {
        uniqueEntityList.add(ALICE);
        UniqueEntityList expectedUniqueOwnerList = new UniqueEntityList();
        expectedUniqueOwnerList.add(BOB);
        uniqueEntityList.setEntities(expectedUniqueOwnerList);
        assertEquals(expectedUniqueOwnerList, uniqueEntityList);
    }

    @Test
    public void setOwners_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntityList.setEntities((List<Owner>) null));
    }

    @Test
    public void setOwners_list_replacesOwnListWithProvidedList() {
        uniqueEntityList.add(ALICE);
        List<Owner> ownerList = Collections.singletonList(BOB);
        uniqueEntityList.setEntities(ownerList);
        UniqueEntityList expectedUniqueOwnerList = new UniqueEntityList();
        expectedUniqueOwnerList.add(BOB);
        assertEquals(expectedUniqueOwnerList, uniqueEntityList);
    }

    @Test
    public void setOwners_listWithDuplicateOwners_throwsDuplicateOwnerException() {
        List<Owner> listWithDuplicateOwners = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateOwnerException.class, () -> uniqueEntityList.setEntities(listWithDuplicateOwners));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueEntityList.asUnmodifiableObservableList().remove(0));
    }
}
