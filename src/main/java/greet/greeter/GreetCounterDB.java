package greet.greeter;

import greet.enums.Language;
import greet.methods.Helper;

import java.sql.*;
import java.util.List;

public class GreetCounterDB  implements GreetUser{

    final String COUNT_ALL_USERS_SQL = "select count(*) from users";

    final String GET_ALL_USERS_SQL = "select name, greets from users order by name asc";

    final String FIND_USER_SQL = "select name, greets from users where name = ?";

    final String INSERT_USER_SQL = "insert into users (name, greets) values(?, ?)";

    final String GET_USER_GREETS_SQL = "select greets from users where name = ?";

    final String GREET_USER_SQL = "update users set greets = ? where name = ?";

    final String DELETE_USER_SQL = "delete from users where name = ?";

    final String DELETE_ALL_USERS_SQL = "truncate table users";

    Helper method = new Helper();

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
            return "Hello, " + method.capitilize(name);
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
            return method.greetLanguage(language) + method.capitilize(name);
        }
    }

    @Override
    public List greeted() {
        return null;
    }

    @Override
    public String greeted(String name) {
        try {
            psFindUser.setString(1, name);
            ResultSet rsUserFound = psFindUser.executeQuery();

            if(rsUserFound.next()){
                String user = rsUserFound.getString("name");
                Integer greeted = rsUserFound.getInt("greets");
                return "user: " + method.capitilize(user) + ", greeted: " + greeted;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
    }
        finally {
            return null;
        }
        }

    @Override
    public int counter() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public void clear(String user) {

    }

    @Override
    public List help() {
        return null;
    }
}
