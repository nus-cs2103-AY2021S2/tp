package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.DietPlanList;

/**
 * A DietPlanList that is serializable to JSON format.
 */
@JsonRootName(value = "dietplanlist")
public class JsonSerializableDietPlanList {

    public static final String MESSAGE_DUPLICATE_ENTRY = "Diet plan list contains duplicated diet plan(s).";

    private final List<JsonAdaptedDietPlan> dietPlans = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableDietPlanList} with the given diet plans.
     */
    @JsonCreator
    public JsonSerializableDietPlanList(@JsonProperty("plans") List<JsonAdaptedDietPlan> dietPlans) {
        this.dietPlans.addAll(dietPlans);
    }

    /**
     * Converts a given {@code DietPlanList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableDietPlanList}.
     */
    public JsonSerializableDietPlanList(DietPlanList source) {
        this.dietPlans.addAll(source.getPlanList().stream().map(JsonAdaptedDietPlan::new).collect(Collectors.toList()));
    }

    /**
     * Converts this diet plan list into the model's {@code DietPlanList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public DietPlanList toModelType() throws IllegalValueException {
        DietPlanList dietPlanList = new DietPlanList();
        for (JsonAdaptedDietPlan jsonAdaptedDietPlan : dietPlans) {
            DietPlan dietPlan = jsonAdaptedDietPlan.toModelType();
            if (dietPlanList.hasDietPlan(dietPlan)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ENTRY);
            }
            dietPlanList.addDietPlan(dietPlan);
        }
        return dietPlanList;
    }

}
