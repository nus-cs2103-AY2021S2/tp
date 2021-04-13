package seedu.plan.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.util.Callback;
import seedu.plan.commons.core.LogsCenter;
import seedu.plan.model.plan.Plan;

/**
 * Panel containing the list of persons.
 */
public class PlanListPanelWithTable extends UiPart<Region> {
    private static final String FXML = "PlanListPanelWithTable.fxml";
    private final Logger logger = LogsCenter.getLogger(PlanListPanelWithTable.class);

    @FXML
    private ListView<Plan> planListView;
    @FXML
    private TableView<Plan> planTableView;
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

        indexCol.setMinWidth(80);
        descriptionCol.setMinWidth(150);
        numMcCol.setMinWidth(135);
        isValidCol.setMinWidth(100);
        numSemestersCol.setMinWidth(135);
        numModulesCol.setMinWidth(135);

        planTableView.setItems(planList);
        planTableView.getColumns().addAll(indexCol, descriptionCol, isValidCol, numMcCol,
                numSemestersCol, numModulesCol);
        indexCol.setCellFactory(new LineNumbersCellFactory<>());
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        isValidCol.setCellValueFactory(new PropertyValueFactory<Plan, Boolean>("isValid"));
        numMcCol.setCellValueFactory(new PropertyValueFactory<Plan, Boolean>("numMcs"));
        numSemestersCol.setCellValueFactory(new PropertyValueFactory<Plan, Integer>("numSemester"));
        numModulesCol.setCellValueFactory(new PropertyValueFactory<Plan, Integer>("numModules"));
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
                        if (!(this.getTableRow() == null)) {
                            setText(this.getTableRow().getIndex() + 1 + "");
                        }
                    } else {
                        setText("");
                    }
                }
            };
        }
    }
}
