package seedu.address.ui;

import java.util.Comparator;
import java.util.stream.Collectors;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.person.Event;
import seedu.address.model.person.Person;

public class PersonDetailsCard extends UiPart<Region> {

    private static final String FXML = "PersonDetailsCard.fxml";

    public final Person person;

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label debt;
    @FXML
    private Label birthday;
    @FXML
    private FlowPane tags;
    @FXML
    private ListView<Event> datesListView;
    @FXML
    private ListView<Event> meetingsListView;
    @FXML
    private StackPane picturePlaceholder;

    /**
     * Creates a {@code PersonDetailsCard} with the given {@code Person}.
     */
    public PersonDetailsCard(Person person) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        debt.setText("Debt: " + person.getDebt().value.toString());
        birthday.setText(person.getBirthday().toUi());
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        datesListView.setItems(FXCollections.observableArrayList(person.getDates()));
        datesListView.setCellFactory(listView -> new EventListViewCell());
        datesListView.prefHeightProperty().bind(Bindings
                .size(FXCollections.observableList(person.getDates()))
                .multiply(EventCard.HEIGHT));

        meetingsListView.setItems(FXCollections.observableArrayList(person.getMeetings().stream()
                .sorted(Comparator.comparing(Event::getDate).reversed())
                .collect(Collectors.toList())));
        meetingsListView.setCellFactory(listView -> new EventListViewCell());
        meetingsListView.prefHeightProperty().bind(Bindings
                .size(FXCollections.observableList(person.getMeetings()))
                .multiply(EventCard.HEIGHT));

        ProfilePicture profilePicture = new ProfilePicture(person, new Insets(0, 0, 10, 0));
        picturePlaceholder.getChildren().add(profilePicture.getRoot());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonDetailsCard)) {
            return false;
        }

        // state check
        PersonDetailsCard card = (PersonDetailsCard) other;
        return person.equals(card.person);
    }

    class EventListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EventCard(event).getRoot());
            }
        }
    }
}
