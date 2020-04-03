package org.example.sytlesdao;

public interface StylesDao {

    Style getStyle(int id);
    boolean insertStyle(Style style);
    boolean updateStyle(Style style);
    boolean deleteStyle(int id);

}
