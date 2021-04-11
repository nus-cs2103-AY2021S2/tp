package seedu.weeblingo.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.weeblingo.testutil.Assert.assertThrows;
import static seedu.weeblingo.testutil.ScoreUtil.DATA_INVALID_1;
import static seedu.weeblingo.testutil.ScoreUtil.DATA_INVALID_2;
import static seedu.weeblingo.testutil.ScoreUtil.DATA_INVALID_3;
import static seedu.weeblingo.testutil.ScoreUtil.DATA_INVALID_4;
import static seedu.weeblingo.testutil.ScoreUtil.DATA_INVALID_5;
import static seedu.weeblingo.testutil.ScoreUtil.DATA_INVALID_6;
import static seedu.weeblingo.testutil.ScoreUtil.DATA_VALID_1;
import static seedu.weeblingo.testutil.ScoreUtil.DATA_VALID_2;
import static seedu.weeblingo.testutil.ScoreUtil.DATA_VALID_3;
import static seedu.weeblingo.testutil.ScoreUtil.DATA_VALID_4;
import static seedu.weeblingo.testutil.ScoreUtil.DATETIME_INVALID_1;
import static seedu.weeblingo.testutil.ScoreUtil.DATETIME_INVALID_2;
import static seedu.weeblingo.testutil.ScoreUtil.DATETIME_INVALID_3;
import static seedu.weeblingo.testutil.ScoreUtil.DATETIME_INVALID_4;
import static seedu.weeblingo.testutil.ScoreUtil.DATETIME_INVALID_5;
import static seedu.weeblingo.testutil.ScoreUtil.DATETIME_INVALID_6;
import static seedu.weeblingo.testutil.ScoreUtil.DATETIME_VALID_1;
import static seedu.weeblingo.testutil.ScoreUtil.DATETIME_VALID_2;
import static seedu.weeblingo.testutil.ScoreUtil.DATETIME_VALID_3;
import static seedu.weeblingo.testutil.ScoreUtil.DATETIME_VALID_4;
import static seedu.weeblingo.testutil.ScoreUtil.DATETIME_VALID_5;
import static seedu.weeblingo.testutil.ScoreUtil.DATETIME_VALID_6;
import static seedu.weeblingo.testutil.ScoreUtil.RATIO_VALID;
import static seedu.weeblingo.testutil.ScoreUtil.TIME_INVALID_1;
import static seedu.weeblingo.testutil.ScoreUtil.TIME_INVALID_2;
import static seedu.weeblingo.testutil.ScoreUtil.TIME_INVALID_3;
import static seedu.weeblingo.testutil.ScoreUtil.TIME_INVALID_4;
import static seedu.weeblingo.testutil.ScoreUtil.TIME_INVALID_5;
import static seedu.weeblingo.testutil.ScoreUtil.TIME_INVALID_6;
import static seedu.weeblingo.testutil.ScoreUtil.TIME_VALID;

import org.junit.jupiter.api.Test;

/**
 * Test class for JsonAdaptedScore.
 */
public class JsonAdaptedScoreTest {

    @Test
    public void toModelType_allValid_nothingIsThrown() {
        JsonAdaptedScore score1 =
                new JsonAdaptedScore(DATETIME_VALID_1, TIME_VALID, DATA_VALID_1[0], DATA_VALID_1[1], RATIO_VALID);
        assertDoesNotThrow(score1::toModelType);

        JsonAdaptedScore score2 =
                new JsonAdaptedScore(DATETIME_VALID_2, TIME_VALID, DATA_VALID_2[0], DATA_VALID_2[1], RATIO_VALID);
        assertDoesNotThrow(score2::toModelType);

        JsonAdaptedScore score3 =
                new JsonAdaptedScore(DATETIME_VALID_3, TIME_VALID, DATA_VALID_3[0], DATA_VALID_3[1], RATIO_VALID);
        assertDoesNotThrow(score3::toModelType);

        JsonAdaptedScore score4 =
                new JsonAdaptedScore(DATETIME_VALID_4, TIME_VALID, DATA_VALID_4[0], DATA_VALID_4[1], RATIO_VALID);
        assertDoesNotThrow(score4::toModelType);

        JsonAdaptedScore score5 =
                new JsonAdaptedScore(DATETIME_VALID_5, TIME_VALID, DATA_VALID_1[0], DATA_VALID_1[1], RATIO_VALID);
        assertDoesNotThrow(score5::toModelType);

        JsonAdaptedScore score6 =
                new JsonAdaptedScore(DATETIME_VALID_6, TIME_VALID, DATA_VALID_2[0], DATA_VALID_2[1], RATIO_VALID);
        assertDoesNotThrow(score6::toModelType);
    }

