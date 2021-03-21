package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.DetailsPanelTab;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonEvent;

public class DetailsBarPanel extends UiPart<Region> {
    private static final String FXML = "DetailsBarPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DetailsBarPanel.class);

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
     * Creates a {@code DetailsBarPanel} with the given {@code ObservableList}.
     * @param detailsList
     * @param detailedPerson
     */
    public DetailsBarPanel(ObservableList<PersonEvent> detailsList, ObservableList<Person> detailedPerson) {
        super(FXML);
        toggleTab(DetailsPanelTab.UPCOMING_EVENTS);
        upcomingEventsTitle.setText("Upcoming Events");
        upcomingDatesListView.setItems(detailsList);
        upcomingDatesListView.setCellFactory(listView -> new UpcomingDatesListViewCell());

        personDetailsTitle.setText("Contact Details");
        personDetailsListView.setItems(detailedPerson);
        personDetailsListView.setCellFactory(listView -> new PersonDetailsListViewCell());
    }

    private void setPaneVisibility(Pane pane, boolean isVisible) {
        pane.setVisible(isVisible);
        pane.setManaged(isVisible);
    }

    public void toggleTab(DetailsPanelTab tab) {
        switch (tab) {
        case UPCOMING_EVENTS:
            setPaneVisibility(upcomingEventsPane, true);
            setPaneVisibility(personDetailsPane, false);
            break;
        case PERSON_DETAILS:
            setPaneVisibility(personDetailsPane, true);
            setPaneVisibility(upcomingEventsPane, false);
            break;
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a Group {@code Name}.
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
