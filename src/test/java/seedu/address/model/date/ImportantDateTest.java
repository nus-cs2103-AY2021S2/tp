package seedu.address.model.date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IMPORTANT_DATE_DESCRIPTION_RAFFLES_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IMPORTANT_DATE_DETAILS_RAFFLES_EXAM;
import static seedu.address.testutil.TypicalDates.JURONG_SEC2_EXAM;
import static seedu.address.testutil.TypicalDates.RAFFLES_EXAM;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ImportantDateBuilder;


public class ImportantDateTest {

    @Test
    public void isSameImportantDate() {
        // same object -> returns true
        assertTrue(JURONG_SEC2_EXAM.isSameImportantDate(JURONG_SEC2_EXAM));

        // null -> returns false
        assertFalse(JURONG_SEC2_EXAM.isSameImportantDate(null));

        // same description, all other attributes different -> returns true
        ImportantDate editedJurongSec2Exam =
            new ImportantDateBuilder(JURONG_SEC2_EXAM).withDetails(VALID_IMPORTANT_DATE_DETAILS_RAFFLES_EXAM).build();
        assertTrue(JURONG_SEC2_EXAM.isSameImportantDate(editedJurongSec2Exam));

        // different description, all other attributes same -> returns false
        editedJurongSec2Exam =
            new ImportantDateBuilder(JURONG_SEC2_EXAM).withDescription(VALID_IMPORTANT_DATE_DESCRIPTION_RAFFLES_EXAM)
                .build();
        assertFalse(JURONG_SEC2_EXAM.isSameImportantDate(editedJurongSec2Exam));

        // description differs in case, all other attributes same -> returns true
        ImportantDate editedRafflesExam =
            new ImportantDateBuilder(RAFFLES_EXAM)
                .withDescription(VALID_IMPORTANT_DATE_DESCRIPTION_RAFFLES_EXAM.toLowerCase()).build();
        assertTrue(RAFFLES_EXAM.isSameImportantDate(editedRafflesExam));

        // description has trailing spaces, all other attributes same -> returns true
        String descriptionWithTrailingSpaces = VALID_IMPORTANT_DATE_DESCRIPTION_RAFFLES_EXAM + " ";
        editedRafflesExam = new ImportantDateBuilder(RAFFLES_EXAM)
            .withDescription(descriptionWithTrailingSpaces).build();
        assertTrue(RAFFLES_EXAM.isSameImportantDate(editedRafflesExam));
    }

    @Test
    public void equals() {
        // same values -> returns true
        ImportantDate jurongSec2ExamCopy = new ImportantDateBuilder(JURONG_SEC2_EXAM).build();
        assertTrue(JURONG_SEC2_EXAM.equals(jurongSec2ExamCopy));

        // same object -> returns true
        assertTrue(JURONG_SEC2_EXAM.equals(JURONG_SEC2_EXAM));

        // null -> returns false
        assertFalse(JURONG_SEC2_EXAM.equals(null));

        // different type -> returns false
        assertFalse(JURONG_SEC2_EXAM.equals(5));

        // different important date -> returns false
        assertFalse(JURONG_SEC2_EXAM.equals(RAFFLES_EXAM));

        // different description -> returns false
        ImportantDate editedJurongSec2Exam = new ImportantDateBuilder(JURONG_SEC2_EXAM)
            .withDescription(VALID_IMPORTANT_DATE_DESCRIPTION_RAFFLES_EXAM).build();
        assertFalse(JURONG_SEC2_EXAM.equals(editedJurongSec2Exam));

        // different details -> returns false
        editedJurongSec2Exam = new ImportantDateBuilder(JURONG_SEC2_EXAM)
            .withDetails(VALID_IMPORTANT_DATE_DETAILS_RAFFLES_EXAM).build();
        assertFalse(JURONG_SEC2_EXAM.equals(editedJurongSec2Exam));
    }
}
