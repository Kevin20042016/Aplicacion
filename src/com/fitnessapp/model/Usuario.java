package com.fitnessapp.model;

public class Usuario {
    private String nombre;
    private String apellidos;
    private int edad;
    private Genero genero;
    private double alturaCm;
    private double pesoKg;
    private Objetivo objetivo;
    private NivelActividad nivelActividad;
    private String problemasSalud;

    public Usuario(String nombre, String apellidos, int edad, Genero genero, double alturaCm, double pesoKg, Objetivo objetivo, NivelActividad nivelActividad, String problemasSalud) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.genero = genero;
        this.alturaCm = alturaCm;
        this.pesoKg = pesoKg;
        this.objetivo = objetivo;
        this.nivelActividad = nivelActividad;
        this.problemasSalud = problemasSalud;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public Genero getGenero() {
        return genero;
    }

    public double getAlturaCm() {
        return alturaCm;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public NivelActividad getNivelActividad() {
        return nivelActividad;
    }

    public String getProblemasSalud() {
        return problemasSalud;
    }

    //Método para calcular las calorías de mantenimiento
    public double calcularCaloriasMantenimiento(){
        double metabolismoBasal = 0;

        //Paso 1: Calcular el Metabolismo Basal seǵun el género
        //Fórmula de Miffilin-St Jeor
        if (this.genero == Genero.MASCULINO){
            metabolismoBasal = (10 * this.pesoKg) + (6.25 * this.alturaCm) - (5 * this.edad) + 5;
        }
        else if (this.genero == Genero.FEMENINO) {
            metabolismoBasal = (10 * this.pesoKg) + (6.25 * this.alturaCm) - (5 * this.edad) - 161;
        }
        else {
            throw new IllegalArgumentException("Error de cálculo: El género debe ser 'M' (Masculino) o 'F' (Femenino). Valor recibido: " + this.genero);
        }

        //Paso 2: Aplicar el multiplicador
        double caloriasMantenimiento = 0;

        switch (this.nivelActividad) {
            case SEDENTARIO:
                caloriasMantenimiento = metabolismoBasal * 1.2;
                break;
            case LIGERO:
                caloriasMantenimiento = metabolismoBasal * 1.375;
                break;
            case MODERADO:
                caloriasMantenimiento = metabolismoBasal * 1.55;
                break;
            case INTENSO:
                caloriasMantenimiento = metabolismoBasal * 1.725;
                break;
            case ATLETA:
                caloriasMantenimiento = metabolismoBasal * 1.9;
                break;
        }
        return caloriasMantenimiento;
    }
}
