package seedu.iscam.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.iscam.model.client.Client;


public class ClientDetailFragment extends UiPart<Region>{

    private static final String FXML = "ClientDetailFragment.fxml";

    private Client client;

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label clientLocation;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    public ClientDetailFragment(){
        super(FXML);
    }

    public void setClientDetials(Client client) {
        this.client = client;
        name.setText(client.getName().fullName);
        phone.setText(client.getPhone().value);
        clientLocation.setText(client.getLocation().value);
        email.setText(client.getEmail().value);
        client.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientDetailFragment)) {
            return false;
        }

        // state check
        ClientDetailFragment fragment = (ClientDetailFragment) other;
        return client.equals(fragment.client);
    }
}
