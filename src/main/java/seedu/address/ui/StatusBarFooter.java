package seedu.address.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    private static final String FXML = "StatusBarFooter.fxml";

    @FXML
    private Label saveLocationStatus;

    @FXML
    private Label imageCredit;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public StatusBarFooter(Path saveLocation) {
        super(FXML);
        saveLocationStatus.setText(Paths.get(".").resolve(saveLocation).toString());
        imageCredit.setStyle("-fx-font-family: 'Segoe UI';"
                + "-fx-font-size: 12px; "
                + "-fx-font-style: italic; "
                + "-fx-alignment: center-right");
        imageCredit.setText("Images provided by Pixel Perfect, Vectors Market and Freepik.");
    }

}
