package seedu.storemando.testutil;

import seedu.storemando.model.StoreMando;
import seedu.storemando.model.item.Item;

/**
 * A utility class to help with building StoreMando objects.
 * Example usage: <br>
 * <<<<<<< HEAD
 * {@code StoreMando ab = new StoreMandoBuilder().withItem("John", "Doe").build();}
 * =======
 * {@code StoreMando ab = new StoreMandoBuilder().withItem("John", "Doe").build();}
 * >>>>>>> mid-1.2-base-refactor
 */
public class StoreMandoBuilder {

    private final StoreMando storeMando;

    public StoreMandoBuilder() {
        storeMando = new StoreMando();
    }

    public StoreMandoBuilder(StoreMando storeMando) {
        this.storeMando = storeMando;
    }

    /**
     * Adds a new {@code Item} to the {@code StoreMando} that we are building.
     */
    public StoreMandoBuilder withItem(Item item) {
        storeMando.addItem(item);
        return this;
    }

    public StoreMando build() {
        return storeMando;
    }
}
