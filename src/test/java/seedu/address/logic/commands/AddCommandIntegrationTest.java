package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGarments.getTypicalWardrobe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.garment.Garment;
import seedu.address.testutil.GarmentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalWardrobe(), new UserPrefs());
    }

    @Test
    public void execute_newGarment_success() {
        Garment validGarment = new GarmentBuilder().build();

        Model expectedModel = new ModelManager(model.getWardrobe(), new UserPrefs());
        expectedModel.addGarment(validGarment);

        assertCommandSuccess(new AddCommand(validGarment), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validGarment), expectedModel);
    }

    @Test
    public void execute_duplicateGarment_throwsCommandException() {
        Garment garmentInList = model.getWardrobe().getGarmentList().get(0);
        assertCommandFailure(new AddCommand(garmentInList), model, AddCommand.MESSAGE_DUPLICATE_GARMENT);
    }

}
