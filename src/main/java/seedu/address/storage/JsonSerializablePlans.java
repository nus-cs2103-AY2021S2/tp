package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.plan.Plan;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "plans")
class JsonSerializablePlans {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate plan(s).";

    private final List<JsonAdaptedPlan> plans = new ArrayList<>();
    private final Integer currentSemesterNumber;

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePlans(@JsonProperty("plans") List<JsonAdaptedPlan> plans,
                                 @JsonProperty("currentSemesterNumber") Integer currentSemesterNumber) {
        this.plans.addAll(plans);
        this.currentSemesterNumber = currentSemesterNumber;
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializablePlans(ReadOnlyAddressBook source) {
        plans.addAll(source.getPersonList().stream().map(JsonAdaptedPlan::new).collect(Collectors.toList()));
        currentSemesterNumber = source.getCurrentSemesterNumber();
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPlan jsonAdaptedPlan : plans) {
            Plan plan = jsonAdaptedPlan.toModelType();
            if (addressBook.hasPlan(plan)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPlan(plan);
        }
        addressBook.setCurrentSemesterNumber(currentSemesterNumber);
        return addressBook;
    }

}
