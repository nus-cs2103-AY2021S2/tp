package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGrades.MATHS_GRADE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.common.Description;
import seedu.address.model.common.Title;
import seedu.address.model.grade.GradeEnum;
import seedu.address.model.grade.GradedItem;
import seedu.address.model.reminder.ReminderDate;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutor.Address;
import seedu.address.model.tutor.Email;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.Phone;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String VALID_SCHEDULE_TITLE = "Maths Homework";
    private static final String VALID_SCHEDULE_DATE_TIME = "2021-05-24 10:00 AM";
    private static final String VALID_SCHEDULE_DESCRIPTION = "Chapter 5 Page 841";

    private static final String INVALID_TITLE = "H@mework";
    private static final String INVALID_DATE_TIME = "2/5/2021 11:00 AM";
    private static final String INVALID_DESCRIPTION = " ";

    private static final String VALID_DATE = "2021-05-24";
    private static final String INVALID_DATE = "2/5/2021";

    private static final String INVALID_SUBJECT_IN_GRADE = "Maths&*^";
    private static final String INVALID_LONG_SUBJECT_NAME = "charactersmorethantwenty";
    private static final String INVALID_GRADED_ITEM = "Midterm&"; // '&' not allowed in graded items
    private static final String INVALID_LONG_GRADED_ITEM = "charactersmorethannnnnnn25";
    private static final String INVALID_GRADE = "A-"; // '-' not allowed in grade

    private static final String VALID_SUBJECT_NAME = "Mathematics";
    private static final String VALID_GRADED_ITEM = MATHS_GRADE.getGradedItem().toString();
    private static final String VALID_GRADE = MATHS_GRADE.getGrade().toString();

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () ->
                ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseTitle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTitle((String) null));
    }

    @Test
    public void parseTitle_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTitle(INVALID_TITLE));
    }

    @Test
    public void parseTitle_validValueWithoutWhitespace_returnsTitle() throws Exception {
        Title expectedTitle = new Title(VALID_SCHEDULE_TITLE);
        assertEquals(expectedTitle, ParserUtil.parseTitle(VALID_SCHEDULE_TITLE));
    }

    @Test
    public void parseTitle_validValueWithWhitespace_returnsTrimmedTitle() throws Exception {
        String titleWithWhitespace = WHITESPACE + VALID_SCHEDULE_TITLE + WHITESPACE;
        Title expectedTitle = new Title(VALID_SCHEDULE_TITLE);
        assertEquals(expectedTitle, ParserUtil.parseTitle(titleWithWhitespace));
    }

    @Test
    public void parseDateTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDateTime((String) null));
    }

    @Test
    public void parseDateTime_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDateTime(INVALID_DATE));
    }

    @Test
    public void parseDateTime_validValueWithoutWhitespace_returnsName() throws Exception {
        AppointmentDateTime expectedDateTime = new AppointmentDateTime(VALID_SCHEDULE_DATE_TIME);
        assertEquals(expectedDateTime, ParserUtil.parseDateTime(VALID_SCHEDULE_DATE_TIME));
    }

    @Test
    public void parseDateTime_validValueWithWhitespace_returnsTrimmedDateTime() throws Exception {
        String titleWithWhitespace = WHITESPACE + VALID_SCHEDULE_DATE_TIME + WHITESPACE;
        AppointmentDateTime expectedDateTime = new AppointmentDateTime(VALID_SCHEDULE_DATE_TIME);
        assertEquals(expectedDateTime, ParserUtil.parseDateTime(titleWithWhitespace));
    }

    @Test
    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription((String) null));
    }

    @Test
    public void parseDescription_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsDescription() throws Exception {
        Description expectedDescription = new Description(VALID_SCHEDULE_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(VALID_SCHEDULE_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedDescription() throws Exception {
        String descriptionWithWhitespace = WHITESPACE + VALID_SCHEDULE_DESCRIPTION + WHITESPACE;
        Description expectedDescription = new Description(VALID_SCHEDULE_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(descriptionWithWhitespace));
    }

    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate((String) null));
    }

    @Test
    public void parseDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDateTime(INVALID_DATE));
    }

    @Test
    public void parseDate_validValueWithoutWhitespace_returnsDate() throws Exception {
        ReminderDate expectedDate = new ReminderDate(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(VALID_DATE));
    }

    @Test
    public void parseDate_validValueWithWhitespace_returnsTrimmedDate() throws Exception {
        String dateWithWhitespace = WHITESPACE + VALID_DATE + WHITESPACE;
        ReminderDate expectedDate = new ReminderDate(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(dateWithWhitespace));
    }

    @Test
    public void parseLocalDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLocalDate((String) null));
    }

    @Test
    public void parseLocalDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDateTime(INVALID_DATE));
    }

    @Test
    public void parseLocalDate_validValueWithoutWhitespace_returnsLocalDate() throws Exception {
        LocalDate expectedDate = LocalDate.parse(VALID_DATE, DateTimeFormatter.ofPattern("y-M-d"));
        assertEquals(expectedDate, ParserUtil.parseLocalDate(VALID_DATE));
    }

    @Test
    public void parseLocalDate_validValueWithWhitespace_returnsTrimmedLocalDate() throws Exception {
        String dateWithWhitespace = WHITESPACE + VALID_DATE + WHITESPACE;
        LocalDate expectedDate = LocalDate.parse(VALID_DATE, DateTimeFormatter.ofPattern("y-M-d"));
        assertEquals(expectedDate, ParserUtil.parseLocalDate(dateWithWhitespace));
    }

    @Test
    public void parseSubjectNameInGrade_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSubjectNameInGrade((String) null));
    }

    @Test
    public void parseSubjectNameInGrade_invalidSubjectName_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSubjectNameInGrade(INVALID_SUBJECT_IN_GRADE));
    }

    @Test
    public void parseSubjectNameInGrade_invalidLongSubjectName_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSubjectNameInGrade(INVALID_LONG_SUBJECT_NAME));
    }

    @Test
    public void parseSubjectNameInGrade_validSubjectWithoutWhitespace_returnsSubjectName() throws Exception {
        SubjectName expectedSubject = new SubjectName(VALID_SUBJECT_NAME);
        assertEquals(expectedSubject, ParserUtil.parseSubjectNameInGrade(VALID_SUBJECT_NAME));
    }

    @Test
    public void parseSubjectNameInGrade_validSubjectWithWhitespace_returnsTrimmedSubjectName() throws Exception {
        String subjectWithWhitespace = WHITESPACE + VALID_SUBJECT_NAME + WHITESPACE;
        SubjectName expectedSubject = new SubjectName(VALID_SUBJECT_NAME);
        assertEquals(expectedSubject, ParserUtil.parseSubjectNameInGrade(subjectWithWhitespace));
    }

    @Test
    public void parseGradedItem_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGradedItem((String) null));
    }

    @Test
    public void parseGradedItem_invalidGradedItem_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGradedItem(INVALID_GRADED_ITEM));
    }

    @Test
    public void parseGradedItem_invalidLongGradedItem_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGradedItem(INVALID_LONG_GRADED_ITEM));
    }

    @Test
    public void parseGradedItem_validGradedItemWithoutWhitespace_returnsGradedItem() throws Exception {
        GradedItem expectedGradedItem = new GradedItem(VALID_GRADED_ITEM);
        assertEquals(expectedGradedItem, ParserUtil.parseGradedItem(VALID_GRADED_ITEM));
    }

    @Test
    public void parseGradedItem_validGradedItemWithWhitespace_returnsTrimmedGradedItem() throws Exception {
        String gradedItemWithWhitespace = WHITESPACE + VALID_GRADED_ITEM + WHITESPACE;
        GradedItem expectedGradedItem = new GradedItem(VALID_GRADED_ITEM);
        assertEquals(expectedGradedItem, ParserUtil.parseGradedItem(gradedItemWithWhitespace));
    }

    @Test
    public void parseGrade_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGrade((String) null));
    }

    @Test
    public void parseGrade_invalidGrade_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGrade(INVALID_GRADE));
    }

    @Test
    public void parseGrade_validGrade_returnsSubjectName() throws Exception {
        GradeEnum expectedGrade = GradeEnum.valueOf(VALID_GRADE);
        assertEquals(expectedGrade, ParserUtil.parseGrade(VALID_GRADE));
    }
}
