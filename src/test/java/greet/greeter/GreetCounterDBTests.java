package greet.greeter;

import greet.enums.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetCounterDBTests {

    public Connection getConnection() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:h2:./target/users", "sa", "");
        System.out.println("Successfully Connected to the database!");
        return conn;
    }

    @BeforeEach
    public void cleanUpTables() {
        try {
            try (Connection conn = getConnection()) {

                Statement statement = conn.createStatement();
                statement.addBatch("TRUNCATE table users");
                statement.executeBatch();

            }
        } catch (Exception ex) {
            System.out.println("These test will fail until the users table is created: " + ex);
        }
    }

    @Test
    public void shouldGreetUserInEnglish() {
        GreetCounter greetCounter = new GreetCounterDB();
        assertEquals("Hello, Nathri", greetCounter.greet("Nathri", Language.english));
        assertEquals("Users: 1", greetCounter.counter());
    }

    @Test
    public void shouldGreetUserInJapanese() {
        GreetCounter greetUser = new GreetCounterDB();
        assertEquals("Konichiwa, Nathri", greetUser.greet("Nathri", Language.japanese));
        assertEquals("Users: 1", greetUser.counter());
    }

    @Test
    public void shouldGreetUserInThai() {
        GreetCounter greetUser = new GreetCounterDB();
        assertEquals("Sawa dee krahp, Nathri", greetUser.greet("Nathri", Language.thai));
        assertEquals("Users: 1", greetUser.counter());
    }

    @Test
    public void shouldGreetUserInEnglishByDefault() {
        GreetCounter greetUser = new GreetCounterDB();
        assertEquals("Hello, Nathri", greetUser.greet("Nathri"));
        assertEquals("Users: 1", greetUser.counter());
    }

    @Test
    public void shouldShowHowManyTimesEachUserHasBeenGreeted() {
        GreetCounter greetUser = new GreetCounterDB();
        greetUser.greet("Nathri");
        greetUser.greet("James");
        greetUser.greet("Nathri");
        greetUser.greet("Thomas");
        assertEquals("user: James, greeted: 1 user: Nathri, greeted: 2 user: Thomas, greeted: 1", greetUser.greeted().replaceAll("\n", " ").replaceAll("\r", " "));
    }

    @Test
    public void shouldShowHowManyTimesUsersHasBeenGreeted() {
        GreetCounter greetUser = new GreetCounterDB();
        greetUser.greet("Nathri");
        greetUser.greet("Nathri");
        assertEquals("user: Nathri, greeted: 2", greetUser.greeted("Nathri"));
    }

    @Test
    public void shouldShowHowManyTimesASpecificUserHasBeenGreeted() {
        GreetCounter greetUser = new GreetCounterDB();
        greetUser.greet("Nathri");
        greetUser.greet("Nathri");
        greetUser.greet("John");
        greetUser.greet("Thomas");
        greetUser.greet("Sandra");
        greetUser.greet("Juniper");
        assertEquals("Users: 5", greetUser.counter());
    }

    @Test
    public void shouldClearListOfUsers() {
        GreetCounter greetUser = new GreetCounterDB();
        greetUser.greet("Nathri");
        greetUser.greet("John");
        greetUser.greet("Thomas");
        greetUser.greet("Sandra");
        greetUser.greet("Juniper");
        assertEquals("Users: 5", greetUser.counter());
        greetUser.clear();
        assertEquals("Users: 0", greetUser.counter());
    }

    @Test
    public void shouldClearOneGreetedUsers() {
        GreetCounter greetUser = new GreetCounterDB();
        greetUser.greet("Nathri");
        greetUser.greet("John");
        greetUser.greet("Thomas");
        greetUser.greet("Sandra");
        greetUser.greet("Juniper");
        assertEquals("Users: 5", greetUser.counter());
        greetUser.clear("Nathri");
        assertEquals("Users: 4", greetUser.counter());
    }


}
