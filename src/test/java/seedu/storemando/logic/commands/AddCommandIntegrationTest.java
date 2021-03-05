package seedu.storemando.logic.commands;

import static seedu.storemando.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.storemando.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.storemando.testutil.TypicalItems.getTypicalStoreMando;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.storemando.model.Model;
import seedu.storemando.model.ModelManager;
import seedu.storemando.model.UserPrefs;
import seedu.storemando.model.item.Item;
import seedu.storemando.testutil.ItemBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalStoreMando(), new UserPrefs());
    }

    @Test
    public void execute_newItem_success() {
        Item validItem = new ItemBuilder().build();

        Model expectedModel = new ModelManager(model.getStoreMando(), new UserPrefs());
        expectedModel.addItem(validItem);

        assertCommandSuccess(new AddCommand(validItem), model,
            String.format(AddCommand.MESSAGE_SUCCESS, validItem), expectedModel);
    }

    @Test
    public void execute_duplicateItem_throwsCommandException() {
        Item itemInList = model.getStoreMando().getItemList().get(0);
        assertCommandFailure(new AddCommand(itemInList), model, AddCommand.MESSAGE_DUPLICATE_ITEM);
    }

}
