package org.example.countrydao;

public interface CountriesDao {

    Country getCountry(int id);
    boolean insertCountry(Country country);
    boolean updateCountry(Country country);
    boolean deleteCountry(int id);
}
