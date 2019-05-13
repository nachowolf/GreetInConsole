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

    @Test
    public void shouldGreetUserInEnglish() {
        GreetCounter greetUser = new GreetCounterMap();
        assertEquals("Hello, Nathri", greetUser.greet("Nathri", Language.english));
        assertEquals("Users: 1", greetUser.counter());
    }

    @Test
    public void shouldGreetUserInJapanese() {
        GreetCounter greetUser = new GreetCounterMap();
        assertEquals("Konichiwa, Nathri", greetUser.greet("Nathri", Language.japanese));
        assertEquals("Users: 1", greetUser.counter());
    }

    @Test
    public void shouldGreetUserInThai() {
        GreetCounter greetUser = new GreetCounterMap();
        assertEquals("Sawa dee krahp, Nathri", greetUser.greet("Nathri", Language.thai));
        assertEquals("Users: 1", greetUser.counter());
    }

    @Test
    public void shouldGreetUserInEnglishByDefault() {
        GreetCounter greetUser = new GreetCounterMap();
        assertEquals("Hello, Nathri", greetUser.greet("Nathri"));
        assertEquals("Users: 1", greetUser.counter());
    }

    @Test
    public void shouldShowHowManyTimesEachUserHasBeenGreeted() {
        GreetCounter greetUser = new GreetCounterMap();
        greetUser.greet("Nathri");
        greetUser.greet("James");
        greetUser.greet("Nathri");
        greetUser.greet("Thomas");
        assertEquals("user: James, greeted: 1 user: Nathri, greeted: 2 user: Thomas, greeted: 1", greetUser.greeted().replaceAll("\n", " ").replaceAll("\r", " "));
    }

    @Test
    public void shouldShowHowManyTimesUsersHasBeenGreeted() {
        GreetCounter greetUser = new GreetCounterMap();
        greetUser.greet("Nathri");
        greetUser.greet("Nathri");
        assertEquals("user: Nathri, greeted: 2", greetUser.greeted("Nathri"));
    }

    @Test
    public void shouldShowHowManyTimesASpecificUserHasBeenGreeted() {
        GreetCounter greetUser = new GreetCounterMap();
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
        GreetCounter greetUser = new GreetCounterMap();
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
        GreetCounter greetUser = new GreetCounterMap();
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
