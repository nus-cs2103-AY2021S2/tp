package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.food.Food;
import seedu.address.model.food.FoodIntake;

/**
 * Jackson-friendly version of {@link FoodIntake}.
 */
public class JsonAdaptedFoodIntake {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "FoodIntake's %s field is missing!";
    public static final String INVALID_FIELD_MESSAGE_FORMAT = "FoodIntake's %s field is invalid!";

    private final LocalDate date;
    private final String name;
    private final double carbos;
    private final double fats;
    private final double proteins;

    /**
     * Constructs a {@code JsonAdaptedFoodIntake} with the given food intake details.
     */
    @JsonCreator
    public JsonAdaptedFoodIntake(@JsonProperty("data") LocalDate date,
                                 @JsonProperty("name") String name,
                                 @JsonProperty("fats") double fats,
                                 @JsonProperty("carbos") double carbos,
                                 @JsonProperty("proteins") double proteins) {
        this.date = date;
        this.name = name;
        this.carbos = carbos;
        this.fats = fats;
        this.proteins = proteins;
    }

    /**
     * Converts a given {@code Food} into this class for Jackson use.
     */
    public JsonAdaptedFoodIntake(FoodIntake foodIntake) {
        this.date = foodIntake.getDate();
        this.name = foodIntake.getFood().getName();
        this.carbos = foodIntake.getFood().getCarbos();
        this.fats = foodIntake.getFood().getFats();
        this.proteins = foodIntake.getFood().getProteins();
    }

    /**
     * Converts this Jackson-friendly adapted foodintake object into the model's {@code FoodIntake} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Food.
     */
    public FoodIntake toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    FoodIntake.class.getSimpleName()));
        }
        if (!Food.isValidFoodName(name)) {
            throw new IllegalValueException(String.format(INVALID_FIELD_MESSAGE_FORMAT,
                    FoodIntake.class.getSimpleName()));
        }

        if (!Food.isValidNumber(fats, carbos, proteins)) {
            throw new IllegalValueException(String.format(INVALID_FIELD_MESSAGE_FORMAT,
                    FoodIntake.class.getSimpleName()));
        }

        return new FoodIntake(date, new Food(name, fats, carbos, proteins));
    }

}
