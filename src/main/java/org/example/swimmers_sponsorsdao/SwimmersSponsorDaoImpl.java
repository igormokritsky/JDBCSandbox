package org.example.swimmers_sponsorsdao;

import org.example.JDBCUtils;

import java.sql.*;

public class SwimmersSponsorDaoImpl implements SwimmerSponsorsDao {

    final private static String insert = "INSERT INTO sponsors" +
            "(id, swimmer_id, sponsor_id, contract_amount) VALUES (?,?,?,?);";
    final private static String update =
            "UPDATE sponsors SET swimmer_id=?, sponsor_id=?, contract_amount=? WHERE id=?";

    public static void main(String[] args) {

    }

    public SwimmersSponsor getSwimmerSponsor(int id) {
        try(Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM swimmers_sponsors WHERE id=" + id);
            connection.setAutoCommit(false);
            if(resultSet.next()){
                SwimmersSponsor swimmersSponsor = new SwimmersSponsor();
                swimmersSponsor.setId(resultSet.getInt("id"));
                swimmersSponsor.setSwimmer_id(resultSet.getInt("swimmer_id"));
                swimmersSponsor.setSponsor_id(resultSet.getInt("sponsor_id"));
                swimmersSponsor.setContract_amount(resultSet.getInt("contract_amount"));
            }

            connection.commit();

        }catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return null;
    }

    public boolean insertSwimmerSponsor(SwimmersSponsor swimmersSponsor) {
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insert);){
            connection.setAutoCommit(false);

            preparedStatement.setInt(1, swimmersSponsor.getId());
            preparedStatement.setInt(2, swimmersSponsor.getSwimmer_id());
            preparedStatement.setInt(3, swimmersSponsor.getSponsor_id());
            preparedStatement.setInt(4, swimmersSponsor.getContract_amount());

            connection.commit();
        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return false;
    }


    public boolean updateSwimmerSponsor(SwimmersSponsor swimmersSponsor) {
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(update)){
            connection.setAutoCommit(false);

            preparedStatement.setInt(1, swimmersSponsor.getSwimmer_id());
            preparedStatement.setInt(2, swimmersSponsor.getSponsor_id());
            preparedStatement.setInt(3, swimmersSponsor.getContract_amount());
            preparedStatement.setInt(4, swimmersSponsor.getId());

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


    public boolean deleteSwimmerSponsor(int id) {
        try(Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement()){
            int i = statement.executeUpdate("DELETE FROM swimmers_sponsors WHERE id=" + id);

            if (i == 1){
                return true;
            }
        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }

        return false;
    }

}
