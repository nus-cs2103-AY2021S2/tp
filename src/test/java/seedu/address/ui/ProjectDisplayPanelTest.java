package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.ui.ProjectDisplayPanel.OVERVIEW_TAB;
import static seedu.address.ui.ProjectDisplayPanel.TODOS_TAB;

import org.junit.jupiter.api.Test;

import guitests.guihandles.ProjectDisplayPanelHandle;
import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.model.project.Project;
import seedu.address.testutil.TypicalProjects;

/**
 * Contains tests for the {@code ProjectDisplayPanel}.
 */
public class ProjectDisplayPanelTest extends GuiUnitTest {

    private ProjectDisplayPanelHandle projectDisplayPanelHandle;

    @Test
    void changeTab_success() throws DateConversionException {
        // Init UI
        ProjectDisplayPanel projectDisplayPanel = new ProjectDisplayPanel();
        Project project = TypicalProjects.getCS2103TProject();
        projectDisplayPanel.displayProject(project);
        uiPartExtension.setUiPart(projectDisplayPanel);
        projectDisplayPanelHandle = new ProjectDisplayPanelHandle(projectDisplayPanel.getRoot());

        // Switch to todos tab
        projectDisplayPanelHandle.setTabInFocus(TODOS_TAB);
        projectDisplayPanel.showOverviewTab();
        assertEquals(projectDisplayPanelHandle.getTabInFocus(), OVERVIEW_TAB);

        // Switch to overview tab
        projectDisplayPanelHandle.setTabInFocus(OVERVIEW_TAB);
        projectDisplayPanel.showTodosTab();
        assertEquals(projectDisplayPanelHandle.getTabInFocus(), TODOS_TAB);
    }

}
