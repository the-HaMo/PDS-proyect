package Utilidades;

import com.fasterxml.jackson.databind.ObjectMapper;

import Modelo.Curso;

import java.io.File;

public class LectorCurso {

    public static Curso leerCursoDesdeJSON(File archivo) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(archivo, Curso.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}