package seedu.storemando.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_QUANTITY_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.storemando.testutil.Assert.assertThrows;
import static seedu.storemando.testutil.TypicalItems.APPLE;
import static seedu.storemando.testutil.TypicalItems.getTypicalStoreMando;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.exceptions.DuplicateItemException;
import seedu.storemando.testutil.ItemBuilder;

public class StoreMandoTest {

    private final StoreMando storeMando = new StoreMando();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), storeMando.getItemList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> storeMando.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyStoreMando_replacesData() {
        StoreMando newData = getTypicalStoreMando();
        storeMando.resetData(newData);
        assertEquals(newData, storeMando);
    }

    @Test
    public void resetData_withDuplicateItems_throwsDuplicateItemException() {
        // Two items with the same identity fields
        Item editedApple = new ItemBuilder(APPLE).withQuantity(VALID_QUANTITY_BANANA).withTags(VALID_TAG_HUSBAND)
            .build();
        List<Item> newItems = Arrays.asList(APPLE, editedApple);
        StoreMandoStub newData = new StoreMandoStub(newItems);

        assertThrows(DuplicateItemException.class, () -> storeMando.resetData(newData));
    }

    @Test
    public void hasItem_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> storeMando.hasItem(null));
    }

    @Test
    public void hasItem_itemNotInStoreMando_returnsFalse() {
        assertFalse(storeMando.hasItem(APPLE));
    }

    @Test
    public void hasItem_itemInStoreMando_returnsTrue() {
        storeMando.addItem(APPLE);
        assertTrue(storeMando.hasItem(APPLE));
    }

    @Test
    public void hasItem_itemWithSameIdentityFieldsInStoreMando_returnsTrue() {
        storeMando.addItem(APPLE);
        Item editedApple = new ItemBuilder(APPLE).withQuantity(VALID_QUANTITY_BANANA).withTags(VALID_TAG_HUSBAND)
            .build();
        assertTrue(storeMando.hasItem(editedApple));
    }

    @Test
    public void hasSimilarItem_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> storeMando.hasSimilarItem(null));
    }

    @Test
    public void hasSimilarItem_itemNotInStoreMando_returnsFalse() {
        assertFalse(storeMando.hasSimilarItem(APPLE));
    }

    @Test
    public void hasSimilarItem_itemInStoreMando_returnsTrue() {
        storeMando.addItem(APPLE);
        assertTrue(storeMando.hasSimilarItem(APPLE));
        Item editedApple = new ItemBuilder(APPLE).withQuantity(VALID_QUANTITY_BANANA).withTags(VALID_TAG_HUSBAND)
            .build();
        assertTrue(storeMando.hasSimilarItem(editedApple));
        editedApple = new ItemBuilder(APPLE).withName("apples").withQuantity(VALID_QUANTITY_BANANA).withTags(VALID_TAG_HUSBAND)
            .build();
        assertTrue(storeMando.hasSimilarItem(editedApple));
        editedApple = new ItemBuilder(APPLE).withName("APPLES").withLocation("kitchen BASKET").withQuantity(VALID_QUANTITY_BANANA).withTags(VALID_TAG_HUSBAND)
            .build();
        assertTrue(storeMando.hasSimilarItem(editedApple));
    }

    @Test
    public void getItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> storeMando.getItemList().remove(0));
    }

    /**
     * A stub ReadOnlyStoreMando whose items list can violate interface constraints.
     */
    private static class StoreMandoStub implements ReadOnlyStoreMando {
        private final ObservableList<Item> items = FXCollections.observableArrayList();

        StoreMandoStub(Collection<Item> items) {
            this.items.setAll(items);
        }

        @Override
        public ObservableList<Item> getItemList() {
            return items;
        }
    }

}
