package seedu.module.commons.core.optionalfield;

/**
 * Wrapper for optional field.
 *
 * @param <T> type of field
 */
public class OptionalField<T> {

    public static final String FIELD_IS_NULL = "NULL";

    private final T nullableField;
    private final boolean isNull;

    /**
     * Constructor of OptionalField class, null check will be performed.
     *
     * @param field the content of this wrapper
     */
    public OptionalField(T field) {
        isNull = field == null;
        this.nullableField = field;
    }

    public T getField() {
        assert !isNull;
        return nullableField;
    }

    public boolean isNull() {
        return isNull;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof OptionalField<?>)) {
            return false;
        }

        if (this.isNull()) {
            return ((OptionalField<?>) other).isNull();
        }

        if (((OptionalField<?>) other).isNull) {
            return this.isNull();
        }

        return this.getField().equals(((OptionalField<?>) other).getField());
    }

    @Override
    public String toString() {
        if (isNull) {
            return FIELD_IS_NULL;
        } else {
            return this.getField().toString();
        }
    }

}
