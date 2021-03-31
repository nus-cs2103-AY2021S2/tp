package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_CLEAR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_RECORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_VIEW;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;

/**
 * Allows user to record, clear and view notes.
 */
public class NoteCommand extends Command {

    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " INDEX " + PREFIX_NOTE_RECORD + "NOTE"
            + ": Records note for specified client. " + "New note will be added to list of notes.\n"
            + COMMAND_WORD + " INDEX " + PREFIX_NOTE_VIEW
            + ": Displays all notes for specified client.\n"
            + COMMAND_WORD + " INDEX " + PREFIX_NOTE_CLEAR
            + ": Clears all notes for specified client.";

    public static final String MESSAGE_RECORD_SUCCESS = "Recorded note \"%2$s\" for: %1$s";
    public static final String MESSAGE_VIEW_SUCCESS = "Displaying notes for: %1$s";
    public static final String MESSAGE_CLEAR_SUCCESS = "Cleared notes for: %1$s";

    private final Index index;
    private final Prefix action;
    private final Note note;


    /**
     * @param index of the person in the filtered person list
     * @param action to be taken (record, view or clear)
     * @param note to add to the person
     */
    public NoteCommand(Index index, Prefix action, Note note) {
        requireNonNull(index);
        requireNonNull(action);
        requireNonNull(note);

        this.index = index;
        this.action = action;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToNote = lastShownList.get(index.getZeroBased());
        if (action.equals(PREFIX_NOTE_RECORD)) {
            personToNote.addNote(note);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_RECORD_SUCCESS, personToNote.getName(), note));
        } else if (action.equals(PREFIX_NOTE_VIEW)) {
            String noteString = personToNote.getNotesString();
            return new CommandResult(String.format(MESSAGE_VIEW_SUCCESS, personToNote.getName()),
                    false, false, Optional.of(noteString), false);
        } else if (action.equals(PREFIX_NOTE_CLEAR)) {
            personToNote.clearNotes();
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_CLEAR_SUCCESS, personToNote.getName()));
        } else {
            assert false : "Unexpected execution";
            throw new CommandException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof NoteCommand)) {
            return false;
        } else {
            NoteCommand o = (NoteCommand) other;
            return index.equals(o.index)
                    && action.equals(o.action)
                    && note.equals(o.note);
        }
    }
}
