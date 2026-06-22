import com.fitnessapp.model.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO SIMULACIÓN DE FITNESS APP ===\n");

        // Envolvemos toda la lógica de negocio en el try para capturar cualquier validación fallida
        try {
            // 1. Creamos al usuario (Si el nombre falla la Regex, saltará directamente al catch)
            Usuario kevin = new Usuario("Kevin", "Pizarro", 22, Genero.MASCULINO, 175.0, 70.0, Objetivo.AUMENTAR_PESO, NivelActividad.MODERADO, "Ninguno");

            System.out.println("👤 Usuario: " + kevin.getNombre());
            // Si el objetivo fuera inválido, esta línea lanzaría la excepción del switch
            System.out.println("🔥 Calorías Objetivo (Aumentar Masa): " + kevin.calcularCaloriasObjetivo() + " kcal\n");

            // 2. Creamos nuestro catálogo de alimentos
            Alimento pechugaPollo = new AlimentoPorPeso("Pechuga de Pollo", 23.0, 0.0, 1.2);
            Alimento arrozBlanco = new AlimentoPorPeso("Arroz Blanco", 2.7, 28.0, 0.3);
            Alimento huevo = new AlimentoPorUnidad("Huevo Entero", 6.0, 0.6, 5.0);
            Alimento manzana = new AlimentoPorUnidad("Manzana", 0.3, 14.0, 0.2);

            // 3. Creamos el registro del día de hoy
            RegistroDiario registroHoy = new RegistroDiario(kevin, LocalDate.now());

            // 4. El usuario registra sus comidas del día
            System.out.println("📝 Registrando comidas...");
            registroHoy.agregarAlimento(pechugaPollo, 250.0);
            registroHoy.agregarAlimento(arrozBlanco, 300.0);
            registroHoy.agregarAlimento(huevo, 3.0);
            registroHoy.agregarAlimento(manzana, 2.0);

            // 5. Calculamos resultados
            double caloriasConsumidas = registroHoy.calcularCaloriasConsumidas();
            System.out.println("🍽️ Calorías consumidas hoy: " + String.format("%.2f", caloriasConsumidas) + " kcal");

            // 6. Verificamos si cumplió el objetivo
            if (registroHoy.cumpleObjetivo()) {
                System.out.println("✅ ¡Excelente trabajo! Has clavado tus calorías diarias.");
            } else {
                System.out.println("❌ No has cumplido el objetivo calórico hoy. ¡Mañana más y mejor!");
            }

            // PRUEBA DE ERRORES
             System.out.println("\n--- Probando manejo de errores ---");
            Usuario usuarioInvalido = new Usuario("Kevin123", "Perez", 25, Genero.MASCULINO, 170, 70, Objetivo.MANTENER, NivelActividad.SEDENTARIO, "Ninguno");


        } catch (IllegalArgumentException e) {
            // Aquí aterrizan todas las excepciones que lanzamos con "throw new IllegalArgumentException"
            System.err.println("\n❌ ERROR DE VALIDACIÓN DETECTADO:");
            System.err.println(e.getMessage());
        } catch (Exception e) {
            // Un catch genérico por si ocurre algún error inesperado (ej. NullPointerException)
            System.err.println("\n❌ ERROR INESPERADO DEL SISTEMA:");
            System.err.println(e.getMessage());
        }
    }
}

