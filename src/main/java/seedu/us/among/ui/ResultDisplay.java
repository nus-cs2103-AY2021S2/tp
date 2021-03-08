package seedu.us.among.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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

    public ResultDisplay() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        responseMeta.getChildren().clear();
        resultDisplay.setText(feedbackToUser);
    }
    public void setApiFeedbackToUser(String feedbackToUser, Endpoint endpoint) {
        requireNonNull(feedbackToUser);

        String textFeedback = String.format("Endpoint:\n%s\nResponse Body:\n%s", endpoint.getAddress(), feedbackToUser);

        responseMeta.getChildren().clear();
        responseMeta.getChildren().addAll(new Label(String.format("Method: %s", endpoint.getMethod().toString())),
                new Label(String.format("Status: %s", endpoint.getResponse().getStatusCode())),
                new Label(String.format("Time: %s", endpoint.getResponse().getResponseTime())));
        resultDisplay.setText(textFeedback);
    }


}
