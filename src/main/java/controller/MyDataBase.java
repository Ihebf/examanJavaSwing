package controller;

import model.Person;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

public class MyDataBase {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public boolean add(Person p){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/examen", "root", "");
            String SQL = "INSERT INTO person (name, genere) VALUES(?,?);";
            preparedStatement = connect.prepareStatement(SQL);
            preparedStatement.setString(1,p.getName());
            preparedStatement.setString(2,p.getGenre());
            preparedStatement.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public int modify(Person p){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/examen", "root", "");
            String SQL = "UPDATE person set genere=?,name=?   WHERE id=?;";
            preparedStatement = connect.prepareStatement(SQL);
            preparedStatement.setInt(1,p.getId());
            preparedStatement.setString(2,p.getGenre());
            preparedStatement.setString(3,p.getName());
            return preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    public boolean delete(Person p){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/examen", "root", "");
            String SQL = "DELETE FROM person WHERE id=?;";
            preparedStatement = connect.prepareStatement(SQL);
            preparedStatement.setInt(1,p.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Person> fillTab(DefaultTableModel model) {
        try {
            model.setRowCount(0);
            ArrayList<Person> p = new ArrayList<>();
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/examen", "root", "");
            String query = "SELECT * FROM personr";
            Statement stm = connect.createStatement();
            ResultSet res = stm.executeQuery(query);
            while (res.next()){
                model.addRow(new Object[]{res.getInt(1),res.getString(2),res.getString(3)});
            }
            return p;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

