package seedu.address.model.diet;

import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Wrapper class to contain nutritional breakdown of macronutrient consumption.
 * Contains a generic breakdown for the three daily meals, breakfast, lunch and dinner.
 */
public class DietPlan {

    private final String planName;
    private final String description;

    private final MacroNutrientComposition macroNutrientComposition;

    /**
     * Constructor for diet plan holding information of daily consumption.
     *
     * @param planName The name of the plan.
     * @param description The description of the plan.
     * @param dietComposition The macronutrient composition.
     */
    public DietPlan(String planName, String description, MacroNutrientComposition dietComposition) {
        requireAllNonNull(planName, description, dietComposition);
        this.planName = planName;
        this.description = description;
        this.macroNutrientComposition = dietComposition;
    }

    /**
     * Returns a string formatted representation of the DietPlan.
     *
     * @return Diet plan information.
     */
    public String viewPlan() {
        // TODO: Agree on message formatting
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the name of the diet plan.
     *
     * @return Name of diet plan.
     */
    public String getPlanName() {
        return planName;
    }

    /**
     * Returns the description of the diet plan.
     *
     * @return Description of diet plan.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the macronutrient composition.
     *
     * @return The nutritional composition of macronutrients.
     */
    public MacroNutrientComposition getMacroNutrientComposition() {
        return macroNutrientComposition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DietPlan dietPlan = (DietPlan) o;
        return planName.equals(dietPlan.planName)
                && description.equals(dietPlan.description)
                && macroNutrientComposition.equals(dietPlan.macroNutrientComposition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planName, description, macroNutrientComposition);
    }

    @Override
    public String toString() {
        return String.format("%s { \"description\": \"%s\", \"composition\": %s }",
                planName, description, macroNutrientComposition);
    }

}
