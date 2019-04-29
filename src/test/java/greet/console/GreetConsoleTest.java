package greet.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetConsoleTest {

    public Connection getConnection() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:h2:./target/users", "sa", "");
        System.out.println("Successfully Connected to the database!");
        return conn;
    }
    @BeforeEach
    public void cleanUpTables() {
        try {
            try(Connection conn = getConnection()) {

                Statement statement = conn.createStatement();
                statement.addBatch("TRUNCATE table users");
                statement.executeBatch();

            }
        } catch(Exception ex) {
            System.out.println("These test will fail until the users table is created: " + ex);
        }
    }

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
            "Counter\n", outContent.toString().replaceAll("\r", ""));
}

    @Test
    public void consoleGreetCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        GreetConsole console = new GreetConsole();
        console.input("greet nathri");
        assertEquals("Hello, Nathri", outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }

    @Test
    public void consoleGreetUserInEnglishCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        GreetConsole console = new GreetConsole();
        console.input("greet jack english");
        assertEquals("Hello, Jack", outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }
@Test
    public void consoleGreetUserInJapaneseCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        GreetConsole console = new GreetConsole();
        console.input("greet sarah japanese");
        assertEquals("Konichiwa, Sarah", outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }

    @Test
    public void consoleGreetUserInThaiCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        GreetConsole console = new GreetConsole();
        console.input("greet Desmond thai");
        assertEquals("Sawa dee krahp, Desmond", outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }
    @Test
    public void consoleGreetedCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        GreetConsole console = new GreetConsole();
        console.input("greet Desmond");
        console.input("greet Jack");
        console.input("greet Desmond");
        console.input("greet Nathri");
        outContent.reset();
        System.setOut(new PrintStream(outContent));
        console.input("greeted");
        assertEquals("user: Desmond, greeted: 2" +
                "user: Jack, greeted: 1" +
                "user: Nathri, greeted: 1", outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
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
        assertEquals("user: Nathri, greeted: 1", outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }

    @Test
    public void consoleGreetedNoUserCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        GreetConsole console = new GreetConsole();
        System.setOut(new PrintStream(outContent));
        console.input("greeted");
        assertEquals("No users has been greeted", outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }
    @Test
    public void consoleGreetedNoneGreetedUserCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        GreetConsole console = new GreetConsole();
        System.setOut(new PrintStream(outContent));
        console.input("greeted Nathri");
        assertEquals("No such user", outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }
    @Test
    public void consoleCounterCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        GreetConsole console = new GreetConsole();
        System.setOut(new PrintStream(outContent));
        console.input("counter");
        assertEquals("Users: 0", outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }

    @Test
    public void consoleClearCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        GreetConsole console = new GreetConsole();
        console.input("greet Nathri");
        assertEquals("Hello, Nathri", outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
        outContent.reset();
        console.input("greeted");
        assertEquals("user: Nathri, greeted: 1", outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
        outContent.reset();
        console.input("clear");
        assertEquals("User list has been cleared", outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
        outContent.reset();
        console.input("greeted");
        assertEquals("No users has been greeted", outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
}

    @Test
    public void consoleClearUserCommandTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        GreetConsole console = new GreetConsole();
        System.setOut(new PrintStream(outContent));
        console.input("greet Nathri");
        assertEquals("Hello, Nathri", outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));

        outContent.reset();
        console.input("greeted nathri");
        assertEquals("user: Nathri, greeted: 1", outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));

        outContent.reset();
        console.input("clear nathri");
        assertEquals("User has been deleted", outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));

        outContent.reset();
        console.input("greeted nathri");
        assertEquals("No such user", outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }
}
