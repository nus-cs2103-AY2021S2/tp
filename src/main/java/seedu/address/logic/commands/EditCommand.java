package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: { shown | selected | INDEX… } (must be a positive integer) "
            + "[" + PREFIX_NAME + " NAME] "
            + "[" + PREFIX_PHONE + " PHONE] "
            + "[" + PREFIX_EMAIL + " EMAIL] "
            + "[" + PREFIX_COMPANY + " COMPANY] "
            + "[" + PREFIX_JOB_TITLE + " JOB_TITLE] "
            + "[" + PREFIX_ADDRESS + " ADDRESS] "
            + "[" + PREFIX_REMARK + " REMARK] "
            + "[" + PREFIX_TAG + " TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + " 91234567 "
            + PREFIX_EMAIL + " johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_EDIT_PERSONS_SUCCESS = "Edited %1$d person";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_NO_SELECTED = "No selected person to edit.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_DUPLICATE_PERSON_BULK =
            "Bulk editing will result in duplicate person in the address book.";

    private final boolean isSpecialIndex;
    private final boolean isEditSelected;
    private final List<Index> targetIndexes;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * Private constructor. Should only be called via builder.
     *
     * @param isSpecialIndex
     * @param isEditSelected
     * @param editPersonDescriptor
     * @param targetIndexes
     */
    private EditCommand(boolean isSpecialIndex, boolean isEditSelected,
            EditPersonDescriptor editPersonDescriptor, List<Index> targetIndexes) {
        this.targetIndexes = requireNonNull(targetIndexes);
        this.isSpecialIndex = isSpecialIndex;
        this.isEditSelected = isEditSelected;
        this.editPersonDescriptor = requireNonNull(editPersonDescriptor);
    }

    /**
     * Initializes EditCommand that edits all the selected items.
     *
     * @param descriptor details to edit the person with
     * @return built {@code EditCommand}
     */
    public static EditCommand buildEditSelectedCommand(EditPersonDescriptor descriptor) {
        return new EditCommand(false, true, descriptor, new ArrayList<>());
    }

    /**
     * Initializes EditCommand that edits all the shown items in list.
     *
     * @param descriptor details to edit the person with
     * @return built {@code EditCommand}
     */
    public static EditCommand buildEditShownCommand(EditPersonDescriptor descriptor) {
        return new EditCommand(true, false, descriptor, new ArrayList<>());
    }

    /**
     * Initializes EditCommand that edits the items from a parsed user input indexes.
     *
     * @param descriptor details to edit the person with
     * @param indexes of the person in the filtered person list to edit
     * @return
     */
    public static EditCommand buildEditIndexCommand(List<Index> indexes,
            EditPersonDescriptor descriptor) {
        return new EditCommand(false, false, descriptor, indexes);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (isSpecialIndex) {
            return editAll(model);
        }
        if (isEditSelected) {
            return editSelected(model);
        }
        return editOneOrMultiple(model);
    }

    /**
     * Edits one more multiple person from {@code Model} based on the targetIndexes.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException if index is invalid or duplicates are found
     */
    private CommandResult editOneOrMultiple(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        // Validate indexes
        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }

        if (targetIndexes.size() == 1) {
            Index targetIndex = targetIndexes.get(0);
            Person personToEdit = lastShownList.get(targetIndex.getZeroBased());
            Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

            if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }

            model.setPerson(personToEdit, editedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
        }

        UniquePersonList editedPersonList = new UniquePersonList();

        // Validate bulk index changes and existing person objects
        for (Index targetIndex : targetIndexes) {
            Person person = lastShownList.get(targetIndex.getZeroBased());
            validateAndAdd(model, editedPersonList, person, editPersonDescriptor);
        }

        // Changes for indexes are validated, proceed with updating
        for (Index targetIndex : targetIndexes) {
            Person person = lastShownList.get(targetIndex.getZeroBased());
            Person editedPerson = createEditedPerson(person, editPersonDescriptor);
            model.setPerson(person, editedPerson);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSONS_SUCCESS, targetIndexes.size()));
    }

    /**
     * Edits all the person in the shown person list.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException if index is invalid or duplicates are found
     */
    private CommandResult editAll(Model model) throws CommandException {
        List<Person> lastShownList = new ArrayList<>(model.getFilteredPersonList());
        UniquePersonList editedPersonList = new UniquePersonList();

        // Validate current bulk changes and existing person objects
        for (Person person : lastShownList) {
            validateAndAdd(model, editedPersonList, person, editPersonDescriptor);
        }

        // Changes are validated, proceed with updating
        for (Person person : lastShownList) {
            Person editedPerson = createEditedPerson(person, editPersonDescriptor);
            model.setPerson(person, editedPerson);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSONS_SUCCESS, lastShownList.size()));
    }

    /**
     * Edits all the person in the selected person list.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException if index is invalid or duplicates are found or there is no selected
     *                          person
     */
    private CommandResult editSelected(Model model) throws CommandException {
        model.applySelectedPredicate();
        if (model.getFilteredPersonList().size() == 0) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            throw new CommandException(MESSAGE_NO_SELECTED);
        }
        return editAll(model);
    }

    /**
     * Checks if editing a person will introduce duplicate and adds into the {@code
     * UniquePersonList}
     *
     * @param model                {@code Model} which the command should operate on.
     * @param editedPersonList     a {@code UniquePersonList} of person that is currently being
     *                             edited.
     * @param person               {@code Person} current person to edit.
     * @param editPersonDescriptor {@code EditPersonDescriptor} of the changes to make.
     * @throws CommandException if change will introduce duplicates.
     */
    private void validateAndAdd(Model model, UniquePersonList editedPersonList, Person person,
            EditPersonDescriptor editPersonDescriptor) throws CommandException {
        Person editedPerson = createEditedPerson(person, editPersonDescriptor);
        if (!person.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON_BULK);
        }
        try {
            editedPersonList.add(editedPerson);
        } catch (DuplicatePersonException ex) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON_BULK);
        }
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Company updateCompany = editPersonDescriptor.getCompany().orElse(personToEdit.getCompany());
        JobTitle updatedJobTitle = editPersonDescriptor.getJobTitle().orElse(personToEdit.getJobTitle());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Remark updatedRemark = editPersonDescriptor.getRemark().orElse(personToEdit.getRemark());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedName, updatedPhone, updatedEmail, updateCompany, updatedJobTitle,
                updatedAddress, updatedRemark, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return targetIndexes.containsAll(e.targetIndexes)
                && isSpecialIndex == ((EditCommand) other).isSpecialIndex
                && isEditSelected == ((EditCommand) other).isEditSelected
                && targetIndexes.containsAll(((EditCommand) other).targetIndexes)
                && ((EditCommand) other).targetIndexes.containsAll(targetIndexes)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Company company;
        private JobTitle jobTitle;
        private Address address;
        private Remark remark;
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setCompany(toCopy.company);
            setJobTitle(toCopy.jobTitle);
            setAddress(toCopy.address);
            setRemark(toCopy.remark);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, company, jobTitle, address, remark, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public Optional<Company> getCompany() {
            return Optional.ofNullable(company);
        }

        public void setJobTitle(JobTitle jobTitle) {
            this.jobTitle = jobTitle;
        }

        public Optional<JobTitle> getJobTitle() {
            return Optional.ofNullable(jobTitle);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Adds {@code tags} to this object's {@code tags}.
         */
        public void addTags(Set<Tag> tags) {
            this.tags.addAll(tags);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getCompany().equals(e.getCompany())
                    && getJobTitle().equals(e.getJobTitle())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags())
                    && getRemark().equals(e.getRemark());
        }
    }
}
