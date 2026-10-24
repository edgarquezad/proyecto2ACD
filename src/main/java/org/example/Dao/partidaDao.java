package org.example.Dao;
import org.example.Model.partida;
import java.util.List;

public interface partidaDao {

    int add(partida partida);

    boolean delete(int id);

    partida getPartida(int id);

    List<partida> getPartidas();

    boolean update(partida partida);
}
