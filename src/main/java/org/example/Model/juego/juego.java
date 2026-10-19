package org.example.Model.juego;

import org.example.Model.estado;
import org.example.Model.tipo;

import java.util.Date;

public class juego {

    private int idJuego;
    private String nombre;
    private String autores;
    private String descripcion;
    private int MinimoJugadores;
    private int MaximoJuegadores;
    private int duracion;
    private String tematica;
    private tipo tipo;
    private String ubicacion;
    private estado estado;
    private Date fechaAlta;
    private int id_asociacion;
    private String DocumentoPropietaria;

    public juego(String nombre, String autores, String descripcion, int minimoJugadores, int maximoJuegadores, int duracion, String tematica, tipo tipo, String ubicacion, estado estado, Date fechaAlta, int id_asociacion, String documentoPropietaria) {
        this.nombre = nombre;
        this.autores = autores;
        this.descripcion = descripcion;
        MinimoJugadores = minimoJugadores;
        MaximoJuegadores = maximoJuegadores;
        this.duracion = duracion;
        this.tematica = tematica;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.fechaAlta = fechaAlta;
        this.id_asociacion = id_asociacion;
        DocumentoPropietaria = documentoPropietaria;
    }

    public juego() {

    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMinimoJugadores() {
        return MinimoJugadores;
    }

    public void setMinimoJugadores(int minimoJugadores) {
        MinimoJugadores = minimoJugadores;
    }

    public int getMaximoJuegadores() {
        return MaximoJuegadores;
    }

    public void setMaximoJuegadores(int maximoJuegadores) {
        MaximoJuegadores = maximoJuegadores;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public tipo getTipo() {
        return tipo;
    }

    public void setTipo(tipo tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public estado getEstado() {
        return estado;
    }

    public void setEstado(estado estado) {
        this.estado = estado;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getId_asociacion() {
        return id_asociacion;
    }

    public void setId_asociacion(int id_asociacion) {
        this.id_asociacion = id_asociacion;
    }

    public String getDocumentoPropietaria() {
        return DocumentoPropietaria;
    }

    public void setDocumentoPropietaria(String documentoPropietaria) {
        DocumentoPropietaria = documentoPropietaria;
    }

    @Override
    public String toString() {
        return "juego{" +
                "nombre='" + nombre + '\'' +
                ", autores='" + autores + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", MinimoJugadores=" + MinimoJugadores +
                ", MaximoJuegadores=" + MaximoJuegadores +
                ", duracion=" + duracion +
                ", tematica='" + tematica + '\'' +
                ", tipo=" + tipo +
                ", ubicacion='" + ubicacion + '\'' +
                ", estado=" + estado +
                ", fechaAlta=" + fechaAlta +
                ", id_asociacion=" + id_asociacion +
                ", DocumentoPropietaria='" + DocumentoPropietaria + '\'' +
                '}';
    }
}
