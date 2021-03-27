package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.util.Callback;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.plan.Plan;

/**
 * Panel containing the list of persons.
 */
public class PlanListPanelWithTable extends UiPart<Region> {
    private static final String FXML = "PlanListPanelWithTable.fxml";
    private final Logger logger = LogsCenter.getLogger(PlanListPanelWithTable.class);

    @FXML
    private ListView<Plan> personListView;
    @FXML
    private TableView<Plan> personTableView;
    @FXML
    private TableColumn<Plan, Number> indexCol = new TableColumn<>("Plan#");
    @FXML
    private TableColumn<Plan, String> descriptionCol = new TableColumn<>("Description");
    @FXML
    private TableColumn<Plan, Boolean> isValidCol = new TableColumn<>("IsValid");
    @FXML
    private TableColumn<Plan, Boolean> numMcCol = new TableColumn<>("MCs");
    @FXML
    private TableColumn<Plan, Integer> numSemestersCol = new TableColumn<>("Semesters");
    @FXML
    private TableColumn<Plan, Integer> numModulesCol = new TableColumn<>("NumMods");

    /**
     * Creates a {@code PersonListPanel} and
     * a {@code TableView} with the given {@code ObservableList}.
     */
    public PlanListPanelWithTable(ObservableList<Plan> planList) {
        super(FXML);

        indexCol.setMinWidth(50);
        descriptionCol.setMinWidth(150);
        numMcCol.setMinWidth(135);
        isValidCol.setMinWidth(100);
        numSemestersCol.setMinWidth(135);
        numModulesCol.setMinWidth(135);

        personTableView.setItems(planList);
        personTableView.getColumns().addAll(indexCol, descriptionCol, isValidCol, numMcCol,
                numSemestersCol, numModulesCol);
        indexCol.setCellFactory(new LineNumbersCellFactory<>());
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        isValidCol.setCellValueFactory(new PropertyValueFactory<Plan, Boolean>("isValid"));
        numMcCol.setCellValueFactory(new PropertyValueFactory<Plan, Boolean>("numMcs"));
        numSemestersCol.setCellValueFactory(new PropertyValueFactory<Plan, Integer>("numSemester"));
        numModulesCol.setCellValueFactory(new PropertyValueFactory<Plan, Integer>("numModules"));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Plan} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Plan> {
        @Override
        protected void updateItem(Plan plan, boolean empty) {
            super.updateItem(plan, empty);

            if (empty || plan == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PlanCard(plan, getIndex() + 1).getRoot());
            }
        }
    }

    public class LineNumbersCellFactory<T, E> implements Callback<TableColumn<T, E>, TableCell<T, E>> {

        public LineNumbersCellFactory() {
        }

        @Override
        public TableCell<T, E> call(TableColumn<T, E> param) {
            return new TableCell<T, E>() {
                @Override
                protected void updateItem(E item, boolean empty) {
                    super.updateItem(item, empty);

                    if (!empty) {
                        setText(this.getTableRow().getIndex() + 1 + "");
                    } else {
                        setText("");
                    }
                }
            };
        }
    }
}
