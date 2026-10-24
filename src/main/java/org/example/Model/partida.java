
package org.example.Model;

import java.sql.Date;
import java.sql.Time;

public class partida {

    private int id_partida;
    private int id_juego;
    private Date fecha;
    private Time hora;
    private int plazas_libres;
    private int jugadores_deseados;

    public partida(int id_partida, int id_juego, Date fecha, Time hora,
                   int plazas_libres, int jugadores_deseados) {
        this.id_partida = id_partida;
        this.id_juego = id_juego;
        this.fecha = fecha;
        this.hora = hora;
        this.plazas_libres = plazas_libres;
        this.jugadores_deseados = jugadores_deseados;
    }

    public partida(int id_juego, Date fecha, Time hora,
                   int plazas_libres, int jugadores_deseados) {
        this.id_juego = id_juego;
        this.fecha = fecha;
        this.hora = hora;
        this.plazas_libres = plazas_libres;
        this.jugadores_deseados = jugadores_deseados;
    }

    public int getId_partida() {
        return id_partida;
    }

    public void setId_partida(int id_partida) {
        this.id_partida = id_partida;
    }

    public int getId_juego() {
        return id_juego;
    }

    public void setId_juego(int id_juego) {
        this.id_juego = id_juego;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public int getPlazas_libres() {
        return plazas_libres;
    }

    public void setPlazas_libres(int plazas_libres) {
        this.plazas_libres = plazas_libres;
    }

    public int getJugadores_deseados() {
        return jugadores_deseados;
    }

    public void setJugadores_deseados(int jugadores_deseados) {
        this.jugadores_deseados = jugadores_deseados;
    }

    @Override
    public String toString() {
        return "Partida{" +
                "id_partida=" + id_partida +
                ", id_juego=" + id_juego +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", plazas_libres=" + plazas_libres +
                ", jugadores_deseados=" + jugadores_deseados +
                '}';
    }
}
