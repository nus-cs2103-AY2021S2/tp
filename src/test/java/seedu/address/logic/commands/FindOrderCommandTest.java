package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_COMPLETE_STATUS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDERS;
import static seedu.address.testutil.TypicalModels.getTypicalAddressBook;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;
import seedu.address.model.order.predicates.OrderCheeseTypePredicate;
import seedu.address.model.order.predicates.OrderCompletionStatusPredicate;
import seedu.address.model.util.predicate.CompositeFieldPredicateBuilder;
import seedu.address.model.util.predicate.FieldPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FindOrderCommand.
 */
public class FindOrderCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPanelToOrderList();
    }

    @Test
    public void execute_listIsNotFiltered_showsEverything() {
        assertCommandSuccess(
                new FindOrderCommand(PREDICATE_SHOW_ALL_ORDERS),
                model,
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, expectedModel.getFilteredOrderList().size()),
                expectedModel
        );
    }

    @Test
    public void execute_listIsFilteredByCheeseTypeOnly() {
        List<String> cheeseTypeKeywords = Arrays.asList("brie", "feta");
        OrderCheeseTypePredicate predicate = new OrderCheeseTypePredicate(cheeseTypeKeywords);
        expectedModel.updateFilteredOrderList(predicate);

        assertCommandSuccess(
                new FindOrderCommand(predicate),
                model,
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, expectedModel.getFilteredOrderList().size()),
                expectedModel
        );
    }

    @Test
    public void execute_listIsFiltered_byCompletionStatusOnly() {
        OrderCompletionStatusPredicate predicate = new OrderCompletionStatusPredicate(VALID_ORDER_COMPLETE_STATUS);
        expectedModel.updateFilteredOrderList(predicate);
        assertCommandSuccess(
                new FindOrderCommand(predicate),
                model,
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, expectedModel.getFilteredOrderList().size()),
                expectedModel
        );
    }

    @Test
    public void execute_listIsFiltered_byCheeseTypeAndCompletionStatus() {
        List<String> cheeseTypeKeywords = Arrays.asList("brie", "feta");
        OrderCheeseTypePredicate typePredicate = new OrderCheeseTypePredicate(cheeseTypeKeywords);
        OrderCompletionStatusPredicate statusPredicate =
                new OrderCompletionStatusPredicate(VALID_ORDER_COMPLETE_STATUS);

        CompositeFieldPredicateBuilder<Order> pBuilder = new CompositeFieldPredicateBuilder<>();
        pBuilder.compose(typePredicate);
        pBuilder.compose(statusPredicate);
        FieldPredicate<Order> predicate = pBuilder.build();

        expectedModel.updateFilteredOrderList(predicate);
        assertCommandSuccess(
                new FindOrderCommand(predicate),
                model,
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, expectedModel.getFilteredOrderList().size()),
                expectedModel
        );
    }
}
