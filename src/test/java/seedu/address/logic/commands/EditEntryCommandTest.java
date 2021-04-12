package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntries.getTypicalEntriesList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.entry.EntryDate;
import seedu.address.model.entry.TemporaryEntry;

/**
 * Contains integration tests (interaction with the Model) for {@code EditEntryCommand}.
 */
public class EditEntryCommandTest {
    private Model model = new ModelManager(getTypicalEntriesList(), new UserPrefs());

    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        Index index = Index.fromZeroBased(0);
        TemporaryEntry tempEntry = new TemporaryEntry();

        assertThrows(NullPointerException.class, () -> new EditEntryCommand(index, null));
        assertThrows(NullPointerException.class, () -> new EditEntryCommand(null, tempEntry));
        assertThrows(NullPointerException.class, () -> new EditEntryCommand(null, null));
    }

    @Test
    public void equals() {
        Index index0 = Index.fromZeroBased(0);
        Index index1 = Index.fromZeroBased(0);
        Index index2 = Index.fromZeroBased(1);
        TemporaryEntry tempEntry0 = new TemporaryEntry();
        TemporaryEntry tempEntry1 = new TemporaryEntry();

        EditEntryCommand editEntryCommand0 = new EditEntryCommand(index0, tempEntry0);
        EditEntryCommand editEntryCommand1 = new EditEntryCommand(index1, tempEntry0);
        EditEntryCommand editEntryCommand2 = new EditEntryCommand(index1, tempEntry1);
        EditEntryCommand editEntryCommand3 = new EditEntryCommand(index2, tempEntry0);

        assertEquals(editEntryCommand0, editEntryCommand1);
        assertEquals(editEntryCommand0, editEntryCommand2);
        assertNotEquals(editEntryCommand0, editEntryCommand3);
    }

    @Test
    public void execute_disjointEntries_commandSuccess() {
        // CLASS_MEETING 2022-02-01 15:00 2022-02-01 16:30
        EntryDate entryDate13 = new EntryDate("2020-02-01 13:00");
        EntryDate entryDate14 = new EntryDate("2020-02-01 14:00");
        EntryDate entryDate15 = new EntryDate("2020-02-01 15:00");

        // editing CONSULTATION
        Index target = Index.fromZeroBased(0);

        // totally disjoint
        TemporaryEntry tempEntry0 = new TemporaryEntry()
                .setEntryStartDate(entryDate13)
                .setEntryEndDate(entryDate14);

        try {
            new EditEntryCommand(target, tempEntry0).execute(model);
        } catch (CommandException e) {
            throw new AssertionError("Command should be executed successfully!");
        }

        // one's end date is another's start date
        TemporaryEntry tempEntry1 = new TemporaryEntry()
                .setEntryEndDate(entryDate15);

        try {
            new EditEntryCommand(target, tempEntry1).execute(model);
        } catch (CommandException e) {
            throw new AssertionError("Command should be executed successfully!");
        }
    }

    @Test
    public void execute_invalidEntryIndex_throwsCommandException() {
        // CLASS_MEETING 2022-02-01 15:00 2022-02-01 16:30
        EntryDate entryDate13 = new EntryDate("2022-02-01 13:00");
        EntryDate entryDate14 = new EntryDate("2022-02-01 14:00");

        // editing CONSULTATION
        Index invalidTarget = Index.fromOneBased(9);

        TemporaryEntry tempEntry = new TemporaryEntry()
                .setEntryStartDate(entryDate13)
                .setEntryEndDate(entryDate14);

        assertThrows(CommandException.class, () -> new EditEntryCommand(invalidTarget, tempEntry).execute(model));
    }

    @Test
    public void execute_invalidDateRange_throwsCommandException() {
        // CLASS_MEETING 2022-02-01 15:00 2022-02-01 16:30
        EntryDate entryDate13 = new EntryDate("2022-02-01 13:00");
        EntryDate entryDate14 = new EntryDate("2022-02-01 14:00");

        // editing CONSULTATION
        Index target = Index.fromZeroBased(0);

        // same date
        TemporaryEntry tempEntry = new TemporaryEntry()
                .setEntryStartDate(entryDate14)
                .setEntryEndDate(entryDate13);

        assertThrows(CommandException.class, () -> new EditEntryCommand(target, tempEntry).execute(model));
    }

    @Test
    public void execute_sameStartAndEndDate_throwsCommandException() {
        // CLASS_MEETING 2022-02-01 15:00 2022-02-01 16:30
        EntryDate entryDate14 = new EntryDate("2022-02-01 14:00");

        // editing CONSULTATION
        Index target = Index.fromZeroBased(0);

        // same date
        TemporaryEntry tempEntry = new TemporaryEntry()
                .setEntryStartDate(entryDate14)
                .setEntryEndDate(entryDate14);

        assertThrows(CommandException.class, () -> new EditEntryCommand(target, tempEntry).execute(model));
    }

    @Test
    public void execute_overlappingEntries_throwsCommandException() {
        // CLASS_MEETING 2022-02-01 15:00 2022-02-01 16:30
        EntryDate entryDate16 = new EntryDate("2022-02-01 16:00");
        EntryDate entryDate17 = new EntryDate("2022-02-01 17:00");

        // editing CONSULTATION
        Index target = Index.fromZeroBased(0);

        // totally disjoint
        TemporaryEntry tempEntry = new TemporaryEntry()
                .setEntryStartDate(entryDate16)
                .setEntryEndDate(entryDate17);

        assertThrows(CommandException.class, () -> new EditEntryCommand(target, tempEntry).execute(model));
    }
}
