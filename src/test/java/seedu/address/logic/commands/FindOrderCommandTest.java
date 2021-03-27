package seedu.address.logic.commands;

import static seedu.address.commons.util.StringUtil.splitToKeywordsList;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_BRIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_COMPLETE_STATUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDERS;
import static seedu.address.testutil.TypicalModels.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.customer.Customer;
import seedu.address.model.order.Order;
import seedu.address.model.order.predicates.OrderCheeseTypePredicate;
import seedu.address.model.order.predicates.OrderCompletionStatusPredicate;
import seedu.address.model.order.predicates.OrderNamePredicate;
import seedu.address.model.order.predicates.OrderPhonePredicate;
import seedu.address.model.util.predicate.CompositeFieldPredicateBuilder;
import seedu.address.model.util.predicate.FieldPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FindOrderCommand.
 */
public class FindOrderCommandTest {

    private Model model;
    private Model expectedModel;
    private List<Customer> customerList;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPanelToOrderList();
        customerList = model.getCompleteCustomerList();
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
        List<String> cheeseTypeKeywords = splitToKeywordsList(VALID_CHEESE_TYPE_BRIE);
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
    public void execute_listIsFiltered_byCustomerNameOnly() {
        List<String> nameKeywords = splitToKeywordsList(VALID_NAME_AMY);
        OrderNamePredicate predicate = new OrderNamePredicate(nameKeywords, customerList);
        expectedModel.updateFilteredOrderList(predicate);
        assertCommandSuccess(
                new FindOrderCommand(predicate),
                model,
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, expectedModel.getFilteredOrderList().size()),
                expectedModel
        );
    }

    @Test
    public void execute_listIsFiltered_byCustomerPhoneOnly() {
        List<String> phoneKeywords = splitToKeywordsList(VALID_PHONE_AMY);
        OrderPhonePredicate predicate = new OrderPhonePredicate(phoneKeywords, customerList);
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
        FieldPredicate<Order> predicate = new CompositeFieldPredicateBuilder<Order>()
                .compose(new OrderCheeseTypePredicate(splitToKeywordsList(VALID_CHEESE_TYPE_BRIE)))
                .compose(new OrderCompletionStatusPredicate(VALID_ORDER_COMPLETE_STATUS))
                .compose(new OrderNamePredicate(splitToKeywordsList(VALID_NAME_AMY), customerList))
                .compose(new OrderPhonePredicate(splitToKeywordsList(VALID_PHONE_AMY), customerList))
                .build();

        expectedModel.updateFilteredOrderList(predicate);
        assertCommandSuccess(
                new FindOrderCommand(predicate),
                model,
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, expectedModel.getFilteredOrderList().size()),
                expectedModel
        );
    }
}
