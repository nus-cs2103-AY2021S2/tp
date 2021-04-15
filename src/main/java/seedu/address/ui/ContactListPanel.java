package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.contact.Contact;

/**
 * Panel containing the list of contacts.
 */
public class ContactListPanel extends UiPart<Region> {
    private static final String FXML = "ContactListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ContactListPanel.class);

    private final ListView<Contact> contactListView = new ListView<>();
    private final Label noContactsLabel = new Label();

    @FXML
    private StackPane contactListViewPlaceholder;

    /**
     * Creates a {@code ContactListPanel} with the given {@code ObservableList}.
     */
    public ContactListPanel(ObservableList<Contact> contactList) {
        super(FXML);
        contactListView.setId("contactListView");
        contactListView.setItems(contactList);
        contactListView.setCellFactory(listView -> new ContactListViewCell());

        if (contactListView.getItems().isEmpty()) {
            noContactsLabel.setText("No contacts to display!");
            contactListViewPlaceholder.getChildren().add(noContactsLabel);
        } else {
            contactListViewPlaceholder.getChildren().add(contactListView);
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Contact} using a {@code ContactCard}.
     */
    class ContactListViewCell extends ListCell<Contact> {
        @Override
        protected void updateItem(Contact contact, boolean empty) {
            super.updateItem(contact, empty);

            if (empty || contact == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ContactCard(contact, getIndex() + 1).getRoot());
            }
        }
    }

    public ListView<Contact> getContactListView() {
        return contactListView;
    }
}
