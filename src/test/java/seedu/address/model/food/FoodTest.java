package seedu.address.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FoodTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Food(null, 0, 0, 0));
    }

    @Test
    public void constructor_invalidFood_throwsIllegalArgumentException() {
        String invalidFood = "^.^";
        assertThrows(IllegalArgumentException.class, () -> new Food(invalidFood, 0, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Food("Jellies", -1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Food("Sweet", 10, 15, -5));
        assertThrows(IllegalArgumentException.class, () -> new Food("Flour", 10, -5, 10));
    }

    @Test
    public void isValidFood() {
        // null address
        assertThrows(NullPointerException.class, () -> Food.isValidFoodName(null));

        // invalid addresses
        assertFalse(Food.isValidFoodName("")); // empty string
        assertFalse(Food.isValidFoodName(":)abc")); //string filled with mixture of alphabets and special chars
        assertFalse(Food.isValidFoodName("^.^")); //string filled with only special characters
        assertFalse(Food.isValidFoodName(" ")); // spaces only

        // valid addresses
        assertTrue(Food.isValidFoodName("Jellies"));
        assertTrue(Food.isValidFoodName("A")); // one character
    }

    @Test
    public void isValidNumber() {
        //invalid inputs
        assertFalse(Food.isValidNumber(-1, 0, 0));
        assertFalse(Food.isValidNumber(0, -1, 0));
        assertFalse(Food.isValidNumber(0, 0, -1));

        //valid inputs
        assertTrue(Food.isValidNumber(0, 0, 0));
        assertTrue(Food.isValidNumber(1, 10, 100));
    }
}
