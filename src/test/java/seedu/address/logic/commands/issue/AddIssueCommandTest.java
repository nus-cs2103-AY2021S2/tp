package seedu.address.logic.commands.issue;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.issue.Issue;
import seedu.address.testutil.issue.IssueBuilder;

public class AddIssueCommandTest {
    @Test
    public void constructor_nullIssue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddIssueCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() throws Exception {
        Issue validIssue = new IssueBuilder().build();

        assertThrows(NullPointerException.class, () -> new AddIssueCommand(validIssue).execute(null));
    }

    @Test
    public void execute_issueAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingIssueAdded modelStub = new ModelStubAcceptingIssueAdded();
        Issue validIssue = new IssueBuilder().build();

        CommandResult commandResult = new AddIssueCommand(validIssue).execute(modelStub);

        assertEquals(String.format(AddIssueCommand.MESSAGE_SUCCESS, validIssue), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validIssue), modelStub.issuesAdded);
    }

    @Test
    public void execute_duplicateIssue_throwsCommandException() {
        // First we create a issue and add that to the command. The command will eventually try to execute
        // by adding this issue to the model
        Issue validIssue = new IssueBuilder().build();
        AddIssueCommand addIssueCommand = new AddIssueCommand(validIssue);

        // We create a model containing the same issue the command is going to try to add to the model
        ModelStub modelStub = new ModelStubWithIssue(validIssue);

        // We see that (hopefully) the command raises an exception because the model already contains this issue
        assertThrows(CommandException.class,
                AddIssueCommand.MESSAGE_DUPLICATE_ISSUE, () -> addIssueCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Issue issue1 = new IssueBuilder()
                .withRoomNumber("10-011")
                .build();
        Issue issue2 = new IssueBuilder()
                .withRoomNumber("11-110")
                .build();

        AddIssueCommand addIssue1Command = new AddIssueCommand(issue1);
        AddIssueCommand addIssue2Command = new AddIssueCommand(issue2);

        // same object -> returns true
        assertTrue(addIssue1Command.equals(addIssue1Command));

        // same values -> returns true
        AddIssueCommand addIssue1CommandCopy = new AddIssueCommand(issue1);
        assertTrue(addIssue1Command.equals(addIssue1CommandCopy));

        // different types -> returns false
        assertFalse(addIssue1Command.equals(1));

        // null -> returns false
        assertFalse(addIssue1Command.equals(null));

        // different issue -> return false
        assertFalse(addIssue1Command.equals(addIssue2Command));
    }

    /**
     * A Model stub that contains a single issue.
     */
    private class ModelStubWithIssue extends ModelStub {
        private final Issue issue;

        ModelStubWithIssue(Issue issue) {
            requireNonNull(issue);
            this.issue = issue;
        }

        @Override
        public boolean hasIssue(Issue issue) {
            requireNonNull(issue);
            return this.issue.equals(issue);
        }

        @Override
        public boolean hasRoom(seedu.address.model.room.RoomNumber roomNumber) {
            return true;
        }
    }

    /**
     * A Model stub that always accepts the issue being added.
     */
    private class ModelStubAcceptingIssueAdded extends ModelStub {
        final ArrayList<Issue> issuesAdded = new ArrayList<>();

        @Override
        public boolean hasIssue(Issue issue) {
            requireNonNull(issue);
            return issuesAdded.stream().anyMatch(issue::equals);
        }

        @Override
        public void addIssue(Issue issue) {
            requireNonNull(issue);
            issuesAdded.add(issue);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public void commitAddressBook() {

        }

        @Override
        public boolean hasRoom(seedu.address.model.room.RoomNumber roomNumber) {
            return true;
        }
    }
}
