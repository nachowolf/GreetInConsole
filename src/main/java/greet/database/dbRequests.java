package greet.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class dbRequests {

    final String COUNT_ALL_USERS_SQL = "select count(*) from users";

    final String GET_ALL_USERS_SQL = "select name, greets from users order by name asc";

    final String FIND_USER_SQL = "select name, greets from users where name = ?";

    final String INSERT_USER_SQL = "insert into users (name, greets) values(?, ?)";

    final String GET_USER_GREETS_SQL = "select greets from users where name = ?";

    final String GREET_USER_SQL = "update users set greets = ? where name = ?";

    final String DELETE_USER_SQL = "delete from users where name = ?";

    final String DELETE_ALL_USERS_SQL = "truncate table users";

    Connection conn;
    PreparedStatement psCountAllUsers;
    PreparedStatement psGetAllUsers;
    PreparedStatement psCreateNewUser;
    PreparedStatement psFindUser;
    PreparedStatement psGetUserGreets;
    PreparedStatement psGreetUser;
    PreparedStatement psDeleteUser;
    PreparedStatement psDeleteAllUsers;

    public dbRequests() {
        try {
            System.out.println("try");
//            Class.forName("org.h2.Driver");
            conn = DriverManager.
                    getConnection("jdbc:h2:./target/users", "sa", "");
            psCountAllUsers = conn.prepareStatement(COUNT_ALL_USERS_SQL);
            psGetAllUsers = conn.prepareStatement(GET_ALL_USERS_SQL);
            psCreateNewUser = conn.prepareStatement(INSERT_USER_SQL);
            psFindUser = conn.prepareStatement(FIND_USER_SQL);
            psGetUserGreets = conn.prepareStatement(GET_USER_GREETS_SQL);
            psGreetUser = conn.prepareStatement(GREET_USER_SQL);
            psDeleteUser = conn.prepareStatement(DELETE_USER_SQL);
            psDeleteAllUsers = conn.prepareStatement(DELETE_ALL_USERS_SQL);

        } catch(Exception ex) {
            System.out.println("catch");
            ex.printStackTrace();
        } finally {
            System.out.println("finally");
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
                psCreateNewUser.execute();

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

    public LinkedHashMap allUsers(){
        LinkedHashMap<String, Integer> userList = new LinkedHashMap <String, Integer>();
        try{
            ResultSet rsUsers = psGetAllUsers.executeQuery();
            while (rsUsers.next()) {
//                System.out.print(rsUsers.getString("name"));
                userList.put(rsUsers.getString("name"), rsUsers.getInt("greets"));

            }
//            System.out.println(userList.keySet());


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public void deleteUser (String name){
        try{
            psDeleteUser.setString(1, name);
            psDeleteUser.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllUsers (){
        try{
            psDeleteAllUsers.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}