package com.fitnessapp.model;

public abstract class Alimento {
    protected String nombre;
    protected double proteinasGramos;
    protected double carbohidratosGramos;
    protected double grasasGramos;

    public Alimento(String nombre, double proteinasGramos, double carbohidratosGramos, double grasasGramos) {
        this.nombre = nombre;
        this.proteinasGramos = proteinasGramos;
        this.carbohidratosGramos = carbohidratosGramos;
        this.grasasGramos = grasasGramos;
    }


    //Método concreto: Todas las hijas heredan esta fórmula científica
    public double calcularCaloriasBase(){
        return (this.proteinasGramos * 4) + (this.carbohidratosGramos * 4) + (this.grasasGramos * 9);
    }

    //Método abstracto: Obligamos a las hijas a inventar cómo se sirven (por unidad o por peso)
    public abstract double calcularCaloriasTotales(double cantidad);
}
