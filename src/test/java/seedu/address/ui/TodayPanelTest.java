package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.util.DateUtil.decodeDate;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import guitests.guihandles.TodayPanelHandle;
import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.model.ReadOnlyColabFolder;

/**
 * Contains tests for the {@code TodayPanel}.
 */
public class TodayPanelTest extends GuiUnitTest {
    private TodayPanelHandle todayPanelHandle;

    @Test
    public void displayDate_success() throws DateConversionException {
        LocalDate date = LocalDate.of(2021, 1, 1);
        initUi(getTypicalColabFolder(), date);

        assertEquals(todayPanelHandle.getDisplayedDate(), decodeDate(date));
    }


    /**
     * Initializes {@code todayPanelHandle} with a {@code TodayPanel} backed by a {@code ReadOnlyColabFolder}.
     *
     * @param colabFolder {@code ReadOnlyColabFolder} that backs the {@code TodayPanel} to be created.
     * @param date {@code LocalDate} to be displayed in the UI.
     */
    private void initUi(ReadOnlyColabFolder colabFolder, LocalDate date) {
        TodayPanel todayPanel =
                new TodayPanel(colabFolder, date);
        uiPartExtension.setUiPart(todayPanel);

        todayPanelHandle = new TodayPanelHandle(todayPanel.getRoot());
    }
}
