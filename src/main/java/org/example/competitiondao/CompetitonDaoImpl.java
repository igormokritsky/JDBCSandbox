package org.example.competitiondao;

import org.example.JDBCUtils;
import org.example.sponsordao.Sponsor;

import java.sql.*;

public class CompetitonDaoImpl implements CompetitionsDao {

    final private static String insert = "INSERT INTO competitions" + "(id, county_id, style_id, distance) VALUES (?,?,?,?);";
    final private static String update = "UPDATE competitions SET county_id=?, style_id=?, distance=? WHERE id=?";

    public static void main(String[] args) {

    }

    public Competition getCompetition(int id) {
        try(Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM competitions WHERE id=" + id);
            connection.setAutoCommit(false);
            if(resultSet.next()){
                Competition competition = new Competition();
                competition.setId(resultSet.getInt("id"));
                competition.setCountry_id(resultSet.getInt("country_id"));
                competition.setStyle_id(resultSet.getInt("style_id"));
            }
            connection.commit();
        }catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return null;
    }

    public boolean insertCompetition(Competition competition) {
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insert);){
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,competition.getId());
            preparedStatement.setInt(2,competition.getCountry_id());
            preparedStatement.setInt(3,competition.getStyle_id());
            preparedStatement.setInt(4,competition.getDistance());

            connection.commit();

        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return false;
    }

    public boolean updateCompetition(Competition competition) {
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(update)){
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,competition.getCountry_id());
            preparedStatement.setInt(2,competition.getStyle_id());
            preparedStatement.setInt(3,competition.getDistance());
            preparedStatement.setInt(4,competition.getId());

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

    public boolean deleteCompetition(int id) {
        try(Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement()){
            int i = statement.executeUpdate("DELETE FROM competitions WHERE id=" + id);

            if (i == 1){
                return true;
            }
        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return false;
    }

}
