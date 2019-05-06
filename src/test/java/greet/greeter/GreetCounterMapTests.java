package greet.greeter;

import greet.enums.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetCounterMapTests {

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
    public void shouldGreetUserInEnglish(){
        GreetCounterMap greetUser = new GreetCounterMap();
        assertEquals("Hello, Nathri",greetUser.greet("Nathri", Language.English));
        assertEquals(1, greetUser.counter());
    }

    @Test
    public void shouldGreetUserInJapanese(){
        GreetCounterMap greetUser = new GreetCounterMap();
        assertEquals("Konichiwa, Nathri",greetUser.greet("Nathri", Language.Japanese));
        assertEquals(1, greetUser.counter());
    }

    @Test
    public void shouldGreetUserInThai(){
        GreetCounterMap greetUser = new GreetCounterMap();
        assertEquals("Sawa dee krahp, Nathri",greetUser.greet("Nathri", Language.Thai));
        assertEquals(1, greetUser.counter());
    }

    @Test
    public void shouldGreetUserInEnglishByDefault(){
        GreetCounterMap greetUser = new GreetCounterMap();
        assertEquals("Hello, Nathri",greetUser.greet("Nathri"));
        assertEquals(1, greetUser.counter());
    }

    @Test
    public void shouldShowHowManyTimesEachUserHasBeenGreeted(){
        GreetCounterMap greetUser = new GreetCounterMap();
        greetUser.greet("Nathri");
        greetUser.greet("James");
        greetUser.greet("Nathri");
        greetUser.greet("Thomas");
        assertEquals(Arrays.asList("user: James, greeted: 1", "user: Nathri, greeted: 2", "user: Thomas, greeted: 1"), greetUser.greeted());
    }

    @Test
    public void shouldShowHowManyTimesUsersHasBeenGreeted(){
        GreetCounterMap greetUser = new GreetCounterMap();
        greetUser.greet("Nathri");
        greetUser.greet("Nathri");
        assertEquals("user: Nathri, greeted: 2", greetUser.greeted("Nathri"));
    }

    @Test
    public void shouldShowHowManyTimesASpecificUserHasBeenGreeted(){
        GreetCounterMap greetUser = new GreetCounterMap();
        greetUser.greet("Nathri");
        greetUser.greet("Nathri");
        greetUser.greet("John");
        greetUser.greet("Thomas");
        greetUser.greet("Sandra");
        greetUser.greet("Juniper");
        assertEquals(5, greetUser.counter());
    }

    @Test
    public void shouldClearListOfUsers(){
        GreetCounterMap greetUser = new GreetCounterMap();
        greetUser.greet("Nathri");
        greetUser.greet("John");
        greetUser.greet("Thomas");
        greetUser.greet("Sandra");
        greetUser.greet("Juniper");
        assertEquals(5, greetUser.counter());
        greetUser.clear();
        assertEquals(0, greetUser.counter());
    }

    @Test
    public void shouldClearOneGreetedUsers(){
        GreetCounterMap greetUser = new GreetCounterMap();
        greetUser.greet("Nathri");
        greetUser.greet("John");
        greetUser.greet("Thomas");
        greetUser.greet("Sandra");
        greetUser.greet("Juniper");
        assertEquals(5, greetUser.counter());
        greetUser.clear("Nathri");
        assertEquals(4, greetUser.counter());
    }

    @Test
    public void shouldReturnAllPossibleCommands(){
        GreetCounterMap greetUser = new GreetCounterMap();
        assertEquals(Arrays.asList("Greet", "Quit", "Help", "Greeted", "Clear", "Counter"), greetUser.help());
    }
}
