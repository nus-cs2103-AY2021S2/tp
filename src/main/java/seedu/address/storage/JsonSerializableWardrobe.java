package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyWardrobe;
import seedu.address.model.Wardrobe;
import seedu.address.model.garment.Garment;

/**
 * An Immutable Wardrobe that is serializable to JSON format.
 */
@JsonRootName(value = "wardrobe")
class JsonSerializableWardrobe {

    public static final String MESSAGE_DUPLICATE_GARMENT = "Garments list contains duplicate garment(s).";

    private final List<JsonAdaptedGarment> garments = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableWardrobe} with the given garments.
     */
    @JsonCreator
    public JsonSerializableWardrobe(@JsonProperty("garments") List<JsonAdaptedGarment> garments) {
        this.garments.addAll(garments);
    }

    /**
     * Converts a given {@code ReadOnlyWardrobe} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableWardrobe}.
     */
    public JsonSerializableWardrobe(ReadOnlyWardrobe source) {
        garments.addAll(source.getGarmentList().stream().map(JsonAdaptedGarment::new).collect(Collectors.toList()));
    }

    /**
     * Converts this wardrobe into the model's {@code Wardrobe} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Wardrobe toModelType() throws IllegalValueException {
        Wardrobe wardrobe = new Wardrobe();
        for (JsonAdaptedGarment jsonAdaptedGarment : garments) {
            Garment garment = jsonAdaptedGarment.toModelType();
            if (wardrobe.hasGarment(garment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GARMENT);
            }
            wardrobe.addGarment(garment);
        }
        return wardrobe;
    }

}
