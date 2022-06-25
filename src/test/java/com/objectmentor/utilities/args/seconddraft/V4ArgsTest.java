package com.objectmentor.utilities.args.seconddraft;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class V4ArgsTest {

    @Test
    public void checkThatIntegerIsEnabledAndCorrespondingArgumentIsPassed() throws ParseException {
        V4Args arg = new V4Args("p#", new String[]{"-p", "8080"});
        int port = arg.getInt('p');
        assertThat(port).isEqualTo(8080);
    }

    @Test
    public void checkThatOneLoggingFlagIsEnabled() throws ParseException {
        V4Args arg = new V4Args("l", new String[]{"-l"});
        boolean logging = arg.getBoolean('l');
        assertThat(logging).isTrue();
    }

    @Test
    public void checkThatOneLoggingFlagThoughItContainsBlanksIsEnabled() throws ParseException {
        V4Args arg = new V4Args("  l     ", new String[]{"-l"});
        boolean logging = arg.getBoolean('l');
        assertThat(logging).isTrue();
    }

    @Test
    public void checkThatTwoLoggingFlagsAreEnabled() throws ParseException {
        V4Args arg = new V4Args("l,X", new String[]{"-l", "-X"});
        boolean loggingFlag1 = arg.getBoolean('l');
        boolean loggingFlag2 = arg.getBoolean('X');
        assertThat(loggingFlag1).isTrue();
        assertThat(loggingFlag2).isTrue();
    }

    @Test
    public void onlyOneOfTwoLoggingFlagsAreEnabled() throws ParseException {
        V4Args arg = new V4Args("l,X", new String[]{"-X"});
        boolean loggingFlag1 = arg.getBoolean('l');
        boolean loggingFlag2 = arg.getBoolean('X');
        assertThat(loggingFlag1).isFalse();
        assertThat(loggingFlag2).isTrue();
    }

    @Test
    public void checkThatOneStringFlagIsEnabledAndCorrespondingArgumentIsPassed() throws ParseException {
        V4Args arg = new V4Args("d*", new String[]{"-d", "C:/Temp"});
        String stringArgument = arg.getString('d');
        assertThat(stringArgument).isEqualTo("C:/Temp");
    }

    @Test
    public void loggingAndStringFlagsEnabledAndBothAreUsed() throws ParseException {
        V4Args arg = new V4Args("f*,l", new String[]{"-l", "-f", "C:/Temp/hello.txt"});
        boolean logging = arg.getBoolean('l');
        String stringArgument = arg.getString('f');
        assertThat(logging).isTrue();
        assertThat(stringArgument).isEqualTo("C:/Temp/hello.txt");
    }

    @Test
    public void checkThatArgsHasFoundLoggingArgument() throws ParseException {
        V4Args arg = new V4Args("l", new String[]{"-l"});
        boolean hasLogging = arg.has('l');
        assertThat(hasLogging).isTrue();
    }

    @Test
    public void ifNothingIsPassedArgsIsValid() throws ParseException {
        V4Args arg = new V4Args("", new String[]{});
        boolean isValid = arg.isValid();
        assertThat(isValid).isTrue();
    }

    @Test
    public void ifSchemaContainsInvalidCharactersThrowParseException() throws ParseException {
        ParseException exception = assertThrows(ParseException.class, () -> {
            V4Args arg = new V4Args("bx", null);
        });
    }

    @Test
    public void ifSchemaContainsInvalidCharactersCheckErrorMessageOfThrownParseException() throws ParseException {
        try {
            new V4Args("bx", null);
        } catch (ParseException e) {
            String errorMessage = e.getMessage();
            assertThat(errorMessage).isEqualTo("Argument: b has invalid format: x.");
        }
    }

    @Test
    public void tryToGetBooleanArgumentWhichDoesntExist() throws ParseException {
        V4Args arg = new V4Args("x", new String[]{""});
        boolean booleanArgument = arg.getBoolean('y');
        assertThat(booleanArgument).isFalse();
    }

    @Test
    public void tryToGetStringArgumentWhichDoesntExist() throws ParseException {
        V4Args arg = new V4Args("d*", new String[]{""});
        String stringArgument = arg.getString('f');
        assertThat(stringArgument).isEqualTo("");
    }

    @Test
    public void tryToGetIntegerArgumentWhichDoesntExist() throws ParseException {
        V4Args arg = new V4Args("p#", new String[]{""});
        int intArgument = arg.getInt('x');
        assertThat(intArgument).isEqualTo(0);
    }

    @Test
    public void invalidArgumentIsPassedForBoolean() throws ParseException {
        V4Args arg = new V4Args("l", new String[]{"-p"});
        boolean isValid = arg.isValid();
        assertThat(isValid).isFalse();
    }

    @Test
    public void checkErrorMessageForMissingArgumentOfStringSchema() throws Exception {
        V4Args arg = new V4Args("d*", new String[]{"-d"});
        String errorMessage = arg.errorMessage();
        assertThat(errorMessage).isEqualTo("Could not find string parameter for -d.");
    }

    @Test
    public void checkErrorMessageForMissingArgumentOfIntegerSchema() throws Exception {
        V4Args arg = new V4Args("p#", new String[]{"-p"});
        String errorMessage = arg.errorMessage();
        assertThat(errorMessage).isEqualTo("Could not find integer parameter for -p.");
    }

    @Test
    public void checkErrorMessageForInvalidArgumentOfIntegerSchema() throws Exception {
        V4Args arg = new V4Args("p#", new String[]{"-p", "Foo"});
        String errorMessage = arg.errorMessage();
        assertThat(errorMessage).isEqualTo("Argument -p expects an integer but was 'Foo'.");
    }

    @Test
    public void ifArgumentIsMissingForStringSchemaArgsMustBeInvalid() throws Exception {
        V4Args arg = new V4Args("d*", new String[]{"-d"});
        boolean isValid = arg.isValid();
        assertThat(isValid).isFalse();
    }

    @Test
    public void ifArgumentIsMissingForIntegerSchemaArgsMustBeInvalid() throws Exception {
        V4Args arg = new V4Args("p#", new String[]{"-p"});
        boolean isValid = arg.isValid();
        assertThat(isValid).isFalse();
    }

    @Test
    public void ifArgumentIsMissingForStringSchemaABlankMustBeReturned() throws Exception {
        V4Args arg = new V4Args("d*", new String[]{"-d"});
        String stringArgument = arg.getString('d');
        assertThat(stringArgument).isEqualTo("");
    }

    @Test
    public void ifArgumentIsMissingForIntegerSchemaZeroMustBeReturned() throws Exception {
        V4Args arg = new V4Args("p#", new String[]{"-p"});
        int intArgument = arg.getInt('p');
        assertThat(intArgument).isEqualTo(0);
    }

    @Test
    public void checkErrorMessageForWrongArgument() throws Exception {
        V4Args arg = new V4Args("l", new String[]{"-p"});
        String errorMessage = arg.errorMessage();
        assertThat(errorMessage).isEqualTo("Argument(s) -p unexpected.");
    }

    @Test
    public void printEmptyErrorMessageForValidArgument() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            V4Args arg = new V4Args("l", new String[]{"-l"});
            String errorMessage = arg.errorMessage();
        });
    }

    @Test
    public void printUsageForLoggingFlag() throws ParseException {
        V4Args arg = new V4Args("l", new String[]{"-l"});
        String usage = arg.usage();
        assertThat(usage).isEqualTo("-[l]");
    }

    @Test
    public void cardinalityForOneValidBooleanArgumentMustBeOne() throws ParseException {
        V4Args arg = new V4Args("l", new String[]{"-l"});
        int cardinality = arg.cardinality();
        assertThat(cardinality).isEqualTo(1);
    }

    @Test
    public void printEmptyUsageIfNoArgumentsArePassed() throws ParseException {
        V4Args arg = new V4Args("", new String[]{});
        String usage = arg.usage();
        assertThat(usage).isEqualTo("");
    }


    @Test
    public void checkErrorMessageForNoneCharacterSchemaElementId() throws ParseException {
        try {
            V4Args arg = new V4Args("1", null);
        } catch (ParseException e) {
            String errorMessage = e.getMessage();
            assertThat(errorMessage).isEqualTo("Bad character:1in Args format: 1");
        }
    }

    @Test
    public void errorMessageOnlyContainsInvalidCharacter() throws ParseException {
        try {
            V4Args arg = new V4Args("g,d*,2", null);
        } catch (ParseException e) {
            String errorMessage = e.getMessage();
            assertThat(errorMessage).isEqualTo("Bad character:2in Args format: g,d*,2");
        }
    }
}