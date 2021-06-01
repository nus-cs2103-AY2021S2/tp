package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import seedu.address.model.contact.Contact;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.CompletableTodo;
import seedu.address.model.task.repeatable.Event;

/**
 * Provides a handle to a {@code ProjectDisplayPanel}.
 */
public class ProjectDisplayPanelHandle extends NodeHandle<Node> {
    private static final String PROJECT_NAME_ID = "#projectName";
    private static final String TAB_PANE_ID = "#tabPane";
    private static final String EVENT_LIST_PLACEHOLDER_ID = "#eventListViewPlaceholder";
    private static final String DEADLINE_LIST_PLACEHOLDER_ID = "#deadlineListViewPlaceholder";
    private static final String TODO_LIST_PLACEHOLDER_ID = "#todoListViewPlaceholder";
    private static final String GROUPMATE_LIST_PLACEHOLDER_ID = "#groupmateListViewPlaceholder";

    private final Label projectName;
    private final TabPane tabPane;
    private final StackPane groupmateListPlaceholder;
    private final StackPane deadlineListPlaceholder;
    private final StackPane eventListPlaceholder;
    private final StackPane todoListPlaceholder;

    private final ListView<Event> eventListView = new ListView<>();
    private final ListView<CompletableDeadline> completableDeadlineListView = new ListView<>();
    private final ListView<CompletableTodo> completableTodoListView = new ListView<>();
    private final ListView<Contact> groupmateListView = new ListView<>();

    /**
     * Constructs a {@code ProjectDisplayPanelHandle} handler object.
     *
     * @param panelNode Node of {@code ProjectDisplayHandle}.
     */
    public ProjectDisplayPanelHandle(Node panelNode) {
        super(panelNode);

        projectName = getChildNode(PROJECT_NAME_ID);
        tabPane = getChildNode(TAB_PANE_ID);
        eventListPlaceholder = getChildNode(EVENT_LIST_PLACEHOLDER_ID);
        deadlineListPlaceholder = getChildNode(DEADLINE_LIST_PLACEHOLDER_ID);
        todoListPlaceholder = getChildNode(TODO_LIST_PLACEHOLDER_ID);
        groupmateListPlaceholder = getChildNode(GROUPMATE_LIST_PLACEHOLDER_ID);
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
