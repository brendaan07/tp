package seedu.tutor.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.tutor.commons.core.GuiSettings;
import seedu.tutor.logic.commands.CommandResult;
import seedu.tutor.logic.commands.exceptions.CommandException;
import seedu.tutor.logic.parser.exceptions.ParseException;
import seedu.tutor.model.ReadOnlyTutorMap;
import seedu.tutor.model.person.Person;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.tutor.model.Model#getTutorMap()
     */
    ReadOnlyTutorMap getTutorMap();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getTutorMapFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
