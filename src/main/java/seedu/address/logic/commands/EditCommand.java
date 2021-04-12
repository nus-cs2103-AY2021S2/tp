package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSURANCE_POLICY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
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
import seedu.address.model.insurancepolicy.InsurancePolicy;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command implements BatchOperation {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the client identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_INSURANCE_POLICY + " POLICY_ID] [-FLAG] "
            + "[" + PREFIX_TAG + "TAG]... \n"
            + "FLAG can be modify, insert or remove or editing policy ids. If no flag is specified, the default "
            + "behaviour is replace.\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Client: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_MODIFY_POLICY_CONSTRAINT = "When -modify flag is indicated for editing policy,"
            + " the format should be i/[TO_MODIFY];[TO_REPLACE]. ";
    public static final String MESSAGE_MODIFY_POLICY_NOT_FOUND = "The policy %s to modify or delete is not found.";
    public static final String MESSAGE_EXCESS_BATCH_ARGUMENTS = "Batch edit can only edit tags or insurance policies.\n"
            + "Please check that you have not entered other fields.";


    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;
    private final EditPolicyMode editPolicyMode;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor, EditPolicyMode editPolicyMode) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
        this.editPolicyMode = editPolicyMode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor, editPolicyMode);

        if (!personToEdit.equals(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    @Override
    public CommandResult executeBatch(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor, editPolicyMode);

        if (!personToEdit.equals(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }


    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor,
                                             EditPolicyMode editPolicyMode) throws CommandException {

        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone().get());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail().get());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress().get());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        List<InsurancePolicy> updatedPolicies;
        switch (editPolicyMode) {
        case REPLACE:
            updatedPolicies = editPersonDescriptor.getPoliciesToAdd().orElse(personToEdit.getPolicies());
            break;
        case MODIFY:
            List<InsurancePolicy> originalList = personToEdit.getPolicies();
            List<InsurancePolicy> listToRemove = editPersonDescriptor.getPoliciesToRemove().orElse(new ArrayList<>());
            List<InsurancePolicy> listToAdd = editPersonDescriptor.getPoliciesToAdd().orElse(new ArrayList<>());
            updatedPolicies = removeInsurancePolicies(originalList, listToRemove);
            updatedPolicies = addInsurancePolicies(updatedPolicies, listToAdd);
            break;
        case APPEND:
            listToAdd = editPersonDescriptor.getPoliciesToAdd().orElse(new ArrayList<>());
            updatedPolicies = addInsurancePolicies(personToEdit.getPolicies(), listToAdd);
            break;
        case REMOVE:
            originalList = personToEdit.getPolicies();
            listToRemove = editPersonDescriptor.getPoliciesToRemove().orElse(new ArrayList<>());
            updatedPolicies = removeInsurancePolicies(originalList, listToRemove);
            break;
        default:
            throw new CommandException(EditPolicyMode.MESSAGE_EDIT_POLICY_MODE_CONSTRAINTS);
        }
        List<Meeting> updatedMeetings = personToEdit.getMeetings();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedTags, updatedPolicies, updatedMeetings);
    }

    private static List<InsurancePolicy> removeInsurancePolicies(List<InsurancePolicy> policiesToRemoveFrom,
                                             List<InsurancePolicy> policiesToRemove) throws CommandException {
        ArrayList<InsurancePolicy> policiesToRemoveFromTemp = new ArrayList<>(policiesToRemoveFrom);
        for (InsurancePolicy insurancePolicy : policiesToRemove) {
            boolean isRemoved = policiesToRemoveFromTemp.remove(insurancePolicy);
            if (!isRemoved) {
                throw new CommandException(String.format(MESSAGE_MODIFY_POLICY_NOT_FOUND, insurancePolicy.policyId));
            }
        }
        return policiesToRemoveFromTemp;
    }

    private static List<InsurancePolicy> addInsurancePolicies(List<InsurancePolicy> policiesToAddTo,
                                             List<InsurancePolicy> policiesToAdd) {
        ArrayList<InsurancePolicy> policiesToAddToTemp = new ArrayList<>(policiesToAddTo);
        policiesToAddToTemp.addAll(policiesToAdd);
        return policiesToAddToTemp;
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
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor)
                && editPolicyMode.equals(e.editPolicyMode);
    }

    public enum EditPolicyMode {
        MODIFY, APPEND, REPLACE, REMOVE;

        public static final String MESSAGE_EDIT_POLICY_MODE_CONSTRAINTS =
                "Edit policy mode should be specified by -MODE, where MODE should be insert, remove or modify.";

        public static final String MESSAGE_EDIT_POLICY_MULTIPLE_FLAG_CONSTRAINTS =
                "Only 1 edit policy mode should be specified.";
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private List<InsurancePolicy> policiesToAdd;
        private List<InsurancePolicy> policiesToRemove;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setPoliciesToAdd(toCopy.policiesToAdd);
            setPoliciesToRemove(toCopy.policiesToRemove);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, policiesToAdd, policiesToRemove);
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

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
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

        public void setPoliciesToAdd(List<InsurancePolicy> policiesToAdd) {
            this.policiesToAdd = policiesToAdd;
        }

        public Optional<List<InsurancePolicy>> getPoliciesToAdd() {
            return Optional.ofNullable(policiesToAdd);
        }

        public void setPoliciesToRemove(List<InsurancePolicy> policiesToRemove) {
            this.policiesToRemove = policiesToRemove;
        }

        public Optional<List<InsurancePolicy>> getPoliciesToRemove() {
            return Optional.ofNullable(policiesToRemove);
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
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags())
                    && getPoliciesToAdd().equals(e.getPoliciesToAdd())
                    && getPoliciesToRemove().equals(e.getPoliciesToRemove());
        }
    }
}
