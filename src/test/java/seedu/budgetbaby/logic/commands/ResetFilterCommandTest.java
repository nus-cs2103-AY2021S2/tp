package seedu.budgetbaby.logic.commands;

import org.junit.jupiter.api.Test;
//import seedu.budgetbaby.ablogic.commands.FindCommand;
//import seedu.budgetbaby.model.BudgetBabyModel;
//import seedu.budgetbaby.model.BudgetBabyModelManager;
//import seedu.budgetbaby.model.ReadOnlyBudgetTracker;
//import seedu.budgetbaby.model.ReadOnlyUserPrefs;
//import seedu.budgetbaby.model.UserPrefs;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static seedu.budgetbaby.ablogic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.budgetbaby.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
//import static seedu.budgetbaby.testutil.TypicalPersons.*;

/**
 * Contains integration tests (interaction with the Model) for {@code ResetFilterCommand}.
 */
public class ResetFilterCommandTest {
//    private BudgetBabyModel model = new BudgetBabyModelManager(getTypicalAddressBook(), new UserPrefs());
//    private BudgetBabyModel expectedModel = new BudgetBabyModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ResetFilterCommand rfc = new ResetFilterCommand();


    }

//    @Test
//    public void execute_withoutKeyword() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
//        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
//        ResetFilterCommand command = new ResetFilterCommand();
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
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
}
