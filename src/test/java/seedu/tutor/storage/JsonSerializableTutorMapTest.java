package seedu.tutor.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tutor.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.tutor.commons.exceptions.IllegalValueException;
import seedu.tutor.commons.util.JsonUtil;
import seedu.tutor.model.TutorMap;
import seedu.tutor.testutil.TypicalPersons;

public class JsonSerializableTutorMapTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTutorMapTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsTutorMap.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonTutorMap.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonTutorMap.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableTutorMap dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableTutorMap.class).get();
        TutorMap addressBookFromFile = dataFromFile.toModelType();
        TutorMap typicalPersonsAddressBook = TypicalPersons.getTypicalTutorMap();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTutorMap dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableTutorMap.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableTutorMap dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableTutorMap.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTutorMap.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
