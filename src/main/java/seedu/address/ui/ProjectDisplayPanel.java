package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.CompletableTodo;
import seedu.address.model.task.repeatable.Event;

/**
 * Panel containing a project.
 */
public class ProjectDisplayPanel extends UiPart<Region> {
    private static final String FXML = "ProjectDisplayPanel.fxml";

    private static final Integer EVENTS_TAB = 0;
    private static final Integer DEADLINES_TAB = 1;
    private static final Integer TODOS_TAB = 2;
    private static final Integer PARTICIPANTS_TAB = 3;

    private final Logger logger = LogsCenter.getLogger(ProjectDisplayPanel.class);

    @FXML
    private Label projectName;

    @FXML
    private TabPane tabPane;

    @FXML
    private ListView<Event> eventListView;

    @FXML
    private ListView<CompletableDeadline> completableDeadlineListView;

    @FXML
    private ListView<CompletableTodo> completableTodoListView;

    @FXML
    private ListView<Person> participantListView;

    /**
     * Creates a {@code ProjectDisplayPanel} with the given {@code project} and {@code displayedIndex}.
     */
    public ProjectDisplayPanel() {
        super(FXML);
    }

    /**
     * Displays a project in the {@code ProjectDisplayPanel}.
     * @param project Project to display.
     */
    public void displayProject(Project project, Index index) {
        this.projectName.setText(index.getOneBased() + ". " + project.getProjectName().toString());

        eventListView.setItems(new FilteredList<>(project.getEvents().getEvents()));
        eventListView.setCellFactory(listView -> new ProjectDisplayPanel.EventListViewCell());

        completableDeadlineListView.setItems(new FilteredList<>(project.getDeadlines().getDeadlines()));
        completableDeadlineListView.setCellFactory(listView ->
                new ProjectDisplayPanel.CompletableDeadlineListViewCell());

        completableTodoListView.setItems(new FilteredList<>(project.getTodos().getTodos()));
        completableTodoListView.setCellFactory(listView -> new ProjectDisplayPanel.CompletableTodoListViewCell());

        participantListView.setItems(new FilteredList<>(project.getParticipants().getAsObservableList()));
        participantListView.setCellFactory(listView -> new ProjectDisplayPanel.ParticipantListViewCell());
    }

    /**
     * Displays the events tab.
     */
    public void showEventsTab() {
        tabPane.getSelectionModel().select(EVENTS_TAB);
    }

    /**
     * Displays the deadlines tab.
     */
    public void showDeadlinesTab() {
        tabPane.getSelectionModel().select(DEADLINES_TAB);
    }

    /**
     * Displays the todo tab.
     */
    public void showTodosTab() {
        tabPane.getSelectionModel().select(TODOS_TAB);
    }

    /**
     * Displays the participants tab.
     */
    public void showParticipantsTab() {
        tabPane.getSelectionModel().select(PARTICIPANTS_TAB);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code Event} using an {@code EventCard}.
     */
    class EventListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EventCard(event, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code CompletableDeadline} using
     * a {@code CompletableDeadlineCard}.
     */
    class CompletableDeadlineListViewCell extends ListCell<CompletableDeadline> {
        @Override
        protected void updateItem(CompletableDeadline completableDeadline, boolean empty) {
            super.updateItem(completableDeadline, empty);

            if (empty || completableDeadline == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CompletableDeadlineCard(completableDeadline, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code CompletableTodo} using
     * a {@code CompletableTodoCard}.
     */
    class CompletableTodoListViewCell extends ListCell<CompletableTodo> {
        @Override
        protected void updateItem(CompletableTodo completableTodo, boolean empty) {
            super.updateItem(completableTodo, empty);

            if (empty || completableTodo == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CompletableTodoCard(completableTodo, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonDisplayCard}.
     */
    class ParticipantListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }
}
