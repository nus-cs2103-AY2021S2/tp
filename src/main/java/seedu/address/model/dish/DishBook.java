package seedu.address.model.dish;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyBook;
import seedu.address.model.UniqueItemList;

public class DishBook implements ReadOnlyBook<Dish> {
    private final UniqueItemList<Dish> dishes;
    {
        dishes = new UniqueItemList<Dish>();
    }

    public DishBook() {}

    /**
     * Constructor to copy dishbook
     * @param toBeCopied dish to be copied
     */
    public DishBook(ReadOnlyBook<Dish> toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Set dishes from list
     * @param dishes list of dishes used to replace
     */
    public void setDishes(List<Dish> dishes) {
        this.dishes.setItems(dishes);
    }

    /**
     * Reset list data using new data
     * @param newData
     */
    public void resetData(ReadOnlyBook<Dish> newData) {
        requireNonNull(newData);

        setDishes(newData.getItemList());
    }

    /**
     * Check if dish exists
     * @param dish
     * @return result
     */
    public boolean hasDish(Dish dish) {
        requireNonNull(dish);

        return dishes.contains(dish);
    }

    /**
     * Add new dish
     * @param dish dish to be added
     */
    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    /**
     * Set details of new dish
     * @param target target dish to be edited
     * @param editedDish edited version of dish
     */
    public void setDish(Dish target, Dish editedDish) {
        requireNonNull(editedDish);
        dishes.setItem(target, editedDish);
    }

    /**
     * Remove dish
     * @param key dish to be removed
     */
    public void removeDish(Dish key) {
        dishes.remove(key);
    }

    @Override
    public String toString() {

        return dishes.asUnmodifiableObservableList().size() + " Dishs";
        // TODO: refine later
    }

    @Override
    public ObservableList<Dish> getItemList() {
        return dishes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DishBook // instanceof handles nulls
                && dishes.equals(((DishBook) other).dishes));
    }

}
