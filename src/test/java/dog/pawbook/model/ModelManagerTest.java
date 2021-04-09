package dog.pawbook.model;

import static dog.pawbook.model.managedentity.IsEntityPredicate.IS_DOG_PREDICATE;
import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalEntities.ALICE;
import static dog.pawbook.testutil.TypicalEntities.APPLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.GuiSettings;
import dog.pawbook.model.managedentity.NameContainsKeywordsPredicate;
import dog.pawbook.testutil.DatabaseBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Database(), new Database(modelManager.getDatabase()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setDatabaseFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setDatabaseFilePath(Paths.get("new/address/book/file/path"));
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
    public void setDatabaseFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setDatabaseFilePath(null));
    }

    @Test
    public void setDatabaseFilePath_validPath_setsDatabaseFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setDatabaseFilePath(path);
        assertEquals(path, modelManager.getDatabaseFilePath());
    }

    @Test
    public void hasOwner_nullOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEntity(null));
    }

    @Test
    public void hasOwner_ownerNotInDatabase_returnsFalse() {
        assertFalse(modelManager.hasEntity(ALICE));
    }

    @Test
    public void hasOwner_ownerInDatabase_returnsTrue() {
        modelManager.addEntity(ALICE);
        assertTrue(modelManager.hasEntity(ALICE));
    }

    @Test
    public void getFilteredOwnerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredEntityList().remove(0));
    }

    @Test
    public void equals() {
        Database database = new DatabaseBuilder().withEntity(ALICE).withEntity(APPLE).build();
        Database differentAddressBook = new Database();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(database, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(database, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different database -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredEntityList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(database, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredEntityList(IS_DOG_PREDICATE);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setDatabaseFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(database, differentUserPrefs)));
    }
}
