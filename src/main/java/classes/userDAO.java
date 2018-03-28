package classes;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class userDAO {
    public Connection getCon() {
        return con;
    }

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    ConnectionPool conObj;
    DataSource dataSource;


    userDAO(){
        conObj = new ConnectionPool();
        try {
            dataSource = conObj.setUpPool();
            con  = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConn(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollbackConn(){
        try {
            con.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addElement(User user){
        String query = "INSERT INTO user (firstName, lastName, email, password, phone) VALUES (?, ?, ?, ?,?);";
        try {
            stmt=con.prepareStatement(query);
            stmt.setString(1,user.getFirstName());
            stmt.setString(2,user.getLastName());
            stmt.setString(3,user.getEmail());
            stmt.setString(4,user.getPassword());
            stmt.setString(5,user.getPhone());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> select(){
        String query = "SELECT * FROM user ;";
        ArrayList<User> users = new ArrayList<User>();
        try {
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()){
               User user = new User(rs.getInt("idUser"),rs.getString("firstName"),rs.getString("lastName"),
                       rs.getString("email"),rs.getString("password"),rs.getString("phone"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void print(){
        for(int i=0;i< this.select().size();i++){
            System.out.println(this.select().get(i).toString());
        }
    }

    public User selectById(int id){
        String query = "SELECT * FROM user WHERE idUser= ?;";
        User user = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            if(rs.next()) {
                user = new User(rs.getInt("idUser"),rs.getString("firstName"),rs.getString("lastName"),
                        rs.getString("email"),rs.getString("password"),rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
