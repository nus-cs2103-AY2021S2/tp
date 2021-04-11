package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.OPTION_DARK;
import static seedu.address.logic.parser.CliSyntax.OPTION_LIGHT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ThemeCommandTest {

    @Test
    public void execute_validOption_success() {
        //light
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.setTheme(OPTION_LIGHT);
        assertCommandSuccess(new ThemeCommand(OPTION_LIGHT), model, ThemeCommand.MESSAGE_SUCCESS, expectedModel);

        //dark
        model = new ModelManager();
        expectedModel = new ModelManager();
        expectedModel.setTheme(OPTION_DARK);
        assertCommandSuccess(new ThemeCommand(OPTION_DARK), model, ThemeCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
