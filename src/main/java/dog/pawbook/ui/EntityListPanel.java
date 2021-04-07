package dog.pawbook.ui;

import javax.swing.text.html.ImageView;

import dog.pawbook.model.managedentity.Entity;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.util.Pair;

/**
 * Panel containing the list of owners.
 */
public class EntityListPanel extends UiPart<Region> {

    private static final String FXML = "EntityListPanel.fxml";
    private static final Image logo = new Image("images/logo.png");


    @FXML
    private ListView<Pair<Integer, Entity>> entityListView;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab dogTab;

    @FXML
    private Tab ownerTab;

    @FXML
    private Tab programTab;

    @FXML
    private ImageView imageView;

    /**
     * Creates a {@code OwnerListPanel} with the given {@code ObservableList}.
     */
    public EntityListPanel(ObservableList<Pair<Integer, Entity>> entityList) {
        super(FXML);
        entityListView.setItems(entityList);
        entityListView.setCellFactory(listView -> new EntityListViewCell());

        this.tabPane = new TabPane();
        tabPane.setTabMinWidth(tabPane.getWidth() / tabPane.getTabs().size());

        dogTab = new Tab("Dog");
        ownerTab = new Tab("Owner");
        programTab = new Tab("Program");

        tabPane.getTabs().add(dogTab);
        tabPane.getTabs().add(ownerTab);
        tabPane.getTabs().add(programTab);


    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code Entity} using a {@code EntityCard}.
     */
    class EntityListViewCell extends ListCell<Pair<Integer, Entity>> {
        @Override
        protected void updateItem(Pair<Integer, Entity> idEntityPair, boolean empty) {
            super.updateItem(idEntityPair, empty);

            if (empty || idEntityPair == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EntityCard(idEntityPair.getValue(), idEntityPair.getKey()).getRoot());
            }
        }
    }

}
