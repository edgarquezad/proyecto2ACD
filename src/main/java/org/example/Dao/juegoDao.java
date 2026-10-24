package org.example.Dao;

import org.example.Model.juego;

import java.util.List;

public interface juegoDao {
    int add(juego juego) ;
    boolean delete(int id) ;
    juego getjuego(int id);
    List<juego> getjuegos();
    boolean update(juego juego) ;

}
