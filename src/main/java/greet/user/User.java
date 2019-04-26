package greet.user;


import java.sql.*;
import java.util.HashMap;

public class User implements Account {

    private String username;
    private int greetCount = 0;

    final String COUNT_ALL_USERS_SQL = "select count(*) from users";

    final String GET_ALL_USERS_SQL = "select name, greets from users";

    final String INSERT_USER_SQL = "insert into users (name, greets) values(?, ?)";

    final String FIND_USER_SQL = "select name, greets from users where name = ?";

    final String GET_USER_GREETS_SQL = "select greets from users where name = ?";

    final String GREET_USER_SQL = "update users set greets = ? where name = ?";

    Connection conn;
    PreparedStatement psCountAllUsers;
    PreparedStatement psGetAllUsers;
    PreparedStatement psCreateNewUser;
    PreparedStatement psFindUser;
    PreparedStatement psGetUserGreets;
    PreparedStatement psGreetUser;

    public User() {
        try {
            conn = DriverManager.
                    getConnection("jdbc:h2:./db/users", "sa", "");
            psCountAllUsers = conn.prepareStatement(COUNT_ALL_USERS_SQL);
            psGetAllUsers = conn.prepareStatement(GET_ALL_USERS_SQL);
            psCreateNewUser = conn.prepareStatement(INSERT_USER_SQL);
            psFindUser = conn.prepareStatement(FIND_USER_SQL);
            psGetUserGreets = conn.prepareStatement(GET_USER_GREETS_SQL);
            psGreetUser= conn.prepareStatement(GREET_USER_SQL);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addUser(String name) {

        try {

            psFindUser.setString(1, name);
            ResultSet rsUserFound = psFindUser.executeQuery();

            if (!rsUserFound.next()) {
                // System.out.println("no count for " + petSpotted);
                // insert
                psCreateNewUser.setString(1, name);
                psCreateNewUser.setInt(2, 1);
                System.out.println("print here => " + psCreateNewUser.execute());

            } else {
                int currentGreets = rsUserFound.getInt("greets");
                psGreetUser.setInt(1, ++currentGreets);
                psGreetUser.setString(2, name);
                psGreetUser.execute();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int allUsersCount() {
        int count = 0;
        try {
            ResultSet rs = psCountAllUsers.executeQuery();
//            ResultSet rs = st.executeQuery("select count(*) from TABLE_NAME");
            rs.next();
            count = rs.getInt(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    public HashMap allUsers(){
        HashMap <String, Integer> userList = new HashMap <String, Integer>();
        try{

            ResultSet rsUsers = psGetAllUsers.executeQuery();
            while (rsUsers.next()) {
                System.out.println(rsUsers);
                userList.put(rsUsers.getString("name"), rsUsers.getInt("greets"));

            }


        } catch (SQLException ex) {
        ex.printStackTrace();
    }
        return userList;
    }

   public User(String name){
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    public int getGreetCount() {
        return greetCount;
    }

    public void greet(){
        this.greetCount++;
    }
}
