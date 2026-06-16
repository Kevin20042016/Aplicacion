package com.fitnessapp.model;

public class Main {
    public static void main(String[] args) {

        // Creamos un usuario con datos correctos
        Usuario kevin = new Usuario("Kevin", "Perez", 22, 'M', 175.0, 70.0, Objetivo.AUMENTAR_MASA, NivelActividad.MODERADO);

        // Creamos un usuario con un género inválido ('X') para forzar el error
        Usuario usuarioError = new Usuario("Error", "Test", 25, 'X', 160.0, 60.0, Objetivo.MANTENER, NivelActividad.SEDENTARIO);

        // Intentamos calcular las calorías de Kevin
        try {
            double caloriasKevin = kevin.calcularCaloriasMantenimiento();
            System.out.println("Calorías de mantenimiento para Kevin: " + caloriasKevin);

            // Intentamos calcular las calorías del usuario con error
            System.out.println("Intentando calcular usuario con error...");
            double caloriasError = usuarioError.calcularCaloriasMantenimiento();
            System.out.println("Calorías: " + caloriasError); // Esta línea nunca se ejecutará

        } catch (IllegalArgumentException e) {
            // AQUÍ CAPTURAMOS Y GESTIONAMOS EL ERROR
            System.err.println("❌ Se produjo un error en la lógica de negocio:");
            System.err.println(e.getMessage()); // Imprime el mensaje exacto que definimos en el throw
        }
    }
}
