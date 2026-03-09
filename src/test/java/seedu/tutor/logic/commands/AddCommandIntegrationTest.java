package seedu.tutor.logic.commands;

import static seedu.tutor.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tutor.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tutor.testutil.TypicalPersons.getTypicalTutorMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.tutor.logic.Messages;
import seedu.tutor.model.Model;
import seedu.tutor.model.ModelManager;
import seedu.tutor.model.UserPrefs;
import seedu.tutor.model.person.Person;
import seedu.tutor.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTutorMap(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getTutorMap(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getTutorMap().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), model,
                AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
