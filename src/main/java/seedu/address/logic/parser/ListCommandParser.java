package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ListType.PERSON_TYPE_LIST;
import static seedu.address.logic.parser.ListType.SESSION_TYPE_LIST;
import static seedu.address.logic.parser.ListType.STUDENT_TYPE_LIST;
import static seedu.address.logic.parser.ListType.TUTOR_TYPE_LIST;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SESSIONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORS;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListCommandParser implements Parser<ListCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        String listType = ParserUtil.parseListType(args);
        switch(listType) {

        case PERSON_TYPE_LIST:
            return new ListCommand(PREDICATE_SHOW_ALL_PERSONS, PREDICATE_SHOW_ALL_SESSIONS, "persons");

        case STUDENT_TYPE_LIST:
            return new ListCommand(PREDICATE_SHOW_ALL_STUDENTS, PREDICATE_SHOW_ALL_SESSIONS, "students");

        case TUTOR_TYPE_LIST:
            return new ListCommand(PREDICATE_SHOW_ALL_TUTORS, PREDICATE_SHOW_ALL_SESSIONS, "tutors");

        case SESSION_TYPE_LIST:
            return new ListCommand(PREDICATE_SHOW_ALL_PERSONS, PREDICATE_SHOW_ALL_SESSIONS, "sessions");

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));

        }
    }
}
