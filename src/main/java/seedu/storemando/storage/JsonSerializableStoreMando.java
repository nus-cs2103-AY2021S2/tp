package seedu.storemando.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.storemando.commons.exceptions.IllegalValueException;
import seedu.storemando.model.ReadOnlyStoreMando;
import seedu.storemando.model.StoreMando;
import seedu.storemando.model.item.Item;

/**
 * An Immutable StoreMando that is serializable to JSON format.
 */
@JsonRootName(value = "storemando")
class JsonSerializableStoreMando {

    public static final String MESSAGE_DUPLICATE_ITEM = "items list contains duplicate item(s).";

    private final List<JsonAdaptedItem> items = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableStoreMando} with the given items.
     */
    @JsonCreator
    public JsonSerializableStoreMando(@JsonProperty("items") List<JsonAdaptedItem> items) {
        this.items.addAll(items);
    }

    /**
     * Converts a given {@code ReadOnlyStoreMando} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableStoreMando}.
     */
    public JsonSerializableStoreMando(ReadOnlyStoreMando source) {
        items.addAll(source.getItemList().stream().map(JsonAdaptedItem::new).collect(Collectors.toList()));
    }

    /**
     * Converts this storemando into the model's {@code StoreMando} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public StoreMando toModelType() throws IllegalValueException {
        StoreMando storeMando = new StoreMando();
        for (JsonAdaptedItem jsonAdaptedItem : items) {
            Item item = jsonAdaptedItem.toModelType();
            if (storeMando.hasItem(item)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ITEM);
            }
            storeMando.addItem(item);
        }
        return storeMando;
    }

}
