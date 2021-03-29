package seedu.address.ui;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.student.Student;
import seedu.address.model.tuition.Tuition;

public class CalendarView extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(CalendarView.class);
    private static final String FXML = "CalendarView.fxml";

    private Logic logic;
    private ObservableList<Tuition> tuitionList;

    // must be a monday, change through setStartDate()
    private LocalDateTime calendarStartDate;

    // FXML date headers (mon - sun)
    @FXML private Label dateHeaderMon;
    @FXML private Label dateHeaderTue;
    @FXML private Label dateHeaderWed;
    @FXML private Label dateHeaderThur;
    @FXML private Label dateHeaderFri;
    @FXML private Label dateHeaderSat;
    @FXML private Label dateHeaderSun;


    // FXML list views (mon - sun)
    @FXML private Label dateLabel;
    @FXML private ListView<Tuition> monListView;
    @FXML private ListView<Tuition> tueListView;
    @FXML private ListView<Tuition> wedListView;
    @FXML private ListView<Tuition> thurListView;
    @FXML private ListView<Tuition> friListView;
    @FXML private ListView<Tuition> satListView;
    @FXML private ListView<Tuition> sunListView;

    public CalendarView(Logic logic) {
        super(FXML);
        this.logic = logic;

        tuitionList = FXCollections.observableArrayList();
        LocalDateTime currentMonday = LocalDateTime.now().with(DayOfWeek.MONDAY);

        setStartDate(currentMonday);
        populateTuitions();
        setStudentListListener();
        setListViews(tuitionList);
    }

    class CalendarViewCell extends ListCell<Tuition> {
        @Override
        protected void updateItem(Tuition tuition, boolean empty) {
            super.updateItem(tuition, empty);

            if (empty || tuition == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CalendarCard(tuition).getRoot());
            }
        }
    }

    /**
     * Changes the start date of the week displayed on calendar and sessions associated with the week
     * @param startDate Date of monday of the intended week to be displayed on calendar
     */
    public void setStartDate(LocalDateTime startDate) {
        if (startDate.getDayOfWeek() == DayOfWeek.MONDAY) {
            this.calendarStartDate = startDate.withHour(0).withMinute(0).withSecond(0);
            this.populateCalendar();
            this.populateTuitions();
        } else {
            throw new IllegalArgumentException("Start date of calendar must be a Monday!");
        }
    }

    /**
     * Advances the calendar by a week (7 days)
     */
    public void advanceCalendar() {
        setStartDate(this.calendarStartDate.plusDays(7));
    }

    /**
     * Moves the calendar backwards by a week (7 days)
     */
    public void backwardCalendar() {
        setStartDate(this.calendarStartDate.minusDays(7));
    }

    /**
     * Changes the labels of Calendar based on the current calendar start date
     */
    private void populateCalendar() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM");
        LocalDateTime calendarEndDate = calendarStartDate.plusDays(6);

        // change main label
        String label = calendarStartDate.format(dateFormatter) + " - " + calendarEndDate.format(dateFormatter);
        dateLabel.setText(label);

        // change date headers
        dateHeaderMon.setText(calendarStartDate.format(dateFormatter));
        dateHeaderTue.setText(calendarStartDate.plusDays(1).format(dateFormatter));
        dateHeaderWed.setText(calendarStartDate.plusDays(2).format(dateFormatter));
        dateHeaderThur.setText(calendarStartDate.plusDays(3).format(dateFormatter));
        dateHeaderFri.setText(calendarStartDate.plusDays(4).format(dateFormatter));
        dateHeaderSat.setText(calendarStartDate.plusDays(5).format(dateFormatter));
        dateHeaderSun.setText(calendarStartDate.plusDays(6).format(dateFormatter));
    }

    /**
     * Helper method to populate list of {@code Tuition} onto the Calendar's tuition list
     */
    private void populateTuitions() {
        tuitionList.clear();
        ObservableList<Student> studentObservableList = logic.getFilteredStudentList();
        for (int i = 0; i < studentObservableList.size(); i++) {
            for (int j = 0; j < studentObservableList.get(i).getListOfSessions().size(); j++) {
                Student currStudent = studentObservableList.get(i);
                Session currSession = currStudent.getListOfSessions().get(j);
                tuitionList.add(new Tuition(currStudent, currSession, i, j));
            }
        }
    }

    /**
     * Invokes listener method to update calendar's tuition list upon new updates
     */
    private void setStudentListListener() {
        ObservableList<Student> studentObservableList = logic.getFilteredStudentList();
        studentObservableList.addListener((ListChangeListener<Student>) change -> {
            while (change.next()) {
                populateTuitions();
            }
        });
    }

    /**
     * Helper method for checking if session (individual and recurring) takes place on a particular date
     */
    private boolean hasSessionOnDate(LocalDateTime date, Tuition tuition) {
        Session tuitionSession = tuition.getSession();
        LocalDateTime tuitionDateTime = tuitionSession.getSessionDate().getDateTime();
        boolean isSessionOnDate = tuitionDateTime.toLocalDate().isEqual(date.toLocalDate());
        boolean isRecurringSessionOnDate = false;

        if (tuitionSession instanceof RecurringSession) {
            RecurringSession recurringTuitionSession = (RecurringSession) tuitionSession;
            isRecurringSessionOnDate = recurringTuitionSession.hasSessionOnDate(new SessionDate(date));
        }

        return isSessionOnDate || isRecurringSessionOnDate;
    }

    /**
     * Binds Monday - Sunday {@code ListView} to respective filtered and sorted tuition list
     */
    private void setListViews(ObservableList<Tuition> tuitionList) {

        Comparator<Tuition> cmpSessionDate = Comparator.comparing(a -> a.getSession().getSessionDate().getTime());

        monListView.setItems(tuitionList
                .filtered(tuitionItem -> hasSessionOnDate(calendarStartDate, tuitionItem))
                .sorted(cmpSessionDate)
        );
        monListView.setCellFactory(listView -> new CalendarViewCell());

        tueListView.setItems(tuitionList
                .filtered(tuitionItem -> hasSessionOnDate(calendarStartDate.plusDays(1), tuitionItem))
                .sorted(cmpSessionDate)
        );
        tueListView.setCellFactory(listView -> new CalendarViewCell());

        wedListView.setItems(tuitionList
                .filtered(tuitionItem -> hasSessionOnDate(calendarStartDate.plusDays(2), tuitionItem))
                .sorted(cmpSessionDate)
        );
        wedListView.setCellFactory(listView -> new CalendarViewCell());

        thurListView.setItems(tuitionList
                .filtered(tuitionItem -> hasSessionOnDate(calendarStartDate.plusDays(3), tuitionItem))
                .sorted(cmpSessionDate)
        );
        thurListView.setCellFactory(listView -> new CalendarViewCell());

        friListView.setItems(tuitionList
                .filtered(tuitionItem -> hasSessionOnDate(calendarStartDate.plusDays(4), tuitionItem))
                .sorted(cmpSessionDate)
        );
        friListView.setCellFactory(listView -> new CalendarViewCell());

        satListView.setItems(tuitionList
                .filtered(tuitionItem -> hasSessionOnDate(calendarStartDate.plusDays(5), tuitionItem))
                .sorted(cmpSessionDate)
        );
        satListView.setCellFactory(listView -> new CalendarViewCell());

        sunListView.setItems(tuitionList
                .filtered(tuitionItem -> hasSessionOnDate(calendarStartDate.plusDays(6), tuitionItem))
                .sorted(cmpSessionDate)
        );
        sunListView.setCellFactory(listView -> new CalendarViewCell());
    }
}
