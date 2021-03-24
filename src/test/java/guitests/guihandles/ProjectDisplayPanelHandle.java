package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import seedu.address.model.person.Person;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.CompletableTodo;
import seedu.address.model.task.repeatable.Event;

/**
 * Provides a handle to a {@code ProjectDisplayPanel}.
 */
public class ProjectDisplayPanelHandle extends NodeHandle<Node> {
    private static final String PROJECT_NAME_ID = "#projectName";
    private static final String TAB_PANE_ID = "#tabPane";
    private static final String EVENT_LIST_ID = "#eventListView";
    private static final String DEADLINE_LIST_ID = "#completableDeadlineListView";
    private static final String TODO_LIST_ID = "#completableTodoListView";
    private static final String PARTICIPANT_LIST_ID = "#participantListView";

    private final Label projectName;
    private final TabPane tabPane;
    private final ListView<Event> eventListView;
    private final ListView<CompletableDeadline> completableDeadlineListView;
    private final ListView<CompletableTodo> completableTodoListView;
    private final ListView<Person> participantListView;

    /**
     * Constructs a {@code ProjectDisplayPanelHandle} handler object.
     * @param panelNode Node of {@code ProjectDisplayHandle}.
     */
    public ProjectDisplayPanelHandle(Node panelNode) {
        super(panelNode);

        projectName = getChildNode(PROJECT_NAME_ID);
        tabPane = getChildNode(TAB_PANE_ID);
        eventListView = getChildNode(EVENT_LIST_ID);
        completableDeadlineListView = getChildNode(DEADLINE_LIST_ID);
        completableTodoListView = getChildNode(TODO_LIST_ID);
        participantListView = getChildNode(PARTICIPANT_LIST_ID);
    }

    public String getProjectName() {
        return projectName.getText();
    }

    public int getTabInFocus() {
        return tabPane.getSelectionModel().getSelectedIndex();
    }

    public void setTabInFocus(int tab) {
        tabPane.getSelectionModel().select(tab);
    }
}
