package fooddiary.logic.commands;

import static fooddiary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static fooddiary.testutil.TypicalEntries.getTypicalFoodDiary;

import org.junit.jupiter.api.Test;

import fooddiary.model.FoodDiary;
import fooddiary.model.Model;
import fooddiary.model.ModelManager;
import fooddiary.model.UserPrefs;


public class ClearCommandTest {

    @Test
    public void execute_emptyFoodDiary_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyFoodDiary_success() {
        Model model = new ModelManager(getTypicalFoodDiary(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalFoodDiary(), new UserPrefs());
        expectedModel.setFoodDiary(new FoodDiary());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
