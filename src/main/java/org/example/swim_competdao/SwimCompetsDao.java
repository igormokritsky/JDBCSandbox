package org.example.swim_competdao;

public interface SwimCompetsDao {

    SwimCompet getSwimCompet(int id);
    boolean insertSwimCompet(SwimCompet swimCompet);
    boolean updateSwimCompet(SwimCompet swimCompet);
    boolean deleteSwimCompet(int id);
}
