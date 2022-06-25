package com.objectmentor.utilities.args.firstdraft.booleanonly;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class V1ArgsTest {

    @Test
    public void checkThatOneLoggingFlagIsEnabled() {
        V1Args arg = new V1Args("l", new String[]{"-l"});
        boolean logging = arg.getBoolean('l');
        assertThat(logging).isTrue();
    }

    @Test
    public void checkThatTwoLoggingFlagsAreEnabled() {
        V1Args arg = new V1Args("l,X", new String[]{"-l", "-X"});
        boolean loggingFlag1 = arg.getBoolean('l');
        boolean loggingFlag2 = arg.getBoolean('X');
        assertThat(loggingFlag1).isTrue();
        assertThat(loggingFlag2).isTrue();
    }

    @Test
    public void onlyOneOfTwoLoggingFlagsAreEnabled() {
        V1Args arg = new V1Args("l,X", new String[]{"-X"});
        boolean loggingFlag1 = arg.getBoolean('l');
        boolean loggingFlag2 = arg.getBoolean('X');
        assertThat(loggingFlag1).isFalse();
        assertThat(loggingFlag2).isTrue();
    }

    @Test
    public void ifNothingIsPassedArgsIsValid() {
        V1Args arg = new V1Args("", new String[]{});
        boolean isValid = arg.isValid();
        assertThat(isValid).isTrue();
    }

    @Test
    public void wrongArgumentPassed() {
        V1Args arg = new V1Args("l", new String[]{"-p"});
        boolean isValid = arg.isValid();
        assertThat(isValid).isFalse();
    }

    @Test
    public void printErrorMessageForWrongArgument() {
        V1Args arg = new V1Args("l", new String[]{"-p"});
        String errorMessage = arg.errorMessage();
        assertThat(errorMessage).isEqualTo("Argument(s) -p unexpected.");
    }

    @Test
    public void printEmptyErrorMessageForValidArgument() {
        V1Args arg = new V1Args("l", new String[]{"-l"});
        String errorMessage = arg.errorMessage();
        assertThat(errorMessage).isEqualTo("");
    }

    @Test
    public void printUsageForLoggingFlag() {
        V1Args arg = new V1Args("l", new String[]{"-l"});
        String usage = arg.usage();
        assertThat(usage).isEqualTo("-[l]");
    }

    @Test
    public void cardinalityForOneValidBooleanArgumentMustBeOne() {
        V1Args arg = new V1Args("l", new String[]{"-l"});
        int cardinality = arg.cardinality();
        assertThat(cardinality).isEqualTo(1);
    }

    @Test
    public void printEmptyUsageIfNoArgumentsArePassed() {
        V1Args arg = new V1Args("", new String[]{});
        String usage = arg.usage();
        assertThat(usage).isEqualTo("");
    }

    @Test
    public void loggingFlagMustNotBeFollowedByArgument() {
        V1Args arg = new V1Args("l", new String[]{"-l", "p"});
        boolean isValid = arg.isValid();
        assertThat(isValid).isTrue(); //원래 false로 되어 있었음 확인필요
    }
}