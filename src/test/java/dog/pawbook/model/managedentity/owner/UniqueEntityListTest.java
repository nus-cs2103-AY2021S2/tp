package dog.pawbook.model.managedentity.owner;

import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalOwners.ALICE;
import static dog.pawbook.testutil.TypicalOwners.BOB;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.exceptions.DuplicateEntityException;
import dog.pawbook.model.managedentity.exceptions.EntityNotFoundException;
import javafx.util.Pair;

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
    public void add_nullOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntityList.add(null));
    }

    @Test
    public void add_duplicateOwner_throwsDuplicateOwnerException() {
        uniqueEntityList.add(ALICE);
        assertThrows(DuplicateEntityException.class, () -> uniqueEntityList.add(ALICE));
    }

    @Test
    public void setOwner_nullEditedOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntityList.setEntity(1, null));
    }

    @Test
    public void setOwner_targetOwnerNotInList_throwsOwnerNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> uniqueEntityList.setEntity(1, ALICE));
    }

    @Test
    public void remove_ownerDoesNotExist_throwsOwnerNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> uniqueEntityList.remove(Integer.MAX_VALUE));
    }

    @Test
    public void remove_invalidOwner_throwsNullPointerException() {
        assertThrows(EntityNotFoundException.class, () -> uniqueEntityList.remove(-1));
    }

    @Test
    public void remove_existingOwner_removesOwner() {
        uniqueEntityList.add(ALICE);

        List<Pair<Integer, Entity>> targets = uniqueEntityList.asUnmodifiableObservableList().stream()
                .filter(p -> p.getValue().equals(ALICE))
                .collect(toList());
        if (targets.get(0).getValue() instanceof Owner) {
            int aliceId = targets.get(0).getKey();
            uniqueEntityList.remove(aliceId);
            UniqueEntityList expectedUniqueOwnerList = new UniqueEntityList();
            assertEquals(expectedUniqueOwnerList, uniqueEntityList);
        }
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
        assertThrows(NullPointerException.class, () -> uniqueEntityList
                .setEntities((List<Pair<Integer, Entity>>) null));
    }

    @Test
    public void setOwners_list_replacesOwnListWithProvidedList() {
        uniqueEntityList.add(ALICE);
        List<Pair<Integer, Entity>> entityList = Collections.singletonList(new Pair<>(1, BOB));
        uniqueEntityList.setEntities(entityList);
        UniqueEntityList expectedUniqueOwnerList = new UniqueEntityList();
        expectedUniqueOwnerList.add(BOB);
        assertEquals(expectedUniqueOwnerList, uniqueEntityList);
    }

    @Test
    public void setOwners_listWithDuplicateOwners_throwsDuplicateOwnerException() {
        List<Pair<Integer, Entity>> listWithDuplicateEntities =
                Arrays.asList(new Pair<>(1, ALICE), new Pair<>(1, ALICE));
        assertThrows(DuplicateEntityException.class, () -> uniqueEntityList.setEntities(listWithDuplicateEntities));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueEntityList
                .asUnmodifiableObservableList()
                .remove(0));
    }
}
