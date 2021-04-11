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
        // ------------------------ WEIGHT LOSS DIETS ------------------------
        DietPlan firstDietPlan = new DietPlan("Standard Ketogenic Diet",
                "The Standard Ketogenic Diet is a high-fat, low-carb weight-loss diet. "
                + "It is designed in such a way that by reducing the intake of carbohydrates,\n"
                + "the body is forced to burn its fat reserves for fuel thereby resulting in weight-loss. \n"
                + "The Standard Ketogenic Diet is suitable for individuals suffering from Type II Diabetes where "
                + "excess carbohydrates would have been converted into glucose.",
                new MacroNutrientComposition(70, 10, 20),
                PlanType.WEIGHT_LOSS);
        DietPlan secondDietPlan = new DietPlan("High-Protein Ketogenic Diet",
                "The High-Protein Ketogenic Diet is a variation of the Ketogenic Diet (high-fat, low-carb) "
                + "which increases the protein intake.\nThis variation is designed to help athletes and "
                + "bodybuilders maintain their muscle mass whilst burning fat.",
                new MacroNutrientComposition(60, 5, 35),
                PlanType.WEIGHT_LOSS);
        // ------------------------ WEIGHT GAIN DIETS ------------------------
        DietPlan thirdDietPlan = new DietPlan("Balanced Plan For Weight Gain",
                "This plan is aimed at individuals who are intending to gain healthy weight "
                        + "in a balanced manner.\nSome exercise coupled with this diet plan will allow individuals "
                        + "to gain some muscle steadily.",
                new MacroNutrientComposition(30, 35, 35),
                PlanType.WEIGHT_GAIN);
        DietPlan fourthDietPlan = new DietPlan("Clean Bulk",
                "The clean bulk is a process which bodybuilders use to gain lean muscle mass. The clean "
                        + "bulk emphasizes consuming healthy whole foods\nas compared to eating sugary and processed "
                        + "foods to hit the calorie intake.",
                new MacroNutrientComposition(30, 30, 40),
                PlanType.WEIGHT_GAIN);
        DietPlan fifthDietPlan = new DietPlan("High Carbohydrates Bulk",
                "This plan is intended for athletes who are involved in high intensity sports which require "
                        + "high energy consumption.\nAs such, this plan prescribes a higher amount of carbohydrates "
                        + "to offset this need whilst ensuring the protein intake is enough to promote muscle growth "
                        + "and in turn, healthy weight gain.",
                new MacroNutrientComposition(15, 55, 30),
                PlanType.WEIGHT_GAIN);
        // ------------------------ WEIGHT MAINTENANCE DIETS ------------------------
        DietPlan sixthDietPlan = new DietPlan("Balanced Plan",
                "The perfect ying-yang. Eat healthy food and complete the calorie goal. Eat lots of fruits "
                        + "and vegetables,\nand base meals on higher fiber starchy carbohydrates.",
                new MacroNutrientComposition(30, 40, 30),
                PlanType.WEIGHT_MAINTAIN);
        DietPlanList dietPlanList = new DietPlanList();
        dietPlanList.addDietPlan(firstDietPlan);
        dietPlanList.addDietPlan(secondDietPlan);
        dietPlanList.addDietPlan(thirdDietPlan);
        dietPlanList.addDietPlan(fourthDietPlan);
        dietPlanList.addDietPlan(fifthDietPlan);
        dietPlanList.addDietPlan(sixthDietPlan);
        return dietPlanList;
    }
}
