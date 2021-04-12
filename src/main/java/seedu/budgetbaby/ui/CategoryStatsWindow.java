package seedu.budgetbaby.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import seedu.budgetbaby.commons.core.LogsCenter;
import seedu.budgetbaby.logic.statistics.CategoryStatistics;

/**
 * Controller for a category stats window
 */
public class CategoryStatsWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(CategoryStatsWindow.class);
    private static final String FXML = "CategoryStatsWindow.fxml";
    private static final String DEFAULT_BAR_CLASS = ".default-color0.chart-bar";
    private static final String DEFAULT_BAR_COLOUR = "-fx-bar-fill: #557571;";

    @FXML
    private BarChart<String, Double> statsBarChart;

    /**
     * Creates a new CategoryStatsWindow.
     *
     * @param root Stage to use as the root of the CategoryStatsWindow.
     */
    public CategoryStatsWindow(Stage root, List<CategoryStatistics> categoryStatsList) {
        super(FXML, root);
        updateStatistics(categoryStatsList);
    }

    /**
     * Creates a new CategoryStatsWindow.
     */
    public CategoryStatsWindow(List<CategoryStatistics> categoryStatsList) {
        this(new Stage(), categoryStatsList);
    }

    /**
     * Updates a {@code CategoryStatsWindow} with {@code CategoryStatistics}.
     */
    public void updateStatistics(List<CategoryStatistics> categoryStatsList) {
        statsBarChart.getData().clear();

        XYChart.Series<String, Double> series = new XYChart.Series<>();
        for (int i = 0; i < categoryStatsList.size(); i++) {
            CategoryStatistics categoryStats = categoryStatsList.get(i);
            String categoryName = categoryStats.getCategory().toString();
            categoryName = categoryName.substring(1, categoryName.length() - 1); // category name with [ and ] stripped
            double categoryAmount = categoryStats.getAmount();

            XYChart.Data<String, Double> entry = new XYChart.Data<>(categoryName, categoryAmount);
            series.getData().add(entry);
        }

        statsBarChart.getData().add(series);
        statsBarChart.lookupAll(DEFAULT_BAR_CLASS)
                .forEach(n -> n.setStyle(DEFAULT_BAR_COLOUR));
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing category statistics.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
