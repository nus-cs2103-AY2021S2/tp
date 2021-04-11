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
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.customer.CustomerAddCommand;
import seedu.address.logic.commands.customer.CustomerCommandUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.dish.Dish;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonBook;
import seedu.address.testutil.PersonBuilder;

public class CustomerAddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CustomerAddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new CustomerAddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(CustomerAddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        CustomerAddCommand addCommand = new CustomerAddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class,
                CustomerCommandUtil.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        CustomerAddCommand addAliceCommand = new CustomerAddCommand(alice);
        CustomerAddCommand addBobCommand = new CustomerAddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        CustomerAddCommand addAliceCommandCopy = new CustomerAddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
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
        public Path getPersonBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPersonBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPersonBook(ReadOnlyBook<Person> newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyBook<Person> getPersonBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getPersonByIndex(int i) {
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
        public void updateFilteredPersonList(Predicate<? super Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDishBook(ReadOnlyBook<Dish> dishBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyBook<Dish> getDishBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDish(Dish dish) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Dish getDishByIndex(int i) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDish(Dish dish) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDish(Dish dish) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDish(Dish target, Dish editedDish) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Dish> getFilteredDishList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredDishList(Predicate<? super Dish> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Dish> getDishesByIngredients(Ingredient ingredient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<Dish> getAvailableDishPredicate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSufficientIngredients(Dish target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSufficientIngredients(Dish target, int quantity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setIngredientBook(ReadOnlyBook<Ingredient> ingredientBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyBook<Ingredient> getIngredientBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasIngredient(Ingredient ingredient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Ingredient getIngredientByIndex(int i) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteIngredient(Ingredient ingredient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addIngredient(Ingredient ingredient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setIngredient(Ingredient target, Ingredient editedIngredient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void decreaseIngredient(Ingredient target, int quantity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void decreaseIngredientByOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void increaseIngredientByName(String name, int quantity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void increaseIngredientByOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Ingredient> getFilteredIngredientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredIngredientList(Predicate<? super Ingredient> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setOrderBook(ReadOnlyBook<Order> orderBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyBook<Order> getOrderBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteOrders(List<Order> orders) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setOrder(Order target, Order editedOrder) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns an unmodifiable view of the filtered order list
         */
        @Override
        public ObservableList<Order> getFilteredOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Order> getIncompleteOrdersContainingDish(Dish target) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns a list of orders that contain a given dish
         *
         * @param target
         */
        @Override
        public List<Order> getOrdersContainingDish(Dish target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Order> getIncompleteOrders() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredOrderList(Predicate<? super Order> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Updates the filtered ingredient list by sorting it with {@code comparator}.
         *
         * @param comparator
         * @throws NullPointerException if {@code predicate} is null.
         */
        @Override
        public void updateFilteredOrderList(Comparator<Order> comparator) {

        }

        @Override
        public List<Order> getOrdersFromPerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Order> getIncompleteOrdersFromPerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void cancelOrder(Order target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void cancelOrders(List<Order> targets) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canFulfilOrder(Order target) {
            return false;
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyBook<Person> getPersonBook() {
            return new PersonBook();
        }
    }

}
