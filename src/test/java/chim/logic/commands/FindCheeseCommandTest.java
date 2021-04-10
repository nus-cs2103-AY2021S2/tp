package chim.logic.commands;

import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_ASSIGNED_STATUS;
import static chim.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chim.model.Model.PREDICATE_SHOW_ALL_CHEESES;
import static chim.testutil.TypicalModels.getTypicalChim;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chim.commons.core.Messages;
import chim.model.Model;
import chim.model.ModelManager;
import chim.model.UserPrefs;
import chim.model.cheese.Cheese;
import chim.model.cheese.predicates.CheeseAssignmentStatusPredicate;
import chim.model.cheese.predicates.CheeseCheeseTypePredicate;
import chim.model.util.predicate.CompositeFieldPredicateBuilder;
import chim.model.util.predicate.FieldPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FindCheeseCommand.
 */
public class FindCheeseCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalChim(), new UserPrefs());
        expectedModel = new ModelManager(model.getChim(), new UserPrefs());
        expectedModel.setPanelToCheeseList();
    }

    @Test
    public void execute_listIsNotFiltered_showsEverything() {
        assertCommandSuccess(
                new FindCheeseCommand(PREDICATE_SHOW_ALL_CHEESES),
                model,
                String.format(Messages.MESSAGE_CHEESES_FOUND_OVERVIEW,
                        expectedModel.getFilteredCheeseList().size(),
                        PREDICATE_SHOW_ALL_CHEESES),
                expectedModel
        );
    }

    @Test
    public void execute_listIsFiltered_byCheeseTypeOnly() {
        List<String> cheeseTypeKeywords = Arrays.asList("brie", "feta");
        CheeseCheeseTypePredicate predicate = new CheeseCheeseTypePredicate(cheeseTypeKeywords);
        expectedModel.updateFilteredCheeseList(predicate);
        assertCommandSuccess(
                new FindCheeseCommand(predicate),
                model,
                String.format(Messages.MESSAGE_CHEESES_FOUND_OVERVIEW,
                        expectedModel.getFilteredCheeseList().size(),
                        predicate),
                expectedModel
        );
    }

    @Test
    public void execute_listIsFiltered_byAssignmentStatusOnly() {
        CheeseAssignmentStatusPredicate predicate = new CheeseAssignmentStatusPredicate(VALID_CHEESE_ASSIGNED_STATUS);
        expectedModel.updateFilteredCheeseList(predicate);
        assertCommandSuccess(
                new FindCheeseCommand(predicate),
                model,
                String.format(Messages.MESSAGE_CHEESES_FOUND_OVERVIEW,
                        expectedModel.getFilteredCheeseList().size(),
                        predicate),
                expectedModel
        );
    }

    @Test
    public void execute_listIsFiltered_byCheeseTypeAndAssignmentStatus() {
        List<String> cheeseTypeKeywords = Arrays.asList("brie", "feta");
        CheeseCheeseTypePredicate typePredicate = new CheeseCheeseTypePredicate(cheeseTypeKeywords);
        CheeseAssignmentStatusPredicate statusPredicate =
                new CheeseAssignmentStatusPredicate(VALID_CHEESE_ASSIGNED_STATUS);

        CompositeFieldPredicateBuilder<Cheese> pBuilder = new CompositeFieldPredicateBuilder<>();
        pBuilder.compose(typePredicate);
        pBuilder.compose(statusPredicate);
        FieldPredicate<Cheese> predicate = pBuilder.build();

        expectedModel.updateFilteredCheeseList(predicate);
        assertCommandSuccess(
                new FindCheeseCommand(predicate),
                model,
                String.format(Messages.MESSAGE_CHEESES_FOUND_OVERVIEW,
                        expectedModel.getFilteredCheeseList().size(),
                        predicate),
                expectedModel
        );
    }

}
