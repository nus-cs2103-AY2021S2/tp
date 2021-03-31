package seedu.address.ui;

import java.time.LocalDate;
import java.util.Comparator;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import seedu.address.model.booking.Booking;
import seedu.address.model.residence.Residence;

public class ResidenceCard extends UiPart<Region> {

    private static final String FXML = "ResidenceListCard.fxml";

    public final Residence residence;

    private LocalDate today = LocalDate.now();

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label address;
    @FXML
    private Label bookingListTitle;
    @FXML
    private FlowPane bookingTitlePane;
    @FXML
    private FlowPane bookingListPane;
    @FXML
    private FlowPane cleanStatusTags;
    @FXML
    private FlowPane tags;
    @FXML
    private ScrollPane bookingListScrollPane;
    @FXML
    private VBox bookingListVBox;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Creates a {@code ResidenceCode} with the given {@code residence} and index to display.
     */
    public ResidenceCard(Residence residence, int displayedIndex) {
        super(FXML);
        this.residence = residence;
        id.setText(displayedIndex + ". ");
        name.setText(residence.getResidenceName().getValue());
        address.setText(residence.getResidenceAddress().getValue());
        bookingListTitle.setText("BOOKINGS");
        cleanStatusTags.getChildren().add(new Label(residence.getCleanStatusTag().getValue()));

        residence.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        ObservableList<Booking> bookingList = residence.getBookingList().getValue();

        for (Booking booking : bookingList) {
            Label label = new Label("  "
                    + String.valueOf(bookingList.indexOf(booking) + 1)
                    + ". "
                    + booking.toString());

            if (booking.getEnd().compareTo(today) > 0 && booking.getStart().compareTo(today) > 0) {
                //green for upcoming bookings
                label.setBackground(new Background(new BackgroundFill(Color.web("#adddce"),
                        new CornerRadii(0.0),
                        new Insets(0.0))));
            } else if (booking.getEnd().compareTo(today) > 0 && booking.getStart().compareTo(today) <= 0) {
                //orange for occurring bookings
                label.setBackground(new Background(new BackgroundFill(Color.web("#fbd7be"),
                        new CornerRadii(0.0),
                        new Insets(0.0))));
            } else {
                //red for past/expired bookings
                label.setBackground(new Background(new BackgroundFill(Color.web("#f6bbc2"),
                        new CornerRadii(0.0),
                        new Insets(0.0))));
            }
            HBox holder = new HBox();
            holder.getChildren().add(label);
            bookingListVBox.getChildren().add(holder);
        }
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
