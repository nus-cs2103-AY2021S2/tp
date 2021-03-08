package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Person;

public class AddMeetingCommand extends Command {

    public static final String COMMAND_WORD = "add-meeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Records a meeting of the person identified "
            + "by the index number used in the last person listing. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME "
            + PREFIX_DESCRIPTION + "DESCRIPTION \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "12-12-2021 "
            + PREFIX_TIME + "1240 "
            + PREFIX_DESCRIPTION + "We went to the beach!";

    public static final String MESSAGE_ADD_MEETING_SUCCESS = "Added meeting for %1$s";

    private final Index index;
    private final Meeting meeting;

    public AddMeetingCommand(Index index, Meeting meeting) {
        requireAllNonNull(index, meeting);

        this.index = index;
        this.meeting = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(person, meeting);

        model.setPerson(person, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_MEETING_SUCCESS, editedPerson.getName()));
    }

    private static Person createEditedPerson(Person personToEdit, Meeting meeting) {
        assert personToEdit != null;
        List<Meeting> meetingsToEdit = new ArrayList<>(personToEdit.getMeetings());
        meetingsToEdit.add(meeting);

        return new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), meetingsToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddMeetingCommand)) {
            return false;
        }

        AddMeetingCommand e = (AddMeetingCommand) other;
        return index.equals(e.index) && meeting.equals(e.meeting);
    }
}
