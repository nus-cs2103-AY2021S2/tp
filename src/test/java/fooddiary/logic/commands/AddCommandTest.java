package fooddiary.logic.commands;

import fooddiary.commons.core.GuiSettings;
import fooddiary.logic.commands.exceptions.CommandException;
import fooddiary.model.FoodDiary;
import fooddiary.model.Model;
import fooddiary.model.ReadOnlyFoodDiary;
import fooddiary.model.ReadOnlyUserPrefs;
import fooddiary.model.entry.Entry;
import fooddiary.testutil.EntryBuilder;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import static fooddiary.testutil.Assert.assertThrows;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

public class AddCommandTest {

    @Test
    public void constructor_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_entryAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEntryAdded modelStub = new ModelStubAcceptingEntryAdded();
        Entry validEntry = new EntryBuilder().build();

        CommandResult commandResult = new AddCommand(validEntry).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validEntry), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEntry), modelStub.entriesAdded);
    }

    @Test
    public void execute_duplicateEntry_throwsCommandException() {
        Entry validEntry = new EntryBuilder().build();
        AddCommand addCommand = new AddCommand(validEntry);
        ModelStub modelStub = new ModelStubWithEntry(validEntry);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_ENTRY, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Entry alice = new EntryBuilder().withName("Alice").build();
        Entry bob = new EntryBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different entry -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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
        public Path getFoodDiaryFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFoodDiaryFilePath(Path foodDiaryFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEntry(Entry entry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFoodDiary(ReadOnlyFoodDiary newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFoodDiary getFoodDiary() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEntry(Entry entry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEntry(Entry target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEntry(Entry target, Entry editedEntry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Entry> getFilteredEntryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEntryList(Predicate<Entry> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single entry.
     */
    private class ModelStubWithEntry extends ModelStub {
        private final Entry entry;

        ModelStubWithEntry(Entry entry) {
            requireNonNull(entry);
            this.entry = entry;
        }

        @Override
        public boolean hasEntry(Entry entry) {
            requireNonNull(entry);
            return this.entry.isSameEntry(entry);
        }
    }

    /**
     * A Model stub that always accept the entry being added.
     */
    private class ModelStubAcceptingEntryAdded extends ModelStub {
        final ArrayList<Entry> entriesAdded = new ArrayList<>();

        @Override
        public boolean hasEntry(Entry entry) {
            requireNonNull(entry);
            return entriesAdded.stream().anyMatch(entry::isSameEntry);
        }

        @Override
        public void addEntry(Entry entry) {
            requireNonNull(entry);
            entriesAdded.add(entry);
        }

        @Override
        public ReadOnlyFoodDiary getFoodDiary() {
            return new FoodDiary();
        }
    }

}
