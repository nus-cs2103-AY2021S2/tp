package seedu.us.among.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.us.among.model.endpoint.Endpoint;

/**
 * An UI component that displays information of a {@code Endpoint}.
 */
public class EndpointCard extends UiPart<Region> {

    private static final String FXML = "EndpointListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX. As a consequence, UI elements' variable names cannot be
     * set to such keywords or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     *      issue on EndpointList level 4</a>
     */

    public final Endpoint endpoint;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label address;
    @FXML
    private Label data;
    @FXML
    private FlowPane headers;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code EndpointCode} with the given {@code Endpoint} and index to
     * display.
     */
    public EndpointCard(Endpoint endpoint, int displayedIndex) {
        super(FXML);
        this.endpoint = endpoint;
        id.setText(displayedIndex + ". ");
        name.setText(endpoint.getMethod().methodName);
        address.setText(endpoint.getAddress().value);
        data.setText(endpoint.getData().value);
        endpoint.getHeaders().stream().sorted(Comparator.comparing(header -> header.headerName))
                .forEach(header -> headers.getChildren().add(new Label(header.headerName)));
        endpoint.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EndpointCard)) {
            return false;
        }

        // state check
        EndpointCard card = (EndpointCard) other;
        return id.getText().equals(card.id.getText()) && endpoint.equals(card.endpoint);
    }
}
