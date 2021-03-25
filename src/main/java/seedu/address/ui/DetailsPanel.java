package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.DetailsPanelTab;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonEvent;

public class DetailsPanel extends UiPart<Region> {

    private static final String FXML = "DetailsPanel.fxml";

    private final PersonDetailsTab personDetailsTab;
    private final UpcomingEventsTab upcomingEventsTab;

    @FXML
    private VBox tabPlaceholder;

    /**
     * Creates a {@code DetailsPanel} with the given {@code ObservableList}s.
     * @param upcomingEventsList A list of upcoming events.
     * @param detailedPerson A list containing a single {@code Person}.
     */
    public DetailsPanel(ObservableList<PersonEvent> upcomingEventsList, ObservableList<Person> detailedPerson) {
        super(FXML);
        upcomingEventsTab = new UpcomingEventsTab(upcomingEventsList);
        personDetailsTab = new PersonDetailsTab(detailedPerson);
        toggleTab(DetailsPanelTab.UPCOMING_EVENTS);
    }

    /**
     * Toggles which tab is visible on the {@code DetailsPanel}.
     * @param tab A {@code DetailsPanelTab} enum representing the tab to toggle to.
     */
    public void toggleTab(DetailsPanelTab tab) {
        switch (tab) {
        case PERSON_DETAILS:
            tabPlaceholder.getChildren().setAll(personDetailsTab.getRoot());
            break;
        case UPCOMING_EVENTS:
        default:
            tabPlaceholder.getChildren().setAll(upcomingEventsTab.getRoot());
            break;
        }
    }
}
