package seedu.storemando.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_LOCATION_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_QUANTITY_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.storemando.testutil.Assert.assertThrows;
import static seedu.storemando.testutil.TypicalItems.APPLE;
import static seedu.storemando.testutil.TypicalItems.BANANA;

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
        assertFalse(uniqueItemList.contains(APPLE));
    }

    @Test
    public void contains_itemInList_returnsTrue() {
        uniqueItemList.add(APPLE);
        assertTrue(uniqueItemList.contains(APPLE));
    }

    @Test
    public void contains_itemWithSameIdentityFieldsInList_returnsTrue() {
        uniqueItemList.add(APPLE);
        Item editedApple = new ItemBuilder(APPLE).withQuantity(VALID_QUANTITY_BANANA).withTags(VALID_TAG_HUSBAND)
            .build();
        assertTrue(uniqueItemList.contains(editedApple));
    }

    @Test
    public void containsSimilar_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.containsSimilar(null));
    }

    @Test
    public void containsSimilar_itemNotInList_returnsFalse() {
        assertFalse(uniqueItemList.containsSimilar(APPLE));
    }

    @Test
    public void containsSimilar_itemInList_returnsTrue() {
        uniqueItemList.add(APPLE);
        assertTrue(uniqueItemList.containsSimilar(APPLE));
        Item editedApple = new ItemBuilder(APPLE).withQuantity(VALID_QUANTITY_BANANA).withTags(VALID_TAG_HUSBAND)
            .build();
        assertTrue(uniqueItemList.containsSimilar(editedApple));
        editedApple = new ItemBuilder(APPLE).withName("APPLES").withQuantity(VALID_QUANTITY_BANANA)
            .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(uniqueItemList.containsSimilar(editedApple));
        editedApple = new ItemBuilder(APPLE).withName("aPplEs").withQuantity(VALID_QUANTITY_BANANA)
            .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(uniqueItemList.containsSimilar(editedApple));
        editedApple = new ItemBuilder(APPLE).withLocation("kItChen BasKeT").withQuantity(VALID_QUANTITY_BANANA)
            .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(uniqueItemList.containsSimilar(editedApple));
        editedApple = new ItemBuilder(APPLE).withName("ApPlE").withLocation("kItChen BasKeT")
            .withQuantity(VALID_QUANTITY_BANANA).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(uniqueItemList.containsSimilar(editedApple));
    }


    @Test
    public void add_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.add(null));
    }

    @Test
    public void add_duplicateItem_throwsDuplicateItemException() {
        uniqueItemList.add(APPLE);
        assertThrows(DuplicateItemException.class, () -> uniqueItemList.add(APPLE));
    }

    @Test
    public void setItem_nullTargetItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItem(null, APPLE));
    }

    @Test
    public void setItem_nullEditedItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItem(APPLE, null));
    }

    @Test
    public void setItem_targetItemNotInList_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueItemList.setItem(APPLE, APPLE));
    }

    @Test
    public void setItem_editedItemIsSameItem_success() {
        uniqueItemList.add(APPLE);
        uniqueItemList.setItem(APPLE, APPLE);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(APPLE);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_editedItemHasSameIdentity_success() {
        uniqueItemList.add(APPLE);
        Item editedApple = new ItemBuilder(APPLE).withLocation(VALID_LOCATION_BANANA).withTags(VALID_TAG_HUSBAND)
            .build();
        uniqueItemList.setItem(APPLE, editedApple);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(editedApple);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_editedItemHasDifferentIdentity_success() {
        uniqueItemList.add(APPLE);
        uniqueItemList.setItem(APPLE, BANANA);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(BANANA);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_editedItemHasNonUniqueIdentity_throwsDuplicateItemException() {
        uniqueItemList.add(APPLE);
        uniqueItemList.add(BANANA);
        assertThrows(DuplicateItemException.class, () -> uniqueItemList.setItem(APPLE, BANANA));
    }

    @Test
    public void remove_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.remove(null));
    }

    @Test
    public void remove_itemDoesNotExist_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueItemList.remove(APPLE));
    }

    @Test
    public void remove_existingItem_removesItem() {
        uniqueItemList.add(APPLE);
        uniqueItemList.remove(APPLE);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItems_nullUniqueItemList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItems((UniqueItemList) null));
    }

    @Test
    public void setItems_uniqueItemList_replacesOwnListWithProvidedUniqueItemList() {
        uniqueItemList.add(APPLE);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(BANANA);
        uniqueItemList.setItems(expectedUniqueItemList);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItems_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItems((List<Item>) null));
    }

    @Test
    public void setItems_list_replacesOwnListWithProvidedList() {
        uniqueItemList.add(APPLE);
        List<Item> itemList = Collections.singletonList(BANANA);
        uniqueItemList.setItems(itemList);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(BANANA);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItems_listWithDuplicateItems_throwsDuplicateItemException() {
        List<Item> listWithDuplicateItems = Arrays.asList(APPLE, APPLE);
        assertThrows(DuplicateItemException.class, () -> uniqueItemList.setItems(listWithDuplicateItems));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueItemList.asUnmodifiableObservableList().remove(0));
    }
}
