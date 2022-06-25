package com.objectmentor.utilities.V3Args.firstdraft.booleanstringandinteger;

import com.objectmentor.utilities.args.firstdraft.booleanstringandinteger.V3Args;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class V3ArgsTest {

    @Test
    public void checkThatIntegerIsEnabledAndCorrespondingArgumentIsPassed() throws ParseException {
        V3Args arg = new V3Args("p#", new String[]{"-p", "8080"});
        int port = arg.getInt('p');
        assertThat(port).isEqualTo(8080);
    }

    @Test
    public void checkThatOneLoggingFlagIsEnabled() throws ParseException {
        V3Args arg = new V3Args("l", new String[]{"-l"});
        boolean logging = arg.getBoolean('l');
        assertThat(logging).isTrue();
    }

    @Test
    public void checkThatOneLoggingFlagThoughItContainsBlanksIsEnabled() throws ParseException {
        V3Args arg = new V3Args("  l     ", new String[]{"-l"});
        boolean logging = arg.getBoolean('l');
        assertThat(logging).isTrue();
    }

    @Test
    public void checkThatTwoLoggingFlagsAreEnabled() throws ParseException {
        V3Args arg = new V3Args("l,X", new String[]{"-l", "-X"});
        boolean loggingFlag1 = arg.getBoolean('l');
        boolean loggingFlag2 = arg.getBoolean('X');
        assertThat(loggingFlag1).isTrue();
        assertThat(loggingFlag2).isTrue();
    }
//
//    @Test
//    public void onlyOneOfTwoLoggingFlagsAreEnabled() throws ParseException {
//        V3Args arg = new V3Args("l,X", new String[]{"-X"});
//        boolean loggingFlag1 = arg.getBoolean('l');
//        boolean loggingFlag2 = arg.getBoolean('X');
//        assertThat("First logging flag", loggingFlag1, is(false));
//        assertThat("Second logging flag", loggingFlag2, is(true));
//    }
//
//    @Test
//    public void checkThatOneStringFlagIsEnabledAndCorrespondingArgumentIsPassed() throws ParseException {
//        V3Args arg = new V3Args("d*", new String[]{"-d", "C:/Temp"});
//        String stringArgument = arg.getString('d');
//        assertThat("String argument", stringArgument, is(equalTo("C:/Temp")));
//    }
//
//    @Test
//    public void loggingAndStringFlagsEnabledAndBothAreUsed() throws ParseException {
//        V3Args arg = new V3Args("f*,l", new String[]{"-l", "-f", "C:/Temp/hello.txt"});
//        boolean logging = arg.getBoolean('l');
//        String stringArgument = arg.getString('f');
//        assertThat("Logging", logging, is(true));
//        assertThat("String argument", stringArgument, is(equalTo("C:/Temp/hello.txt")));
//    }
//
//    @Test
//    public void checkThatV3ArgsHasFoundLoggingArgument() throws ParseException {
//        V3Args arg = new V3Args("l", new String[]{"-l"});
//        boolean hasLogging = arg.has('l');
//        assertThat("Logging was found", hasLogging, is(true));
//    }
//
//    @Test
//    public void ifNothingIsPassedV3ArgsIsValid() throws ParseException {
//        V3Args arg = new V3Args("", new String[]{});
//        boolean isValid = arg.isValid();
//        assertThat("Arg must be valid", isValid, is(true));
//    }
//
//    @Test(expected = ParseException.class)
//    public void ifSchemaContainsInvalidCharactersThrowParseException() throws ParseException {
//        new V3Args("bx", null);
//    }
//
//    @Test
//    public void ifSchemaContainsInvalidCharactersCheckErrorMessageOfThrownParseException() throws ParseException {
//        try {
//            new V3Args("bx", null);
//        } catch (ParseException e) {
//            String errorMessage = e.getMessage();
//            assertThat("Error message", errorMessage, is(equalTo("Argument: b has invalid format: x.")));
//        }
//    }
//
//    @Test
//    public void invalidArgumentIsPassedForBoolean() throws ParseException {
//        V3Args arg = new V3Args("l", new String[]{"-p"});
//        boolean isValid = arg.isValid();
//        assertThat("Arg must be invalid", isValid, is(false));
//    }
//
//    @Test
//    public void checkErrorMessageForMissingArgumentOfStringSchema() throws Exception {
//        V3Args arg = new V3Args("d*", new String[]{"-d"});
//        String errorMessage = arg.errorMessage();
//        assertThat("Error message", errorMessage, is(equalTo("Could not find string parameter for -d.")));
//    }
//
//    @Test
//    public void checkErrorMessageForMissingArgumentOfIntegerSchema() throws Exception {
//        V3Args arg = new V3Args("p#", new String[]{"-p"});
//        String errorMessage = arg.errorMessage();
//        assertThat("Error message", errorMessage, is(equalTo("Could not find integer parameter for -p.")));
//    }
//
//    @Test
//    public void checkErrorMessageForInvalidArgumentOfIntegerSchema() throws Exception {
//        V3Args arg = new V3Args("p#", new String[]{"-p", "Foo"});
//        String errorMessage = arg.errorMessage();
//        assertThat("Error message", errorMessage, is(equalTo("Argument -p expects an integer but was 'Foo'.")));
//    }
//
//    @Test
//    public void ifArgumentIsMissingForStringSchemaV3ArgsMustBeInvalid() throws Exception {
//        V3Args arg = new V3Args("d*", new String[]{"-d"});
//        boolean isValid = arg.isValid();
//        assertThat("com.objectmentor.utilities.V3Args.seconddraft.com.objectmentor.utilities.V3Args.seconddraft.V3Args must be invalid", isValid, is(false));
//    }
//
//    @Test
//    public void ifArgumentIsMissingForIntegerSchemaV3ArgsMustBeInvalid() throws Exception {
//        V3Args arg = new V3Args("p#", new String[]{"-p"});
//        boolean isValid = arg.isValid();
//        assertThat("com.objectmentor.utilities.V3Args.seconddraft.com.objectmentor.utilities.V3Args.seconddraft.V3Args must be invalid", isValid, is(false));
//    }
//
//    @Test
//    public void ifArgumentIsMissingForStringSchemaABlankMustBeReturned() throws Exception {
//        V3Args arg = new V3Args("d*", new String[]{"-d"});
//        String stringArgument = arg.getString('d');
//        assertThat("String argument", stringArgument, is(equalTo("")));
//    }
//
//    @Test
//    public void ifArgumentIsMissingForIntegerSchemaZeroMustBeReturned() throws Exception {
//        V3Args arg = new V3Args("p#", new String[]{"-p"});
//        int intArgument = arg.getInt('p');
//        assertThat("Integer argument", intArgument, is(0));
//    }
//
//    @Test
//    public void checkErrorMessageForWrongArgument() throws Exception {
//        V3Args arg = new V3Args("l", new String[]{"-p"});
//        String errorMessage = arg.errorMessage();
//        assertThat("Error message", errorMessage, is(equalTo("Argument(s) -p unexpected.")));
//    }
//
//    @Test(expected = Exception.class)
//    public void printEmptyErrorMessageForValidArgument() throws Exception {
//        V3Args arg = new V3Args("l", new String[]{"-l"});
//        String errorMessage = arg.errorMessage();
//        assertThat("Error message", errorMessage, is(equalTo("")));
//    }
//
//    @Test
//    public void printUsageForLoggingFlag() throws ParseException {
//        V3Args arg = new V3Args("l", new String[]{"-l"});
//        String usage = arg.usage();
//        assertThat("Usage", usage, is(equalTo("-[l]")));
//    }
//
//    @Test
//    public void cardinalityForOneValidBooleanArgumentMustBeOne() throws ParseException {
//        V3Args arg = new V3Args("l", new String[]{"-l"});
//        int cardinality = arg.cardinality();
//        assertThat("Cardinality", cardinality, is(1));
//    }
//
//    @Test
//    public void printEmptyUsageIfNoArgumentsArePassed() throws ParseException {
//        V3Args arg = new V3Args("", new String[]{});
//        String usage = arg.usage();
//        assertThat("Usage", usage, is(equalTo("")));
//    }
//
//    @Test
//    public void loggingFlagMustNotBeFollowedByArgument() throws ParseException {
//        V3Args arg = new V3Args("l", new String[]{"-l", "p"});
//        boolean isValid = arg.isValid();
//        assertThat("Arg must be invalid", isValid, is(false));
//    }
//
//    @Test(expected = ParseException.class)
//    public void ifSchemaElementIdIsNotACharacterThrowParseException() throws ParseException {
//        V3Args arg = new V3Args("1", null);
//    }
//
//    @Test
//    public void checkErrorMessageForNoneCharacterSchemaElementId() throws ParseException {
//        try {
//            V3Args arg = new V3Args("1", null);
//        } catch (ParseException e) {
//            String errorMessage = e.getMessage();
//            assertThat("Error message", errorMessage, is(equalTo("Bad character:1in com.objectmentor.utilities.V3Args.seconddraft.com.objectmentor.utilities.V3Args.seconddraft.V3Args format: 1")));
//        }
//    }
//
//    @Test
//    public void errorMessageOnlyContainsInvalidCharacter() throws ParseException {
//        try {
//            V3Args arg = new V3Args("g,d*,2", null);
//        } catch (ParseException e) {
//            String errorMessage = e.getMessage();
//            assertThat("Error message", errorMessage, is(equalTo("Bad character:2in com.objectmentor.utilities.V3Args.seconddraft.com.objectmentor.utilities.V3Args.seconddraft.V3Args format: g,d*,2")));
//        }
//    }
}