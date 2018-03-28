package classes;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class categoryDAO{
    public Connection getCon() {
        return con;
    }

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    ConnectionPool conObj;
    DataSource dataSource;


    public categoryDAO(){
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

    public ArrayList<Category> select() {
        String query = "select * from category;";
        ArrayList<Category> categories = new ArrayList<Category>();
        Category cat;
        try {
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()){
                cat = new Category(rs.getInt("idcategory"),rs.getString("name"));
                categories.add(cat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Category selectById(int id){
        String query = "SELECT * FROM category WHERE idcategory= ?;";
        Category cat=null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            if(rs.next()) {
                cat = new Category(rs.getInt("idcategory"),rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cat;
    }

    public void print(){
        for(int i=0;i< this.select().size();i++){
            System.out.println(this.select().get(i).toString());
        }
    }

    public Category selectCatByName(String name){
        String query = "SELECT * FROM category WHERE name= ?;";
        Category cat=null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1,name);
            rs = stmt.executeQuery();
            if(rs.next()) {
                cat = new Category(rs.getInt("idcategory"),rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cat;
    }
}
