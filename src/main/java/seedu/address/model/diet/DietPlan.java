package seedu.address.model.diet;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Wrapper class to contain nutritional breakdown of macronutrient consumption.
 * Contains a generic breakdown for the three daily meals, breakfast, lunch and dinner.
 */
public class DietPlan {

    private final String planName;
    private final String description;
    private final PlanType planType;
    private final MacroNutrientComposition macroNutrientComposition;

    /**
     * Constructor for diet plan holding information of daily consumption.
     *
     * @param planName The name of the plan.
     * @param description The description of the plan.
     * @param dietComposition The macronutrient composition.
     * @param planType The purpose of the diet plan.
     */
    public DietPlan(String planName, String description, MacroNutrientComposition dietComposition,
                    PlanType planType) {
        requireAllNonNull(planName, description, dietComposition);
        this.planName = planName;
        this.description = description;
        this.macroNutrientComposition = dietComposition;
        this.planType = planType;
    }

    /**
     * Returns a string formatted representation of the DietPlan.
     *
     * @return Diet plan information.
     */
    public String viewPlan() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("== %s ==", planName));
        sb.append(String.format("\n%s", description));
        sb.append(String.format("\n(Fats: %.2f, Proteins: %.2f, Carbohydrates: %.2f)",
                macroNutrientComposition.getFats(), macroNutrientComposition.getProteins(),
                macroNutrientComposition.getCarbohydrates()));
        return sb.toString();
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

    /**
     * Returns the purpose of the diet plan.
     *
     * @return The diet plan's purpose
     */
    public PlanType getPlanType() {
        return planType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
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
