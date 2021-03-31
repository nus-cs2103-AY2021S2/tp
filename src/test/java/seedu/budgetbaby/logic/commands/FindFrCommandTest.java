//package seedu.budgetbaby.logic.commands;
//
//import org.junit.jupiter.api.Test;
//import seedu.budgetbaby.ablogic.commands.FindCommand;
//import seedu.budgetbaby.abmodel.person.NameContainsKeywordsPredicate;
//import seedu.budgetbaby.model.BudgetBabyModel;
//import seedu.budgetbaby.model.BudgetBabyModelManager;
//import seedu.budgetbaby.model.ReadOnlyBudgetTracker;
//import seedu.budgetbaby.model.ReadOnlyUserPrefs;
//import seedu.budgetbaby.model.UserPrefs;
//import seedu.budgetbaby.model.record.Amount;
//import seedu.budgetbaby.model.record.Category;
//import seedu.budgetbaby.model.record.Description;
//import seedu.budgetbaby.model.record.FinancialRecord;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.function.Predicate;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static seedu.budgetbaby.ablogic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.budgetbaby.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
//import static seedu.budgetbaby.testutil.TypicalPersons.*;
//
///**
// * Contains integration tests (interaction with the Model) for {@code FindFrCommand}.
// */
//public class FindFrCommandTest {
//    private BudgetBabyModel model = new BudgetBabyModelManager(getTypicalAddressBook(), new UserPrefs());
//    private BudgetBabyModel expectedModel = new BudgetBabyModelManager(getTypicalAddressBook(), new UserPrefs());
//
//    @Test
//    public void equals() {
//        Description description = new Description("Breakfast");
//        Amount amount = new Amount("5");
//        Set<Category> categories = new HashSet<>(Collections.singletonList(new Category("Food")));
//
//
//        FindFrCommand findFirstCommand = new FindFrCommand(description, amount, categories);
//        FindFrCommand findSecondCommand = new FindFrCommand(new Description("Lunch"), amount, categories);
//
//        // same object -> returns true
//        assertTrue(findFirstCommand.equals(findFirstCommand));
//
//        // same values -> returns true
//        FindFrCommand findFirstCommandCopy = new FindFrCommand(description, amount, categories);
//        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(findFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(findFirstCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(findFirstCommand.equals(findSecondCommand));
//    }
//
//    @Test
//    public void execute_withoutKeyword() {
//        String expectedMessage = "Failed to execute";
//        Predicate<FinancialRecord> predicate = fr -> true;
//        FindFrCommand command = new FindFrCommand(description, amount, categories);
//        expectedModel.updateFilteredFinancialRecordList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(model.getFilteredFinancialRecordList(), model.getFilteredFinancialRecordList());
//    }
//
//    @Test
//    public void execute_withKeyword() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
//        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
//        FindCommand command = new FindCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
//    }
//
//    /**
//     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
//     */
//    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
//        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
//    }
//}
