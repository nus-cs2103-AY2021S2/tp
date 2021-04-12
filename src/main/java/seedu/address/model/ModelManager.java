package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Pair;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.DishBook;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientBook;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonBook;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final PersonBook personBook;
    private final DishBook dishBook;
    private final IngredientBook ingredientBook;
    private final OrderBook orderBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Dish> filteredDishes;
    private final FilteredList<Ingredient> filteredIngredients;
    private FilteredList<Order> filteredOrders;

    /**
     * Initializes a ModelManager with the given books and userPrefs.
     */
    public ModelManager(ReadOnlyBook<Person> personBook, ReadOnlyBook<Dish> dishBook,
                        ReadOnlyBook<Ingredient> ingredientBook, ReadOnlyBook<Order> orderBook,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(personBook, userPrefs);

        logger.fine("Initializing with address book: " + personBook + " and user prefs " + userPrefs);

        this.personBook = new PersonBook(personBook);
        this.dishBook = new DishBook(dishBook);
        this.ingredientBook = new IngredientBook(ingredientBook);
        this.orderBook = new OrderBook(orderBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.personBook.getItemList());
        filteredDishes = new FilteredList<>(this.dishBook.getItemList());
        filteredIngredients = new FilteredList<>(this.ingredientBook.getItemList());
        filteredOrders = new FilteredList<>(this.orderBook.getItemList());
    }

    /**
     * Initializes a ModelManager with the given person book and userPrefs. (Legacy used for unit tests)
     */
    public ModelManager(ReadOnlyBook<Person> personBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(personBook, userPrefs);

        logger.fine("Initializing with address book: " + personBook + " and user prefs " + userPrefs);

        this.personBook = new PersonBook(personBook);
        this.dishBook = null;
        this.ingredientBook = null;
        this.orderBook = new OrderBook();
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.personBook.getItemList());
        filteredDishes = null;
        filteredIngredients = null;
        filteredOrders = new FilteredList<>(this.orderBook.getItemList());
    }


    public ModelManager() {
        this(new PersonBook(), new DishBook(), new IngredientBook(), new OrderBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getPersonBookFilePath() {
        return userPrefs.getPersonBookFilePath();
    }

    @Override
    public void setPersonBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setPersonBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setPersonBook(ReadOnlyBook<Person> personBook) {
        this.personBook.resetData(personBook);
    }

    @Override
    public ReadOnlyBook<Person> getPersonBook() {
        return personBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return personBook.hasPerson(person);
    }

    @Override
    //@@author kangtinglee
    public Person getPersonByIndex(int i) {
        return getFilteredPersonList().get(i);
    }

    //@@author kangtinglee
    @Override
    public void deletePerson(Person target) {
        personBook.removePerson(target);
        List<Order> deletionList = getOrdersFromPerson(target);
        deleteOrders(deletionList);

        assert !personBook.hasPerson(target);

        for (Order o : deletionList) {
            assert !hasOrder(o);
            logger.fine(String.format("Order %s belonging to %s deleted by cascade", o, target));
        }
    }

    @Override
    public void addPerson(Person person) {
        personBook.addPerson(person);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        personBook.setPerson(target, editedPerson);
        List<Order> ordersFromTarget = getOrdersFromPerson(target);
        for (Order orderFromTarget : ordersFromTarget) {
            Order updatedOrder = orderFromTarget.updateCustomer(editedPerson);
            setOrder(orderFromTarget, updatedOrder);
        }
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public List<Order> getIncompleteOrders() {
        ObservableList<Order> orders = getOrderBook().getItemList();
        List<Order> incompleteOrders = new ArrayList<>();
        for (Order o : orders) {
            if (o.getState() == Order.State.UNCOMPLETED) {
                incompleteOrders.add(o);
            }
        }
        return incompleteOrders;
    }

    //@@ author kangtinglee
    @Override
    public List<Order> getIncompleteOrdersContainingDish(Dish target) {
        List<Order> incompleteOrders = getIncompleteOrders();
        List<Order> incompleteAndContainsDishOrders = new ArrayList<>();
        for (Order o : incompleteOrders) {
            if (o.contains(target)) {
                incompleteAndContainsDishOrders.add(o);
            }
        }
        return incompleteAndContainsDishOrders;
    }

    //@@ author kangtinglee
    @Override
    public List<Order> getOrdersContainingDish(Dish target) {
        List<Order> ordersContainingDish = new ArrayList<>();
        for (Order o : getOrderBook().getItemList()) {
            if (o.contains(target)) {
                ordersContainingDish.add(o);
            }
        }
        return ordersContainingDish;
    }

    @Override
    public void updateFilteredPersonList(Predicate<? super Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Dishes ================================================================================
    @Override
    public void setDishBook(ReadOnlyBook<Dish> dishBook) {
        this.dishBook.resetData(dishBook);
    }

    @Override
    public ReadOnlyBook<Dish> getDishBook() {
        return dishBook;
    }

    @Override
    public boolean hasDish(Dish dish) {
        requireNonNull(dish);
        return dishBook.hasDish(dish);
    }

    @Override
    public Dish getDishByIndex(int i) {
        return getFilteredDishList().get(i);
    }

    @Override
    public void deleteDish(Dish target) {
        dishBook.removeDish(target);
    }

    @Override
    public void addDish(Dish dish) {
        dishBook.addDish(dish);
    }

    @Override
    public void setDish(Dish target, Dish editedDish) {
        requireAllNonNull(target, editedDish);

        dishBook.setDish(target, editedDish);
        List<Order> orderContainingDish = getIncompleteOrdersContainingDish(target);
        for (Order targetOrder : orderContainingDish) {
            Order updatedOrder = targetOrder.updateDish(target, editedDish);
            setOrder(targetOrder, updatedOrder);
        }
    }

    @Override
    public ObservableList<Dish> getFilteredDishList() {
        return filteredDishes;
    }

    @Override
    public void updateFilteredDishList(Predicate<? super Dish> predicate) {
        requireNonNull(predicate);
        filteredDishes.setPredicate(predicate);
    }

    @Override
    public Predicate<Dish> getAvailableDishPredicate() {
        return dish -> hasSufficientIngredients(dish);
    }

    @Override
    public boolean hasSufficientIngredients(Dish target) {
        return hasSufficientIngredients(target, 1);
    }

    @Override
    public boolean hasSufficientIngredients(Dish target, int amount) {
        List<Pair<Ingredient, Integer>> requiredIngredients = target.getIngredientQuantityList();
        for (Pair<Ingredient, Integer> ingredientQtyPair : requiredIngredients) {
            Ingredient ingredient = ingredientQtyPair.getKey();
            Integer quantity = ingredientQtyPair.getValue();
            if (!ingredientBook.hasSufficientIngredients(ingredient, amount * quantity)) {
                return false;
            }
        }
        return true;
    }

    //=========== Ingredients ================================================================================
    @Override
    public void setIngredientBook(ReadOnlyBook<Ingredient> ingredientBook) {
        this.ingredientBook.resetData(ingredientBook);
    }

    @Override
    public ReadOnlyBook<Ingredient> getIngredientBook() {
        return ingredientBook;
    }

    @Override
    public boolean hasIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);
        return ingredientBook.hasIngredient(ingredient);
    }

    @Override
    public Ingredient getIngredientByIndex(int i) {
        return getFilteredIngredientList().get(i);
    }

    @Override
    public void deleteIngredient(Ingredient target) {
        ingredientBook.removeIngredient(target);

        List<Dish> dishesToDelete = getDishesByIngredients(target);
        for (Dish targetDish : dishesToDelete) {
            deleteDish(targetDish);

            assert !hasDish(targetDish);
            logger.fine(String.format("Dish %s which contains %s deleted by cascade", targetDish, target));
        }
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        ingredientBook.addIngredient(ingredient);
    }

    @Override
    public void setIngredient(Ingredient target, Ingredient editedIngredient) {
        requireAllNonNull(target, editedIngredient);

        ingredientBook.setIngredient(target, editedIngredient);
        List<Dish> dishesToEdit = getDishesByIngredients(target);
        for (Dish targetDish : dishesToEdit) {
            Dish updatedDish = targetDish.updateIngredient(target, editedIngredient);
            setDish(targetDish, updatedDish);
        }
    }

    @Override
    public void decreaseIngredient(Ingredient target, int decreaseQuantity) {
        Ingredient decreasedIngredient = new Ingredient(target.getName(), target.getQuantity() - decreaseQuantity);
        this.setIngredient(target, decreasedIngredient);
    }

    @Override
    public void decreaseIngredientByOrder(Order order) {
        List<Pair<Dish, Integer>> dishQuantityList = order.getDishQuantityList();

        for (Pair<Dish, Integer> dishPair: dishQuantityList) {
            int dishQuantity = dishPair.getValue();
            List<Pair<Ingredient, Integer>> ingredientList = dishPair.getKey().getIngredientQuantityList();

            for (Pair<Ingredient, Integer> ingredientPair: ingredientList) {
                String ingredientName = ingredientPair.getKey().getName();
                Ingredient ingredientNeeded = ingredientBook.getIngredientByName(ingredientName);
                int decreaseQuantity = ingredientPair.getValue() * dishQuantity;
                decreaseIngredient(ingredientNeeded, decreaseQuantity);
            }
        }
    }

    private void increaseIngredient(Ingredient target, int increaseQuantity) {
        Ingredient increase = new Ingredient(target.getName(), target.getQuantity() + increaseQuantity);
        this.setIngredient(target, increase);
    }

    @Override
    public void increaseIngredientByName(String name, int increaseQuantity) {
        Ingredient ingredient = ingredientBook.getIngredientByName(name);
        increaseIngredient(ingredient, increaseQuantity);
    }

    @Override
    public void increaseIngredientByOrder(Order order) {
        List<Pair<Dish, Integer>> dishQuantityList = order.getDishQuantityList();

        for (Pair<Dish, Integer> dishPair: dishQuantityList) {
            int dishQuantity = dishPair.getValue();
            List<Pair<Ingredient, Integer>> ingredientList = dishPair.getKey().getIngredientQuantityList();

            for (Pair<Ingredient, Integer> ingredientPair: ingredientList) {
                String ingredientName = ingredientPair.getKey().getName();
                Ingredient ingredientNeeded = ingredientBook.getIngredientByName(ingredientName);
                int increaseQuantity = ingredientPair.getValue() * dishQuantity;
                increaseIngredient(ingredientNeeded, increaseQuantity);
            }
        }
    }

    @Override
    public ObservableList<Ingredient> getFilteredIngredientList() {
        return filteredIngredients;
    }

    @Override
    public void updateFilteredIngredientList(Predicate<? super Ingredient> predicate) {
        requireNonNull(predicate);
        filteredIngredients.setPredicate(predicate);
    }


    //@@author kangtinglee
    public List<Dish> getDishesByIngredients(Ingredient ingredient) {
        List<Dish> result = new ArrayList<>();
        List<Dish> dishes = getDishBook().getItemList();
        for (Dish d : dishes) {
            if (d.contains(ingredient)) {
                result.add(d);
            }
        }
        return result;
    }

    //=========== Orders ================================================================================
    @Override
    public void setOrderBook(ReadOnlyBook<Order> orderBook) {
        this.orderBook.resetData(orderBook);
    }

    @Override
    public ReadOnlyBook<Order> getOrderBook() {
        return orderBook;
    }

    @Override
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return orderBook.hasOrder(order);
    }

    @Override
    public void deleteOrder(Order target) {
        orderBook.removeOrder(target);
    }

    @Override
    public void deleteOrders(List<Order> orders) {
        requireNonNull(orders);
        for (Order o : orders) {
            deleteOrder(o);
        }
    }

    public void addOrder(Order order) {
        orderBook.addOrder(order);
    }

    public void setOrder(Order target, Order editedOrder) {
        requireAllNonNull(target, editedOrder);

        orderBook.setOrder(target, editedOrder);
    }

    @Override
    public void cancelOrder(Order target) {
        Order cancelledOrder = orderBook.cancelOrder(target);
        setOrder(target, cancelledOrder);
    }

    @Override
    public void cancelOrders(List<Order> targets) {
        for (Order o : targets) {
            cancelOrder(o);
        }
    }

    //@@ author kangtinglee
    @Override
    public boolean canFulfilOrder(Order target) {
        List<Order> temp = new ArrayList<>();
        temp.add(target);
        return canFulfilOrders(temp);
    }

    //@@ author kangtinglee
    @Override
    public boolean canFulfilOrders(List<Order> orders) {
        HashMap<Ingredient, Integer> ingredientsTable = new HashMap<>();
        for (Order target : orders) {
            List<Pair<Dish, Integer>> dishesQtyList = target.getDishQuantityList();
            collateIngredientsFromDishes(ingredientsTable, dishesQtyList);
        }
        for (Ingredient i : ingredientsTable.keySet()) {
            if (!ingredientBook.hasSufficientIngredients(i, ingredientsTable.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Add ingredients and respective quantities of a list of dishes into ingredientsTable
     * @param ingredientsTable HashMap of ingredients and their quantities
     * @param dishesQtyList List of {@code Pair<Dish, Integer>} containing the Dish and its quantity
     */
    private static void collateIngredientsFromDishes(HashMap<Ingredient, Integer> ingredientsTable,
                                      List<Pair<Dish, Integer>> dishesQtyList) {
        for (Pair<Dish, Integer> dishQtyPair : dishesQtyList) {
            for (Pair<Ingredient, Integer> ingredientQtyPair : dishQtyPair.getKey().getIngredientQuantityList()) {
                collateIngredients(ingredientsTable, ingredientQtyPair);
            }
        }
    }

    /**
     * Add ingredients and respective quantity into ingredientsTable
     * @param ingredientsTable HashMap of ingredients and their quantities
     * @param ingredientQtyPair of Ingredient and its quantity
     */
    private static void collateIngredients(HashMap<Ingredient, Integer> ingredientsTable,
                                                   Pair<Ingredient, Integer> ingredientQtyPair) {
        if (!ingredientsTable.containsKey(ingredientQtyPair.getKey())) {
            ingredientsTable.put(ingredientQtyPair.getKey(), 0);
        }
        ingredientsTable.put(ingredientQtyPair.getKey(), ingredientsTable.get(ingredientQtyPair.getKey())
                + ingredientQtyPair.getValue() * ingredientQtyPair.getValue());
    }

    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return filteredOrders;
    }

    @Override
    public void updateFilteredOrderList(Predicate<? super Order> predicate) {
        requireNonNull(predicate);
        filteredOrders.setPredicate(predicate);
    }

    @Override
    public void updateFilteredOrderList(Comparator<Order> comparator) {
        requireNonNull(comparator);
        orderBook.sortItemsByDateTime(comparator);
    }

    //@@author kangtinglee
    public List<Order> getOrdersFromPerson(Person target) {
        List<Order> result = new ArrayList<>();
        ObservableList<Order> orders = getOrderBook().getItemList();
        for (Order o : orders) {
            if (o.isFromCustomer(target)) {
                result.add(o);
            }
        }
        return result;
    }

    //@@author kangtinglee
    public List<Order> getIncompleteOrdersFromPerson(Person target) {
        List<Order> result = new ArrayList<>();
        List<Order> orders = getOrdersFromPerson(target);
        for (Order o : orders) {
            if (o.getState() == Order.State.UNCOMPLETED) {
                result.add(o);
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return personBook.equals(other.personBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
