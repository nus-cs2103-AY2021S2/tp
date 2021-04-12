package seedu.address.storage.ingredient;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyBook;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientBook;

/**
 * An immutable IngredientBook that is serializable to JSON format.
 */
@JsonRootName(value = "ingredientbook")
public class JsonSerializableIngredientBook {
    public static final String MESSAGE_DUPLICATE_DISH = "Ingredient list contains duplicate ingredients.";

    private final List<Ingredient> ingredients = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableIngredientBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableIngredientBook(@JsonProperty("ingredients") List<Ingredient> ingredients) {
        this.ingredients.addAll(ingredients);
    }

    /**
     * Converts a given {@code ReadOnlyBook<Ingredient>} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableIngredientBook}.
     */
    public JsonSerializableIngredientBook(ReadOnlyBook<Ingredient> source) {
        ingredients.addAll(source.getItemList());
    }

    /**
     * Converts this ingredient book into the model's {@code IngredientBook} object.
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public IngredientBook toModelType() throws IllegalValueException {
        IngredientBook ingredientBook = new IngredientBook();
        ingredientBook.setIngredients(ingredients);
        return ingredientBook;
    }
}
