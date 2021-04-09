package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.room.Room;

/**
 * An UI component that displays information of a {@code Room}.
 */
public class RoomCard extends UiPart<Region> {
    private static final String FXML = "RoomListCard.fxml";

    public final Room room;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label roomNumber;
    @FXML
    private Label roomType;
    @FXML
    private Label isOccupied;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code RoomCard} with the given {@code Room} and index to display.
     */
    public RoomCard(Room room, int displayedIndex) {
        super(FXML);
        this.room = room;

        id.setText(displayedIndex + ". ");
        roomNumber.setText(room.getRoomNumber().roomNumber);
        roomType.setText(room.getRoomType().toString());
        isOccupied.setText(room.isOccupied().isOccupied ? "Occupied" : "Not Occupied");
        room.getTags().stream()
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
        if (!(other instanceof RoomCard)) {
            return false;
        }

        // state check
        RoomCard card = (RoomCard) other;
        return id.getText().equals(card.id.getText())
                && room.equals(card.room);
    }
}
