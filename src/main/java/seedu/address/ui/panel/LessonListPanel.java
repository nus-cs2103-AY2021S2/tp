package seedu.address.ui.panel;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.lesson.Lesson;
import seedu.address.ui.UiPart;
import seedu.address.ui.card.LessonListCard;

/**
 * Panel containing the list of Lessons.
 */
public class LessonListPanel extends UiPart<Region> {
    private static final String FXML = "LessonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(LessonListPanel.class);

    @FXML
    private ListView<Lesson> lessonListView;

    /**
     * Creates a {@code LessonListPanel} with the given {@code ObservableList}.
     */
    public LessonListPanel(ObservableList<Lesson> lessonList) {
        super(FXML);
        lessonListView.setItems(lessonList);
        lessonListView.setCellFactory(listView -> new LessonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class LessonListViewCell extends ListCell<Lesson> {
        @Override
        protected void updateItem(Lesson lesson, boolean empty) {
            super.updateItem(lesson, empty);

            if (empty || lesson == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LessonListCard(lesson).getRoot());
            }
        }
    }

}
