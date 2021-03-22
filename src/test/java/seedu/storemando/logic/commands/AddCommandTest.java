package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.model.expirydate.ExpiryDate.NO_EXPIRY_DATE;
import static seedu.storemando.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.storemando.commons.core.GuiSettings;
import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.model.Model;
import seedu.storemando.model.ReadOnlyStoreMando;
import seedu.storemando.model.ReadOnlyUserPrefs;
import seedu.storemando.model.StoreMando;
import seedu.storemando.model.item.Item;
import seedu.storemando.testutil.ItemBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_itemAcceptedByModel_addSuccessful() throws CommandException {
        ModelStubAcceptingItemAdded modelStub = new ModelStubAcceptingItemAdded();
        Item validItem = new ItemBuilder().build();

        CommandResult commandResult = new AddCommand(validItem).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validItem), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validItem), modelStub.itemsAdded);
    }

    @Test
    public void execute_itemAcceptedByModel_addExpiredSuccessful() throws CommandException {
        ModelStubAcceptingItemAdded modelStub = new ModelStubAcceptingItemAdded();
        Item validItem = new ItemBuilder().withExpiryDate("2010-10-10").build();

        CommandResult commandResult = new AddCommand(validItem).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS + AddCommand.MESSAGE_ITEM_EXPIRED_WARNING, validItem),
            commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validItem), modelStub.itemsAdded);
    }


    @Test
    public void execute_duplicateItem_throwsCommandException() {
        Item validItem = new ItemBuilder().build();
        AddCommand addCommand = new AddCommand(validItem);
        ModelStub modelStub = new ModelStubWithItem(validItem);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_ITEM, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_itemWithoutExpiryDate_success() throws CommandException {
        ModelStubAcceptingItemAdded modelStub = new ModelStubAcceptingItemAdded();
        Item validItem = new ItemBuilder().withExpiryDate(NO_EXPIRY_DATE).build();

        CommandResult commandResult = new AddCommand(validItem).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validItem), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validItem), modelStub.itemsAdded);
    }


    @Test
    public void equals() {
        Item alice = new ItemBuilder().withName("Alice").build();
        Item bob = new ItemBuilder().withName("Bob").build();
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

        // different item -> returns false
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
        public Path getStoreMandoFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStoreMandoFilePath(Path storeMandoFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addItem(Item item) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStoreMando(ReadOnlyStoreMando newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyStoreMando getStoreMando() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasItem(Item item) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSimilarItem(Item item) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteItem(Item target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItem(Item target, Item editedItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItems(List<Item> itemList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Item> getFilteredItemList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredItemList(Predicate<Item> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Item> getSortedItemList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedItemList(Comparator<Item> comparator) {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that contains a single item.
     */
    private class ModelStubWithItem extends ModelStub {
        private final Item item;

        ModelStubWithItem(Item item) {
            requireNonNull(item);
            this.item = item;
        }

        @Override
        public boolean hasItem(Item item) {
            requireNonNull(item);
            return this.item.isSameItem(item);
        }

        @Override
        public boolean hasSimilarItem(Item item) {
            requireNonNull(item);
            return this.item.isSimilarItem(item);
        }
    }

    /**
     * A Model stub that always accept the item being added.
     */
    private class ModelStubAcceptingItemAdded extends ModelStub {
        final ArrayList<Item> itemsAdded = new ArrayList<>();

        @Override
        public boolean hasItem(Item item) {
            requireNonNull(item);
            return itemsAdded.stream().anyMatch(item::isSameItem);
        }

        @Override
        public boolean hasSimilarItem(Item item) {
            requireNonNull(item);
            return itemsAdded.stream().anyMatch(item::isSimilarItem);
        }

        @Override
        public void addItem(Item item) {
            requireNonNull(item);
            itemsAdded.add(item);
        }

        @Override
        public ReadOnlyStoreMando getStoreMando() {
            return new StoreMando();
        }
    }

}
