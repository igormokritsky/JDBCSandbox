package org.example.sponsordao;

import org.example.JDBCUtils;

import java.sql.*;

public class SponsorDaoImpl implements SponsorsDao {
    final private static String insert = "INSERT INTO sponsors" + "(id, name) VALUES (?,?);";
    final private static String update = "UPDATE sponsors SET name=? WHERE id=?";

    public static void main(String[] args) {

    }

    public Sponsor getSponsor(int id){
        try(Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sponsors WHERE id=" + id);
            connection.setAutoCommit(false);
            if(resultSet.next()){
                Sponsor sponsor = new Sponsor();
                sponsor.setId(resultSet.getInt("id"));
                sponsor.setName(resultSet.getString("name"));
            }
            connection.commit();
        }catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return null;
    }


    public boolean insertSponsor(Sponsor sponsor) {
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insert);){
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,sponsor.getId());
            preparedStatement.setString(2,sponsor.getName());

            connection.commit();

        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return false;
    }


    public boolean updateSponsor(Sponsor sponsor) {
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(update)){
            connection.setAutoCommit(false);

            preparedStatement.setString(1,sponsor.getName());
            preparedStatement.setInt(2,sponsor.getId());

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

    public boolean deleteSponsor(int id) {
        try(Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement()){
            int i = statement.executeUpdate("DELETE FROM sponsors WHERE id=" + id);

            if (i == 1){
                return true;
            }
        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }

        return false;
    }



}
