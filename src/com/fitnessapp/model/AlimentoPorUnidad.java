package com.fitnessapp.model;

public class AlimentoPorUnidad extends Alimento{
    public AlimentoPorUnidad(String nombre, double proteinasGramos, double carbohidratosGramos, double grasasGramos) {
        super(nombre, proteinasGramos, carbohidratosGramos, grasasGramos);
    }

    @Override
    public double calcularCaloriasTotales(double cantidad){
        return this.calcularCaloriasBase() * cantidad;
    }
}
