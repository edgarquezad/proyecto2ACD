package org.example.Dao;

import org.example.Model.socio;

import java.util.List;

public interface sociosDao {

    int add(socio socia);

    boolean delete(String documento);

    boolean update(socio socia);

    socio getSocia(String documento);

    List<socio> getSocias();
}
