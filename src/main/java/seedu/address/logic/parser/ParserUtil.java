package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignee.Assignee;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Priority;
import seedu.address.model.task.TaskStatus;
import seedu.address.model.task.Title;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        System.out.println(trimmedIndex);
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String role} into an {@code Role}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code role} is invalid.
     */
    public static Role parseRole(String role) throws ParseException {
        requireNonNull(role);
        String trimmedRole = role.trim();
        if (!Role.isValidRole(trimmedRole)) {
            throw new ParseException(Role.MESSAGE_CONSTRAINTS);
        }
        return new Role(trimmedRole);
    }

    /**
     * Parses a {@code String assignee} into a {@code Assignee}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code assignee} is invalid.
     */
    public static Assignee parseAssignee(String assignee) throws ParseException {
        requireNonNull(assignee);
        String trimmedAssignee = assignee.trim();
        if (!Assignee.isValidAssigneeName(trimmedAssignee)) {
            throw new ParseException(Assignee.MESSAGE_CONSTRAINTS);
        }
        return new Assignee(trimmedAssignee);
    }

    /**
     * Parses {@code Collection<String> assignees} into a {@code Set<Assignee>}.
     */
    public static Set<Assignee> parseAssignees(Collection<String> assignees) throws ParseException {
        requireNonNull(assignees);
        final Set<Assignee> assigneeSet = new HashSet<>();
        for (String assigneeName : assignees) {
            assigneeSet.add(parseAssignee(assigneeName));
        }
        return assigneeSet;
    }

    /**
     * Parses a {@code String title} into a {@code title}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code title} is invalid.
     */
    public static Title parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();

        try {
            assert Title.isValidTitle(trimmedTitle) : "You have to input a task Title";
        } catch (AssertionError e) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }

        if (!Title.isValidTitle(trimmedTitle)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(trimmedTitle);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDesc = description.trim();

        try {
            assert Description.isValidDescription(trimmedDesc) : "You have to input a task description";
        } catch (AssertionError e) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }

        if (!Description.isValidDescription(trimmedDesc)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDesc);
    }

    /**
     * Parses a {@code String status} into a {@code TaskStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static TaskStatus parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();

        try {
            assert TaskStatus.isValidValue(trimmedStatus) : "You have to input a valid status";
        } catch (AssertionError e) {
            throw new ParseException(TaskStatus.MESSAGE_CONSTRAINTS);
        }

        if (!TaskStatus.isValidValue(trimmedStatus)) {
            throw new ParseException(TaskStatus.MESSAGE_CONSTRAINTS);
        }
        return TaskStatus.valueOf(trimmedStatus.toUpperCase());
    }

    /**
     * Parses a {@code String deadline} into a {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();

        try {
            assert Deadline.isValidDeadline(trimmedDeadline) : "You have to input a valid deadline!";
        } catch (AssertionError e) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }

        if (!Deadline.isValidDeadline(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return new Deadline(trimmedDeadline);
    }

    /**
     * Parses a {@code String priorty} into a {@code Priority}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code priority} is invalid.
     */
    public static Priority parsePriority(String priority) throws ParseException {
        requireNonNull(priority);
        String trimmedPriority = priority.trim();

        if (!Priority.isValidValue(trimmedPriority)) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS);
        }
        return Priority.valueOf(trimmedPriority.toUpperCase());
    }
}
