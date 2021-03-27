package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.PriorityContainsKeywordPredicate;

public class FindByPriorityCommandIntegrationTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void checkSuccessFinding() {

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        ArrayList<String> newList = new ArrayList<>();
        newList.add("high");

        PriorityContainsKeywordPredicate predicate = new PriorityContainsKeywordPredicate(newList);
        expectedModel.updateFilteredTaskList(predicate);

        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW,
                expectedModel.getFilteredTaskList().size());

        assertCommandSuccess(new FindByPriorityCommand(predicate), model, expectedMessage, expectedModel);
    }

}
