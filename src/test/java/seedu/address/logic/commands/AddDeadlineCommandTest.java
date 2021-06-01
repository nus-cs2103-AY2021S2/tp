package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.testutil.DeadlineBuilder;

public class AddDeadlineCommandTest {

    private Model model;
    private Deadline deadlineToAdd;

    @BeforeEach
    public void setUp() throws DateConversionException {
        model = new ModelManager(getTypicalColabFolder(), new UserPrefs());
        deadlineToAdd = new DeadlineBuilder().withDescription("CS2106 Tutorial")
                .withByDate(LocalDate.of(2020, 01, 01)).build();
    }

    @Test
    public void execute_validParameters_success() throws Exception {
        CommandResult commandResult = new AddDeadlineCommand(INDEX_FIRST, deadlineToAdd).execute(model);
        Project projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());

        assertEquals(String.format(Messages.MESSAGE_ADD_DEADLINE_SUCCESS, deadlineToAdd,
                projectToEdit.getProjectName()), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidProjectIndex_throwsCommandException() {
        // Typical project list contains only 2 projects
        AddDeadlineCommand addDeadlineCommand = new AddDeadlineCommand(INDEX_THIRD, deadlineToAdd);

        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, () -> addDeadlineCommand.execute(model)
        );
    }

    @Test
    public void execute_duplicateDeadline_throwsCommandException() {
        Project projectToAddTo = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        AddDeadlineCommand addDeadlineCommand = new AddDeadlineCommand(INDEX_FIRST, deadlineToAdd);

        projectToAddTo.addDeadline(deadlineToAdd);

        assertThrows(CommandException.class, Messages.MESSAGE_DUPLICATE_DEADLINE, () ->
                addDeadlineCommand.execute(model));
    }

    @Test
    public void equals() {
        AddDeadlineCommand addDeadlineToOneCommand = new AddDeadlineCommand(INDEX_FIRST, deadlineToAdd);
        AddDeadlineCommand addDeadlineToTwoCommand = new AddDeadlineCommand(INDEX_SECOND, deadlineToAdd);

        // same object -> returns true
        assertEquals(addDeadlineToOneCommand, addDeadlineToOneCommand);

        // same values -> returns true
        AddDeadlineCommand addDeadlineToOneCommandCopy = new AddDeadlineCommand(INDEX_FIRST, deadlineToAdd);;
        assertEquals(addDeadlineToOneCommandCopy, addDeadlineToOneCommand);

        // different types -> returns false
        assertNotEquals(addDeadlineToOneCommand, 1);

        // null -> returns false
        assertNotEquals(addDeadlineToOneCommand, null);

        // different event -> returns false
        assertNotEquals(addDeadlineToTwoCommand, addDeadlineToOneCommand);
    }

}
