package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.DisplayFilterPredicate;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUniqueAliasMap;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UniqueAliasMap;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.CommandAlias;
import seedu.address.model.person.Person;
import seedu.address.testutil.CommandAliasBuilder;

public class AddAliasCommandTest {

    @Test
    public void constructor_nullCommandAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAliasCommand(null));
    }

    @Test
    public void execute_commandAliasAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCommandAliasAdded modelStub = new ModelStubAcceptingCommandAliasAdded();
        CommandAlias validCommandAlias = new CommandAliasBuilder().build();

        CommandResult commandResult = new AddAliasCommand(validCommandAlias).execute(modelStub);

        assertEquals(String.format(AddAliasCommand.MESSAGE_SUCCESS, validCommandAlias),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCommandAlias), modelStub.aliasesAdded);
    }

    @Test
    public void execute_duplicateCommandAlias_throwsCommandException() {
        CommandAlias validCommandAlias = new CommandAliasBuilder().build();
        AddAliasCommand addAliasCommand = new AddAliasCommand(validCommandAlias);
        ModelStub modelStub = new ModelStubWithCommandAlias(validCommandAlias);

        assertThrows(CommandException.class, AddAliasCommand.MESSAGE_DUPLICATE_ALIAS, () ->
                addAliasCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        CommandAlias add = new CommandAliasBuilder().withAlias("a").build();
        CommandAlias delete = new CommandAliasBuilder().withAlias("d").build();
        AddAliasCommand addAddCommand = new AddAliasCommand(add);
        AddAliasCommand addDeleteCommand = new AddAliasCommand(delete);

        // same object -> returns true
        assertTrue(addAddCommand.equals(addAddCommand));

        // same values -> returns true
        AddAliasCommand addAddCommandCopy = new AddAliasCommand(add);
        assertTrue(addAddCommand.equals(addAddCommandCopy));

        // different types -> returns false
        assertFalse(addAddCommand.equals(1));

        // null -> returns false
        assertFalse(addAddCommand.equals(null));

        // different person -> returns false
        assertFalse(addAddCommand.equals(addDeleteCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getSortedFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedFilteredPersonList(Comparator<Person> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUniqueAliasMap getAliases() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateDisplayFilter(DisplayFilterPredicate displayFilterPredicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAlias(CommandAlias commandAlias) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAlias(Alias alias) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAlias(Alias alias) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCommandAlias(CommandAlias commandAlias) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public CommandAlias getCommandAlias(Alias alias) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public DisplayFilterPredicate getDisplayFilter() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single commandAlias.
     */
    private class ModelStubWithCommandAlias extends ModelStub {
        private final CommandAlias commandAlias;

        ModelStubWithCommandAlias(CommandAlias commandAlias) {
            requireNonNull(commandAlias);
            this.commandAlias = commandAlias;
        }

        @Override
        public boolean hasCommandAlias(CommandAlias commandAlias) {
            requireNonNull(commandAlias);
            return this.commandAlias.isSameCommandAlias(commandAlias);
        }
    }

    /**
     * A Model stub that always accept the commandAlias being added.
     */
    private class ModelStubAcceptingCommandAliasAdded extends ModelStub {
        final ArrayList<CommandAlias> aliasesAdded = new ArrayList<>();

        @Override
        public boolean hasCommandAlias(CommandAlias commandAlias) {
            requireNonNull(commandAlias);
            return aliasesAdded.stream().anyMatch(commandAlias::isSameCommandAlias);
        }

        @Override
        public void addAlias(CommandAlias commandAlias) {
            requireNonNull(commandAlias);
            aliasesAdded.add(commandAlias);
        }

        @Override
        public ReadOnlyUniqueAliasMap getAliases() {
            return new UniqueAliasMap();
        }
    }

}