    @Test
    public void toModelType_invalidDatetime_throwsException() {
        JsonAdaptedScore score1 =
                new JsonAdaptedScore(DATETIME_INVALID_1, TIME_VALID, DATA_VALID_1[0], DATA_VALID_1[1], RATIO_VALID);
        assertThrows(Exception.class, score1::toModelType);

        JsonAdaptedScore score2 =
                new JsonAdaptedScore(DATETIME_INVALID_2, TIME_VALID, DATA_VALID_2[0], DATA_VALID_2[1], RATIO_VALID);
        assertThrows(Exception.class, score2::toModelType);

        JsonAdaptedScore score3 =
                new JsonAdaptedScore(DATETIME_INVALID_3, TIME_VALID, DATA_VALID_3[0], DATA_VALID_3[1], RATIO_VALID);
        assertThrows(Exception.class, score3::toModelType);

        JsonAdaptedScore score4 =
                new JsonAdaptedScore(DATETIME_INVALID_4, TIME_VALID, DATA_VALID_4[0], DATA_VALID_4[1], RATIO_VALID);
        assertThrows(Exception.class, score4::toModelType);

        JsonAdaptedScore score5 =
                new JsonAdaptedScore(DATETIME_INVALID_5, TIME_VALID, DATA_VALID_1[0], DATA_VALID_1[1], RATIO_VALID);
        assertThrows(Exception.class, score5::toModelType);

        JsonAdaptedScore score6 =
                new JsonAdaptedScore(DATETIME_INVALID_6, TIME_VALID, DATA_VALID_2[0], DATA_VALID_2[1], RATIO_VALID);
        assertThrows(Exception.class, score6::toModelType);
    }

    @Test
    public void toModelType_invalidDuration_throwsException() {
        JsonAdaptedScore score1 =
                new JsonAdaptedScore(DATETIME_VALID_1, TIME_INVALID_1, DATA_VALID_1[0], DATA_VALID_1[1], RATIO_VALID);
        assertThrows(Exception.class, score1::toModelType);

        JsonAdaptedScore score2 =
                new JsonAdaptedScore(DATETIME_VALID_2, TIME_INVALID_2, DATA_VALID_2[0], DATA_VALID_2[1], RATIO_VALID);
        assertThrows(Exception.class, score2::toModelType);

        JsonAdaptedScore score3 =
                new JsonAdaptedScore(DATETIME_VALID_3, TIME_INVALID_3, DATA_VALID_3[0], DATA_VALID_3[1], RATIO_VALID);
        assertThrows(Exception.class, score3::toModelType);

        JsonAdaptedScore score4 =
                new JsonAdaptedScore(DATETIME_VALID_4, TIME_INVALID_4, DATA_VALID_4[0], DATA_VALID_4[1], RATIO_VALID);
        assertThrows(Exception.class, score4::toModelType);

        JsonAdaptedScore score5 =
                new JsonAdaptedScore(DATETIME_VALID_5, TIME_INVALID_5, DATA_VALID_1[0], DATA_VALID_1[1], RATIO_VALID);
        assertThrows(Exception.class, score5::toModelType);

        JsonAdaptedScore score6 =
                new JsonAdaptedScore(DATETIME_VALID_6, TIME_INVALID_6, DATA_VALID_2[0], DATA_VALID_2[1], RATIO_VALID);
        assertThrows(Exception.class, score6::toModelType);
    }

    @Test
    public void toModelType_invalidData_throwsException() {
        JsonAdaptedScore score1 =
                new JsonAdaptedScore(DATETIME_VALID_1, TIME_VALID, DATA_INVALID_1[0], DATA_INVALID_1[1], RATIO_VALID);
        assertThrows(Exception.class, score1::toModelType);

        JsonAdaptedScore score2 =
                new JsonAdaptedScore(DATETIME_VALID_2, TIME_VALID, DATA_INVALID_2[0], DATA_INVALID_2[1], RATIO_VALID);
        assertThrows(Exception.class, score2::toModelType);

        JsonAdaptedScore score3 =
                new JsonAdaptedScore(DATETIME_VALID_3, TIME_VALID, DATA_INVALID_3[0], DATA_INVALID_3[1], RATIO_VALID);
        assertThrows(Exception.class, score3::toModelType);

        JsonAdaptedScore score4 =
                new JsonAdaptedScore(DATETIME_VALID_4, TIME_VALID, DATA_INVALID_4[0], DATA_INVALID_4[1], RATIO_VALID);
        assertThrows(Exception.class, score4::toModelType);

        JsonAdaptedScore score5 =
                new JsonAdaptedScore(DATETIME_VALID_5, TIME_VALID, DATA_INVALID_5[0], DATA_INVALID_5[1], RATIO_VALID);
        assertThrows(Exception.class, score5::toModelType);

        JsonAdaptedScore score6 =
                new JsonAdaptedScore(DATETIME_VALID_6, TIME_VALID, DATA_INVALID_6[0], DATA_INVALID_6[1], RATIO_VALID);
        assertThrows(Exception.class, score6::toModelType);
    }
}
