package org.example.userdao;


import org.example.JDBCUtils;

import java.sql.*;
import java.sql.SQLException;

public class UserDaoImpl implements UsersDao {


    public static void main(String[] args) {

    }

    public User getUser(int id) {
        try(Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id=" + id);
            if(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getInt("phone"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        }catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return null;
    }



    public boolean insertUser(User user){

        String insert = "INSERT INTO swimmers" + "(id, username, email, phone, password) VALUES" +
                "(?,?,?,?,?);";
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insert);){
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,user.getId());
            preparedStatement.setString(2,user.getUsername());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setInt(4, user.getPhone());
            preparedStatement.setString(5, user.getPassword());
            connection.commit();


        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return false;
    }



    public boolean updateUser(User user) {
        String update = "UPDATE swimmers SET username=?, email=?, phone=?, password=? WHERE id=?";

        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(update)){
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setInt(3, user.getPhone());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5,user.getId());

            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return true;
            }

        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return false;
    }



    public boolean deleteUser(int id){
        try(Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement()){
            int i = statement.executeUpdate("DELETE FROM users WHERE id=" + id);

            if (i == 1){
                return true;
            }
        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }

        return false;
    }
}
