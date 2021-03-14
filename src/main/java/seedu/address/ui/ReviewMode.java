package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class ReviewMode extends UiPart<Region> {
    public static final String EXIT_REVIEW_MODE = "Exit review mode";
    private static final String FXML = "ReviewMode.fxml";
    private MainWindow parent;
    @FXML
    private Button exitButton;
    @FXML
    private TextField commandInReviewMode;
    @FXML
    private StackPane resultDisplayPlaceholderReviewMode;
    @FXML
    private Label progressLabel;

    /**
     * Create the {@code ReviewMode} with the reference to {@code MainWindow}.
     */
    public ReviewMode(MainWindow parent) {
        super(FXML);
        this.parent = parent;
        ResultDisplay resultDisplay = new ResultDisplay();
        resultDisplayPlaceholderReviewMode.getChildren().add(resultDisplay.getRoot());
        exitButton.setOnAction(event -> parent.exitReviewMode());
    }
    @FXML
    private void handleCommandEnteredReview() {
        String text = commandInReviewMode.getText().trim();
        commandInReviewMode.setText("");
        progressLabel.setText(text);
    }
}
