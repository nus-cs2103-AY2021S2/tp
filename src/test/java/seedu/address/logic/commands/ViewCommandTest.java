package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGarments.getTypicalWardrobe;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GARMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_GARMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_GARMENT;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.garment.Garment;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewCommand}.
 */
public class ViewCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalWardrobe(), new UserPrefs());
        expectedModel = new ModelManager(model.getWardrobe(), new UserPrefs());
    }

    @Test
    public void execute_validIndex_success() {
        List<Garment> garmentList = new ArrayList<>();
        Garment upper = model.getFilteredGarmentList().get(INDEX_FIRST_GARMENT.getZeroBased());
        Garment lower = model.getFilteredGarmentList().get(INDEX_SECOND_GARMENT.getZeroBased());
        Garment footwear = model.getFilteredGarmentList().get(INDEX_THIRD_GARMENT.getZeroBased());
        garmentList.add(upper);
        garmentList.add(lower);
        garmentList.add(footwear);

        Predicate<Garment> predicateViewGarments = garment -> garmentList.contains(garment);
        expectedModel.updateFilteredGarmentList(predicateViewGarments);

        List<Index> indexList = new ArrayList<>();
        indexList.add(INDEX_FIRST_GARMENT);
        indexList.add(INDEX_SECOND_GARMENT);
        indexList.add(INDEX_THIRD_GARMENT);

        ViewCommand viewCommand = new ViewCommand(indexList);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_GARMENT_SUCCESS);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        List<Index> indexList = new ArrayList<>();
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGarmentList().size() + 1);
        indexList.add(INDEX_FIRST_GARMENT);
        indexList.add(INDEX_SECOND_GARMENT);
        indexList.add(outOfBoundIndex);

        ViewCommand viewCommand = new ViewCommand(indexList);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_GARMENT_DISPLAYED_INDEX);
    }
}
