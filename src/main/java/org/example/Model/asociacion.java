package org.example.Model;

import java.sql.Date;

public class asociacion {
    private int id_asociacion;
    private String nombre;
    private String cif;
    private String direccion;
    private String telefono;
    private String email;
    private Date fecha_apertura;

    public asociacion(String nombre, String cif, String direccion,
                      String telefono, String email, Date fecha_apertura) {
        this.nombre = nombre;
        this.cif = cif;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fecha_apertura = fecha_apertura;
    }

    public int getId_asociacion() {
        return id_asociacion;
    }

    public void setId_asociacion(int id_asociacion) {
        this.id_asociacion = id_asociacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCif() {
        return cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public Date getFecha_apertura() {
        return fecha_apertura;
    }

}
