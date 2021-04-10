package seedu.iscam.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Comparator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.client.InsurancePlan;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.util.clientbook.ObservableClient;


public class ClientDetailFragment extends UiPart<Region> {

    private static final String FXML = "ClientDetailFragment.fxml";

    private Client client;
    private ObservableList<Meeting> meetingList;
    private final Image placeholderImage = new Image(this.getClass()
            .getResourceAsStream("/images/person_icon.png"));

    @FXML
    private ImageView profileImage;
    @FXML
    private Label profileImageRef;
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
    @FXML
    private ListView<Meeting> clientMeetingListView;
    @FXML
    private Label tooltip;
    @FXML
    private HBox clientSummaryBox;
    @FXML
    private HBox clientMeetingsAndPlansBox;
    @FXML
    private ListView<InsurancePlan> plansListView;
    @FXML
    private Separator separator;

    /**
     * Creates a ClientDetailFragment that observes the given ObservableClient
     *
     * @param observableClient ObservableClient to monitor
     */
    public ClientDetailFragment(ObservableClient observableClient, ObservableList<Meeting> meetingList) {
        super(FXML);
        observableClient.addListener(new ClientListener());
        this.meetingList = meetingList;
        setTooltipMode();
    }

    public void setClientDetails(Client client) {
        this.client = client;
        Circle imageMask = new Circle(60, 60, 60);
        profileImage.setClip(imageMask);
        profileImage.setImage(getImageFromData(client.getImageRes().value));
        name.setText(client.getName().fullName);
        name.setWrapText(true);
        phone.setText(client.getPhone().value);
        clientLocation.setText(client.getLocation().value);
        clientLocation.setWrapText(true);
        email.setText(client.getEmail().value);
        email.setWrapText(true);
        tags.getChildren().clear();
        client.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        clientMeetingListView.setItems(
                meetingList.filtered(meeting -> meeting.getClientName().equals(client.getName())));
        clientMeetingListView.setCellFactory(listview -> new MeetingListPanel.MeetingListViewCell());
        ObservableList<InsurancePlan> observablePlanList = FXCollections.observableArrayList(client.getPlans());
        plansListView.setItems(observablePlanList);
        plansListView.setCellFactory(listview -> new PlanListPanel.PlanListViewCell());
    }

    private void setTooltipMode() {
        tooltip.setManaged(true);
        tooltip.setVisible(true);
        clientSummaryBox.setVisible(false);
        clientMeetingsAndPlansBox.setVisible(false);
        separator.setVisible(false);
    }

    private void setClientDetailMode() {
        tooltip.setManaged(false);
        tooltip.setVisible(false);
        clientSummaryBox.setVisible(true);
        clientMeetingsAndPlansBox.setVisible(true);
        separator.setVisible(true);
    }

    // TODO: Migrate image access to the model and storage and not hardcode the "data" path
    private Image getImageFromData(String imageRes) {
        try {
            InputStream is = new FileInputStream(String.valueOf(Path.of("data", imageRes)));
            profileImageRef.setText(imageRes);
            return new Image(is, 120, 120, false, true);
        } catch (FileNotFoundException e) {
            profileImageRef.setText(imageRes + "\n(image not found)");
            return placeholderImage;
        }
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

    class ClientListener implements ChangeListener<Client> {
        @Override
        public void changed(ObservableValue<? extends Client> observable, Client oldValue, Client newValue) {
            if (observable.getValue() != null) {
                setClientDetails(observable.getValue());
                setClientDetailMode();
            } else {
                setTooltipMode();
            }
        }
    }
}
