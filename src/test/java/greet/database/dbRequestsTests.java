package greet.database;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class dbRequestsTests {

    public Connection getConnection() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:h2:./target/user" +
                "s", "sa", "");
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
    public void countAllUsersInDatabase(){
        dbRequests request = new dbRequests();
        request.addUser("Nathri");
        request.addUser("Nappa");
        assertEquals(2, request.allUsersCount());
    }

    @Test
    public void getAllUsersInDatabase(){
        dbRequests request = new dbRequests();
        request.addUser("Nathri");
        request.addUser("Nappa");
        request.addUser("Nathri");

        assertEquals(true, request.allUsers().containsKey("Nathri"));
        assertEquals(2, request.allUsers().get("Nathri"));
        assertEquals(true, request.allUsers().containsKey("Nappa"));
        assertEquals(1, request.allUsers().get("Nappa"));

    }

    @Test
    public void deleteAUserFromDatabase(){
        dbRequests request = new dbRequests();
        request.addUser("Nathri");

        assertEquals(true, request.allUsers().containsKey("Nathri"));
        assertEquals(1, request.allUsersCount());

        request.deleteUser("Nathri");
        assertEquals(false, request.allUsers().containsKey("Nathri"));
        assertEquals(0, request.allUsersCount());
    }

    @Test
    public void deleteAllUsersFromDatabase(){
        dbRequests request = new dbRequests();
        request.addUser("Poppy");
        request.addUser("Orwell");
        request.addUser("Thomas");
        request.addUser("Adam");
        request.addUser("Tim");
        request.addUser("Olivia");
        assertEquals(true, request.allUsers().containsKey("Poppy"));
        assertEquals(true, request.allUsers().containsKey("Orwell"));
        assertEquals(true, request.allUsers().containsKey("Thomas"));
        assertEquals(true, request.allUsers().containsKey("Adam"));
        assertEquals(true, request.allUsers().containsKey("Tim"));
        assertEquals(true, request.allUsers().containsKey("Olivia"));
        assertEquals(6, request.allUsersCount());

        request.deleteAllUsers();
        assertEquals(false, request.allUsers().containsKey("Poppy"));
        assertEquals(false, request.allUsers().containsKey("Orwell"));
        assertEquals(false, request.allUsers().containsKey("Thomas"));
        assertEquals(false, request.allUsers().containsKey("Adam"));
        assertEquals(false, request.allUsers().containsKey("Tim"));
        assertEquals(false, request.allUsers().containsKey("Olivia"));

        assertEquals(0, request.allUsersCount());
    }

}
