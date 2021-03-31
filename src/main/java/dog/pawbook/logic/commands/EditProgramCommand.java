package dog.pawbook.logic.commands;

import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_SESSION;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.Set;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.commons.util.CollectionUtil;
import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.model.managedentity.program.Session;
import dog.pawbook.model.managedentity.tag.Tag;

public class EditProgramCommand extends EditEntityCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the " + Program.ENTITY_WORD
            + " identified by the ID number. "
            + "Existing values will be overwritten by the input values.\n"
            + "ID (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_SESSION + "DATE&TIME OF SESSION]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + Program.ENTITY_WORD + " 100 "
            + PREFIX_NAME + "Obedience Training "
            + PREFIX_SESSION + "01-02-2021 18:00 "
            + PREFIX_SESSION + "08-02-2021 18:00";

    public static final String MESSAGE_EDIT_PROGRAM_SUCCESS = "Edited Program: %1$s";

    /**
     * @param id                    of the program in the entity list to edit
     * @param editProgramDescriptor details to edit the program with
     */
    public EditProgramCommand(Integer id, EditProgramDescriptor editProgramDescriptor) {
        super(id, editProgramDescriptor);
    }

    @Override
    protected String getSuccessMessage(Entity editedEntity) {
        return String.format(MESSAGE_EDIT_PROGRAM_SUCCESS, editedEntity);
    }

    @Override
    protected String getDuplicateEntityMessage() {
        return Messages.MESSAGE_DUPLICATE_PROGRAM;
    }

    @Override
    protected String getInvalidIdMessage() {
        return Messages.MESSAGE_INVALID_PROGRAM_ID;
    }

    /**
     * Creates and returns an {@code Program} with the details of {@code entityToEdit}
     * edited with {@code editEntityDescriptor}.
     */
    @Override
    protected Program createEditedEntity(Entity entityToEdit, EditEntityDescriptor editEntityDescriptor)
            throws CommandException {
        requireAllNonNull(entityToEdit, editEntityDescriptor);

        if (!(entityToEdit instanceof Program)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROGRAM_ID);
        }

        assert editEntityDescriptor instanceof EditProgramDescriptor;

        EditProgramDescriptor editProgramDescriptor = (EditProgramDescriptor) editEntityDescriptor;

        Program programToEdit = (Program) entityToEdit;

        Name updatedName = editProgramDescriptor.getName().orElse(programToEdit.getName());
        Set<Session> updatedSessions = editProgramDescriptor.getSessions().orElse(programToEdit.getSessions());
        Set<Tag> updatedTags = editProgramDescriptor.getTags().orElse(programToEdit.getTags());

        // enrolment and dropping of dogs are handled separately, thus not updated or editable.
        return new Program(updatedName, updatedSessions, updatedTags, programToEdit.getDogIdSet());
    }

    public static class EditProgramDescriptor extends EditEntityDescriptor {
        private Set<Session> sessions;

        public EditProgramDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditProgramDescriptor(EditProgramDescriptor toCopy) {
            super(toCopy);
            setSessions(toCopy.sessions);
        }

        @Override
        public boolean isNoFieldEdited() {
            return super.isNoFieldEdited()
                    && CollectionUtil.isAllNull(sessions);
        }

        public Optional<Set<Session>> getSessions() {
            return Optional.ofNullable(sessions);
        }

        public void setSessions(Set<Session> sessions) {
            this.sessions = sessions;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditProgramDescriptor)) {
                return false;
            }

            // state check
            EditProgramDescriptor e = (EditProgramDescriptor) other;

            return super.equals(e)
                    && getSessions().equals(e.getSessions());
        }
    }
}
