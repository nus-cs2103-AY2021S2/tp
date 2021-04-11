package seedu.address.logic.commands.schedulecommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showScheduleAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalModel.ModelType.SCHEDULETRACKER;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.testutil.TypicalModel;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListScheduleCommand.
 */
public class ListScheduleCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = TypicalModel.getTypicalModel();
        expectedModel = TypicalModel.getTypicalModel(model, SCHEDULETRACKER);
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListScheduleCommand(), model, String.format(ListScheduleCommand.MESSAGE_SUCCESS,
                model.getFilteredScheduleList().size()), expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showScheduleAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListScheduleCommand(), model, String.format(ListScheduleCommand.MESSAGE_SUCCESS,
                expectedModel.getFilteredScheduleList().size()), expectedModel);
    }
}
