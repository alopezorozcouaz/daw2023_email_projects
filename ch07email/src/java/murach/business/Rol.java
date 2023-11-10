/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.business;

import java.io.Serializable;

/**
 * Representa el mapeo de la entidad rol de la base de datos
 * murach
 * @author alopezorozco
 */
public class Rol implements Serializable{
    private int id;
    private String nombre;
    private String descripcion;

    //constructor vacío
    public Rol() {
    }

    // constructor con 3 parámetros
    public Rol(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // descriptores de acceso
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * Devuelve una representación de cadena (nombre)
     * de este objeto
     * @return el nombre que representa el objeto.
     */
    @Override
    public String toString(){
        return this.nombre;
    }
}
