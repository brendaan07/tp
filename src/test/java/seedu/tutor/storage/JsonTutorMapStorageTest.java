package seedu.tutor.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.tutor.testutil.Assert.assertThrows;
import static seedu.tutor.testutil.TypicalPersons.ALICE;
import static seedu.tutor.testutil.TypicalPersons.HOON;
import static seedu.tutor.testutil.TypicalPersons.IDA;
import static seedu.tutor.testutil.TypicalPersons.getTypicalTutorMap;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.tutor.commons.exceptions.DataLoadingException;
import seedu.tutor.model.TutorMap;
import seedu.tutor.model.ReadOnlyTutorMap;

public class JsonTutorMapStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTutorMapStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTutorMap_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTutorMap(null));
    }

    private java.util.Optional<ReadOnlyTutorMap> readTutorMap(String filePath) throws Exception {
        return new JsonTutorMapStorage(Paths.get(filePath)).readTutorMap(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTutorMap("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readTutorMap("notJsonFormatTutorMap.json"));
    }

    @Test
    public void readTutorMap_invalidPersonTutorMap_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readTutorMap("invalidPersonTutorMap.json"));
    }

    @Test
    public void readTutorMap_invalidAndValidPersonTutorMap_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readTutorMap("invalidAndValidPersonTutorMap.json"));
    }

    @Test
    public void readAndSaveTutorMap_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        TutorMap original = getTypicalTutorMap();
        JsonTutorMapStorage jsonTutorMapStorage = new JsonTutorMapStorage(filePath);

        // Save in new file and read back
        jsonTutorMapStorage.saveTutorMap(original, filePath);
        ReadOnlyTutorMap readBack = jsonTutorMapStorage.readTutorMap(filePath).get();
        assertEquals(original, new TutorMap(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonTutorMapStorage.saveTutorMap(original, filePath);
        readBack = jsonTutorMapStorage.readTutorMap(filePath).get();
        assertEquals(original, new TutorMap(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonTutorMapStorage.saveTutorMap(original); // file path not specified
        readBack = jsonTutorMapStorage.readTutorMap().get(); // file path not specified
        assertEquals(original, new TutorMap(readBack));

    }

    @Test
    public void saveTutorMap_nullTutorMap_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTutorMap(null, "SomeFile.json"));
    }

    /**
     * Saves {@code tutorMap} at the specified {@code filePath}.
     */
    private void saveTutorMap(ReadOnlyTutorMap tutorMap, String filePath) {
        try {
            new JsonTutorMapStorage(Paths.get(filePath))
                    .saveTutorMap(tutorMap, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTutorMap_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTutorMap(new TutorMap(), null));
    }
}
