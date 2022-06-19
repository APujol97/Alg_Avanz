/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Colores.Paleta;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author pujol
 */
public class Bandera {
    
    private String nombrePais;
    private HashMap<String, Double> colores;
    private int points;
    
    public Bandera(String pais){
        this.nombrePais = pais;
        this.colores = new HashMap <> ();
        colores.put("Blanco",0.0);
        colores.put("Negro",0.0);
        colores.put("Rojo",0.0);
        colores.put("Verde",0.0);
        colores.put("Azul",0.0);
        colores.put("Amarillo",0.0);
        colores.put("Naranja",0.0);
        this.points = 0;
    }
    
    public void addPoint(){
        this.points++;
    }
    
    public void putColorValue(String color, Double value){
        this.colores.put(color, value);
    }
    
    public Double getColorValue(String color){
        return this.colores.get(color);
    }
    
    //método ToString()???
    
}
