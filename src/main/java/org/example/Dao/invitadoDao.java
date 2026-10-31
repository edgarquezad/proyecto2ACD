package org.example.Dao;

import org.example.Model.invitado;

import java.util.List;

public interface invitadoDao {
    int add(invitado invitado);

    boolean delete(String documento);

    boolean update(invitado invitada);

    invitado getInvitada(String documento);

    List<invitado> getInvitadas();
}
