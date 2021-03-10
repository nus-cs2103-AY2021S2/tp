package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.model.DisplayFilterPredicate;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class FilterCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_modelUpdated_success() throws Exception {
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(PREFIX_ADDRESS, "");

        DisplayFilterPredicate displayFilterPredicate = new DisplayFilterPredicate(
                argumentMultimap);

        FilterCommand filterCommand = new FilterCommand(displayFilterPredicate);

        // before executing
        assertNotEquals(model.getDisplayFilter(), displayFilterPredicate);

        filterCommand.execute(model);

        // after executing
        assertEquals(model.getDisplayFilter(), displayFilterPredicate);
    }
}
