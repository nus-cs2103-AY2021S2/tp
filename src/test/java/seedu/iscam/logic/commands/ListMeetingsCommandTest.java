package seedu.iscam.logic.commands;

import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.iscam.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.iscam.testutil.TypicalClients.getTypicalClientBook;
import static seedu.iscam.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.iscam.testutil.TypicalMeetings.ALICE_1;
import static seedu.iscam.testutil.TypicalMeetings.ALICE_2;
import static seedu.iscam.testutil.TypicalMeetings.BENSON_1;
import static seedu.iscam.testutil.TypicalMeetings.CARL_1;
import static seedu.iscam.testutil.TypicalMeetings.DANIEL_1;
import static seedu.iscam.testutil.TypicalMeetings.ELLE_1;
import static seedu.iscam.testutil.TypicalMeetings.FIONA_1;
import static seedu.iscam.testutil.TypicalMeetings.FIONA_2;
import static seedu.iscam.testutil.TypicalMeetings.getTypicalMeetingBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.iscam.model.Model;
import seedu.iscam.model.ModelManager;
import seedu.iscam.model.user.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListMeetingsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalClientBook(), getTypicalMeetingBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getClientBook(), model.getMeetingBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListMeetingsCommand(), model, ListMeetingsCommand.MESSAGE_SUCCESS
                + "\n" + ALICE_1 + "\n" + ALICE_2 + "\n" + BENSON_1
                + "\n" + DANIEL_1 + "\n" + ELLE_1 + "\n" + FIONA_1
                + "\n" + FIONA_2 + "\n" + CARL_1 + "\n", expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showMeetingAtIndex(model, INDEX_FIRST_ITEM);
        expectedModel = new ModelManager(model.getClientBook(), model.getMeetingBook(), new UserPrefs());
        assertCommandSuccess(new ListMeetingsCommand(), model, ListMeetingsCommand.MESSAGE_SUCCESS
                + "\n" + ALICE_1 + "\n" + ALICE_2 + "\n" + BENSON_1
                + "\n" + DANIEL_1 + "\n" + ELLE_1 + "\n" + FIONA_1
                + "\n" + FIONA_2 + "\n" + CARL_1 + "\n", expectedModel);
    }
}
