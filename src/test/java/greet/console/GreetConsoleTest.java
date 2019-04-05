package greet.console;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetConsoleTest {

@Test
    public void TestHelpCommand(){
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    GreetConsole console = new GreetConsole();
    console.input("help");
    assertEquals("All greet app commands :\n" +
            "Greet\n" +
            "Quit\n" +
            "Help\n" +
            "Greeted\n" +
            "Clear\n" +
            "Counter\n", outContent.toString());
}

    @Test
    public void consoleGreetCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        GreetConsole console = new GreetConsole();
        console.input("greet nathri");
        assertEquals("Hello, Nathri\n", outContent.toString());
    }

    @Test
    public void consoleGreetUserInEnglishCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        GreetConsole console = new GreetConsole();
        console.input("greet jack english");
        assertEquals("Hello, Jack\n", outContent.toString());
    }
@Test
    public void consoleGreetUserInJapaneseCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        GreetConsole console = new GreetConsole();
        console.input("greet sarah japanese");
        assertEquals("Konichiwa, Sarah\n", outContent.toString());
    }

    @Test
    public void consoleGreetUserInThaiCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        GreetConsole console = new GreetConsole();
        console.input("greet Desmond thai");
        assertEquals("Sawa dee krahp, Desmond\n", outContent.toString());
    }
    @Test
    public void consoleGreetedCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        GreetConsole console = new GreetConsole();
        console.input("greet Desmond");
        console.input("greet Jack");
        console.input("greet Desmond");
        console.input("greet Nathri");
        System.setOut(new PrintStream(outContent));
        console.input("greeted");
        assertEquals("user: Nathri, greeted: 1\n" +
                "user: Jack, greeted: 1\n" +
                "user: Desmond, greeted: 2\n", outContent.toString());
    }
    @Test
    public void consoleGreetedSpecificUserCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        GreetConsole console = new GreetConsole();
        console.input("greet Desmond");
        console.input("greet Jack");
        console.input("greet Desmond");
        console.input("greet Nathri");
        System.setOut(new PrintStream(outContent));
        console.input("greeted Nathri");
        assertEquals("user: Nathri, greeted: 1\n", outContent.toString());
    }

    @Test
    public void consoleGreetedNoUserCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        GreetConsole console = new GreetConsole();
        System.setOut(new PrintStream(outContent));
        console.input("greeted");
        assertEquals("No users has been greeted\n", outContent.toString());
    }
    @Test
    public void consoleGreetedNoneGreetedUserCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        GreetConsole console = new GreetConsole();
        System.setOut(new PrintStream(outContent));
        console.input("greeted Nathri");
        assertEquals("No such user\n", outContent.toString());
    }
    @Test
    public void consoleCounterCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        GreetConsole console = new GreetConsole();
        System.setOut(new PrintStream(outContent));
        console.input("counter");
        assertEquals("Users: 0\n", outContent.toString());
    }

    @Test
    public void consoleClearCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        GreetConsole console = new GreetConsole();
        console.input("greet Nathri");
        System.setOut(new PrintStream(outContent));
        console.input("greeted");
        assertEquals("user: Nathri, greeted: 1\n", outContent.toString());
        System.setOut(new PrintStream(outContent));
        console.input("clear");
        assertEquals("User list has been cleared\n", outContent.toString());
        System.setOut(new PrintStream(outContent));
        console.input("greeted");
        assertEquals("No users has been greeted\n", outContent.toString());
}

    @Test
    public void consoleClearUserCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        GreetConsole console = new GreetConsole();
        console.input("greet Nathri");
        System.setOut(new PrintStream(outContent));
        console.input("greeted nathri");
        assertEquals("user: Nathri, greeted: 1\n", outContent.toString());
        System.setOut(new PrintStream(outContent));
        console.input("clear nathri");
        assertEquals("User list has been deleted \n", outContent.toString());
        System.setOut(new PrintStream(outContent));
        console.input("greeted nathri");
        assertEquals("No such user\n", outContent.toString());
    }
}
