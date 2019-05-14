package greet;


import greet.greeter.GreetCounter;
import greet.greeter.GreetCounterDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandExecuterDBTests {

    GreetCounter greetCounter = new GreetCounterDB();

    public Connection getConnection() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:h2:./target/users", "sa", "");
//        System.out.println("Successfully Connected to the database!");
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
    public void executeGreetUser() {
        CommandExecuter executer = new CommandExecuter(greetCounter);
        CommandExtractor extractor = new CommandExtractor("greet nathri");

        assertEquals("Hello, Nathri", executer.execute(extractor));
    }

    @Test
    public void executeGreetUserThai() {
        CommandExecuter executer = new CommandExecuter(greetCounter);
        CommandExtractor extractor = new CommandExtractor("greet nathri thai");

        assertEquals("Sawa dee krahp, Nathri", executer.execute(extractor));
    }

    @Test
    public void executeGreetUserJapanese() {
        CommandExecuter executer = new CommandExecuter(greetCounter);
        CommandExtractor extractor = new CommandExtractor("greet nathri japanese");

        assertEquals("Konichiwa, Nathri", executer.execute(extractor));
    }

    @Test
    public void executeGreetedSingleUser() {
        CommandExecuter executer = new CommandExecuter(greetCounter);

        CommandExtractor extract1 = new CommandExtractor("greet nathri");
        executer.execute(extract1);

        CommandExtractor extract2 = new CommandExtractor("greet nathri");
        executer.execute(extract2);

        CommandExtractor extract3 = new CommandExtractor("greet john");
        executer.execute(extract3);

        CommandExtractor extractor = new CommandExtractor("greeted nathri");
        assertEquals("user: Nathri, greeted: 2", executer.execute(extractor));
    }

    @Test
    public void executeGreeted() {
        CommandExecuter executer = new CommandExecuter(greetCounter);

        CommandExtractor extract1 = new CommandExtractor("greet nathri");
        executer.execute(extract1);

        CommandExtractor extract2 = new CommandExtractor("greet nathri");
        executer.execute(extract2);

        CommandExtractor extract3 = new CommandExtractor("greet john");
        executer.execute(extract3);

        CommandExtractor extract4 = new CommandExtractor("greet ann");
        executer.execute(extract4);


        CommandExtractor extractor = new CommandExtractor("greeted");
        assertEquals("user: Ann, greeted: 1 user: John, greeted: 1 user: Nathri, greeted: 2", executer.execute(extractor).replaceAll("\n", " ").replaceAll("\r", " "));
    }

    @Test
    public void executeClearUser() {
        CommandExecuter executer = new CommandExecuter(greetCounter);

        CommandExtractor extract1 = new CommandExtractor("greet nathri");
        executer.execute(extract1);

        CommandExtractor extract2 = new CommandExtractor("greet nathri");
        executer.execute(extract2);

        CommandExtractor extract3 = new CommandExtractor("greet john");
        executer.execute(extract3);

        CommandExtractor extract4 = new CommandExtractor("greet ann");
        executer.execute(extract4);

        CommandExtractor extractClear = new CommandExtractor("clear john");
        assertEquals("User has been deleted", executer.execute(extractClear));

        CommandExtractor extractorGreeted = new CommandExtractor("greeted");
        assertEquals("user: Ann, greeted: 1 user: Nathri, greeted: 2", executer.execute(extractorGreeted).replaceAll("\n", " ").replaceAll("\r", " "));
    }

    @Test
    public void executeClearAllUser() {
        CommandExecuter executer = new CommandExecuter(greetCounter);

        CommandExtractor extract1 = new CommandExtractor("greet nathri");
        executer.execute(extract1);

        CommandExtractor extract2 = new CommandExtractor("greet nathri");
        executer.execute(extract2);

        CommandExtractor extract3 = new CommandExtractor("greet john");
        executer.execute(extract3);

        CommandExtractor extract4 = new CommandExtractor("greet ann");
        executer.execute(extract4);

        CommandExtractor extractClear = new CommandExtractor("clear");
        assertEquals("All users have been deleted", executer.execute(extractClear));

        CommandExtractor extractorGreeted = new CommandExtractor("greeted");
        assertEquals("No users has been greeted", executer.execute(extractorGreeted).replaceAll("\n", " ").replaceAll("\r", " "));
    }

    @Test
    public void executeCounter() {
        CommandExecuter executer = new CommandExecuter(greetCounter);

        CommandExtractor extract1 = new CommandExtractor("greet micheal");
        executer.execute(extract1);

        CommandExtractor extract2 = new CommandExtractor("greet nathri");
        executer.execute(extract2);

        CommandExtractor extract3 = new CommandExtractor("greet john");
        executer.execute(extract3);

        CommandExtractor extract4 = new CommandExtractor("greet ann");
        executer.execute(extract4);

        CommandExtractor extractCounter = new CommandExtractor("counter");
        assertEquals("Users: 4", executer.execute(extractCounter));

    }

    @Test
    public void executeHelp() {
        CommandExecuter executer = new CommandExecuter(greetCounter);
        CommandExtractor extractor = new CommandExtractor("help");
        assertEquals("Greeter Application Commands: greet : Can be used as 'greet <user>' or 'greet <user> <language>'. greets in English by default. quit : Exits application. help : Prints list of available command greeted : 'greeted' returns all users that has been greeted and how many times they have been greeted.'greeted <user>' returns a specific user. clear : 'clear' removes all users that has been greeted and 'clear <user>' removes a specific user. counter : returns how many users have been greeted", executer.execute(extractor).replaceAll("\n", " ").replaceAll("\r", " "));
    }

    @Test
    public void executeQuit() {
        CommandExecuter executer = new CommandExecuter(greetCounter);
        CommandExtractor extractor = new CommandExtractor("quit");
        assertEquals("quit", executer.execute(extractor));
    }

    @Test
    public void executeCommandNull() {
        CommandExecuter executer = new CommandExecuter(greetCounter);
        CommandExtractor extractor = new CommandExtractor("grapes");
        assertEquals("invalid command.", executer.execute(extractor));
    }

    @Test
    public void executeGreetNullName() {
        CommandExecuter executer = new CommandExecuter(greetCounter);
        CommandExtractor extractor = new CommandExtractor("greet");
        assertEquals("Please specify a name.", executer.execute(extractor));
    }

    @Test
    public void executeClearEmptyUsersCount() {
        CommandExecuter executer = new CommandExecuter(greetCounter);
        CommandExtractor extractor = new CommandExtractor("clear");
        assertEquals("No users have been greeted", executer.execute(extractor));
    }

    @Test
    public void executeClearUsersThenClearEmptyUsersCount() {
        CommandExecuter executer = new CommandExecuter(greetCounter);

        CommandExtractor extractor1 = new CommandExtractor("greet john");
        executer.execute(extractor1);

        CommandExtractor extractor2 = new CommandExtractor("greet jack");
        executer.execute(extractor2);

        CommandExtractor extractor3 = new CommandExtractor("clear");
        assertEquals("All users have been deleted", executer.execute(extractor3));

        CommandExtractor extractor4 = new CommandExtractor("clear");
        assertEquals("No users have been greeted", executer.execute(extractor4));
    }

    @Test
    public void executeClearEmptyUserCount() {
        CommandExecuter executer = new CommandExecuter(greetCounter);
        CommandExtractor extractor = new CommandExtractor("clear nat");
        assertEquals("No such user has been greeted", executer.execute(extractor));
    }

    @Test
    public void executeClearUserThenClearEmptyUserCount() {
        CommandExecuter executer = new CommandExecuter(greetCounter);

        CommandExtractor extractor1 = new CommandExtractor("greet john");
        executer.execute(extractor1);

        CommandExtractor extractor2 = new CommandExtractor("clear john");
        assertEquals("User has been deleted", executer.execute(extractor2));

        CommandExtractor extractor3 = new CommandExtractor("clear john");
        assertEquals("No such user has been greeted", executer.execute(extractor3));
    }
}


