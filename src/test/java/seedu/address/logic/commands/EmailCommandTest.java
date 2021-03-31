package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAliases.getTypicalAliases;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.VALID_INDEXES;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class EmailCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalAliases());

    @Test
    public void initialize_nullIndexes_throwsException() {
        assertThrows(NullPointerException.class, () -> new EmailCommand(null));
    }

    @Test
    public void execute_outOfBoundsIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EmailCommand emailCommand = new EmailCommand(Collections.singletonList(outOfBoundIndex));
        assertCommandFailure(emailCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
        EmailCommand emailCommand = new EmailCommand(Collections.singletonList(outOfBoundIndex));
        assertCommandFailure(emailCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        List<Index> validIndexes = new ArrayList<>(VALID_INDEXES);
        List<Index> validIndexesReversed = new ArrayList<>(VALID_INDEXES);
        Collections.reverse(validIndexesReversed);

        // same indexes -> equals
        assertEquals(new EmailCommand(validIndexes), new EmailCommand(validIndexes));

        // same indexes, different order -> equals
        assertEquals(new EmailCommand(validIndexes), new EmailCommand(validIndexesReversed));

        // different instance, same values -> equals
        assertEquals(new EmailCommand(), new EmailCommand());

        // different command -> not equals
        assertNotEquals(new EmailCommand(), new ListCommand());

        // different indexes -> not equals
        assertNotEquals(new EmailCommand(), new EmailCommand(validIndexes));

        // different types -> not equals
        assertNotEquals(null, new EmailCommand());
        assertNotEquals(1, new EmailCommand());
    }

}
