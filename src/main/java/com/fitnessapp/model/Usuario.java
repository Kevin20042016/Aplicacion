package com.fitnessapp.model;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    //Añadimos la clave primaria y la anotación @Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Atributos originales
    private String nombre;
    private String apellidos;
    private int edad;

    //Enumerados, guardamos a que guarde el texto del Enum, no su número de posición
    @Enumerated(EnumType.STRING)
    private Genero genero;

    private double alturaCm;
    private double pesoKg;

    @Enumerated(EnumType.STRING)
    private Objetivo objetivo;

    @Enumerated(EnumType.STRING)
    private NivelActividad nivelActividad;

    private String problemasSalud;

    private double caloriasRecomendadas;

    //Constructor vacío para Hibernate
    public Usuario(){

    }

    //Constructor original
    public Usuario(String nombre, String apellidos, int edad, Genero genero, double alturaCm, double pesoKg, Objetivo objetivo, NivelActividad nivelActividad, String problemasSalud) {
        if(!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")){
            throw new IllegalArgumentException("Error: Nombre en formato no válido");
        }
        this.nombre = nombre;

        if(!apellidos.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")){
            throw new IllegalArgumentException("Error: Apellidos en formato no válido");
        }
        this.apellidos = apellidos;
        this.edad = edad;
        this.genero = genero;
        this.alturaCm = alturaCm;
        this.pesoKg = pesoKg;
        this.objetivo = objetivo;
        this.nivelActividad = nivelActividad;
        this.problemasSalud = problemasSalud;
    }

    public Long getId(){
        return id;
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

    public double getCaloriasRecomendadas() {
        return caloriasRecomendadas;
    }

    public void setCaloriasRecomendadas(double caloriasRecomendadas) {
        this.caloriasRecomendadas = caloriasRecomendadas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setAlturaCm(double alturaCm) {
        this.alturaCm = alturaCm;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public void setNivelActividad(NivelActividad nivelActividad) {
        this.nivelActividad = nivelActividad;
    }

    public void setProblemasSalud(String problemasSalud) {
        this.problemasSalud = problemasSalud;
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
            throw new IllegalArgumentException("Error de cálculo: El género debe ser (Masculino) o (Femenino).");
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

    /**
     * Calcula las calorías finales del usuario aplicando déficit o superávit
     * según el objetivo físico configurado
     * @return double Calorías totales diarias recomendadas.
     */
    public double calcularCaloriasObjetivo(){
        //1. Obtenemos las calorías base llamando al método que hicimos en la Historia 1
        double caloriasBase = this.calcularCaloriasMantenimiento();

        //2. Retornamosm directamente las calorías objetivo
        return switch (this.objetivo) {
            case BAJAR_PESO -> caloriasBase - 500.0;
            case AUMENTAR_PESO -> caloriasBase + 500;
            case MANTENER -> caloriasBase;
            case MEJORAR_RENDIMIENTO -> caloriasBase + 250;
            default -> throw new IllegalArgumentException("Objetivo no válido");
        };
    }
}
