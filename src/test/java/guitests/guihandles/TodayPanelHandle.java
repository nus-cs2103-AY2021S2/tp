package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Labeled;

/**
 * Provides a handle for {@code TodayPanel} containing the list of {@code EventCard} and
 * {@code CompletableDeadlineCard}.
 */
public class TodayPanelHandle extends NodeHandle<Node> {
    public static final String DATE_ID = "#date";
    private static final String EVENTS_PLACEHOLDER_ID = "#eventsListViewPlaceholder";
    private static final String DEADLINES_PLACEHOLDER_ID = "#deadlinesListViewPlaceholder";

    /**
     * Constructs a {@code TodayPanelHandle} handler object.
     *
     * @param todayPanelNode Node of the {@code TodayPanel}.
     */
    public TodayPanelHandle(Node todayPanelNode) {
        super(todayPanelNode);
    }

    /**
     * Returns the date displayed in the {@code TodoCard}.
     *
     * @return {@code LocalDate} displayed in the {@code TodoCard}.
     */
    public String getDisplayedDate() {
        Labeled date = getChildNode(DATE_ID);
        return date.getText();
    }
}
