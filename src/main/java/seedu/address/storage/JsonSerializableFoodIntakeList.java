package seedu.address.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.food.FoodIntake;
import seedu.address.model.food.FoodIntakeList;

/**
 * A FoodIntakeList that is serializable to JSON format.
 */
@JsonRootName(value = "foodintakelist")
public class JsonSerializableFoodIntakeList {

    private LocalDate date;
    private final List<JsonAdaptedFoodIntake> foodIntakes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFoodIntakeList} with the given foodIntakes.
     */
    @JsonCreator
    public JsonSerializableFoodIntakeList(@JsonProperty("date") LocalDate date,
                                          @JsonProperty("foodIntakes") List<JsonAdaptedFoodIntake> foodIntakes) {
        this.date = date;
        this.foodIntakes.addAll(foodIntakes);
    }

    /**
     * Converts a given {@code ReadOnlyFoodIntakeList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFoodIntakeList}.
     */
    public JsonSerializableFoodIntakeList(FoodIntakeList source) {
        this.foodIntakes.addAll(source.getFoodIntakeList().stream()
                .map(JsonAdaptedFoodIntake::new).collect(Collectors.toList()));
    }

    /**
     * Converts this foodIntake list into the model's {@code FoodIntakeList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FoodIntakeList toModelType() throws IllegalValueException {
        FoodIntakeList foodIntakeList = new FoodIntakeList();
        for (JsonAdaptedFoodIntake jsonAdaptedFoodIntake : foodIntakes) {
            FoodIntake foodIntake = jsonAdaptedFoodIntake.toModelType();
            foodIntakeList.addFoodIntake(foodIntake);
        }
        return foodIntakeList;
    }

}
