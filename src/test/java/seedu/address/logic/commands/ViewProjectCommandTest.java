package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.uicommands.ViewProjectUiCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectBuilder;

/**
 * Contains unit tests for {@code ViewProjectCommand}.
 */
public class ViewProjectCommandTest {

    @Test
    public void execute_validIndex_success() {
        Model model = new ModelManager();
        Project validProject = new ProjectBuilder().build();
        Model expectedModel = new ModelManager();
        model.addProject(validProject);
        expectedModel.addProject(validProject);

        assertCommandSuccess(new ViewProjectCommand(INDEX_FIRST), model,
                String.format(ViewProjectCommand.MESSAGE_SUCCESS, validProject),
                new ViewProjectUiCommand(INDEX_FIRST), expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Model model = new ModelManager();
        ViewProjectCommand viewProjectCommand = new ViewProjectCommand(INDEX_FIRST);

        assertCommandFailure(viewProjectCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

}
