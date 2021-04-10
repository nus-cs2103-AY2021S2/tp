package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCommandAliases.ADD_ALIAS;
import static seedu.address.testutil.TypicalCommandAliases.ADD_ALIAS_STRING;
import static seedu.address.testutil.TypicalCommandAliases.ADD_COMMAND;
import static seedu.address.testutil.TypicalCommandAliases.ADD_COMMAND_ALIAS;
import static seedu.address.testutil.TypicalCommandAliases.DELETE_ALIAS;
import static seedu.address.testutil.TypicalCommandAliases.INVALID_ALIAS_STRING;
import static seedu.address.testutil.TypicalCommandAliases.getTypicalAliasMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.CommandAlias;
import seedu.address.model.alias.exceptions.AliasNotFoundException;
import seedu.address.model.alias.exceptions.DuplicateAliasException;

public class UniqueAliasMapTest {

    private final UniqueAliasMap aliasMap = new UniqueAliasMap();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyMap(), aliasMap.getCommandAliases());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> aliasMap.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyUniqueAliasMap_replacesData() {
        UniqueAliasMap newData = getTypicalAliasMap();
        aliasMap.resetData(newData);
        assertEquals(newData, aliasMap);
    }

    @Test
    public void resetData_withDuplicateCommandAliases_throwsDuplicateAliasException() {
        // Two command aliases with the same identity fields
        Map<Alias, CommandAlias> newAliasMap = new HashMap<>();
        newAliasMap.put(ADD_ALIAS, ADD_COMMAND_ALIAS);
        newAliasMap.put(DELETE_ALIAS, ADD_COMMAND_ALIAS);
        UniqueAliasMapStub newData = new UniqueAliasMapStub(newAliasMap);

        assertThrows(DuplicateAliasException.class, () -> aliasMap.resetData(newData));
    }

    @Test
    public void hasAlias_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> aliasMap.hasAlias(null));
    }

    @Test
    public void hasCommandAlias_nullCommandAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> aliasMap.hasCommandAlias(null));
    }

    @Test
    public void hasAlias_aliasNotInAliasMap_returnsFalse() {
        assertFalse(aliasMap.hasAlias(ADD_ALIAS));
    }

    @Test
    public void hasCommandAlias_commandAliasNotInAliasMap_returnsFalse() {
        assertFalse(aliasMap.hasCommandAlias(ADD_COMMAND_ALIAS));
    }

    @Test
    public void hasAlias_aliasInAliasMap_returnsTrue() {
        aliasMap.addAlias(ADD_COMMAND_ALIAS);
        assertTrue(aliasMap.hasAlias(ADD_ALIAS));
    }

    @Test
    public void hasCommandAlias_commandAliasInAliasMap_returnsTrue() {
        aliasMap.addAlias(ADD_COMMAND_ALIAS);
        assertTrue(aliasMap.hasCommandAlias(ADD_COMMAND_ALIAS));
    }

    @Test
    public void getCommandAliases_modifyAlias_throwsUnsupportedOperationException() {
        aliasMap.addAlias(ADD_COMMAND_ALIAS);
        assertThrows(UnsupportedOperationException.class, () -> aliasMap.getCommandAliases().remove(ADD_ALIAS));
        assertThrows(UnsupportedOperationException.class, () ->
                aliasMap.getCommandAliases().put(ADD_ALIAS, ADD_COMMAND_ALIAS));
    }

    @Test
    public void addAlias_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> aliasMap.addAlias(null));
    }

    @Test
    public void addAlias_duplicatePerson_throwsDuplicateAliasException() {
        aliasMap.addAlias(ADD_COMMAND_ALIAS);
        assertThrows(DuplicateAliasException.class, () -> aliasMap.addAlias(ADD_COMMAND_ALIAS));
    }

    @Test
    public void removeAlias_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> aliasMap.removeAlias(null));
    }

    @Test
    public void removeAlias_aliasDoesNotExist_throwsAliasNotFoundException() {
        assertThrows(AliasNotFoundException.class, () -> aliasMap.removeAlias(ADD_ALIAS));
    }

    @Test
    public void removeAlias_existingAlias_removesAlias() {
        aliasMap.addAlias(ADD_COMMAND_ALIAS);
        aliasMap.removeAlias(ADD_ALIAS);
        UniqueAliasMap expectedAliasMap = new UniqueAliasMap();
        assertEquals(expectedAliasMap, aliasMap);
    }

    @Test
    public void getCommandAlias_existingAlias_returnsAlias() {
        aliasMap.addAlias(ADD_COMMAND_ALIAS);
        assertEquals(ADD_COMMAND_ALIAS, aliasMap.getCommandAlias(ADD_ALIAS));
    }

    @Test
    public void getCommandAlias_aliasDoesNotExist_returnsNull() {
        assertNull(aliasMap.getCommandAlias(ADD_ALIAS));
    }

    @Test
    public void getCommand_existingAlias_returnsAlias() {
        aliasMap.addAlias(ADD_COMMAND_ALIAS);
        assertEquals(ADD_COMMAND, aliasMap.getCommand(ADD_ALIAS));
    }

    @Test
    public void getCommand_aliasDoesNotExist_throwsNullPointerException() {
        assertNull(aliasMap.getCommand(ADD_ALIAS));
    }

    @Test
    public void parseAliasToCommand_existingAliasDoesNotExist_returnsCommandAliasInString() {
        aliasMap.addAlias(ADD_COMMAND_ALIAS);
        assertEquals(AddCommand.COMMAND_WORD, aliasMap.parseAliasToCommand(ADD_ALIAS_STRING));
    }

    @Test
    public void parseAliasToCommand_aliasDoesNotExist_returnsUserInput() {
        assertEquals(INVALID_ALIAS_STRING, aliasMap.parseAliasToCommand(INVALID_ALIAS_STRING));
    }

    @Test
    public void getNumOfAlias_noAlias_returnsZero() {
        UniqueAliasMap emptyMap = new UniqueAliasMap();
        assertEquals(emptyMap.getCommandAliases().size(), aliasMap.getNumOfAlias());
    }

    @Test
    public void getNumOfAlias_oneAlias_returnsOne() {
        aliasMap.addAlias(ADD_COMMAND_ALIAS);
        UniqueAliasMap testMap = new UniqueAliasMap();
        testMap.addAlias(ADD_COMMAND_ALIAS);
        assertEquals(testMap.getCommandAliases().size(), aliasMap.getNumOfAlias());
    }

    @Test
    public void getCommandAliasesStringList_modifyStringAlias_throwsUnsupportedOperationException() {
        aliasMap.addAlias(ADD_COMMAND_ALIAS);
        assertThrows(UnsupportedOperationException.class, () -> aliasMap.getCommandAliasesStringList().remove(0));
        assertThrows(UnsupportedOperationException.class, () -> aliasMap.getCommandAliasesStringList().add("error"));
    }

    @Test
    public void equals() {
        // same empty alias map
        assertEquals(new UniqueAliasMap(), aliasMap);

        // same alias map
        aliasMap.resetData(getTypicalAliasMap());
        assertEquals(getTypicalAliasMap(), aliasMap);

        // different alias map
        assertNotEquals(new UniqueAliasMap(), aliasMap);
    }

    /**
     * A stub ReadOnlyUniqueAliasMap whose alias map can violate interface constraints.
     */
    private static class UniqueAliasMapStub implements ReadOnlyUniqueAliasMap {
        private final ObservableMap<Alias, CommandAlias> internalList = FXCollections.observableMap(new HashMap<>());
        private final ObservableMap<Alias, CommandAlias> internalUnmodifiableList =
                FXCollections.unmodifiableObservableMap(internalList);
        private final ObservableList<String> internalStringList = FXCollections.emptyObservableList();
        private final ObservableList<String> internalUnmodifiableStringList =
                FXCollections.unmodifiableObservableList(internalStringList);

        UniqueAliasMapStub(Map<Alias, CommandAlias> aliasMap) {
            internalList.putAll(aliasMap);
        }

        @Override
        public ObservableMap<Alias, CommandAlias> getCommandAliases() {
            return internalUnmodifiableList;
        }

        @Override
        public String parseAliasToCommand(String userInput) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getNumOfAlias() {
            return internalList.size();
        }

        @Override
        public ObservableList<String> getCommandAliasesStringList() {
            return internalUnmodifiableStringList;
        }
    }

}
