package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Labeled;
import javafx.scene.control.OverrunStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

public class LabelWithIcon extends UiPart<Region> {
    private static final String FXML = "LabelWithIcon.fxml";

    @FXML
    private Image icon;
    @FXML
    private Labeled text;
    @FXML
    private ImageView iconHolder;

    public LabelWithIcon(String iconImgPath, String textToDisplay) {
        super(FXML);
        icon = new Image(getClass().getResourceAsStream(iconImgPath));
        iconHolder.setImage(icon);
        text.setText(textToDisplay);
        text.setTextOverrun(OverrunStyle.CLIP);
    }
}
