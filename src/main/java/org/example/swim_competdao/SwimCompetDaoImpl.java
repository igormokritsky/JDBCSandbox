package org.example.swim_competdao;

import org.example.JDBCUtils;

import java.sql.*;

public class SwimCompetDaoImpl implements SwimCompetsDao {

    final private static String insert = "INSERT INTO sponsors" + "(id, competition_id, swimmer_id, time) VALUES (?,?,?,?);";
    final private static String update = "UPDATE sponsors SET competition_id=?, swimmer_id=?, time=? WHERE id=?";

    public static void main(String[] args) {

    }

    public SwimCompet getSwimCompet(int id) {
        try(Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM swim_compet WHERE id=" + id);
            connection.setAutoCommit(false);
            if(resultSet.next()){
                SwimCompet swimCompet = new SwimCompet();
                swimCompet.setId(resultSet.getInt("id"));
                swimCompet.setCompetition_id(resultSet.getInt("competition_id"));
                swimCompet.setSwimmer_id(resultSet.getInt("swimmer_id"));
                swimCompet.setTime(resultSet.getInt("time"));
            }
            connection.commit();

        }catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return null;
    }

    public boolean insertSwimCompet(SwimCompet swimCompet) {
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insert);){
            connection.setAutoCommit(false);
            preparedStatement.setInt(1,swimCompet.getId());
            preparedStatement.setInt(2,swimCompet.getCompetition_id());
            preparedStatement.setInt(3,swimCompet.getSwimmer_id());
            preparedStatement.setInt(4,swimCompet.getTime());


            connection.commit();
        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return false;
    }

    public boolean updateSwimCompet(SwimCompet swimCompet) {

        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(update)){
            connection.setAutoCommit(false);
            preparedStatement.setInt(1,swimCompet.getCompetition_id());
            preparedStatement.setInt(2,swimCompet.getSwimmer_id());
            preparedStatement.setInt(3,swimCompet.getTime());
            preparedStatement.setInt(4,swimCompet.getId());

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


    public boolean deleteSwimCompet(int id) {
        try(Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement()){
            int i = statement.executeUpdate("DELETE FROM swim_compet WHERE id=" + id);

            if (i == 1){
                return true;
            }
        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }

        return false;
    }

}
