package seedu.address.ui;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.ReadOnlyProjectsFolder;

/**
 * Panel displaying today screen.
 */
public class TodayPanel extends UiPart<Region> {
    private static final String FXML = "TodayPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TodayPanel.class);
    private LocalDate currentDate;
    private ReadOnlyProjectsFolder projectsFolder;

    @FXML
    Label date;

    @FXML
    ListView eventsListView;

    /**
     * Creates a {@code TodayPanel}.
     * @param projectsFolder Projects folder used to create today panel.
     */
    public TodayPanel(ReadOnlyProjectsFolder projectsFolder) {
        super(FXML);

        this.projectsFolder = projectsFolder;
        this.currentDate = LocalDate.now();
        date.setText(DateUtil.decodeDate(currentDate));

        initEventsSection();
        initDeadlinesSection();
    }

    private void initDeadlinesSection() {

    }

    private void initEventsSection() {
        
    }

}
