package seedu.address.model.food;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class FoodIntakeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FoodIntake(null, null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() throws ParseException {
        String invalidName = "Chicken rice #2";
        LocalDate date = ParserUtil.parseDate("7 Apr 2021");
        Food invalidFoodName = new Food("Chicken rice", 10, 10, 10);
        invalidFoodName.setName(invalidName);
        assertThrows(IllegalArgumentException.class, () -> new FoodIntake(date, invalidFoodName));
    }

    @Test
    public void loadFromFileConstructor_validName_writtenCorrectly() throws ParseException {
        String validFoodName = "Chicken rice #2";
        LocalDate date = ParserUtil.parseDate("7 Apr 2021");
        FoodIntake foodIntake = new FoodIntake(date, validFoodName, 10, 10, 10);
        assertTrue("Chicken rice #2" == foodIntake.getFood().getName());
    }
}
