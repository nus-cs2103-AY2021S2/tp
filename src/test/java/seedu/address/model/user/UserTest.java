package seedu.address.model.user;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.food.Food;
import seedu.address.model.food.FoodIntakeList;



public class UserTest {
    public static final double INIT_USER_WEIGHT = 70;
    public static final double INIT_USER_HEIGHT = 165;
    public static final double INIT_USER_IDEAL_WEIGHT = 60;
    public static final int INIT_USER_AGE = 24;
    public static final String INIT_USER_GENDER = "M";

    @Test
    public void constructor_null_throwsNullPointerException() {
        Bmi bmi = new Bmi(INIT_USER_WEIGHT, INIT_USER_HEIGHT);
        Gender gender = new Gender(INIT_USER_GENDER);
        Age age = new Age(INIT_USER_AGE);
        IdealWeight idealWeight = new IdealWeight(INIT_USER_IDEAL_WEIGHT);
        assertThrows(NullPointerException.class, ()
            -> new User(bmi, null, null, age, gender, idealWeight));
    }

    @Test
    public void equals_checkEquality() {
        Bmi bmi = new Bmi(INIT_USER_WEIGHT, INIT_USER_HEIGHT);
        Gender gender = new Gender(INIT_USER_GENDER);
        Age age = new Age(INIT_USER_AGE);
        IdealWeight idealWeight = new IdealWeight(INIT_USER_IDEAL_WEIGHT);
        FoodIntakeList foodIntakeList = new FoodIntakeList();
        List<Food> foodList = new ArrayList<Food>();
        User user = new User(bmi, foodList, foodIntakeList, age, gender,idealWeight);
        assertTrue(user.equals(new User(bmi, foodList, foodIntakeList, age, gender, idealWeight)));

        Age newAge = new Age(15);
        assertFalse(user.equals(new User(bmi, foodList, foodIntakeList, newAge, gender, idealWeight)));
    }

}
