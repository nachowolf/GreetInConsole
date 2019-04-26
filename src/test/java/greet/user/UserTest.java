package greet.user;

import greet.database.dbRequests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    public Connection getConnection() throws Exception {
        // TODO - add a username of "sa" and a blank password ""
        // TODO - if the db is created via default flyway config the username will be "sa" with a blank password
        // you can change this by removing the user element containing sa in the pom.xml file
        // if not be sure to delete the *.db files in your target folder before running mvn flyway:migrate the first time
        // and be sure the set the username to "sa" password blank ""
        // if your remove the user element from the pom.xml file you are use a username of "" and a password of ""

        Connection conn = DriverManager.getConnection("jdbc:h2:./db/users", "sa", "");
        return conn;
    }

    @BeforeEach
    public void cleanUpTables() {
        // don't touch any code in here!!!
        try {
            try(Connection conn = getConnection()) {
                // I repeat don't touch any code in here!!!
                Statement statement = conn.createStatement();
                statement.addBatch("delete from users where name in ('Nathri', 'john', 'Nappa')");
//                statement.addBatch("update fruit set price = 4.75  where name = 'red apple'");
                statement.executeBatch();
                // I repeat once again don't touch any code in here!!!
            }
        } catch(Exception ex) {
            System.out.println("These test will fail until the fruit table is created: " + ex);
        }
    }

    @Test
    public void shouldCreateUser(){
        User user = new User("Nathri");
        assertEquals("Nathri", user.getUsername());
    }

    @Test
    public void shouldReturnUserGreetCount(){
        User user = new User("Nathri");
        assertEquals(0, user.getGreetCount());
    }

    @Test
    public void shouldIncrementAndReturnUserGreetCount(){
        User user = new User("Nathri");
        user.greet();
        assertEquals(1, user.getGreetCount());
    }

    @Test
    public void countAllUsersInDatabase(){
        User user = new User();
        user.addUser("Nathri");
        user.addUser("Nappa");
        assertEquals(2, user.allUsersCount());
    }

    @Test
    public void getAllUsersInDatabase(){
        User user = new User();
        user.addUser("Nathri");
        user.addUser("Nappa");
        user.addUser("Nathri");
        assertEquals(2, user.allUsers());
    }
}
