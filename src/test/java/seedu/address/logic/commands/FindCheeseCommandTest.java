package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_ASSIGNED_STATUS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CHEESES;
import static seedu.address.testutil.TypicalModels.getTypicalAddressBook;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.predicates.CheeseAssignmentStatusPredicate;
import seedu.address.model.cheese.predicates.CheeseCheeseTypePredicate;
import seedu.address.model.util.predicate.CompositeFieldPredicateBuilder;
import seedu.address.model.util.predicate.FieldPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FindCheeseCommand.
 */
public class FindCheeseCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPanelToCheeseList();
    }

    @Test
    public void execute_listIsNotFiltered_showsEverything() {
        assertCommandSuccess(
                new FindCheeseCommand(PREDICATE_SHOW_ALL_CHEESES),
                model,
                String.format(Messages.MESSAGE_CHEESES_LISTED_OVERVIEW, expectedModel.getFilteredCheeseList().size()),
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
                String.format(Messages.MESSAGE_CHEESES_LISTED_OVERVIEW, expectedModel.getFilteredCheeseList().size()),
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
                String.format(Messages.MESSAGE_CHEESES_LISTED_OVERVIEW, expectedModel.getFilteredCheeseList().size()),
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
                String.format(Messages.MESSAGE_CHEESES_LISTED_OVERVIEW, expectedModel.getFilteredCheeseList().size()),
                expectedModel
        );
    }

}
