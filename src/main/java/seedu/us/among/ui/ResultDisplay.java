package seedu.us.among.ui;

import static java.util.Objects.requireNonNull;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import seedu.us.among.commons.core.GuiSettings;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Response;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";
    private static final int MAX_TEXT_LENGTH = 100000;

    @FXML
    private TextArea resultDisplay;
    @FXML
    private FlowPane responseMeta;
    @FXML
    private ImageView loadingSpinnerPlaceholder;
    @FXML
    private ImageView errorPlaceholder;
    @FXML
    private ImageView emptyListPlaceholder;

    private Timeline errorGifTimeline;

    private String errorGifType = "error-white.gif";

    private final Image error = new Image(this.getClass().getResourceAsStream("/images/" + errorGifType));

    private final Image loadingSpinnerForDark = new Image(this.getClass().getResourceAsStream(
            "/images/loading_spinner-white.gif"));
    private final Image loadingSpinnerForLight = new Image(this.getClass().getResourceAsStream(
            "/images/loading_spinner-black.gif"));
    private final Image emptyList = new Image(this.getClass().getResourceAsStream("/images/imPosterEmptyList.png"));

    /**
     * Constructor for ResultDisplay.
     */
    public ResultDisplay() {
        super(FXML);

        if (GuiSettings.getLoadedTheme().equalsIgnoreCase("light")
            || GuiSettings.getLoadedTheme().equalsIgnoreCase("material")) {
            this.errorGifType = "error-black.gif";
            this.loadingSpinnerPlaceholder.setImage(loadingSpinnerForLight);
        } else {
            this.errorGifType = "error-white.gif";
            this.loadingSpinnerPlaceholder.setImage(loadingSpinnerForDark);
        }

        this.errorPlaceholder.setImage(error);
        this.emptyListPlaceholder.setImage(emptyList);

        // Set timeline for error message
        this.errorGifTimeline = new Timeline(
            new KeyFrame(Duration.ZERO, x -> this.getErrorPlaceholder().setVisible(true)),
            new KeyFrame(Duration.seconds(3), x -> this.getErrorPlaceholder().setVisible(false))
        );
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
     * Gets emptyListPlaceholder
     *
     * @return emptyListPlaceholder
     */
    public ImageView getEmptyListPlaceholder() {
        return this.emptyListPlaceholder;
    }

    /**
     * Gets errorPlaceholder.
     *
     * @return errorPlaceholder
     */
    public ImageView getErrorPlaceholder() {
        //hack to reset animation by loading image again
        this.errorPlaceholder.setImage(new Image(this.getClass().getResourceAsStream("/images/" + this.errorGifType)));
        return this.errorPlaceholder;
    }

    public Timeline getErrorGifTimeline() {
        return this.errorGifTimeline;
    }

    public void setErrorGifType(String theme) {
        if (theme.equalsIgnoreCase("light") || theme.equalsIgnoreCase("material")) {
            this.errorGifType = "error-black.gif";
        } else {
            this.errorGifType = "error-white.gif";
        }
    }

    public void setSpinnerGifType(String theme) {
        if (theme.equalsIgnoreCase("light") || theme.equalsIgnoreCase("material")) {
            this.loadingSpinnerPlaceholder.setImage(loadingSpinnerForLight);
        } else {
            this.loadingSpinnerPlaceholder.setImage(loadingSpinnerForDark);
        }
    }

    /**
     * Returns color code for responseMeta labels based on status code.
     *
     * @param statusCode status code to determine color from
     */
    public String getColorCode(String statusCode) {
        char firstChar = statusCode.charAt(0);
        switch (firstChar) {
        case '2':
            return "-fx-background-color: #228B22"; //green
        case '3':
            return "-fx-background-color: #999900"; //orange
        case 'N':
            return "-fx-background-color: #999999"; //grey
        default:
            return "-fx-background-color: #B22222"; //red
        }
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
     * Helper function for setting responseMetaFeedback to user.
     * @param feedbackToUser feedback to give user
     * @param endpoint endpoint to display
     */
    public void responseMetaFeedbackHelper(String feedbackToUser, Endpoint endpoint) {
        if (feedbackToUser.length() > MAX_TEXT_LENGTH) {
            feedbackToUser = feedbackToUser.substring(0, MAX_TEXT_LENGTH);
        }
        setResponseMetaToUser(endpoint);
        resultDisplay.setText(feedbackToUser);
    }

    public void setResponseMetaFeedbackHelper(String feedbackToUser, Endpoint endpoint) {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> responseMetaFeedbackHelper(feedbackToUser, endpoint));
        } else {
            responseMetaFeedbackHelper(feedbackToUser, endpoint);
        }
    }

    /**
     * Helper function for setting API feedback to user.
     *
     * @param feedbackToUser API feedback to give user
     * @param endpoint endpoint to give feedback for
     */
    public void apiFeedbackHelper(String feedbackToUser, Endpoint endpoint) {
        if (feedbackToUser.length() > MAX_TEXT_LENGTH) {
            feedbackToUser = feedbackToUser.substring(0, MAX_TEXT_LENGTH);
        }
        setResponseMetaToUser(endpoint);
        resultDisplay.setText(feedbackToUser);
    }

    /**
     * Sets API feedback to user.
     *
     * @param feedbackToUser API feedback to give user
     * @param endpoint endpoint to give feedback for
     */
    public void setApiFeedbackToUser(String feedbackToUser, Endpoint endpoint) {
        requireNonNull(feedbackToUser);

        String textFeedback = String.format(
                "Endpoint:\n============\n\n%s\n\nResponse Body:\n============\n\n%s",
                        endpoint.getAddress(), feedbackToUser);

        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> apiFeedbackHelper(textFeedback, endpoint));
        } else {
            apiFeedbackHelper(textFeedback, endpoint);
        }
    }

    /**
     * Sets the flow pane for a unique endpoint. Contains the fields of methods,
     * status code + phrase, response time and protocol version.
     * @param endpoint endpoint to give feedback for
     */
    public void setResponseMetaToUser(Endpoint endpoint) {
        Label method;
        Label statusCodeAndPhrase;
        Label responseTime;
        Label protocolVersion;

        if (endpoint.getResponse().isReasonEmpty()) {
            statusCodeAndPhrase = new Label(String.format("Status: %s", Response.EMPTY_RESPONSE_STRING));
        } else {
            statusCodeAndPhrase = new Label(String.format("Status: %s %s", endpoint.getResponse().getStatusCode(),
                    endpoint.getReasonPhrase()));
        }

        method = new Label(String.format("Method: %s", endpoint.getMethod().toString()));
        responseTime = new Label(String.format("Time: %s", endpoint.getResponse().getResponseTime()));
        protocolVersion = new Label(String.format("Protocol Version: %s", endpoint.getResponse().getProtocolVersion()));

        String colorCode = getColorCode(endpoint.getResponse().getStatusCode());

        method.setStyle(colorCode);
        statusCodeAndPhrase.setStyle(colorCode);
        responseTime.setStyle(colorCode);
        protocolVersion.setStyle(colorCode);

        responseMeta.getChildren().clear();
        responseMeta.getChildren().addAll(method, statusCodeAndPhrase, responseTime, protocolVersion);
    }
}
