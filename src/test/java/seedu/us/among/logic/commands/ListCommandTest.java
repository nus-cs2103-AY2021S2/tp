package seedu.us.among.logic.commands;

import static seedu.us.among.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.us.among.logic.commands.CommandTestUtil.showEndpointAtIndex;
import static seedu.us.among.testutil.TypicalEndpoints.getTypicalEndpointList;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_FIRST_ENDPOINT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.us.among.model.EndpointList;
import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;
import seedu.us.among.model.UserPrefs;
import seedu.us.among.testutil.TypicalEndpoints;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;
    private Model emptyModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalEndpointList(), new UserPrefs());
        expectedModel = new ModelManager(model.getEndpointList(), new UserPrefs());
        emptyModel = new ModelManager(TypicalEndpoints.getTypicalEndpointList(), new UserPrefs());
        emptyModel.setEndpointList(new EndpointList());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model,
                ListCommand.MESSAGE_SUCCESS_WITH_FILLED_LIST, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showEndpointAtIndex(model, INDEX_FIRST_ENDPOINT);
        assertCommandSuccess(new ListCommand(), model,
                ListCommand.MESSAGE_SUCCESS_WITH_FILLED_LIST, expectedModel);
    }

    @Test
    public void execute_listIsEmpty_displayEmptyMessage() {
        assertCommandSuccess(new ListCommand(), emptyModel,
                ListCommand.MESSAGE_SUCCESS_WITH_EMPTY_LIST, emptyModel);
    }

}
