package com.fitnessapp.model;

public class AlimentoPorPeso extends Alimento{
    public AlimentoPorPeso(String nombre, double proteinasGramos, double carbohidratosGramos, double grasasGramos) {
        super(nombre, proteinasGramos, carbohidratosGramos, grasasGramos);
    }

    @Override
    public double calcularCaloriasTotales(double cantidad){
        //Primero sacamos las calorías de 1 gramo, y luego multiplicamos por los gramos totales
        return (this.calcularCaloriasBase() / 100.0) * cantidad;
    }
}
