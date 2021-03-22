package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.DetailsPanelTab;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonEvent;

public class DetailsPanel extends UiPart<Region> {
    private static final String FXML = "DetailsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DetailsPanel.class);

    @FXML
    private VBox upcomingEventsPane;

    @FXML
    private Label upcomingEventsTitle;

    @FXML
    private ListView<PersonEvent> upcomingDatesListView;

    @FXML
    private VBox personDetailsPane;

    @FXML
    private Label personDetailsTitle;

    @FXML
    private ListView<Person> personDetailsListView;

    /**
     * Creates a {@code DetailsPanel} with the given {@code ObservableList}s.
     * @param upcomingDatesList A list of upcoming dates.
     * @param detailedPerson A list containing a single {@code Person}.
     */
    public DetailsPanel(ObservableList<PersonEvent> upcomingDatesList, ObservableList<Person> detailedPerson) {
        super(FXML);
        toggleTab(DetailsPanelTab.UPCOMING_EVENTS);
        upcomingEventsTitle.setText("Upcoming Events");
        upcomingDatesListView.setItems(upcomingDatesList);
        upcomingDatesListView.setCellFactory(listView -> new UpcomingDatesListViewCell());

        personDetailsTitle.setText("Contact Details");
        personDetailsListView.setItems(detailedPerson);
        personDetailsListView.setCellFactory(listView -> new PersonDetailsListViewCell());
    }

    private void setPaneVisibility(Pane pane, boolean isVisible) {
        pane.setVisible(isVisible);
        pane.setManaged(isVisible);
    }

    /**
     * Toggles which tab is visible on the {@code DetailsPanel}.
     * @param tab A {@code DetailsPanelTab} enum representing the tab to toggle to.
     */
    public void toggleTab(DetailsPanelTab tab) {
        switch (tab) {
        case PERSON_DETAILS:
            setPaneVisibility(personDetailsPane, true);
            setPaneVisibility(upcomingEventsPane, false);
            break;
        case UPCOMING_EVENTS:
        default:
            setPaneVisibility(upcomingEventsPane, true);
            setPaneVisibility(personDetailsPane, false);
            break;
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an upcoming date.
     */
    class UpcomingDatesListViewCell extends ListCell<PersonEvent> {
        @Override
        protected void updateItem(PersonEvent personEvent, boolean empty) {
            super.updateItem(personEvent, empty);

            if (empty || personEvent == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new UpcomingDateCard(personEvent).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the full details of a {@code Person}.
     */
    class PersonDetailsListViewCell extends ListCell<Person> {
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
