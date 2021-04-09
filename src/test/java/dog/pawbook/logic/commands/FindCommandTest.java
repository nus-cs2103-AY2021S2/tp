package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_ENTITIES_LISTED_OVERVIEW;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.testutil.TypicalEntities.CARL;
import static dog.pawbook.testutil.TypicalEntities.ELLE;
import static dog.pawbook.testutil.TypicalEntities.FIONA;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.NameContainsKeywordsPredicate;
import javafx.util.Pair;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different owner -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noOwnerFound() {
        String expectedMessage = String.format(MESSAGE_ENTITIES_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEntityList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEntityList());
    }

    @Test
    public void execute_multipleKeywords_multipleOwnersFound() {
        String expectedMessage = String.format(MESSAGE_ENTITIES_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEntityList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        List<Entity> testList = model.getFilteredEntityList().stream()
                .map(Pair::getValue)
                .collect(toList());
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), testList);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
