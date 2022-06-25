package com.objectmentor.utilities.args;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ArgsTest {

    @Test
    public void withLoggingEnabled() throws ArgsException {
        Args arg = new Args("l", new String[]{"-l"});
        boolean logging = arg.getBoolean('l');
        assertThat(logging).isTrue();
    }

    @Test
    public void withSetPort() throws ArgsException {
        Args arg = new Args("p#", new String[]{"-p", "8080"});
        int port = arg.getInt('p');
        assertThat(port).isEqualTo(8080);
//        assertThat("Port", port, is(8080));
    }

    @Test
    public void withConfiguredDirectory() throws ArgsException {
        Args arg = new Args("d*", new String[]{"-d", "C:/Temp"});
        String directory = arg.getString('d');
        assertThat(directory).isEqualTo("C:/Temp");
//        assertThat("Directory", directory, is(equalTo("C:/Temp")));
    }

    @Test
    public void withLoggingEnabledSetPortAndConfiguredDirectory() throws ArgsException {
        Args arg = new Args("l,p#,d*", new String[]{"-d", "C:/Temp", "-p", "8080", "-l"});

        boolean logging = arg.getBoolean('l');
        int port = arg.getInt('p');
        String directory = arg.getString('d');

        assertThat(logging).isTrue();
        assertThat(port).isEqualTo(8080);
        assertThat(directory).isEqualTo("C:/Temp");
    }


}
