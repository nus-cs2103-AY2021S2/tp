package seedu.address.ui;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.fee.Month;
import seedu.address.model.fee.MonthlyFee;
import seedu.address.model.fee.Year;
import seedu.address.model.session.Session;
import seedu.address.model.student.Student;

/**
 * Panel containing the list of the upcoming tuition in 1 week.
 */
public class MonthlyFeeListPanel extends UiPart<Region> {
    private static final String FXML = "MonthlyFeeListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MonthlyFeeListPanel.class);

    @FXML
    private ListView<MonthlyFee> monthlyFeeListView;

    /**
     * Creates a {@code MonthlyFeeListPanel} with the given {@code ObservableList}.
     */
    public MonthlyFeeListPanel(ObservableList<Student> studentList) {
        super(FXML);
        ObservableList<MonthlyFee> monthlyFeeList = FXCollections.observableArrayList();

        populateMonthlyFeeList(studentList, monthlyFeeList);
        System.out.println("Size is: " + monthlyFeeList.size());

        studentList.addListener((ListChangeListener<Student>) change -> {
            while (change.next()) {
                monthlyFeeList.clear();
                populateMonthlyFeeList(studentList, monthlyFeeList);
                populateMonthlyFeeListView(monthlyFeeList);
            }
        });

        populateMonthlyFeeListView(monthlyFeeList);
    }

    /**
     * Populates the MonthlyFeeListViewCell with the given {@code ObservableList}
     * @param monthlyFeeList Represents an observable monthly fee list
     */
    private void populateMonthlyFeeListView(ObservableList<MonthlyFee> monthlyFeeList) {
        monthlyFeeListView.setItems(monthlyFeeList);
        monthlyFeeListView.setCellFactory(listView -> new MonthlyFeeListViewCell());
    }

    /**
     * Gets the local date time format of the month and year combined
     * @return LocalDateTime of the month and year combined
     */
    private LocalDateTime getLocalDate(Month month, Year year) {
        requireAllNonNull(month, year);
        return LocalDateTime.of(year.getYear(), month.getMonth(), 1, 0, 0);
    }

    /**
     * Populates the tuitionList with {@code Tuition} for all sessions in the studentList.
     */
    private void populateMonthlyFeeList(ObservableList<Student> studentList,
        ObservableList<MonthlyFee> monthlyFeeList) {
        LocalDateTime currMonthYear;
        LocalDateTime nextMonthYear;

        LocalDateTime now = LocalDateTime.now();
        Month month = new Month(now.getMonth().getValue());
        Year year = new Year(now.getYear());
        currMonthYear = getLocalDate(month, year);
        nextMonthYear = currMonthYear.plusMonths(1);

        for (int i = 0; i < 3; i++) {
            // Get current month value and add the result to the resulting string
            double currMonthlyFee = getCurrMonthFee(studentList, currMonthYear, nextMonthYear);
            monthlyFeeList.add(new MonthlyFee(currMonthlyFee, new Month(currMonthYear.getMonth().getValue()),
                new Year(currMonthYear.getYear())));
            // Update to previous month
            currMonthYear = currMonthYear.minusMonths(1);
            nextMonthYear = currMonthYear.plusMonths(1);
        }
    }

    private double getCurrMonthFee(ObservableList<Student> studentList, LocalDateTime startPeriod,
        LocalDateTime endPeriod) {
        double fee = 0;
        for (Student student : studentList) {
            fee += getFeePerStudent(student, startPeriod, endPeriod);
        }
        return fee;
    }

    public double getFeePerStudent(Student student, LocalDateTime startPeriod, LocalDateTime endPeriod) {
        double fee = 0;
        for (Session session : student.getListOfSessions()) {
            LocalDateTime dateTime = session.getSessionDate().getDateTime();
            if (dateTime.compareTo(startPeriod) >= 0 && dateTime.compareTo(endPeriod) < 0) {
                // This session date is within the period
                fee += session.getFee().getFee();
            }
        }
        return fee;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Tuition} using a {@code UpcomingTuitionCard}.
     */
    static class MonthlyFeeListViewCell extends ListCell<MonthlyFee> {
        @Override
        protected void updateItem(MonthlyFee monthlyFee, boolean empty) {
            super.updateItem(monthlyFee, empty);

            if (empty || monthlyFee == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MonthlyFeeCard(monthlyFee).getRoot());
            }
        }
    }
}
