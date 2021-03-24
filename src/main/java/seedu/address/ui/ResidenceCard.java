package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.residence.Residence;

public class ResidenceCard extends UiPart<Region> {

    private static final String FXML = "ResidenceListCard.fxml";

    public final Residence residence;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label address;
    @FXML
    private Label bookingList;
    @FXML
    private FlowPane cleanStatusTags;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ResidenceCard(Residence residence, int displayedIndex) {
        super(FXML);
        this.residence = residence;
        id.setText(displayedIndex + ". ");
        name.setText(residence.getResidenceName().getValue());
        address.setText(residence.getResidenceAddress().getValue());
        bookingList.setText(residence.getBookingDetails().getValue());
        cleanStatusTags.getChildren().add(new Label(residence.getCleanStatusTag().getValue()));

        residence.getTags().stream()
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
        if (!(other instanceof ResidenceCard)) {
            return false;
        }

        // state check
        ResidenceCard card = (ResidenceCard) other;
        return id.getText().equals(card.id.getText())
                && residence.equals(card.residence);
    }
}
