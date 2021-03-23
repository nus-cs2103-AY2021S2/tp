package seedu.budgetbaby.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.budgetbaby.commons.core.LogsCenter;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * Panel containing the list of financial records.
 */
public class FinancialRecordListPanel extends UiPart<Region> {
    private static final String FXML = "FinancialRecordListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FinancialRecordListPanel.class);

    @FXML
    private ListView<FinancialRecord> financialRecordListView;

    /**
     * Creates a {@code FinancialRecordListPanel} with the given {@code ObservableList}.
     */
    public FinancialRecordListPanel(ObservableList<FinancialRecord> financialRecordList) {
        super(FXML);
        financialRecordListView.setItems(financialRecordList);
        financialRecordListView.setCellFactory(listView -> new FinancialRecordListViewCell());
    }

    public void updateObservableList(ObservableList<FinancialRecord> financialRecordList) {
        financialRecordListView.setItems(financialRecordList);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code FinancialRecord}
     * using a {@code FinancialRecordCard}.
     */
    class FinancialRecordListViewCell extends ListCell<FinancialRecord> {
        @Override
        protected void updateItem(FinancialRecord financialRecord, boolean empty) {
            super.updateItem(financialRecord, empty);

            if (empty || financialRecord == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FinancialRecordCard(financialRecord, getIndex() + 1).getRoot());
            }
        }
    }

}
