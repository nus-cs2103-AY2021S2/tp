package seedu.iscam.ui;

import java.awt.TextArea;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;


public class ClientDetailFragment extends UiPart<Region>{

    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private TextArea clientDetailName;

    public ClientDetailFragment(){
        super(FXML);
    }
}
