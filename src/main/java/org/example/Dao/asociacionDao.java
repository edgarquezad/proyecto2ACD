package org.example.Dao;

import org.example.Model.asociacion;

import java.util.List;

public interface asociacionDao {
    int add( asociacion asociacion );
    List<asociacion> getAsociaciones();
    asociacion getAsociacion(int id_asociacion);
}
