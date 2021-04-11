package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.ReadOnlyBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonBook;
import seedu.address.storage.dish.JsonDishBookStorage;
import seedu.address.storage.ingredient.JsonIngredientBookStorage;
import seedu.address.storage.order.JsonOrderBookStorage;
import seedu.address.storage.person.JsonPersonBookStorage;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonPersonBookStorage personBookStorage = new JsonPersonBookStorage(getTempFilePath("ab"));
        JsonDishBookStorage dishBookStorage =
                new JsonDishBookStorage(getTempFilePath("db"));
        JsonIngredientBookStorage ingredientBookStorage =
                new JsonIngredientBookStorage(getTempFilePath("ib"));
        JsonOrderBookStorage orderBookStorage =
                new JsonOrderBookStorage(getTempFilePath("ob"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(personBookStorage, dishBookStorage,
                ingredientBookStorage, orderBookStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void addressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        PersonBook original = getTypicalAddressBook();
        storageManager.savePersonBook(original);
        ReadOnlyBook<Person> retrieved = storageManager.readPersonBook().get();
        assertEquals(original, new PersonBook(retrieved));
    }

    @Test
    public void getAddressBookFilePath() {
        assertNotNull(storageManager.getPersonBookFilePath());
    }

}
