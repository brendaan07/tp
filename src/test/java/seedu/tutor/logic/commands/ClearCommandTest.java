package seedu.tutor.logic.commands;

import static seedu.tutor.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tutor.testutil.TypicalPersons.getTypicalTutorMap;

import org.junit.jupiter.api.Test;

import seedu.tutor.model.Model;
import seedu.tutor.model.ModelManager;
import seedu.tutor.model.TutorMap;
import seedu.tutor.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyTutorMap_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTutorMap_success() {
        Model model = new ModelManager(getTypicalTutorMap(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTutorMap(), new UserPrefs());
        expectedModel.setTutorMap(new TutorMap());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
