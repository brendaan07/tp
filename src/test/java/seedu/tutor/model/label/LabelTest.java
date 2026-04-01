package seedu.tutor.model.label;

import static seedu.tutor.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LabelTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Label(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Label(invalidTagName));
    }

    @Test
    public void isValidLabelName() {
        // null label name
        assertThrows(NullPointerException.class, () -> Label.isValidLabelName(null));
    }

}
