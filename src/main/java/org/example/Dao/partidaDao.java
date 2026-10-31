package org.example.Dao;

import org.example.Model.partida;

import java.util.List;

public interface partidaDao {

    int add(partida partida);

    boolean delete(int id);
    boolean eliminarParticipante(int idPartida, String documento);

    partida getPartida(int id);

    List<partida> getPartidas();

    boolean update(partida partida);
    List<String> getParticipantes(int id_partida);

    boolean addParticipante(int id_partida, String Documento, String tipoParticipante);

    boolean addGanador(int idPartida, String documento, String tipoParticipante);

    List<String> getGanadores(int idPartida);

}
