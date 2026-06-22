package com.fitnessapp;

import com.fitnessapp.model.Genero;
import com.fitnessapp.model.NivelActividad;
import com.fitnessapp.model.Objetivo;
import com.fitnessapp.model.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        System.out.println("Arrancando el motor de Hibernate...");

        //Instanciamos la Fábrica leyendo el archivo de configuración
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuario.class)
                .buildSessionFactory();


        //Abrimos la sesión con try-with-resources
        try (Session session = factory.openSession();){
            System.out.println("¡Conexión establecida con éxito!");

            //Iniciamos la transacción
            Transaction transaction = session.beginTransaction();

            System.out.println("Instanciando un usuario con las validaciones");
            //Crear el objeto usando el constructor original realizado en la Fase 1
            Usuario kevin = new Usuario(
                    "Kevin",
                    "Pizarro",
                    22,
                    Genero.MASCULINO,
                    175.0,
                    81.2,
                    Objetivo.AUMENTAR_PESO,
                    NivelActividad.MODERADO,
                    "Ninguno"
            );

            System.out.println("Enviando el usuario a Hibernate...");
            //Ahora le decinmos a Hibernate que prepare este objeto para la base de datos
            session.persist(kevin);

            //Confirmar el guardado definitivo en PostgreSQL
            transaction.commit();

            System.out.println("¡Éxito rotundo! Usuario guardado con el ID: " + kevin.getId());

        } catch (Exception e) {
            System.err.println("Ocurrió un error al conectar: " + e.getMessage());
        } finally {
            factory.close();
            System.out.println("Motor apagado.");
        }
    }
}