package seedu.address.model.util;

import java.time.LocalDate;

import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.DietPlanList;
import seedu.address.model.diet.MacroNutrientComposition;
import seedu.address.model.diet.PlanType;
import seedu.address.model.food.Food;
import seedu.address.model.food.FoodIntake;
import seedu.address.model.food.FoodIntakeList;
import seedu.address.model.food.UniqueFoodList;
import seedu.address.model.user.Age;
import seedu.address.model.user.Bmi;
import seedu.address.model.user.Gender;
import seedu.address.model.user.IdealWeight;
import seedu.address.model.user.User;

/**
 * Class for generating a template for first-time use.
 */
public class TemplateInitializer {
    public static final double INIT_USER_WEIGHT = 70;
    public static final double INIT_USER_HEIGHT = 165;
    public static final double INIT_USER_IDEAL_WEIGHT = 60;
    public static final int INIT_USER_AGE = 24;
    public static final String INIT_USER_GENDER = "M";
    public static final int INIT_USER_PLAN = 0;

    private UniqueFoodList uniqueFoodListTemplate;
    private FoodIntakeList foodIntakeListTemplate;
    private DietPlanList dietPlanListTemplate;

    /**
     * Initialises a TemplateInitializer instance.
     */
    public TemplateInitializer() {
        this.uniqueFoodListTemplate = createFoodTemplate();
        this.foodIntakeListTemplate = createFoodIntakeTemplate();
        this.dietPlanListTemplate = createDietPlanTemplate();
    }

    public UniqueFoodList getUniqueFoodListTemplate() {
        return this.uniqueFoodListTemplate;
    }

    public FoodIntakeList getFoodListIntakeTemplate() {
        return this.foodIntakeListTemplate;
    }

    public DietPlanList getDietPlanListTemplate() {
        return this.dietPlanListTemplate;
    }

    /**
     * Intialises the User object with the default template values.
     *
     * @return User object
     */
    public User createUser(UniqueFoodList foodList, FoodIntakeList foodIntakeList) {
        Bmi bmi = new Bmi(INIT_USER_WEIGHT, INIT_USER_HEIGHT);
        Gender gender = new Gender(INIT_USER_GENDER);
        Age age = new Age(INIT_USER_AGE);
        IdealWeight idealWeight = new IdealWeight(INIT_USER_IDEAL_WEIGHT);
        System.out.println("\n -- making freshhh user -- \n");
        User user = new User(bmi, foodList.getFoodList(), foodIntakeList, age, gender, idealWeight);
        user.setActiveDietPlan(getDietPlanListTemplate().getDietPlan(INIT_USER_PLAN));
        return user;
    }

    /**
     * Creates a simple Food List template for first-time use.
     *
     * @return UniqueFoodList template
     */
    public UniqueFoodList createFoodTemplate() {
        Food chocolate = new Food("chocolate", 57, 30, 7.3);
        Food biscuit = new Food("biscuit", 9.3, 3.1, 1.0);
        Food fruitJam = new Food("jam", 13.77, 0.01, 0.07);
        UniqueFoodList foodList = new UniqueFoodList();
        foodList.addFoodItem(chocolate);
        foodList.addFoodItem(biscuit);
        foodList.addFoodItem(fruitJam);
        return foodList;
    }

    /**
     * Creates a simple Food Intake template for first-time use. FoodIntakeList is assumed to have 3 elements
     * because of the creation of food template happening first.
     *
     * @return FoodIntakeList template
     */
    public FoodIntakeList createFoodIntakeTemplate() {
        assert this.uniqueFoodListTemplate.getFoodList().size() == 3
                : "Unique food list template is not created properly.";
        FoodIntake firstFoodIntake = new FoodIntake(LocalDate.now(), this.uniqueFoodListTemplate.getFoodList().get(0));
        FoodIntake secondFoodIntake = new FoodIntake(LocalDate.now(),
                this.uniqueFoodListTemplate.getFoodList().get(1));
        FoodIntake thirdFoodIntake = new FoodIntake(LocalDate.now(),
                this.uniqueFoodListTemplate.getFoodList().get(2));
        FoodIntakeList foodIntakeList = new FoodIntakeList();
        foodIntakeList.addFoodIntake(firstFoodIntake);
        foodIntakeList.addFoodIntake(secondFoodIntake);
        foodIntakeList.addFoodIntake(thirdFoodIntake);
        return foodIntakeList;
    }

    /**
     * Creates Diet Plan templates in Diet Plan List for first-time use.
     *
     * @return DietPlanList template
     */
    public DietPlanList createDietPlanTemplate() {
        DietPlan firstDietPlan = new DietPlan("Standard Ketogenic Diet",
                "The Standard Ketogenic Diet is a high-fat, low-carb weight-loss diet. "
                + "It is designed in such a way that by reducing the intake of carbohydrates, "
                + "the body is forced to burn its fat reserves for fuel thereby resulting in weight-loss. \n"
                + "\n"
                + "The Standard Ketogenic Diet is suitable for individuals suffering from Type II Diabetes where "
                + "excess carbohydrates would have been converted into glucose.\n",
                new MacroNutrientComposition(70, 10, 20),
                PlanType.WEIGHTLOSS);
        DietPlan secondDietPlan = new DietPlan("High-Protein Ketogenic Diet",
                "The High-Protein Ketogenic Diet is a variation of the Ketogenic Diet (high-fat, low-carb) "
                + "which increases the protein intake. This variation is designed to help athletes and "
                + "bodybuilders maintain their muscle mass whilst burning fat.",
                new MacroNutrientComposition(60, 35, 5),
                PlanType.WEIGHTLOSS);
        DietPlan thirdDietPlan = new DietPlan("High Carb Bulking Diet",
                "The goal for bodybuilders is to increase muscle mass. Consume high-quality, nutrient-dense "
                        + "carbs when the body needs them most, around workouts. The hyperenergetic diet "
                        + "plan recommends consuming starchy food during and after workouts, and less "
                        + "starchy content on off-hours.",
                new MacroNutrientComposition(15, 55, 30),
                PlanType.WEIGHTGAIN);
        DietPlan forthDietPlan = new DietPlan("Balanced Plan",
                "The perfect ying-yang. Eat healthy food and complete the calorie goal. Eat lots of fruits "
                        + "and vegetables, and base meals on higher fiber starchy carbohydrates.",
                new MacroNutrientComposition(30, 40, 30),
                PlanType.WEIGHTMAINTAIN);
        DietPlanList dietPlanList = new DietPlanList();
        dietPlanList.addDietPlan(firstDietPlan);
        dietPlanList.addDietPlan(secondDietPlan);
        dietPlanList.addDietPlan(thirdDietPlan);
        dietPlanList.addDietPlan(forthDietPlan);
        return dietPlanList;
    }
}
