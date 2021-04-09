package seedu.address.logic.commands.remindercommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showReminderAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalModel.ModelType.REMINDERTRACKER;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.testutil.TypicalModel;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListReminderCommand.
 */
public class ListReminderCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = TypicalModel.getTypicalModel();
        expectedModel = TypicalModel.getTypicalModel(model, REMINDERTRACKER);
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListReminderCommand(), model,
                ListReminderCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showReminderAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListReminderCommand(), model,
                ListReminderCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
