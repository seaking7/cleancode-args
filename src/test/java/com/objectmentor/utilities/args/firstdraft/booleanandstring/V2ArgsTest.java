package com.objectmentor.utilities.args.firstdraft.booleanandstring;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class V2ArgsTest {

    @Test
    public void checkThatOneLoggingFlagIsEnabled() throws ParseException {
        V2Args arg = new V2Args("l", new String[]{"-l"});
        boolean logging = arg.getBoolean('l');
        assertThat(logging).isTrue();
    }

    @Test
    public void checkThatOneLoggingFlagThoughItContainsBlanksIsEnabled() throws ParseException {
        V2Args arg = new V2Args("  l     ", new String[]{"-l"});
        boolean logging = arg.getBoolean('l');
        assertThat(logging).isTrue();
    }

    @Test
    public void checkThatTwoLoggingFlagsAreEnabled() throws ParseException {
        V2Args arg = new V2Args("l,X", new String[]{"-l", "-X"});
        boolean loggingFlag1 = arg.getBoolean('l');
        boolean loggingFlag2 = arg.getBoolean('X');
        assertThat(loggingFlag1).isTrue();
        assertThat(loggingFlag2).isTrue();
    }

    @Test
    public void onlyOneOfTwoLoggingFlagsAreEnabled() throws ParseException {
        V2Args arg = new V2Args("l,X", new String[]{"-X"});
        boolean loggingFlag1 = arg.getBoolean('l');
        boolean loggingFlag2 = arg.getBoolean('X');
        assertThat(loggingFlag1).isFalse();
        assertThat(loggingFlag2).isTrue();
    }

    @Test
    public void checkThatOneStringFlagIsEnabledAndCorrespondingArgumentIsPassed() throws ParseException {
        V2Args arg = new V2Args("d*", new String[]{"-d", "C:/Temp"});
        String stringArgument = arg.getString('d');
        assertThat(stringArgument).isEqualTo("C:/Temp");
    }

    @Test
    public void loggingAndStringFlagsEnabledAndBothAreUsed() throws ParseException {
        V2Args arg = new V2Args("f*,l", new String[]{"-l", "-f", "C:/Temp/hello.txt"});
        boolean logging = arg.getBoolean('l');
        String stringArgument = arg.getString('f');
        assertThat(logging).isTrue();
        assertThat(stringArgument).isEqualTo("C:/Temp/hello.txt");
    }

    @Test
    public void checkThatArgsHasFoundLoggingArgument() throws ParseException {
        V2Args arg = new V2Args("l", new String[]{"-l"});
        boolean hasLogging = arg.has('l');
        assertThat(hasLogging).isTrue();
    }

    @Test
    public void ifNothingIsPassedArgsIsValid() throws ParseException {
        V2Args arg = new V2Args("", new String[]{});
        boolean isValid = arg.isValid();
        assertThat(isValid).isTrue();
    }

    @Test
    public void invalidArgumentIsPassedForBoolean() throws ParseException {
        V2Args arg = new V2Args("l", new String[]{"-p"});
        boolean isValid = arg.isValid();
        assertThat(isValid).isFalse();
    }

    @Test
    public void checkErrorMessageForMissingArgumentOfStringSchema() throws Exception {
        V2Args arg = new V2Args("d*", new String[]{"-d"});
        String errorMessage = arg.errorMessage();
        assertThat(errorMessage).isEqualTo("Could not find string parameter for -d.");
    }

    @Test
    public void ifArgumentIsMissingForStringSchemaArgsMustBeInvalid() throws Exception {
        V2Args arg = new V2Args("d*", new String[]{"-d"});
        boolean isValid = arg.isValid();
        assertThat(isValid).isFalse();
    }

    @Test
    public void ifArgumentIsMissingForStringSchemaABlankMustBeReturned() throws Exception {
        V2Args arg = new V2Args("d*", new String[]{"-d"});
        String stringArgument = arg.getString('d');
        assertThat(stringArgument).isEqualTo("");
    }

    @Test
    public void checkErrorMessageForWrongArgument() throws Exception {
        V2Args arg = new V2Args("l", new String[]{"-p"});
        String errorMessage = arg.errorMessage();
        assertThat(errorMessage).isEqualTo("Argument(s) -p unexpected.");
    }

    @Test
    public void printEmptyErrorMessageForValidArgument() throws Exception {
        V2Args arg = new V2Args("l", new String[]{"-l"});
        Exception exception = assertThrows(Exception.class, () -> {
                    String errorMessage = arg.errorMessage();
                });
        assertThat(exception.getMessage()).isEqualTo("TILT: Should not get here.");
    }

    @Test
    public void printUsageForLoggingFlag() throws ParseException {
        V2Args arg = new V2Args("l", new String[]{"-l"});
        String usage = arg.usage();
        assertThat(usage).isEqualTo("-[l]");
    }

    @Test
    public void cardinalityForOneValidBooleanArgumentMustBeOne() throws ParseException {
        V2Args arg = new V2Args("l", new String[]{"-l"});
        int cardinality = arg.cardinality();
        assertThat(cardinality).isEqualTo(1);
    }

    @Test
    public void printEmptyUsageIfNoArgumentsArePassed() throws ParseException {
        V2Args arg = new V2Args("", new String[]{});
        String usage = arg.usage();
        assertThat(usage).isEqualTo("");
    }

    @Test
    public void loggingFlagMustNotBeFollowedByArgument() throws ParseException {
        V2Args arg = new V2Args("l", new String[]{"-l", "p"});
        boolean isValid = arg.isValid();
        assertThat(isValid).isTrue();
    }

    @Test
    public void ifSchemaElementIdIsNotACharacterThrowParseException() throws ParseException {
        ParseException exception = assertThrows(ParseException.class, () -> {
            V2Args arg = new V2Args("1", null);
        });
        assertThat(exception.getMessage()).contains("Bad character");
    }

    @Test
    public void checkErrorMessageForNoneCharacterSchemaElementId() throws ParseException {
        try {
            V2Args arg = new V2Args("1", null);
        } catch (ParseException e) {
            String errorMessage = e.getMessage();
            assertThat(errorMessage).isEqualTo("Bad character:1in com.objectmentor.utilities.args.seconddraft.com.objectmentor.utilities.args.seconddraft.Args format: 1");
        }
    }

    @Test
    public void errorMessageOnlyContainsInvalidCharacter() throws ParseException {
        try {
            V2Args arg = new V2Args("g,d*,2", null);
        } catch (ParseException e) {
            String errorMessage = e.getMessage();
            assertThat(errorMessage).isEqualTo("Bad character:2in com.objectmentor.utilities.args.seconddraft.com.objectmentor.utilities.args.seconddraft.Args format: g,d*,2");
        }
    }
}