package org.example.sytlesdao;

import org.example.JDBCUtils;
import org.example.sponsordao.Sponsor;

import java.sql.*;

public class StyleDaoImpl implements StylesDao {

    final private static String insert = "INSERT INTO styles" + "(id, style_name) VALUES (?,?);";
    final private static String update = "UPDATE styles SET style_name=? WHERE id=?";

    public static void main(String[] args) {

    }

    public Style getStyle(int id) {
        try(Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM styles WHERE id=" + id);
            connection.setAutoCommit(false);
            if(resultSet.next()){
                Style style = new Style();
                style.setId(resultSet.getInt("id"));
                style.setStyle_name(resultSet.getString("style_name"));
            }
            connection.commit();
        }catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return null;
    }


    public boolean insertStyle(Style style) {
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insert);){
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,style.getId());
            preparedStatement.setString(2,style.getStyle_name());

            connection.commit();

        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return false;
    }

    public boolean updateStyle(Style style) {
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(update)){
            connection.setAutoCommit(false);

            preparedStatement.setString(1,style.getStyle_name());
            preparedStatement.setInt(2,style.getId());

            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return true;
            }
            connection.commit();
        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return false;
    }

    public boolean deleteStyle(int id) {
        try(Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement()){
            int i = statement.executeUpdate("DELETE FROM styles WHERE id=" + id);

            if (i == 1){
                return true;
            }
        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }

        return false;
    }
}
