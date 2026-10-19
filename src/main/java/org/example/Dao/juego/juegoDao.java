package org.example.Dao.juego;

import org.example.Model.juego.juego;

import java.util.List;

public interface juegoDao {
    int add(juego juego) ;
    void delete(int id) ;
    juego getjuego(int id);
    List<juego> getjuegos();
    boolean update(juego juego) ;

}
