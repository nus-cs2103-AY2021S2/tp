package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAliases.ADD_ALIAS;
import static seedu.address.testutil.TypicalAliases.ADD_ALIAS_STRING;
import static seedu.address.testutil.TypicalAliases.ADD_COMMAND;
import static seedu.address.testutil.TypicalAliases.ADD_COMMAND_ALIAS;
import static seedu.address.testutil.TypicalAliases.DELETE_ALIAS;
import static seedu.address.testutil.TypicalAliases.INVALID_ALIAS_STRING;
import static seedu.address.testutil.TypicalAliases.getTypicalAliases;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.CommandAlias;
import seedu.address.model.alias.exceptions.AliasNotFoundException;
import seedu.address.model.alias.exceptions.DuplicateAliasException;

public class UniqueAliasMapTest {

    private final UniqueAliasMap aliases = new UniqueAliasMap();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyMap(), aliases.getAliases());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> aliases.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyUniqueAliasMap_replacesData() {
        UniqueAliasMap newData = getTypicalAliases();
        aliases.resetData(newData);
        assertEquals(newData, aliases);
    }

    @Test
    public void resetData_withDuplicateAliases_throwsDuplicateAliasException() {
        // Two aliases with the same identity fields
        Map<Alias, CommandAlias> newAliases = new HashMap<>();
        newAliases.put(ADD_ALIAS, ADD_COMMAND_ALIAS);
        newAliases.put(DELETE_ALIAS, ADD_COMMAND_ALIAS);
        UniqueAliasMapStub newData = new UniqueAliasMapStub(newAliases);

        assertThrows(DuplicateAliasException.class, () -> aliases.resetData(newData));
    }

    @Test
    public void hasAlias_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> aliases.hasAlias(null));
    }

    @Test
    public void hasCommandAlias_nullCommandAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> aliases.hasCommandAlias(null));
    }

    @Test
    public void hasAlias_aliasNotInAliases_returnsFalse() {
        assertFalse(aliases.hasAlias(ADD_ALIAS));
    }

    @Test
    public void hasCommandAlias_commandAliasNotInAliases_returnsFalse() {
        assertFalse(aliases.hasCommandAlias(ADD_COMMAND_ALIAS));
    }

    @Test
    public void hasAlias_aliasInAliases_returnsTrue() {
        aliases.addAlias(ADD_COMMAND_ALIAS);
        assertTrue(aliases.hasAlias(ADD_ALIAS));
    }

    @Test
    public void hasCommandAlias_commandAliasInAliases_returnsTrue() {
        aliases.addAlias(ADD_COMMAND_ALIAS);
        assertTrue(aliases.hasCommandAlias(ADD_COMMAND_ALIAS));
    }

    @Test
    public void getAliases_modifyAlias_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> aliases.getAliases().remove(ADD_ALIAS));
    }

    @Test
    public void addAlias_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> aliases.addAlias(null));
    }

    @Test
    public void addAlias_duplicatePerson_throwsDuplicateAliasException() {
        aliases.addAlias(ADD_COMMAND_ALIAS);
        assertThrows(DuplicateAliasException.class, () -> aliases.addAlias(ADD_COMMAND_ALIAS));
    }

    @Test
    public void removeAlias_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> aliases.removeAlias(null));
    }

    @Test
    public void removeAlias_aliasDoesNotExist_throwsAliasNotFoundException() {
        assertThrows(AliasNotFoundException.class, () -> aliases.removeAlias(ADD_ALIAS));
    }

    @Test
    public void removeAlias_existingAlias_removesAlias() {
        aliases.addAlias(ADD_COMMAND_ALIAS);
        aliases.removeAlias(ADD_ALIAS);
        UniqueAliasMap expectedAliases = new UniqueAliasMap();
        assertEquals(expectedAliases, aliases);
    }

    @Test
    public void getCommandAlias_existingAlias_returnsAlias() {
        aliases.addAlias(ADD_COMMAND_ALIAS);
        assertEquals(ADD_COMMAND_ALIAS, aliases.getCommandAlias(ADD_ALIAS));
    }

    @Test
    public void getCommandAlias_aliasDoesNotExist_returnsNull() {
        assertNull(aliases.getCommandAlias(ADD_ALIAS));
    }

    @Test
    public void getCommand_existingAlias_returnsAlias() {
        aliases.addAlias(ADD_COMMAND_ALIAS);
        assertEquals(ADD_COMMAND, aliases.getCommand(ADD_ALIAS));
    }

    @Test
    public void getCommand_aliasDoesNotExist_throwsNullPointerException() {
        assertNull(aliases.getCommand(ADD_ALIAS));
    }

    @Test
    public void parseAliasToCommand_existingAliasDoesNotExist_returnsCommandAliasInString() {
        aliases.addAlias(ADD_COMMAND_ALIAS);
        assertEquals(AddCommand.COMMAND_WORD, aliases.parseAliasToCommand(ADD_ALIAS_STRING));
    }

    @Test
    public void parseAliasToCommand_aliasDoesNotExist_returnsUserInput() {
        assertEquals(INVALID_ALIAS_STRING, aliases.parseAliasToCommand(INVALID_ALIAS_STRING));
    }

    @Test
    public void equals() {
        // same empty aliases
        assertEquals(new UniqueAliasMap(), aliases);

        // same aliases
        aliases.resetData(getTypicalAliases());
        assertEquals(getTypicalAliases(), aliases);

        // different aliases
        assertNotEquals(new UniqueAliasMap(), aliases);
    }

    /**
     * A stub ReadOnlyUniqueAliasMap whose aliases can violate interface constraints.
     */
    private static class UniqueAliasMapStub implements ReadOnlyUniqueAliasMap {
        private final ObservableMap<Alias, CommandAlias> internalList = FXCollections.observableMap(new HashMap<>());
        private final ObservableMap<Alias, CommandAlias> internalUnmodifiableList =
                FXCollections.unmodifiableObservableMap(internalList);

        UniqueAliasMapStub(Map<Alias, CommandAlias> aliases) {
            internalList.putAll(aliases);
        }

        @Override
        public ObservableMap<Alias, CommandAlias> getAliases() {
            return internalUnmodifiableList;
        }

        @Override
        public String parseAliasToCommand(String userInput) {
            throw new AssertionError("This method should not be called.");
        }
    }

}
