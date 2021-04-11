package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCommandAliases.getTypicalAliasMap;
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
            getTypicalAliasMap());

    @Test
    public void initialize_nullIndexes_throwsException() {
        assertThrows(NullPointerException.class, () -> EmailCommand.buildEmailIndexCommand(null));
    }

    @Test
    public void execute_outOfBoundsIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EmailCommand emailCommand = EmailCommand
                .buildEmailIndexCommand(Collections.singletonList(outOfBoundIndex));
        assertCommandFailure(emailCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
        EmailCommand emailCommand = EmailCommand
                .buildEmailIndexCommand(Collections.singletonList(outOfBoundIndex));
        assertCommandFailure(emailCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        List<Index> validIndexesReversed = new ArrayList<>(VALID_INDEXES);
        Collections.reverse(validIndexesReversed);

        // same indexes -> equals
        assertEquals(EmailCommand.buildEmailIndexCommand(VALID_INDEXES),
                EmailCommand.buildEmailIndexCommand(VALID_INDEXES));

        // same indexes, different order -> equals
        assertEquals(EmailCommand.buildEmailIndexCommand(VALID_INDEXES),
                EmailCommand.buildEmailIndexCommand(validIndexesReversed));

        // different indexes - not equals
        assertNotEquals(
                EmailCommand.buildEmailIndexCommand(Collections.singletonList(INDEX_FIRST_PERSON)),
                EmailCommand.buildEmailIndexCommand(VALID_INDEXES));

        // different instance, same values -> equals
        assertEquals(EmailCommand.buildEmailShownCommand(), EmailCommand.buildEmailShownCommand());
        assertEquals(EmailCommand.buildEmailSelectedCommand(),
                EmailCommand.buildEmailSelectedCommand());

        // different command -> not equals
        assertNotEquals(EmailCommand.buildEmailShownCommand(), new ListCommand());

        // different indexes -> not equals
        assertNotEquals(EmailCommand.buildEmailIndexCommand(VALID_INDEXES),
                EmailCommand
                        .buildEmailIndexCommand(Collections.singletonList(INDEX_SECOND_PERSON)));

        // different types -> not equals
        assertNotEquals(null, EmailCommand.buildEmailShownCommand());
        assertNotEquals(1, EmailCommand.buildEmailShownCommand());
    }

}
