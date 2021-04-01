package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.food.Food;
import seedu.address.model.food.UniqueFoodList;

/**
 * A UniqueFoodList that is serializable to JSON format.
 */
@JsonRootName(value = "foodlist")
public class JsonSerializableUniqueFoodList {

    public static final String MESSAGE_DUPLICATE_ENTRY = "Food list contains duplicated food item(s).";

    private final List<JsonAdaptedFood> foods = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFoodList} with the given food items.
     */
    @JsonCreator
    public JsonSerializableUniqueFoodList(@JsonProperty("foods") List<JsonAdaptedFood> foods) {
        this.foods.addAll(foods);
    }

    /**
     * Converts a given {@code ReadOnlyFoodList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFoodList}.
     */
    public JsonSerializableUniqueFoodList(UniqueFoodList source) {
        this.foods.addAll(source.getFoodList().stream().map(JsonAdaptedFood::new).collect(Collectors.toList()));
    }

    /**
     * Converts this food list into the model's {@code FoodList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public UniqueFoodList toModelType() throws IllegalValueException {
        UniqueFoodList foodList = new UniqueFoodList();
        for (JsonAdaptedFood jsonAdaptedFood : foods) {
            Food food = jsonAdaptedFood.toModelType();
            if (foodList.hasFoodItem(food)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ENTRY);
            }
            foodList.addFoodItem(food);
        }
        return foodList;
    }

}
