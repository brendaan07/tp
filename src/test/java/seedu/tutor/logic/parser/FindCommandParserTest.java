package seedu.tutor.logic.parser;

import static seedu.tutor.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tutor.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tutor.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.tutor.logic.commands.FindCommand;
import seedu.tutor.model.person.SubjectContainsStringPredicate;
import seedu.tutor.model.person.TagContainsStringPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validSubjectArgs_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new SubjectContainsStringPredicate(Arrays.asList("Math", "English")));
        assertParseSuccess(parser, "s/Math English", expectedFindCommand);

        FindCommand expectedFindCommandWithPrefixWhitespace =
                new FindCommand(new SubjectContainsStringPredicate(Arrays.asList("English", "Math")));
        assertParseSuccess(parser, "s/   English Math", expectedFindCommandWithPrefixWhitespace);

        FindCommand expectedFindCommandWithWhitespace =
                new FindCommand(new SubjectContainsStringPredicate(Arrays.asList("Science", "Physics")));
        assertParseSuccess(parser, " \n s/   Science   Physics  ", expectedFindCommandWithWhitespace);
    }

    @Test
    public void parse_validTagArgs_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new TagContainsStringPredicate(Arrays.asList("friend", "study")));
        assertParseSuccess(parser, "t/friend study", expectedFindCommand);

        FindCommand expectedFindCommandWithPrefixWhitespace =
                new FindCommand(new TagContainsStringPredicate(Arrays.asList("homework", "online")));
        assertParseSuccess(parser, "t/   homework online", expectedFindCommandWithPrefixWhitespace);

        FindCommand expectedFindCommandWithWhitespace =
                new FindCommand(new TagContainsStringPredicate(Arrays.asList("homework", "paid")));
        assertParseSuccess(parser, " \n t/   homework   paid  ", expectedFindCommandWithWhitespace);
    }

    @Test
    public void parse_invalidSubjectArgs_throwsParseException() {
        String expectedMessage = "Keyword missing! Please specify a non-space, "
                + "non-slash keyword (subject) after 's/' \n"
                + "Example: find s/Math, find s/Science";

        assertParseFailure(parser, "s/", expectedMessage);
        assertParseFailure(parser, "s/   ", expectedMessage);
    }

    @Test
    public void parse_invalidTagArgs_throwsParseException() {
        String expectedMessage = "Keyword missing! Please specify a non-space, "
                + "non-slash keyword (tag) after 't/' \n"
                + "Example: find t/friend, find t/homework";

        assertParseFailure(parser, "t/", expectedMessage);
        assertParseFailure(parser, "t/   ", expectedMessage);
    }

}
