package seedu.plan.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.plan.commons.exceptions.IllegalValueException;
import seedu.plan.model.ModulePlanner;
import seedu.plan.model.ReadOnlyModulePlanner;
import seedu.plan.model.plan.Plan;

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
    public JsonSerializablePlans(ReadOnlyModulePlanner source) {
        plans.addAll(source.getPersonList().stream().map(JsonAdaptedPlan::new).collect(Collectors.toList()));
        currentSemesterNumber = source.getCurrentSemesterNumber();
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ModulePlanner toModelType() throws IllegalValueException {
        ModulePlanner modulePlanner = new ModulePlanner();
        for (JsonAdaptedPlan jsonAdaptedPlan : plans) {
            Plan plan = jsonAdaptedPlan.toModelType();
            if (modulePlanner.hasPlan(plan)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            modulePlanner.addPlan(plan);
        }
        modulePlanner.setCurrentSemesterNumber(currentSemesterNumber);
        return modulePlanner;
    }

}
