package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAliases.getTypicalAliases;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class SelectShowCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalAliases());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                model.getAliases());
    }

    @Test
    public void execute_showEmpty_success() {
        expectedModel.applySelectedPredicate();
        assertCommandSuccess(new SelectShowCommand(), model, SelectShowCommand.MESSAGE_SUCCESS,
                expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_selectAllShowAll_success() {
        model.updateSelectedPersonList(model.getFilteredPersonList());
        expectedModel.updateSelectedPersonList(expectedModel.getFilteredPersonList());
        expectedModel.applySelectedPredicate();
        assertCommandSuccess(new SelectShowCommand(), model, SelectShowCommand.MESSAGE_SUCCESS,
                expectedModel);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_selectOneShowOne_success() {
        List<Person> oneItemList = new ArrayList<>();
        oneItemList
                .add(model.getFilteredPersonList().get(model.getFilteredPersonList().size() - 1));
        model.updateSelectedPersonList(oneItemList);
        expectedModel.updateSelectedPersonList(oneItemList);
        expectedModel.applySelectedPredicate();
        assertCommandSuccess(new SelectShowCommand(), model, SelectShowCommand.MESSAGE_SUCCESS,
                expectedModel);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }
}
