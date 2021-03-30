package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalHeyMatez;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.PriorityContainsKeywordPredicate;

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
