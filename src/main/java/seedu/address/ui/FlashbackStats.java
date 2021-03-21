package seedu.address.ui;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.flashcard.Question;
import seedu.address.model.flashcard.Statistics;

/**
 * An UI component that displays statistics of {@code Flashcard}.
 */
public class FlashbackStats extends UiPart<Region> {
    private static final String FXML = "FlashbackStats.fxml";
    private static final String LIST_STATS_HEADING = "Overall Statistics of Flashcard List";
    private static final String NOT_YET_REVIEWED_MSG = "You have not reviewed the card(s) yet";

    @FXML
    private Label heading;

    @FXML
    private PieChart piechart;

    @FXML
    private Label stats;

    private final Statistics flashbackStats;

    /**
     * Creates a {@code FlashbackStats} with the given {@code flashbackStats} and {question} to display.
     */
    public FlashbackStats(Statistics flashbackStats, Optional<Question> question) {
        super(FXML);
        this.flashbackStats = flashbackStats;
        if (flashbackStats.getReviewCount() == 0) {
            heading.setText(NOT_YET_REVIEWED_MSG);
        } else {
            setHeading(question);
            setPieChart(flashbackStats);
            setStats(flashbackStats);
        }
    }

    /**
     * Sets the heading of the main statistics pane.
     *
     * @param question An optional parameter. If a question is provided, then the question of the requested flashcard
     *                 is displayed. If no question is provided, a default heading will be shown to indicate that
     *                 the overall statistics is shown.
     */
    private void setHeading(Optional<Question> question) {
        if (question.isPresent()) {
            String qn = question.get().fullQuestion;
            heading.setText(qn);
        } else {
            heading.setText(LIST_STATS_HEADING);
        }
    }

    /**
     * Creates and sets the pie chart in the main statistics pane.
     *
     * @param flashbackStats Statistics used to create the pie chart.
     */
    private void setPieChart(Statistics flashbackStats) {
        double correctRate = flashbackStats.getCorrectRate();
        double wrongRate = 100 - correctRate;
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                    new PieChart.Data(String.format("Correct Rate: %.1f%%", correctRate), correctRate),
                    new PieChart.Data(String.format("Wrong Rate: %.1f%%", wrongRate), wrongRate)
                );
        piechart.setData(pieChartData);
        piechart.setLegendVisible(false);
    }

    /**
     * Sets the review count, correct count and correct rate of the flash card(s) in the secondary statistics pane
     * located at the bottom of the main statistics pane.
     *
     * @param flashbackStats Statistics to show in the secondary statistics pane.
     */
    private void setStats(Statistics flashbackStats) {
        stats.setText(flashbackStats.toString());
    }
}
