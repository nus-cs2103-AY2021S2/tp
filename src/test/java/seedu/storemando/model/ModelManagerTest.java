package seedu.storemando.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.model.Model.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.storemando.testutil.Assert.assertThrows;
import static seedu.storemando.testutil.TypicalItems.APPLE;
import static seedu.storemando.testutil.TypicalItems.BANANA;
import static seedu.storemando.testutil.TypicalItems.BREAD;
import static seedu.storemando.testutil.TypicalItems.FAKEAPPLE;
import static seedu.storemando.testutil.TypicalItems.FAKEBREAD;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.storemando.commons.core.GuiSettings;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.ItemName;
import seedu.storemando.model.item.ItemNameContainsKeywordsPredicate;
import seedu.storemando.model.item.Location;
import seedu.storemando.testutil.ItemBuilder;
import seedu.storemando.testutil.StoreMandoBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new StoreMando(), new StoreMando(modelManager.getStoreMando()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setStoreMandoFilePath(Paths.get("storemando/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setStoreMandoFilePath(Paths.get("new/storemando/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setStoreMandoFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setStoreMandoFilePath(null));
    }

    @Test
    public void setStoreMandoFilePath_validPath_setsStoreMandoFilePath() {
        Path path = Paths.get("storemando/book/file/path");
        modelManager.setStoreMandoFilePath(path);
        assertEquals(path, modelManager.getStoreMandoFilePath());
    }

    @Test
    public void hasItem_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasItem(null));
    }

    @Test
    public void hasItem_itemNotInStoreMando_returnsFalse() {
        assertFalse(modelManager.hasItem(APPLE));
    }

    @Test
    public void hasItem_itemInStoreMando_returnsTrue() {
        modelManager.addItem(APPLE);
        assertTrue(modelManager.hasItem(APPLE));
    }

    @Test
    public void hasSimilarItem_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasSimilarItem(null));
    }

    @Test
    public void hasSimilarItem_itemNotInStoreMando_returnsFalse() {
        assertFalse(modelManager.hasSimilarItem(APPLE));
    }

    @Test
    public void hasSimilarItem_itemInStoreMando_returnsTrue() {
        modelManager.addItem(APPLE);
        modelManager.addItem(BREAD);
        assertTrue(modelManager.hasSimilarItem(APPLE));
        assertTrue(modelManager.hasSimilarItem(BREAD));
        assertTrue(modelManager.hasSimilarItem(FAKEAPPLE));
        assertTrue(modelManager.hasSimilarItem(FAKEBREAD));
    }

    @Test
    public void getFilteredItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredItemList().remove(0));
    }

    @Test
    public void equals() {
        StoreMando storeMando = new StoreMandoBuilder().withItem(APPLE).withItem(BREAD).build();
        StoreMando differentStoreMando = new StoreMando();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(storeMando, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(storeMando, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different storeMando -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentStoreMando, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = APPLE.getItemName().fullName.split("\\s+");
        modelManager.updateFilteredItemList(new ItemNameContainsKeywordsPredicate(Arrays.asList(keywords)));

        assertFalse(modelManager.equals(new ModelManager(storeMando, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setStoreMandoFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(storeMando, differentUserPrefs)));
    }
}
