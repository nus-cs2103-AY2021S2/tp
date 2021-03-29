package seedu.address.ui;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.session.Session;
import seedu.address.model.student.Student;
import seedu.address.model.tuition.Tuition;

import static seedu.address.commons.util.DateTimeUtil.isSameDay;

public class CalendarView extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(CalendarView.class);
    private static final String FXML = "CalendarView.fxml";

    private Logic logic;
    private ObservableList<Tuition> tuitionList;

    // must be a monday
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

    public void setStartDate(LocalDateTime startDate) {
        if (startDate.getDayOfWeek() == DayOfWeek.MONDAY) {
            this.calendarStartDate = startDate.withHour(0).withMinute(0).withSecond(0);
            this.populateCalendar();
            this.populateTuitions();
        } else {
            throw new IllegalArgumentException("Start date of calendar must be a Monday!");
        }
    }

    public void advanceCalendar() {
        setStartDate(this.calendarStartDate.plusDays(7));
    }

    public void backwardCalendar() {
        setStartDate(this.calendarStartDate.minusDays(7));
    }

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

    private void populateTuitions() {
        tuitionList.clear();
        ObservableList<Student> studentObservableList = logic.getFilteredStudentList();
        for (int i = 0; i < studentObservableList.size(); i++) {
            for (int j = 0; j < studentObservableList.get(i).getListOfSessions().size(); j++) {
                Student currStudent = studentObservableList.get(i);
                Session currSession = currStudent.getListOfSessions().get(j);
                tuitionList.add(new Tuition(currStudent, currSession, i, j));
                System.out.println(tuitionList);
            }
        }
    }

    private void setStudentListListener() {
        ObservableList<Student> studentObservableList = logic.getFilteredStudentList();
        studentObservableList.addListener((ListChangeListener<Student>) change -> {
            while (change.next()) {
                tuitionList.clear();
                for (int i = 0; i < studentObservableList.size(); i++) {
                    for (int j = 0; j < studentObservableList.get(i).getListOfSessions().size(); j++) {
                        Student currStudent = studentObservableList.get(i);
                        Session currSession = currStudent.getListOfSessions().get(j);
                        tuitionList.add(new Tuition(currStudent, currSession, i, j));
                    }
                }
            }
        });
    }

    private void setListViews(ObservableList<Tuition> tuitionList) {
        monListView.setItems(tuitionList
                .filtered(tuitionItem -> isSameDay(tuitionItem.getSession().getSessionDate().getDateTime(), calendarStartDate))
                .sorted(Comparator.comparing(a -> a.getSession().getSessionDate().getDateTime()))
        );
        monListView.setCellFactory(listView -> new CalendarViewCell());

        tueListView.setItems(tuitionList
                .filtered(tuitionItem -> isSameDay(tuitionItem.getSession().getSessionDate().getDateTime(), calendarStartDate.plusDays(1)))
                .sorted(Comparator.comparing(a -> a.getSession().getSessionDate().getDateTime()))
        );
        tueListView.setCellFactory(listView -> new CalendarViewCell());

        wedListView.setItems(tuitionList
                .filtered(tuitionItem -> isSameDay(tuitionItem.getSession().getSessionDate().getDateTime(), calendarStartDate.plusDays(2)))
                .sorted(Comparator.comparing(a -> a.getSession().getSessionDate().getDateTime()))
        );
        wedListView.setCellFactory(listView -> new CalendarViewCell());

        thurListView.setItems(tuitionList
                .filtered(tuitionItem -> isSameDay(tuitionItem.getSession().getSessionDate().getDateTime(), calendarStartDate.plusDays(3)))
                .sorted(Comparator.comparing(a -> a.getSession().getSessionDate().getDateTime()))
        );
        thurListView.setCellFactory(listView -> new CalendarViewCell());

        friListView.setItems(tuitionList
                .filtered(tuitionItem -> isSameDay(tuitionItem.getSession().getSessionDate().getDateTime(), calendarStartDate.plusDays(4)))
                .sorted(Comparator.comparing(a -> a.getSession().getSessionDate().getDateTime()))
        );
        friListView.setCellFactory(listView -> new CalendarViewCell());

        satListView.setItems(tuitionList
                .filtered(tuitionItem -> isSameDay(tuitionItem.getSession().getSessionDate().getDateTime(), calendarStartDate.plusDays(5)))
                .sorted(Comparator.comparing(a -> a.getSession().getSessionDate().getDateTime()))
        );
        satListView.setCellFactory(listView -> new CalendarViewCell());

        sunListView.setItems(tuitionList
                .filtered(tuitionItem -> isSameDay(tuitionItem.getSession().getSessionDate().getDateTime(), calendarStartDate.plusDays(6)))
                .sorted(Comparator.comparing(a -> a.getSession().getSessionDate().getDateTime()))
        );
        sunListView.setCellFactory(listView -> new CalendarViewCell());
    }
}
