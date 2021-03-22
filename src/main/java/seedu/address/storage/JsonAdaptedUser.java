package seedu.address.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.food.Food;
import seedu.address.model.food.FoodIntake;
import seedu.address.model.food.FoodIntakeList;
import seedu.address.model.user.Age;
import seedu.address.model.user.Bmi;
import seedu.address.model.user.Gender;
import seedu.address.model.user.IdealWeight;
import seedu.address.model.user.User;

/**
 * Jackson-friendly version of {@link User}.
 */
public class JsonAdaptedUser {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "User's %s field is missing!";

    private final String age;
    private final String gender;
    private final IdealWeight idealWeight;
    private final String lastUpdated;
    private final Bmi bmi;

    // Data fields
    private final List<Food> foodList;
    private final FoodIntakeList foodIntakeList;
    private DietPlan activeDietPlan;

    /**
     * Constructs a {@code JsonAdaptedUser} with the given details.
     */
    @JsonCreator
    public JsonAdaptedUser(@JsonProperty("bmi") Bmi bmi,
                                 @JsonProperty("foodList") List<Food> foodList,
                                 @JsonProperty("age") String age,
                                 @JsonProperty("gender") String gender,
                                 @JsonProperty("idealWeight") IdealWeight idealWeight,
                                 @JsonProperty("lastUpdated") String lastUpdated,
                                 @JsonProperty("foodIntakeList") FoodIntakeList foodIntakeList,
                                 @JsonProperty("activeDietPlan") DietPlan activeDietPlan) {
        this.bmi = bmi;
        this.foodList = foodList;
        this.age = age;
        this.gender = gender;
        this.idealWeight = idealWeight;
        this.lastUpdated = lastUpdated;
        this.foodIntakeList = foodIntakeList;
        this.activeDietPlan = activeDietPlan;
    }

    /**
     * Converts a given {@code User} into this class for Jackson use.
     */
    public JsonAdaptedUser(User source) {
        bmi = source.getBmi();
        foodList = source.getFoodList();
        age = String.valueOf(source.getAge().age);
        gender = source.getGender().gender;
        idealWeight = source.getIdealWeight();
        lastUpdated = source.getLastUpdated();
        foodIntakeList = source.getFoodIntakeList();
        activeDietPlan = source.getActiveDietPlan();
    }

    /**
     * Converts this Jackson-friendly adapted User object into the model's {@code User} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted User.
     */
    public User toModelType() throws IllegalValueException {
        if (age == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName()));
        }
        if (!Age.isValidAge(age)) {
            throw new IllegalValueException(Age.MESSAGE_CONSTRAINTS);
        }
        if (bmi == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Bmi.class.getSimpleName()));
        }
        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        User newUser = new User(bmi, foodList, foodIntakeList,
                new Age(Integer.valueOf(age)), new Gender(gender), idealWeight);
        if (activeDietPlan != null) {
            newUser.setActiveDietPlan(activeDietPlan);
        }
        return newUser;
    }

}
