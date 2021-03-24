package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.io.InputStream;

import static java.util.Objects.requireNonNull;

/**
 * A ui that display a group consists of an icon and a label for any card
 */
public class LabelWithIcon extends UiPart<Region> {
    private static final String FXML = "LabelWithIcon.fxml";

    @FXML
    private Image icon;
    @FXML
    private Label text;
    @FXML
    private ImageView iconHolder;
    @FXML
    private HBox labelWithIcon;

    /**
     * Creates {@code StatusBarFooter} with the given {@code String} of icon's file path and text
     */
    public LabelWithIcon(String iconImgPath, String textToDisplay) {
        super(FXML);
        requireNonNull(iconImgPath);
        InputStream imgStream = getClass().getResourceAsStream(iconImgPath);
        requireNonNull(imgStream);
        icon = new Image(imgStream);
        iconHolder.setImage(icon);
        text.setText(textToDisplay);
        text.setTextOverrun(OverrunStyle.CLIP);
        labelWithIcon.setAlignment(Pos.CENTER_LEFT);
    }
}
