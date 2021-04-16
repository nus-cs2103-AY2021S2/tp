package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCommandAliases.ADD_ALIAS;
import static seedu.address.testutil.TypicalCommandAliases.ADD_COMMAND_ALIAS;
import static seedu.address.testutil.TypicalCommandAliases.DELETE_COMMAND_ALIAS;
import static seedu.address.testutil.TypicalCommandAliases.EDIT_COMMAND_ALIAS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.UniqueAliasMapBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
        assertEquals(new UniqueAliasMap(), new UniqueAliasMap(modelManager.getAliasMap()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void hasAlias_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasAlias(null));
        assertThrows(NullPointerException.class, () -> modelManager.hasCommandAlias(null));
    }

    @Test
    public void hasAlias_aliasNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasAlias(ADD_ALIAS));
        assertFalse(modelManager.hasCommandAlias(ADD_COMMAND_ALIAS));
    }

    @Test
    public void hasAlias_aliasInAddressBook_returnsTrue() {
        modelManager.addAlias(ADD_COMMAND_ALIAS);
        assertTrue(modelManager.hasAlias(ADD_ALIAS));
        assertTrue(modelManager.hasCommandAlias(ADD_COMMAND_ALIAS));
    }

    @Test
    public void getCommandAlias_aliasInAddressBook_returnsCommandAlias() {
        modelManager.addAlias(ADD_COMMAND_ALIAS);
        assertEquals(modelManager.getCommandAlias(ADD_ALIAS), ADD_COMMAND_ALIAS);
    }

    @Test
    public void getCommandAlias_aliasNotInAddressBook_returnsNull() {
        assertEquals(modelManager.getCommandAlias(ADD_ALIAS), null);
    }

    @Test
    public void getCommandAliasesStringList_withCommandAliases_returnsSameCommandAliases() {
        modelManager.addAlias(ADD_COMMAND_ALIAS);
        modelManager.addAlias(DELETE_COMMAND_ALIAS);
        modelManager.addAlias(EDIT_COMMAND_ALIAS);
        assertEquals(modelManager.getCommandAliasesStringList().get(0), ADD_COMMAND_ALIAS.toString());
        assertEquals(modelManager.getCommandAliasesStringList().get(1), DELETE_COMMAND_ALIAS.toString());
        assertEquals(modelManager.getCommandAliasesStringList().get(2), EDIT_COMMAND_ALIAS.toString());
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();
        UniqueAliasMap aliasMap = new UniqueAliasMapBuilder().withCommandAlias(ADD_COMMAND_ALIAS)
                .withCommandAlias(DELETE_COMMAND_ALIAS).build();
        UniqueAliasMap differentAliasMap = new UniqueAliasMap();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs, aliasMap);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs, aliasMap);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // different selectedPersonList -> return false
        modelManagerCopy.updateSelectedPersonList(Collections.singletonList(
                modelManagerCopy.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased())));
        modelManager.updateSelectedPersonList(modelManager.getFilteredPersonList());
        assertFalse(modelManager.equals(modelManagerCopy));
        assertFalse(modelManagerCopy.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs, aliasMap)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs, aliasMap)));

        // different alias map -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs, differentAliasMap)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs, aliasMap)));
    }
}
