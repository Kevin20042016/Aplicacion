package com.fitnessapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistroDiario {
    private Usuario usuario;
    private LocalDate fecha;
    private Map<Alimento, Double> alimentosConsumidos;

    public RegistroDiario(Usuario usuario, LocalDate fecha) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.alimentosConsumidos = new HashMap<>();
    }

    public void agregarAlimento(Alimento alimento, double cantidad){
        this.alimentosConsumidos.put(alimento, cantidad);
    }

    public double calcularCaloriasConsumidas(){
        return this.alimentosConsumidos.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().calcularCaloriasTotales(entry.getValue()))
                .sum();
    }

    public boolean cumpleObjetivo(){
        double caloriasObjetivo = this.usuario.calcularCaloriasObjetivo();
        double caloriasConsumidas = this.calcularCaloriasConsumidas();

        return Math.abs(caloriasObjetivo - caloriasConsumidas) <= 50;
    }
}
