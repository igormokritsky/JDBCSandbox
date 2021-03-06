package org.example.swimmers_sponsorsdao;

public interface SwimmerSponsorsDao {

    SwimmersSponsor getSwimmerSponsor(int id);
    boolean insertSwimmerSponsor(SwimmersSponsor swimmersSponsor);
    boolean updateSwimmerSponsor(SwimmersSponsor swimmersSponsor);
    boolean deleteSwimmerSponsor(int id);

}
