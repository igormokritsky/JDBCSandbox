package org.example;


import java.util.*;

public interface CoachesDao {
    Coach getCoach(int id);
    boolean insertCoach(Coach coach);
    boolean updateCoach(Coach coach);
    boolean deleteCoach(int id);
}
