/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

/**
 *
 * @author Blueweb
 */
public class Parametro {
        private int id;
        private String nombre;
        private String valor;
        private String descripcion;
        private int id_usuario;
    
    
    public Parametro(int id, String nombre, String valor, String descripcion, int id_usuario) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
        this.descripcion = descripcion;
        this.id_usuario = id_usuario;
    }
    
    public Parametro(){
    }
    
    public int getId(){
    return id;
    }
    
    public void setId(int id){
    this.id = id;
    }
    
    public String getNombre(){
    return nombre;
    }
    
    public void setNombre(String nombre){
    this.nombre = nombre;
    }
    
    public String getValor(){
    return valor;
    }
    
    public void setValor(String valor){
    this.valor = valor;
    }

    public String getDescripcion(){
    return descripcion;
    }
    
    public void setDescripcion(String descripcion){
    this.descripcion = descripcion;
    }

    /**
     * @return the id_usuario
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    

}