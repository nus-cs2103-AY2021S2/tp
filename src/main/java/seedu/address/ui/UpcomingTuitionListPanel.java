package seedu.address.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.student.Student;
import seedu.address.model.tuition.Tuition;

/**
 * Panel containing the list of the upcoming tuition in 1 week.
 */
public class UpcomingTuitionListPanel extends UiPart<Region> {
    private static final String FXML = "UpcomingTuitionListPanel.fxml";

    @FXML
    private ListView<Tuition> upcomingTuitionListView;

    /**
     * Creates a {@code UpcomingTuitionListPanel} with the given {@code ObservableList}.
     */
    public UpcomingTuitionListPanel(ObservableList<Student> studentList) {
        super(FXML);
        ObservableList<Tuition> tuitionList = FXCollections.observableArrayList();
        populateTuitionList(studentList, tuitionList);
        sortByDate(tuitionList);
        addListener(studentList, tuitionList);
        populateUpcomingTuitionListView(tuitionList);
    }

    private void addListener(ObservableList<Student> studentList, ObservableList<Tuition> tuitionList) {
        studentList.addListener((ListChangeListener<Student>) change -> {
            while (change.next()) {
                tuitionList.clear();
                populateTuitionList(studentList, tuitionList);
                sortByDate(tuitionList);
            }
        });
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
            public int compare(Tuition tuition1, Tuition tuition2) {
                return tuition1.getSession().getSessionDate().getDateTime().compareTo(
                        tuition2.getSession().getSessionDate().getDateTime()
                );
            }
        });
    }

    /**
     * Populates the tuitionList with {@code Tuition} for all sessions in the studentList between today and
     * the day after tomorrow.
     */
    private void populateTuitionList(ObservableList<Student> studentList, ObservableList<Tuition> tuitionList) {

        SessionDate today = new SessionDate(LocalDateTime.now());
        SessionDate tomorrow = new SessionDate(LocalDateTime.now().plusDays(1));
        SessionDate dayAfterTomorrow = new SessionDate(LocalDateTime.now().plusDays(2));

        for (int i = 0; i < studentList.size(); i++) {
            Student currStudent = studentList.get(i);
            for (int j = 0; j < studentList.get(i).getListOfSessions().size(); j++) {
                Session currSession = currStudent.getListOfSessions().get(j);
                if (currSession instanceof RecurringSession) {
                    RecurringSession recurringSession = (RecurringSession) currSession;

                    if (!recurringSession.endBefore(today)) {
                        if (recurringSession.hasSessionOnDate(today)) {
                            tuitionList.add(new Tuition(currStudent,
                                    recurringSession.buildSessionOnDate(today.getDate()), i, j));
                        }
                        if (recurringSession.hasSessionOnDate(tomorrow)) {
                            tuitionList.add(new Tuition(currStudent,
                                    recurringSession.buildSessionOnDate(tomorrow.getDate()), i, j));
                        }
                        if (recurringSession.hasSessionOnDate(dayAfterTomorrow)) {
                            tuitionList.add(new Tuition(currStudent,
                                    recurringSession.buildSessionOnDate(dayAfterTomorrow.getDate()), i, j));
                        }
                    }
                } else {
                    LocalDate dateOfSingleSession = currSession.getSessionDate().getDate();
                    if (isWithinThreeDaysRange(today, dayAfterTomorrow, dateOfSingleSession)) {
                        tuitionList.add(new Tuition(currStudent, currSession, i, j));
                    }
                }
            }
        }
    }

    /**
     * Returns true if dateOfSingleSession is between today and the day after tomorrow.
     */
    private boolean isWithinThreeDaysRange(SessionDate today, SessionDate dayAfterTomorrow,
                                           LocalDate dateOfSingleSession) {
        assert today.getDateTime().toLocalDate().plusDays(2).equals(dayAfterTomorrow.getDateTime().toLocalDate())
                : "dayAfterTomorrow should be 2 days after today!";
        return today.getDate().compareTo(dateOfSingleSession) <= 0
                && dateOfSingleSession.compareTo(dayAfterTomorrow.getDate()) <= 0;
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
