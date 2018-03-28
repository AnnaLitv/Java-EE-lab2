package classes;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class goodsDAO  {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    private ConnectionPool conObj;
    private DataSource dataSource;


    public goodsDAO(){
        conObj = new ConnectionPool();
        try {
            dataSource = conObj.setUpPool();
            con  = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getCon() {
        return con;
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

    public void totalCount(){
        String query = "select count(*) from goods";
        try {
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery(query);
            while (rs.next()){
                int count = rs.getInt(1);
                System.out.println("Total number of items in the table : " + count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       // closeConn();
    }

    public void addElement(Goods goods){
        String query = "INSERT INTO goods (code, name, weight, price, quantity, idcategory, img) VALUES (?, ?, ?, ?,?,?,?);";
        try {
            stmt=con.prepareStatement(query);
            stmt.setInt(1,goods.getCode());
            stmt.setString(2,goods.getName());
            stmt.setInt(3,goods.getWeight());
            stmt.setInt(4,goods.getPrice());
            stmt.setInt(5,goods.getQuantity());
            stmt.setInt(6,goods.getCategory().getId());
            stmt.setString(7,goods.getImg());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delElementId(int id){
        String query = "DELETE FROM goods WHERE idgoods = ?;";
        try {
            stmt=con.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delElementByName(String name){
        String query = "DELETE FROM goods WHERE name = ?;";
        try {
            stmt=con.prepareStatement(query);
            stmt.setString(1,name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Goods> select(){
        String query = "SELECT * FROM goods ;";
        ArrayList<Goods> goods = new ArrayList<Goods>();
        categoryDAO categoryDAO = new categoryDAO();
        Category category =null;
        try {
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()){
                category = categoryDAO.selectById(rs.getInt("idcategory"));
                Goods good = new Goods(rs.getInt("idgoods"),rs.getInt("code"),rs.getString("name"),rs.getInt("weight"),
                        rs.getInt("price"),rs.getInt("quantity"),
                        category,rs.getString("img"));
                goods.add(good);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    public ArrayList<Goods> selectByCategory(int id){
        String query = "SELECT * FROM goods WHERE idcategory=?;";
        ArrayList<Goods> goods = new ArrayList<Goods>();
        categoryDAO categoryDAO = new categoryDAO();
        Category category =null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            while(rs.next()){
                category = categoryDAO.selectById(rs.getInt("idcategory"));
                Goods good = new Goods(rs.getInt("idgoods"),rs.getInt("code"),rs.getString("name"),rs.getInt("weight"),
                        rs.getInt("price"),rs.getInt("quantity"),
                        category,rs.getString("img"));
                goods.add(good);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    public void updateElementById(Goods goods, int id){
        String query = "UPDATE goods SET code = ?,name = ?, weight = ?, price = ?, quantity = ?,idcategory=?,img = ? WHERE idgoods = ?;";
        try {
            stmt=con.prepareStatement(query);
            stmt.setInt(1,goods.getCode());
            stmt.setString(2,goods.getName());
            stmt.setInt(3,goods.getWeight());
            stmt.setInt(4,goods.getPrice());
            stmt.setInt(5,goods.getQuantity());
            stmt.setInt(6,goods.getCategory().getId());
            stmt.setString(7,goods.getImg());
            stmt.setInt(8,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void print(){
        for(int i=0;i< this.select().size();i++){
            System.out.println(this.select().get(i).toString());
        }
    }

    public Goods selectById(int id){
        String query = "SELECT * FROM goods WHERE idgoods= ?;";
        Goods gd = null;
        categoryDAO categoryDAO = new categoryDAO();
        Category category =null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            if(rs.next()) {
                category = categoryDAO.selectById(rs.getInt("idcategory"));
                gd = new Goods(rs.getInt("idgoods"),rs.getInt("code"),rs.getString("name"),rs.getInt("weight"),
                        rs.getInt("price"),rs.getInt("quantity"),
                        category,rs.getString("img"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gd;
    }

    public int getIdByName(String name){
        String query = "SELECT * FROM goods WHERE name= ?;";
        int id=0;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1,name);
            rs = stmt.executeQuery();
            if(rs.next()) {
                id = rs.getInt("idgoods");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

}
