package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
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

    private static final Integer OVERVIEW_TAB = 0;
    private static final Integer TODOS_TAB = 1;

    private static final int SAFETY_MARGIN = 5; // Applied to each listview to prevent card from being cut off
    private static final int EVENTS_CARD_HEIGHT = 50;
    private static final int DEADLINES_CARD_HEIGHT = 35;
    private static final int GROUPMATES_CARD_HEIGHT = 105;

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
     *
     * @param project Project to display.
     */
    public void displayProject(Project project) {
        this.projectName.setText(project.getProjectName().toString());

        setUpEventList(project.getEvents().getEvents());
        setUpDeadlinesList(project.getDeadlines().getDeadlines());
        setUpTodoList(project.getTodos().getTodos());
        setUpGroupmatesList(project.getParticipants().getParticipants());
    }

    private void setUpTodoList(ObservableList<CompletableTodo> todos) {
        completableTodoListView.setItems(new FilteredList<>(todos));
        completableTodoListView.setCellFactory(listView -> new ProjectDisplayPanel.CompletableTodoListViewCell());
    }

    private void setUpGroupmatesList(ObservableList<Person> groupmates) {
        participantListView.prefHeightProperty()
                .bind(Bindings.size(groupmates).multiply(GROUPMATES_CARD_HEIGHT).add(SAFETY_MARGIN));
        participantListView.setItems(new FilteredList<>(groupmates));
        participantListView.setCellFactory(listView -> new ProjectDisplayPanel.ParticipantListViewCell());
    }

    private void setUpDeadlinesList(ObservableList<CompletableDeadline> deadlines) {
        completableDeadlineListView.prefHeightProperty()
                .bind(Bindings.size(deadlines).multiply(DEADLINES_CARD_HEIGHT).add(SAFETY_MARGIN));
        completableDeadlineListView.setItems(new FilteredList<>(deadlines));
        completableDeadlineListView.setCellFactory(listView ->
                new ProjectDisplayPanel.CompletableDeadlineListViewCell());
    }

    private void setUpEventList(ObservableList<Event> events) {
        eventListView.prefHeightProperty()
                .bind(Bindings.size(events).multiply(EVENTS_CARD_HEIGHT).add(SAFETY_MARGIN));
        eventListView.setItems(new FilteredList<>(events));
        eventListView.setCellFactory(listView -> new ProjectDisplayPanel.EventListViewCell());
    }

    /**
     * Displays the overview tab.
     */
    public void showOverviewTab() {
        tabPane.getSelectionModel().select(OVERVIEW_TAB);
    }

    /**
     * Displays the todo tab.
     */
    public void showTodosTab() {
        tabPane.getSelectionModel().select(TODOS_TAB);
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
