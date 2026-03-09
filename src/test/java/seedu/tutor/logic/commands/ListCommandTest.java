package seedu.tutor.logic.commands;

import static seedu.tutor.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tutor.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.tutor.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.tutor.testutil.TypicalPersons.getTypicalTutorMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.tutor.model.Model;
import seedu.tutor.model.ModelManager;
import seedu.tutor.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTutorMap(), new UserPrefs());
        expectedModel = new ModelManager(model.getTutorMap(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
