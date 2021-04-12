package seedu.address.storage.dish;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyBook;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.DishBook;

/**
 * An immutable DishBook that is serializable to JSON format.
 */
@JsonRootName(value = "dishbook")
public class JsonSerializableDishBook {
    public static final String MESSAGE_DUPLICATE_DISH = "Dish list contains duplicate dishes.";

    private final List<Dish> dishes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableDishBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableDishBook(@JsonProperty("dishes") List<Dish> dishes) {
        this.dishes.addAll(dishes);
    }

    /**
     * Converts a given {@code ReadOnlyBook<Dish>} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableDishBook}.
     */
    public JsonSerializableDishBook(ReadOnlyBook<Dish> source) {
        dishes.addAll(source.getItemList());
    }

    /**
     * Converts this dish book into the model's {@code DishBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public DishBook toModelType() throws IllegalValueException {
        DishBook dishBook = new DishBook();
        dishBook.setDishes(dishes);
        return dishBook;
    }
}
