package greet.greeter;

import greet.enums.Language;
import greet.methods.Helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GreetCounterDB  implements GreetCounter {

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

    public GreetCounterDB(){
        try {

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

            ex.printStackTrace();
        }
    }


    @Override
    public String greet(String name) {
        try {

            psFindUser.setString(1, name);
            ResultSet rsUserFound = psFindUser.executeQuery();

            if (!rsUserFound.next()) {

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
       finally {
            return "Hello, " + Helper.capitilize(name);
        }
    }

    @Override
    public String greet(String name, Language language) {
        try {

            psFindUser.setString(1, name);
            ResultSet rsUserFound = psFindUser.executeQuery();

            if (!rsUserFound.next()) {

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
        finally {
            return Helper.greetLanguage(language) + Helper.capitilize(name);
        }
    }

    @Override
    public List greeted() {

        ArrayList<String> result = new ArrayList<String>();
        try {
            ResultSet rsUsers = psGetAllUsers.executeQuery();

            while (rsUsers.next()) {
                String user = rsUsers.getString("name");
                Integer greeted = rsUsers.getInt("greets");
                result.add("user: " + Helper.capitilize(user) + ", greeted: " + greeted);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            return result;
        }
    }


    @Override
    public String greeted(String name) {
        String result = "No such user has been greeted.";
        try {
            psFindUser.setString(1, name);
            ResultSet rsUserFound = psFindUser.executeQuery();

            if(rsUserFound.next()){
                String user = rsUserFound.getString("name");
                Integer greeted = rsUserFound.getInt("greets");
                result = "user: " + Helper.capitilize(user) + ", greeted: " + greeted;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
    }
        finally {
            return result;
        }
        }

    @Override
    public String counter() {
        String count = "0";
        try {
            ResultSet rs = psCountAllUsers.executeQuery();
//            ResultSet rs = st.executeQuery("select count(*) from TABLE_NAME");
            rs.next();
            count = rs.getString(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "Users: " + count;
    }

    @Override
    public String clear() {
        String result = null;
        try{
            psDeleteAllUsers.execute();
            result = "All users have been deleted";

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }

    @Override
    public String clear(String name) {
        String result = "No such user";
        try{
            psDeleteUser.setString(1, name);
            psDeleteUser.execute();
            result = "User has been deleted";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }


}
