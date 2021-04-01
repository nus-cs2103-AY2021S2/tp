package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.MacroNutrientComposition;
import seedu.address.model.diet.PlanType;

/**
 * Jackson-friendly version of {@link DietPlan}.
 */
public class JsonAdaptedDietPlan {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "%s's %s field is missing!";
    public static final String INVALID_FIELD_MESSAGE_FORMAT = "%s's %s field is invalid!";

    private final String name;
    private final String description;
    private final MacroNutrientComposition macroNutrientComposition;
    private final PlanType planType;

    /**
     * Constructs a {@code DietPlan} with the given details.
     */
    @JsonCreator
    public JsonAdaptedDietPlan(@JsonProperty("name") String name, @JsonProperty("description") String description,
                               @JsonProperty("fats") double fats, @JsonProperty("carbos") double carbos,
                               @JsonProperty("proteins") double proteins,
                               @JsonProperty("plantype") PlanType planType) {
        this.name = name;
        this.description = description;
        this.macroNutrientComposition = new MacroNutrientComposition(fats, carbos, proteins);
        this.planType = planType;
    }

    /**
     * Converts a given {@code DietPlan} into this class for Jackson use.
     */
    public JsonAdaptedDietPlan(DietPlan dietPlan) {
        this.name = dietPlan.getPlanName();
        this.description = dietPlan.getDescription();
        this.macroNutrientComposition = dietPlan.getMacroNutrientComposition();
        this.planType = dietPlan.getPlanType();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code DietPlan} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted DietPlan.
     */
    public DietPlan toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DietPlan.class.getSimpleName(), "name"));
        }
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DietPlan.class.getSimpleName(), "description"));
        }

        return new DietPlan(name, description, macroNutrientComposition, planType);
    }

}
