package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RESIDENCES;
import static seedu.address.testutil.TypicalResidences.getTypicalResidenceTracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.residence.Residence;
import seedu.address.testutil.ResidenceBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalResidenceTracker(), new UserPrefs());
    }

    @Test
    public void execute_newResidence_success() {
        Residence validResidence = new ResidenceBuilder().build();

        Model expectedModel = new ModelManager(model.getResidenceTracker(), new UserPrefs());
        expectedModel.addResidence(validResidence);
        expectedModel.updateFilteredResidenceList(PREDICATE_SHOW_ALL_RESIDENCES);

        assertCommandSuccess(new AddCommand(validResidence), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validResidence), expectedModel);
    }

    @Test
    public void execute_duplicateResidence_throwsCommandException() {
        Residence residenceInList = model.getResidenceTracker().getResidenceList().get(0);
        assertCommandFailure(new AddCommand(residenceInList), model, AddCommand.MESSAGE_DUPLICATE_RESIDENCE);
    }

}
