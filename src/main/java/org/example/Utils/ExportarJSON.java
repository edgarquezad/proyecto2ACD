package org.example.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.Model.juego;
import org.example.Model.partida;
import org.example.Model.socio;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportarJSON {

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();


    public static void exportarJuegos(List<juego> juegos) {

        try (FileWriter writer = new FileWriter("juegos.json")) {

            gson.toJson(juegos, writer);

            System.out.println("Juegos exportados correctamente a juegos.json");

        } catch (IOException e) {

            System.out.println("Error al exportar los juegos a JSON: " + e.getMessage());
        }
    }


    public static void exportarSocios(List<socio> socios) {

        try (FileWriter writer = new FileWriter("socios.json")) {

            gson.toJson(socios, writer);

            System.out.println("Socios exportados correctamente a socios.json");

        } catch (IOException e) {

            System.out.println("Error al exportar los socios a JSON: " + e.getMessage());
        }
    }


    public static void exportarPartidas(List<partida> partidas) {

        try (FileWriter writer = new FileWriter("partidas.json")) {

            gson.toJson(partidas, writer);

            System.out.println("Partidas exportadas correctamente a partidas.json");

        } catch (IOException e) {

            System.out.println("Error al exportar las partidas a JSON: " + e.getMessage());
        }
    }
}