package seedu.storemando.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_LOCATION_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_TAG_ESSENTIAL;
import static seedu.storemando.testutil.Assert.assertThrows;
import static seedu.storemando.testutil.TypicalItems.MILK;
import static seedu.storemando.testutil.TypicalItems.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.storemando.model.item.exceptions.DuplicateItemException;
import seedu.storemando.model.item.exceptions.ItemNotFoundException;
import seedu.storemando.testutil.ItemBuilder;

public class UniqueItemListTest {

    private final UniqueItemList uniqueItemList = new UniqueItemList();

    @Test
    public void contains_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.contains(null));
    }

    @Test
    public void contains_itemNotInList_returnsFalse() {
        assertFalse(uniqueItemList.contains(MILK));
    }

    @Test
    public void contains_itemInList_returnsTrue() {
        uniqueItemList.add(MILK);
        assertTrue(uniqueItemList.contains(MILK));
    }

    @Test
    public void contains_itemWithSameIdentityFieldsInList_returnsTrue() {
        uniqueItemList.add(MILK);
        Item editedAlice = new ItemBuilder(MILK).withLocation(VALID_LOCATION_BANANA).withTags(VALID_TAG_ESSENTIAL)
            .build();
        assertTrue(uniqueItemList.contains(editedAlice));
    }

    @Test
    public void add_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.add(null));
    }

    @Test
    public void add_duplicateItem_throwsDuplicateItemException() {
        uniqueItemList.add(MILK);
        assertThrows(DuplicateItemException.class, () -> uniqueItemList.add(MILK));
    }

    @Test
    public void setItem_nullTargetItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItem(null, MILK));
    }

    @Test
    public void setItem_nullEditedItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItem(MILK, null));
    }

    @Test
    public void setItem_targetItemNotInList_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueItemList.setItem(MILK, MILK));
    }

    @Test
    public void setItem_editedItemIsSameItem_success() {
        uniqueItemList.add(MILK);
        uniqueItemList.setItem(MILK, MILK);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(MILK);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_editedItemHasSameIdentity_success() {
        uniqueItemList.add(MILK);
        Item editedAlice = new ItemBuilder(MILK).withLocation(VALID_LOCATION_BANANA).withTags(VALID_TAG_ESSENTIAL)
            .build();
        uniqueItemList.setItem(MILK, editedAlice);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(editedAlice);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_editedItemHasDifferentIdentity_success() {
        uniqueItemList.add(MILK);
        uniqueItemList.setItem(MILK, BOB);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(BOB);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_editedItemHasNonUniqueIdentity_throwsDuplicateItemException() {
        uniqueItemList.add(MILK);
        uniqueItemList.add(BOB);
        assertThrows(DuplicateItemException.class, () -> uniqueItemList.setItem(MILK, BOB));
    }

    @Test
    public void remove_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.remove(null));
    }

    @Test
    public void remove_itemDoesNotExist_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueItemList.remove(MILK));
    }

    @Test
    public void remove_existingItem_removesItem() {
        uniqueItemList.add(MILK);
        uniqueItemList.remove(MILK);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItems_nullUniqueItemList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItems((UniqueItemList) null));
    }

    @Test
    public void setItems_uniqueItemList_replacesOwnListWithProvidedUniqueItemList() {
        uniqueItemList.add(MILK);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(BOB);
        uniqueItemList.setItems(expectedUniqueItemList);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItems_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItems((List<Item>) null));
    }

    @Test
    public void setItems_list_replacesOwnListWithProvidedList() {
        uniqueItemList.add(MILK);
        List<Item> itemList = Collections.singletonList(BOB);
        uniqueItemList.setItems(itemList);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(BOB);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItems_listWithDuplicateItems_throwsDuplicateItemException() {
        List<Item> listWithDuplicateItems = Arrays.asList(MILK, MILK);
        assertThrows(DuplicateItemException.class, () -> uniqueItemList.setItems(listWithDuplicateItems));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueItemList.asUnmodifiableObservableList().remove(0));
    }
}
