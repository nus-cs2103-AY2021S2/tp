package dog.pawbook.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import dog.pawbook.logic.parser.exceptions.ParseException;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.dog.Breed;
import dog.pawbook.model.managedentity.dog.DateOfBirth;
import dog.pawbook.model.managedentity.dog.Sex;
import dog.pawbook.model.managedentity.owner.Address;
import dog.pawbook.model.managedentity.owner.Email;
import dog.pawbook.model.managedentity.owner.Phone;
import dog.pawbook.model.managedentity.program.Session;
import dog.pawbook.model.managedentity.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

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
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
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
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses a {@code String breed} into a {@code Breed}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code breed} is invalid.
     */
    public static Breed parseBreed(String breed) throws ParseException {
        requireNonNull(breed);
        String trimmedBreed = breed.trim();
        if (!Breed.isValidBreed(trimmedBreed)) {
            throw new ParseException(Breed.MESSAGE_CONSTRAINTS);
        }
        return new Breed(trimmedBreed);
    }

    /**
     * Parses a {@code String dob} into a {@code DateOfBirth}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dob} is invalid.
     */
    public static DateOfBirth parseDob(String dob) throws ParseException {
        requireNonNull(dob);
        String trimmedDob = dob.trim();
        if (!DateOfBirth.isValidDob(trimmedDob)) {
            throw new ParseException(DateOfBirth.MESSAGE_CONSTRAINTS);
        }
        return new DateOfBirth(trimmedDob);
    }

    /**
     * Parses a {@code String sex} into a {@code Sex}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sex} is invalid.
     */
    public static Sex parseSex(String sex) throws ParseException {
        requireNonNull(sex);
        String trimmedSex = sex.trim();
        if (!Sex.isValidSex(trimmedSex)) {
            throw new ParseException(Sex.MESSAGE_CONSTRAINTS);
        }
        return new Sex(trimmedSex);
    }

    /**
     * Parses a {@code String id} into a {@code int}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static int parseId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedOwnerId = id.trim();
        try {
            return Integer.parseInt(trimmedOwnerId);
        } catch (NumberFormatException e) {
            throw new ParseException("ID must be a positive integer!");
        }
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code Collection<String> dop} into a {@code Set<Session>}.
     */
    public static Set<Session> parseSessions(Collection<String> sessions) throws ParseException {
        requireNonNull(sessions);
        final Set<Session> dateSet = new HashSet<>();
        for (String s : sessions) {
            dateSet.add(parseSession(s));
        }
        return dateSet;
    }

    /**
     * Parses a {@code String dop} into a {@code Session}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dop} is invalid.
     */
    public static Session parseSession(String dop) throws ParseException {
        requireNonNull(dop);
        String trimmedDop = dop.trim();
        if (!Session.isValidDate(trimmedDop)) {
            throw new ParseException(Session.MESSAGE_CONSTRAINTS);
        }
        return new Session(trimmedDop);
    }

    /**
     * Parses a {@code String dateString} into {@code LocalDate}.
     *
     * @throws ParseException if the given {@code dateString} is not in a valid format.
     */
    public static LocalDate parseDate(String dateString) throws ParseException {
        requireNonNull(dateString);
        LocalDate date;
        try {
            date = LocalDate.parse(dateString.trim(), DateTimeFormatter.ofPattern("d-M-yyyy"));
        } catch (DateTimeParseException d) {
            throw new ParseException("Date should be in the d-M-yyyy format");
        }
        return date;
    }

}
