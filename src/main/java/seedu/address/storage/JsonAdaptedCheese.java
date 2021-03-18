package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.CheeseId;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.cheese.ExpiryDate;
import seedu.address.model.cheese.ManufactureDate;
import seedu.address.model.cheese.MaturityDate;

/**
 * Jackson-friendly version of {@link Cheese}.
 */
class JsonAdaptedCheese {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Cheese's %s field is missing!";

    private final Integer cheeseId;
    private final String cheeseType;
    private final String manufactureDate;
    private final String maturityDate;
    private final String expiryDate;
    private final boolean isAssigned;

    /**
     * Constructs a {@code JsonAdaptedCheese} with the given cheese details.
     */
    @JsonCreator
    public JsonAdaptedCheese(@JsonProperty("cheeseId") Integer cheeseId,
                             @JsonProperty("cheeseType") String cheeseType,
                             @JsonProperty("manufactureDate") String manufactureDate,
                             @JsonProperty("maturityDate") String maturityDate,
                             @JsonProperty("expiryDate") String expiryDate,
                             @JsonProperty("isAssigned") boolean isAssigned) {
        this.cheeseId = cheeseId;
        this.cheeseType = cheeseType;
        this.manufactureDate = manufactureDate;
        this.maturityDate = maturityDate;
        this.expiryDate = expiryDate;
        this.isAssigned = isAssigned;
    }

    /**
     * Converts a given {@code Cheese} into this class for Jackson use.
     */
    public JsonAdaptedCheese(Cheese source) {
        cheeseId = source.getCheeseId().value;
        cheeseType = source.getCheeseType().value;
        manufactureDate = source.getManufactureDate().toJsonString();
        maturityDate = source.getMaturityDate().map(MaturityDate::toJsonString).orElse(null);
        expiryDate = source.getExpiryDate().map(ExpiryDate::toJsonString).orElse(null);
        isAssigned = source.getAssignStatus();
    }

    /**
     * Converts this Jackson-friendly adapted cheese object into the model's {@code Cheese} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted cheese.
     */
    public Cheese toModelType() throws IllegalValueException {
        if (!CheeseId.isValidId(cheeseId)) {
            throw new IllegalValueException(CheeseId.MESSAGE_CONSTRAINTS);
        }
        final CheeseId modelId = CheeseId.getNextId(cheeseId);

        if (cheeseType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                CheeseType.class.getSimpleName()));
        }
        final CheeseType modelCheeseType = CheeseType.getCheeseType(cheeseType);

        if (manufactureDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ManufactureDate.class.getSimpleName()));
        }
        if (!ManufactureDate.isValidDate(manufactureDate)) {
            throw new IllegalValueException(ManufactureDate.MESSAGE_CONSTRAINTS);
        }
        final ManufactureDate modelManufactureDate = new ManufactureDate(manufactureDate);

        final MaturityDate modelMaturityDate;

        if (maturityDate == null) {
            modelMaturityDate = null;
        } else if (!MaturityDate.isValidDate(maturityDate)) {
            throw new IllegalValueException(MaturityDate.MESSAGE_CONSTRAINTS);
        } else {
            modelMaturityDate = new MaturityDate(maturityDate);
        }

        final ExpiryDate modelExpiryDate;

        if (expiryDate == null) {
            modelExpiryDate = null;
        } else if (!ExpiryDate.isValidDate(expiryDate)) {
            throw new IllegalValueException(ExpiryDate.MESSAGE_CONSTRAINTS);
        } else {
            modelExpiryDate = new ExpiryDate(expiryDate);
        }

        return new Cheese(modelCheeseType, modelManufactureDate,
                modelMaturityDate, modelExpiryDate, modelId, isAssigned);
    }

}
