package seedu.module.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import seedu.module.model.ModuleManager;

public class StatsPanel extends UiPart<Region> {

    private static final String TITLE = "WorkLoad Distribution";
    private static final String FXML = "StatsPanel.fxml";
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    @FXML
    private PieChart pieChart;

    /**
     * Creates a {@code StatsPanel} with the given {@code ObservableList}.
     */
    public StatsPanel() {
        super(FXML);
        setPieChartData();
        setChart();
        setTitle();
        pieChart.setLegendSide(Side.LEFT);
    }

    /**
     * Adds data into pie chart using existing module list, where each data is the module and corresponds workload.
     */
    private void setPieChartData() {
        pieChartData = ModuleManager.getModulePieChartData();
    }

    private void setTitle() {
        pieChart.setTitle(TITLE);
    }

    private void setChart() {
        pieChart.setData(pieChartData);
    }
}
