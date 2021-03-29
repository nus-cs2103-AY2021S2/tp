package seedu.address.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.session.Session;
import seedu.address.model.student.Student;
import seedu.address.model.tuition.Tuition;

/**
 * Panel containing the list of the upcoming tuition in 1 week.
 */
public class UpcomingTuitionListPanel extends UiPart<Region> {
    private static final String FXML = "UpcomingTuitionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(UpcomingTuitionListPanel.class);

    @FXML
    private ListView<Tuition> upcomingTuitionListView;

    /**
     * Creates a {@code UpcomingTuitionListPanel} with the given {@code ObservableList}.
     */
    public UpcomingTuitionListPanel(ObservableList<Student> studentList) {
        super(FXML);
        ObservableList<Tuition> tuitionList = FXCollections.observableArrayList();
        ObservableList<Tuition> finalTuitionList = FXCollections.observableArrayList();

        populateTuitionList(studentList, tuitionList);
        filterOneWeekTuitionSessions(tuitionList, finalTuitionList);
        sortByDate(finalTuitionList);

        studentList.addListener((ListChangeListener<Student>) change -> {
            while (change.next()) {
                tuitionList.clear();
                finalTuitionList.clear();
                populateTuitionList(studentList, tuitionList);
                filterOneWeekTuitionSessions(tuitionList, finalTuitionList);
                sortByDate(finalTuitionList);
                populateUpcomingTuitionListView(finalTuitionList);
            }
        });

        populateUpcomingTuitionListView(finalTuitionList);
    }

    /**
     * Populates the upcomingTuitionListView with the given {@code ObservableList}
     * @param finalTuitionList
     */
    private void populateUpcomingTuitionListView(ObservableList<Tuition> finalTuitionList) {
        upcomingTuitionListView.setItems(finalTuitionList);
        upcomingTuitionListView.setCellFactory(listView -> new UpcomingTuitionListViewCell());
    }

    /**
     * Sorts tuition by ascending order of date.
     */
    private void sortByDate(ObservableList<Tuition> finalTuitionList) {
        finalTuitionList.sort(new Comparator<Tuition>() {
            @Override
            public int compare(Tuition o1, Tuition o2) {
                return o1.getSession().getSessionDate().getDateTime().compareTo(
                        o2.getSession().getSessionDate().getDateTime()
                );
            }
        });
    }

    /**
     * Filters all tuition 1 week from today.
     */
    private void filterOneWeekTuitionSessions(ObservableList<Tuition> tuitionList,
                                              ObservableList<Tuition> finalTuitionList) {
        LocalDate today = LocalDateTime.now().toLocalDate();
        LocalDate weekToday = today.plusWeeks(1);
        for (Tuition tuition : tuitionList) {
            LocalDate sessionDate = tuition.getSession().getSessionDate().getDate();
            if (today.compareTo(sessionDate) <= 0 && sessionDate.compareTo(weekToday) < 0) {
                finalTuitionList.add(tuition);
            }
        }
    }

    /**
     * Populates the tuitionList with {@code Tuition} for all sessions in the studentList.
     */
    private void populateTuitionList(ObservableList<Student> studentList, ObservableList<Tuition> tuitionList) {
        for (int i = 0; i < studentList.size(); i++) {
            Student currStudent = studentList.get(i);
            for (int j = 0; j < studentList.get(i).getListOfSessions().size(); j++) {
                Session currSession = currStudent.getListOfSessions().get(j);
                tuitionList.add(new Tuition(currStudent, currSession, i, j));
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Tuition} using a {@code UpcomingTuitionCard}.
     */
    class UpcomingTuitionListViewCell extends ListCell<Tuition> {
        @Override
        protected void updateItem(Tuition tuition, boolean empty) {
            super.updateItem(tuition, empty);

            if (empty || tuition == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new UpcomingTuitionCard(tuition).getRoot());
            }
        }
    }
}
