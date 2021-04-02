package seedu.address.logic.commands.findcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRemindMe.getTypicalRemindMe;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;
import seedu.address.model.module.TitleContainsKeywordsPredicate;


public class FindModuleCommandTest {
    private final Model model = new ModelManager(getTypicalRemindMe(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalRemindMe(), new UserPrefs());

    @Test
    public void equals() {
        TitleContainsKeywordsPredicate firstPredicate =
                new TitleContainsKeywordsPredicate(Collections.singletonList("first"));
        TitleContainsKeywordsPredicate secondPredicate =
                new TitleContainsKeywordsPredicate(List.of("second"));

        FindModuleCommand findFirstModule = new FindModuleCommand(firstPredicate);
        FindModuleCommand findSecondModule = new FindModuleCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstModule.equals(findFirstModule));

        // same attributes -> returns true
        FindModuleCommand findFirstModuleCopy = new FindModuleCommand(firstPredicate);
        assertTrue(findFirstModule.equals(findFirstModuleCopy));

        // different types -> return false
        assertFalse(findFirstModule.equals(1));

        // null -> returns false
        assertFalse(findSecondModule.equals(null));

        // different predicates -> returns false
        assertFalse(findFirstModule.equals(findSecondModule));
    }

    @Test
    public void execute_zeroKeywords_noModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULE_LISTED_OVERVIEW, 0);
        TitleContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindModuleCommand command = new FindModuleCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_multipleKeywords_multipleModulesFound() {
        String expectedMessage = String.format(MESSAGE_MODULE_LISTED_OVERVIEW, 2);
        TitleContainsKeywordsPredicate predicate = preparePredicate("1 2 mod");
        FindModuleCommand command = new FindModuleCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        Module mod1 = new Module(new Title("MOD 1"));
        Module mod2 = new Module(new Title("MOD 2"));
        assertEquals(Arrays.asList(mod1, mod2), model.getFilteredModuleList());
    }

    @Test
    public void execute_throws() {
        TitleContainsKeywordsPredicate predicate = preparePredicate("mod");
        FindModuleCommand command = new FindModuleCommand(predicate);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    /**
     * Parses {@code userInput} into a {@code TitleContainsKeywordsPredicate}.
     */
    private TitleContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TitleContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
