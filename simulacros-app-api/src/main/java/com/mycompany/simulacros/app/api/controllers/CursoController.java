/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simulacros.app.api.controllers;

import com.mycompany.simulacros.app.api.models.Curso;
import java.io.InputStream;
import com.mycompany.simulacros.app.api.services.CursoDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;

/**
 *
 * @author Cristian, Eduardo
 */
public class CursoController {
    
    public Curso[] obtenerCursos() {
        try {
            CursoDB cursoDB = new CursoDB();
            ArrayList<Curso> listaCursos = cursoDB.obtenerCursos();
            Curso[] cursos = listaCursos.toArray(Curso[]::new);
            return cursos;
        } catch (SQLException e) {
            return null;
        }
    }
    
    public Curso obtenerCurso(String carrera, String nombre) {
        try {
            CursoDB cursoDB = new CursoDB();
            Curso curso = cursoDB.obtenerCurso(carrera, nombre);
            return curso;
        } catch (SQLException e) {
            return null;
        }
    }

    public Curso[] obtenerCursos(String carrera){
        Curso[] cursos;
        try {
            CursoDB cursoDB = new CursoDB();
            cursos = cursoDB.obtenerCursos(carrera);
            return cursos;
        } catch (SQLException e) {
            return null;
        }
    }
    
    public boolean crearCurso(Curso curso) {
        if (curso == null
                || curso.getNombre() == null
                || curso.getCarrera() == null
                || curso.getDivision() == null
                || curso.getDescripcion() == null) {
            return false;
        }

        if (curso.getNombre().isEmpty()
                || curso.getCarrera().isEmpty()
                || curso.getDivision().isEmpty()
                || curso.getDescripcion().isEmpty()) {
            return false;
        }

        if (curso.getNombre().length() > 99
                || curso.getCarrera().length() > 99
                || curso.getDivision().length() > 99) {
            return false;
        }

        
        try {
              CursoDB cursoDB = new CursoDB();
              cursoDB.crearCurso(curso);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean actualizarImagen(String nombre, String carrera, FormDataBodyPart part) {
        
        if (nombre.length() > 99 || carrera.length() > 99) {
            return false;
        }

        if (part != null) {
            String formato = part.getMediaType().toString();
            if (!formatoCorrecto(formato)) {
                return false;
            }
        }else{
            return false;
        }
        
        long tamañoMaximo = 100*1024*1024;
        long tamañoArchivo = part.getContentDisposition().getSize();
        if(tamañoArchivo >= tamañoMaximo){
            return false;
        }
        
        
        try (InputStream inputStream = part.getValueAs(InputStream.class)){
            CursoDB cursoDB = new CursoDB();
            cursoDB.actualizarImagen(nombre, carrera, inputStream);
            
        } catch (SQLException e) {
            return false;
        } catch (IOException ex) {
            Logger.getLogger(CursoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }

    public boolean formatoCorrecto(String formato) {
        String[] formatos = new String[3];
        formatos[0] = "image/png";
        formatos[1] = "image/jpeg";
        formatos[2] = "image/jpg";
        for (int i = 0; i < formatos.length; i++) {
            if (formatos[i].equals(formato)) {
                return true;
            }
        }
        return false;
    }

}
