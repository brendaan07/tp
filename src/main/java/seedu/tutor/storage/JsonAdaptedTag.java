package seedu.tutor.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.tutor.commons.exceptions.IllegalValueException;
import seedu.tutor.model.label.Label;

/**
 * Jackson-friendly version of {@link Label}.
 */
class JsonAdaptedTag {

    private final String tagName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Converts a given {@code Label} into this class for Jackson use.
     */
    public JsonAdaptedTag(Label source) {
        tagName = source.labelName;
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

    /**
     * Converts this Jackson-friendly adapted label object into the model's {@code Label} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted label.
     */
    public Label toModelType() throws IllegalValueException {
        if (!Label.isValidLabelName(tagName)) {
            throw new IllegalValueException(Label.MESSAGE_CONSTRAINTS);
        }
        return new Label(tagName);
    }

}
