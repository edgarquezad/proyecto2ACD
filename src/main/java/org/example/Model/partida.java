package org.example.Model;

import java.sql.Date;
import java.sql.Time;

public class partida {

    private int idJuego;
    private Date fecha;
    private Time hora;
    private int plazasLibres ;
    private int jugadoresDeseados ;

    public partida(int idJuego,Date fecha,  Time hora, int plazasLibres,int jugadoresDeseados) {
        this.idJuego = idJuego;
        this.jugadoresDeseados = jugadoresDeseados;
        this.plazasLibres = plazasLibres;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public int getJugadoresDeseados() {
        return jugadoresDeseados;
    }

    public void setJugadoresDeseados(int jugadoresDeseados) {
        this.jugadoresDeseados = jugadoresDeseados;
    }

    public int getPlazasLibres() {
        return plazasLibres;
    }

    public void setPlazasLibres(int plazasLibres) {
        this.plazasLibres = plazasLibres;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "partida{" +
                "idJuego=" + idJuego +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", plazasLibres=" + plazasLibres +
                ", jugadoresDeseados=" + jugadoresDeseados +
                '}';
    }


}


















