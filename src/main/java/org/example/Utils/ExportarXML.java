package org.example.Utils;
import com.thoughtworks.xstream.XStream;
import org.example.Model.juego;
import org.example.Model.socio;
import org.example.Model.partida;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class ExportarXML {

    public static void exportarJuegos(List<juego> juegos) {

        try {
            XStream xstream = new XStream();

            xstream.alias("juego", juego.class);

            xstream.toXML(juegos, new FileOutputStream("juegos.xml"));

            System.out.println("Creado el archivo juegos.xml");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void exportarSocios(List<socio> socios) {

        try {
            XStream xstream = new XStream();

            xstream.alias("socio", socio.class);

            xstream.toXML(
                    socios,
                    new FileOutputStream("socios.xml")
            );

            System.out.println("Creado el archivo socios.xml");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void exportarPartidas(List<partida> partidas) {

        try {
            XStream xstream = new XStream();

            xstream.alias("partida", partida.class);

            xstream.toXML(
                    partidas,
                    new FileOutputStream("partidas.xml")
            );

            System.out.println("Creado el archivo partidas.xml");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}