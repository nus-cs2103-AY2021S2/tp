package seedu.address.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.food.exceptions.FoodIntakeNotFoundException;

public class FoodIntakeListTest {

    private final FoodIntakeList foodIntakeList = new FoodIntakeList();

    @Test
    public void getFoodIntakeList_nullFoodIntake_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> foodIntakeList.getFoodIntakeList().get(-1));
    }

    @Test
    public void getFoodIntakeList_foodIntakeNotInList_returnsFalse() {
        assertFalse(foodIntakeList.getFoodIntakeList().contains("fake food"));
    }

    @Test
    public void getFoodIntakeListByDate_emptyList_returnsTrue() throws ParseException {
        LocalDate date = ParserUtil.parseDate("1 Apr 2021");
        assertTrue(foodIntakeList.getFoodIntakeListByDate(date).equals("Summary Food Intake for the Day (1 Apr 2021):\n"
                + "No record found during this date."));
    }

    @Test
    public void getFoodIntakeListByDate_foodIntakeExists_success() throws ParseException {
        Food food = new Food("avocado", 10, 10, 10);
        LocalDate date = ParserUtil.parseDate("7 Apr 2021");
        FoodIntake foodIntake = new FoodIntake(date, food);
        foodIntakeList.addFoodIntake(foodIntake);
        assertTrue(foodIntakeList.getFoodIntakeListByDate(date).equals("Summary Food Intake for the Day (7 Apr 2021):\n"
                + "1. avocado (Carbos: 10.0g, Fats: 10.0g, Proteins: 10.0g): 170.00 calories\n\n"
                + "Total Daily Calories Intake: 170.00 calories.\n"));
    }

    @Test
    public void addFoodIntake_validInput_success() throws ParseException {
        Food food = new Food("apple", 10, 10, 10);
        LocalDate date = ParserUtil.parseDate("7 Apr 2021");
        FoodIntake foodIntake = new FoodIntake(date, food);
        foodIntakeList.addFoodIntake(foodIntake);
        assertTrue(foodIntakeList.getFoodIntakeList().get(0).getFood().getName().equals("apple"));
    }

    @Test
    public void addFoodIntake_duplicateFoodName_successfullyAddDuplicateCount() throws ParseException {
        Food food = new Food("apple", 10, 10, 10);
        LocalDate date = ParserUtil.parseDate("7 Apr 2021");
        FoodIntake foodIntake = new FoodIntake(date, food);
        foodIntakeList.addFoodIntake(foodIntake);
        foodIntakeList.addFoodIntake(foodIntake);

        String foodIntakeOne = foodIntakeList.getFoodIntakeList().get(0).getFood().getName();
        String foodIntakeTwo = foodIntakeList.getFoodIntakeList().get(1).getFood().getName();

        assertTrue(foodIntakeOne.equals("apple") && foodIntakeTwo.equals("apple #2"));
    }

    @Test
    public void deleteFoodIntake_validFoodIntake_success() throws ParseException {
        Food food = new Food("apple", 10, 10, 10);
        LocalDate date = ParserUtil.parseDate("7 Apr 2021");
        FoodIntake foodIntake = new FoodIntake(date, food);
        foodIntakeList.addFoodIntake(foodIntake);
        foodIntakeList.deleteFoodIntake(date, "apple");
        assertThrows(IndexOutOfBoundsException.class, () -> foodIntakeList.getFoodIntakeList().get(0));
    }

    @Test
    public void deleteFoodIntake_validDeletion_successfullyReorderDuplicates()
            throws ParseException, FoodIntakeNotFoundException {
        LocalDate date = ParserUtil.parseDate("7 Apr 2021");
        foodIntakeList.addFoodIntake(new FoodIntake(date, "cherries", 10, 10, 10));
        foodIntakeList.addFoodIntake(new FoodIntake(date, "cherries #2", 10, 10, 10));
        foodIntakeList.addFoodIntake(new FoodIntake(date, "cherries #3", 10, 10, 10));
        foodIntakeList.deleteFoodIntake(date, "cherries #2");
        assertTrue(foodIntakeList.getFoodIntakeListByDate(date).equals("Summary Food Intake for the Day (7 Apr 2021):\n"
                + "1. cherries (Carbos: 10.0g, Fats: 10.0g, Proteins: 10.0g): 170.00 calories\n"
                + "2. cherries #2 (Carbos: 10.0g, Fats: 10.0g, Proteins: 10.0g): 170.00 calories\n\n"
                + "Total Daily Calories Intake: 340.00 calories.\n"));
    }

    @Test
    public void deleteFoodIntake_invalidFoodIntake_exceptionThrown()
            throws ParseException, FoodIntakeNotFoundException {
        Food food = new Food("apple", 10, 10, 10);
        LocalDate date = ParserUtil.parseDate("7 Apr 2021");
        FoodIntake foodIntake = new FoodIntake(date, food);
        foodIntakeList.addFoodIntake(foodIntake);
        assertThrows(FoodIntakeNotFoundException.class, () -> foodIntakeList.deleteFoodIntake(date, "banana"));
    }


    @Test
    public void updateFoodIntake_invalidIndex_throwsIndexOutOfBoundsException()
            throws ParseException {
        Food food = new Food("avocado", 10, 10, 10);
        LocalDate date = ParserUtil.parseDate("7 Apr 2021");
        FoodIntake foodIntake = new FoodIntake(date, food);
        assertThrows(IndexOutOfBoundsException.class, () -> foodIntakeList.updateFoodIntake(10, foodIntake));
    }

    @Test
    public void updateFoodIntake_validIndex_success() throws ParseException {
        Food food = new Food("avocado", 10, 10, 10);
        LocalDate date = ParserUtil.parseDate("7 Apr 2021");
        FoodIntake foodIntake = new FoodIntake(date, food);
        foodIntakeList.addFoodIntake(foodIntake);

        Food newFood = new Food("avocado", 99, 99, 99);
        FoodIntake newFoodIntake = new FoodIntake(date, newFood);

        foodIntakeList.updateFoodIntake(0, newFoodIntake);

        assertTrue(foodIntakeList.getFoodIntakeList().get(0).getFood().getCarbos() == 99);
    }

    @Test
    public void getFoodIntakeCount_valid_success() throws ParseException {
        Food food = new Food("avocado", 10, 10, 10);
        LocalDate date = ParserUtil.parseDate("7 Apr 2021");
        FoodIntake foodIntake = new FoodIntake(date, food);
        foodIntakeList.addFoodIntake(foodIntake);
        foodIntakeList.addFoodIntake(foodIntake);
        foodIntakeList.addFoodIntake(foodIntake);
        int foodIntakeCount = foodIntakeList.getFoodIntakeItemCount(date, "avocado");
        assertTrue(foodIntakeCount == 3);
    }

    @Test
    public void getOriginalFoodName_valid_success() {
        assertTrue(foodIntakeList.getOriginalFoodName("avocado").equals("avocado"));
        assertTrue(foodIntakeList.getOriginalFoodName("avocado #4").equals("avocado"));
        assertTrue(foodIntakeList.getOriginalFoodName("7up").equals("7up"));
        assertTrue(foodIntakeList.getOriginalFoodName("banana 2").equals("banana 2"));
        assertTrue(foodIntakeList.getOriginalFoodName("banana 2 #5").equals("banana 2"));
    }

    @Test
    public void reorderDuplicateFoodNames_valid_success() throws ParseException {
        LocalDate date = ParserUtil.parseDate("7 Apr 2021");
        foodIntakeList.addFoodIntake(new FoodIntake(date, "cherries", 10, 10, 10));
        foodIntakeList.addFoodIntake(new FoodIntake(date, "cherries #3", 10, 10, 10));
        foodIntakeList.addFoodIntake(new FoodIntake(date, "cherries #4", 10, 10, 10));
        foodIntakeList.reorderDuplicateFoodNames(date, "cherries #3");

        String foodIntakeOne = foodIntakeList.getFoodIntakeList().get(0).getFood().getName();
        String foodIntakeTwo = foodIntakeList.getFoodIntakeList().get(1).getFood().getName();
        String foodIntakeThree = foodIntakeList.getFoodIntakeList().get(2).getFood().getName();

        assertTrue(foodIntakeOne.equals("cherries")
                && foodIntakeTwo.equals("cherries #2") && foodIntakeThree.equals("cherries #3"));
    }

    @Test
    public void resetToBlank_valid_success() throws ParseException {
        LocalDate date = ParserUtil.parseDate("7 Apr 2021");
        foodIntakeList.addFoodIntake(new FoodIntake(date, "cherries", 10, 10, 10));
        foodIntakeList.addFoodIntake(new FoodIntake(date, "cherries #3", 10, 10, 10));
        foodIntakeList.addFoodIntake(new FoodIntake(date, "cherries #4", 10, 10, 10));
        foodIntakeList.reorderDuplicateFoodNames(date, "cherries #3");

        foodIntakeList.resetToBlank();
        assertTrue(foodIntakeList.getFoodIntakeList().size() == 0);
    }
}
