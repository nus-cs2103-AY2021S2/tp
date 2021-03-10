package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.model.DisplayFilterPredicate;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;

class FilterCommandTest {

    @Test
    public void execute_modelUpdated_success() throws Exception {
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(PREFIX_ADDRESS, "");

        DisplayFilterPredicate displayFilterPredicate = new DisplayFilterPredicate(
                argumentMultimap);

        FilterCommand filterCommand = new FilterCommand(displayFilterPredicate);
        ModelStub modelStub = new ModelStubWithFilter();

        // before executing
        assertNotEquals(modelStub.getDisplayFilter(), displayFilterPredicate);

        filterCommand.execute(modelStub);

        // after executing
        assertEquals(modelStub.getDisplayFilter(), displayFilterPredicate);
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
        public void updateDisplayFilter(DisplayFilterPredicate displayFilterPredicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public DisplayFilterPredicate getDisplayFilter() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that implements {@code DisplayFilterPredicate}.
     */
    private class ModelStubWithFilter extends FilterCommandTest.ModelStub {

        private DisplayFilterPredicate displayFilterPredicate;

        public ModelStubWithFilter() {
            displayFilterPredicate = new DisplayFilterPredicate();
        }

        public ModelStubWithFilter(DisplayFilterPredicate displayFilterPredicate) {
            this.displayFilterPredicate = displayFilterPredicate;
        }

        @Override
        public void updateDisplayFilter(DisplayFilterPredicate displayFilterPredicate) {
            this.displayFilterPredicate = displayFilterPredicate;
        }

        @Override
        public DisplayFilterPredicate getDisplayFilter() {
            return displayFilterPredicate;
        }
    }

}
