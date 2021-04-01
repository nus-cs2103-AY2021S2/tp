package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.food.Food;

/**
 * Jackson-friendly version of {@link Food}.
 */
public class JsonAdaptedFood {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "%s's name field is missing!";
    public static final String INVALID_FIELD_MESSAGE_FORMAT = "%s's name field is invalid!";

    private final String name;
    private final double carbos;
    private final double fats;
    private final double proteins;

    /**
     * Constructs a {@code JsonAdaptedFood} with the given food details.
     */
    @JsonCreator
    public JsonAdaptedFood(@JsonProperty("name") String name, @JsonProperty("fats") double fats,
                           @JsonProperty("carbos") double carbos, @JsonProperty("proteins") double proteins) {
        this.name = name;
        this.carbos = carbos;
        this.fats = fats;
        this.proteins = proteins;
    }

    /**
     * Converts a given {@code Food} into this class for Jackson use.
     */
    public JsonAdaptedFood(Food food) {
        this.name = food.getName();
        this.carbos = food.getCarbos();
        this.fats = food.getFats();
        this.proteins = food.getProteins();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Food} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Food.
     */
    public Food toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Food.class.getSimpleName()));
        }
        if (!Food.isValidFoodName(name)) {
            throw new IllegalValueException(String.format(INVALID_FIELD_MESSAGE_FORMAT, Food.class.getSimpleName()));
        }

        if (!Food.isValidNumber(fats, carbos, proteins)) {
            throw new IllegalValueException(String.format(INVALID_FIELD_MESSAGE_FORMAT, Food.class.getSimpleName()));
        }

        return new Food(name, fats, carbos, proteins);
    }

}
