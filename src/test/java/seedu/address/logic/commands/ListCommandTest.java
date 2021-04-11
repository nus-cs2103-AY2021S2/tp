package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.address.logic.commands.ListCommand.MESSAGE_LIST_FAV_SUCCESS;
import static seedu.address.logic.commands.ListCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_FAVOURITE;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new AppointmentBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new AppointmentBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);
        assertCommandSuccess(new ListCommand(), model, MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listFav_success() {
        ListCommand listFavCommand = new ListCommand(OPTION_FAVOURITE);
        expectedModel.updateFilteredContactList(contact -> contact.getFavourite().isFav());
        assertCommandSuccess(listFavCommand, model, MESSAGE_LIST_FAV_SUCCESS, expectedModel);
    }
}
