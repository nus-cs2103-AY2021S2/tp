package seedu.address.model.pool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPassengers.getTypicalPassengers;
import static seedu.address.testutil.TypicalPools.HOMEPOOL;
import static seedu.address.testutil.TypicalPools.OFFICEPOOL;
import static seedu.address.testutil.TypicalPools.WORKPOOL;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.pool.exceptions.DuplicatePoolException;
import seedu.address.model.pool.exceptions.PoolNotFoundException;
import seedu.address.testutil.PoolBuilder;

public class UniquePoolListTest {

    private final UniquePoolList uniquePoolList = new UniquePoolList();

    @Test
    public void contains_nullPool_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePoolList.contains(null));
    }

    @Test
    public void contains_poolNotInList_returnsFalse() {
        assertFalse(uniquePoolList.contains(HOMEPOOL));
    }

    @Test
    public void contains_poolInList_returnsTrue() {
        uniquePoolList.add(HOMEPOOL);
        assertTrue(uniquePoolList.contains(HOMEPOOL));
    }

    @Test
    public void contains_differentPassengerListWithSameIdentityFieldsInList_returnsTrue() {
        uniquePoolList.add(WORKPOOL);
        Pool editedPool = new PoolBuilder(WORKPOOL).withPassengers(getTypicalPassengers())
                .build();
        assertTrue(uniquePoolList.contains(editedPool));
    }

    @Test
    public void add_nullPool_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePoolList.add(null));
    }

    @Test
    public void add_duplicatePool_throwsDuplicatePoolException() {
        uniquePoolList.add(OFFICEPOOL);
        assertThrows(DuplicatePoolException.class, () -> uniquePoolList.add(OFFICEPOOL));
    }

    @Test
    public void setPool_nullTargetPool_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePoolList.setPool(null, OFFICEPOOL));
    }

    @Test
    public void setPool_nullEditedPool_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePoolList.setPool(OFFICEPOOL, null));
    }

    @Test
    public void setPool_targetPoolNotInList_throwsPoolNotFoundException() {
        assertThrows(PoolNotFoundException.class, () -> uniquePoolList.setPool(HOMEPOOL, HOMEPOOL));
    }

    @Test
    public void setPool_editedPoolIsSamePool_success() {
        uniquePoolList.add(HOMEPOOL);
        uniquePoolList.setPool(HOMEPOOL, HOMEPOOL);
        UniquePoolList expecteduniquePoolList = new UniquePoolList();
        expecteduniquePoolList.add(HOMEPOOL);
        assertEquals(expecteduniquePoolList, uniquePoolList);
    }

    @Test
    public void setPool_editedPoolHasSameIdentity_success() {
        uniquePoolList.add(OFFICEPOOL);
        Pool editedPool = new PoolBuilder(OFFICEPOOL).withPassengers(getTypicalPassengers())
                .build();
        uniquePoolList.setPool(OFFICEPOOL, editedPool);
        UniquePoolList expectedUniquePoolList = new UniquePoolList();
        expectedUniquePoolList.add(editedPool);
        assertEquals(expectedUniquePoolList, uniquePoolList);
    }

    @Test
    public void setPool_editedPoolHasDifferentIdentity_success() {
        uniquePoolList.add(WORKPOOL);
        uniquePoolList.setPool(WORKPOOL, OFFICEPOOL);
        UniquePoolList expecteduniquePoolList = new UniquePoolList();
        expecteduniquePoolList.add(OFFICEPOOL);
        assertEquals(expecteduniquePoolList, uniquePoolList);
    }

    @Test
    public void setPool_editedPoolHasNonUniqueIdentity_throwsDuplicatePoolException() {
        uniquePoolList.add(WORKPOOL);
        uniquePoolList.add(HOMEPOOL);
        assertThrows(DuplicatePoolException.class, () -> uniquePoolList.setPool(HOMEPOOL, WORKPOOL));
    }

    @Test
    public void setPool_nullPool_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePoolList.remove(null));
    }

    @Test
    public void remove_poolDoesNotExist_throwsPoolNotFoundException() {
        assertThrows(PoolNotFoundException.class, () -> uniquePoolList.remove(HOMEPOOL));
    }

    @Test
    public void remove_existingPool_removesPool() {
        uniquePoolList.add(OFFICEPOOL);
        uniquePoolList.remove(OFFICEPOOL);
        UniquePoolList expectedUniquePoolList = new UniquePoolList();
        assertEquals(expectedUniquePoolList, uniquePoolList);
    }

    @Test
    public void setPools_nullUniquePoolList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePoolList.setPools((UniquePoolList) null));
    }

    @Test
    public void setPools_uniquePoolList_replacesOwnListWithProvideduniquePoolList() {
        uniquePoolList.add(WORKPOOL);
        UniquePoolList expecteduniquePoolList = new UniquePoolList();
        expecteduniquePoolList.add(HOMEPOOL);
        uniquePoolList.setPools(expecteduniquePoolList);
        assertEquals(expecteduniquePoolList, uniquePoolList);
    }

    @Test
    public void setPools_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePoolList.setPools((List<Pool>) null));
    }

    @Test
    public void setPools_list_replacesOwnListWithProvidedList() {
        uniquePoolList.add(HOMEPOOL);
        List<Pool> poolList = Collections.singletonList(WORKPOOL);
        uniquePoolList.setPools(poolList);
        UniquePoolList expectedUniquePoolList = new UniquePoolList();
        expectedUniquePoolList.add(WORKPOOL);
        assertEquals(expectedUniquePoolList, uniquePoolList);
    }

    @Test
    public void setPools_listWithDuplicatePools_throwsDuplicatePoolException() {
        List<Pool> listWithDuplicatePools = Arrays.asList(OFFICEPOOL, OFFICEPOOL);
        assertThrows(
                DuplicatePoolException.class, () -> uniquePoolList.setPools(listWithDuplicatePools)
        );
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePoolList.asUnmodifiableObservableList().remove(0));
    }
}
