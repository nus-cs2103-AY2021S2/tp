package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.commons.core.identifier.Identifier;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Description;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventPriority;
import seedu.address.model.event.EventStatus;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_IDENTIFIER = "Identifier is not a non-zero unsigned integer"
            + " or the input integer is too large.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Identifier} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Identifier parseIdentifier(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_IDENTIFIER);
        }
        int identifierInt;
        try {
            identifierInt = Integer.parseInt(trimmedIndex);
        } catch (Exception e) {
            System.err.println("From here");
            throw new ParseException(MESSAGE_INVALID_IDENTIFIER);
        }
        return Identifier.fromIdentifier(identifierInt);
    }

    /**
     * Parses a {@code String eventName} into a {@code EventName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventName} is invalid.
     */
    public static EventName parseEventName(String eventName) throws ParseException {
        requireNonNull(eventName);
        String trimmedEventName = eventName.trim();
        if (!EventName.isValidName(trimmedEventName)) {
            throw new ParseException(EventName.MESSAGE_CONSTRAINTS);
        }
        return new EventName(trimmedEventName);
    }

    /**
     * Parses a {@code String eventPriority} into a {@code EventPriority}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventPriority} is invalid.
     */
    public static EventPriority parseEventPriority(String eventPriority) throws ParseException {
        requireNonNull(eventPriority);
        return Optional.ofNullable(eventPriority)
                .map(EventPriority::valueOfPriority)
                .orElseThrow(() -> new ParseException(EventPriority.MESSAGE_CONSTRAINTS));
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String status} into a {@code EventStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static EventStatus parseEventStatus(String status) throws ParseException {
        requireNonNull(status);
        return Optional.ofNullable(status)
                .map(EventStatus::valueOfStatus)
                .orElseThrow(() -> new ParseException(EventStatus.MESSAGE_CONSTRAINTS));
    }
}
