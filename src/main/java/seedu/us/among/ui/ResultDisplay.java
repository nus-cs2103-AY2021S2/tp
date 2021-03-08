package seedu.us.among.ui;

import static java.util.Objects.requireNonNull;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.us.among.model.endpoint.Endpoint;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private TextArea resultDisplay;
    @FXML
    private FlowPane responseMeta;
    @FXML
    private ImageView loadingSpinnerPlaceholder;

    private final Image loadingSpinner = new Image(this.getClass().getResourceAsStream("/images/loading_spinner.gif"));

    /**
     * Constructor for ResultDisplay.
     */
    public ResultDisplay() {
        super(FXML);
        this.loadingSpinnerPlaceholder.setImage(loadingSpinner);
    }

    /**
     * Gets loadingSpinnerPlaceholder.
     *
     * @return loadingSpinnerPlaceholder
     */
    public ImageView getLoadingSpinnerPlaceholder() {
        return this.loadingSpinnerPlaceholder;
    }

    /**
     * Helper function for setting feedback to user.
     *
     * @param feedbackToUser feedback to give user
     */
    public void feedbackHelper(String feedbackToUser) {
        responseMeta.getChildren().clear();
        resultDisplay.setText(feedbackToUser);
    }

    /**
     * Sets feedback to user.
     *
     * @param feedbackToUser feedback to give user
     */
    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> feedbackHelper(feedbackToUser));
        } else {
            feedbackHelper(feedbackToUser);
        }
    }

    /**
     * Helper function for setting API feedback to user.
     *
     * @param textFeedback API feedback to give user
     * @param endpoint endpoint to give feedback for
     */
    public void apiFeedbackHelper(String textFeedback, Endpoint endpoint) {
        responseMeta.getChildren().clear();
        responseMeta.getChildren().addAll(new Label(String.format("Method: %s", endpoint.getMethod().toString())),
                new Label(String.format("Status: %s", endpoint.getResponse().getStatusCode())),
                new Label(String.format("Time: %s", endpoint.getResponse().getResponseTime())));
        resultDisplay.setText(textFeedback);
        resultDisplay.setWrapText(true);
    }

    /**
     * Sets API feedback to user.
     *
     * @param feedbackToUser API feedback to give user
     * @param endpoint endpoint to give feedback for
     */
    public void setApiFeedbackToUser(String feedbackToUser, Endpoint endpoint) {
        requireNonNull(feedbackToUser);

        String textFeedback = String.format("Endpoint:\n%s\nResponse Body:\n%s", endpoint.getAddress(), feedbackToUser);

        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> apiFeedbackHelper(textFeedback, endpoint));
        } else {
            apiFeedbackHelper(textFeedback, endpoint);
        }
    }
}
