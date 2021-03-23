package seedu.address.ui;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.DateUtil;

/**
 * Panel displaying today screen.
 */
public class TodayPanel extends UiPart<Region> {
    private static final String FXML = "TodayPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TodayPanel.class);
    private LocalDate currentDate;

    @FXML
    Label date;

    /**
     * Creates a {@code TodayPanel}.
     */
    public TodayPanel() {
        super(FXML);

        currentDate = LocalDate.now();
        date.setText(DateUtil.decodeDate(currentDate));

        initEventsSection();
        initDeadlinesSection();
    }

    private void initDeadlinesSection() {

    }

    private void initEventsSection() {

    }

}
