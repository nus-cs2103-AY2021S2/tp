package seedu.heymatez.logic.commands;

import static seedu.heymatez.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.heymatez.testutil.TypicalPersons.getTypicalHeyMatez;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.heymatez.model.Model;
import seedu.heymatez.model.ModelManager;
import seedu.heymatez.model.UserPrefs;
import seedu.heymatez.model.task.PriorityContainsKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindByPriorityCommand}.
 */
public class FindByPriorityCommandIntegrationTest {

    private Model model = new ModelManager(getTypicalHeyMatez(), new UserPrefs());

    @Test
    public void checkSuccessFinding() {

        Model expectedModel = new ModelManager(getTypicalHeyMatez(), new UserPrefs());

        ArrayList<String> newList = new ArrayList<>();
        newList.add("high");

        PriorityContainsKeywordPredicate predicate = new PriorityContainsKeywordPredicate(newList);
        expectedModel.updateFilteredTaskList(predicate);

        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW,
                expectedModel.getFilteredTaskList().size());

        assertCommandSuccess(new FindByPriorityCommand(predicate), model, expectedMessage, expectedModel);
    }

}
