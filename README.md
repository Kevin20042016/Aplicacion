# Fitness Tracker App
Desarrollo de Aplicación Fitness
Aplicación personal para la gestión de calorías, macronutrientes y monitorización de objetivos físicos.

* [x]**Fase 1 (Completada): ** Java Core (Lógica de negocio, POO avanzado, Poliformismo).
* [ ]**Fase 2 (Actual):** Persistencia de datos (PostgresSQL, Hibernate/JPA).
* [ ]**Fase 3:** Backend API REST (Spring Boot, Spring Security).

## Producto Mínimo Viable (MVP) - Fase 1 (Java Puro)
* [x] **Historia 1:** Como usuario, quiero registrar mis datos físicos (peso, altura, edad, género, actividad) para que el sistema calcule mis calorías de mantenimiento.
* [x] **Historia 2:** Como usuario, quiero definir un objetivo físico (ej. Bajar de peso) para que el sistema ajuste mis necesidades calóricas diarias.
* [x] **Historia 3:** Como sistema, necesito un catálogo de alimentos abstractos que calculen sus macronutrientes exactos ya sea por peso (gramos) o por unidad.
* [x] **Historia 4:** Como usuario, quiero registrar los alimentos que consumo en el día para saber si he cumplido mis objetivos nutricionales.


## Reglas de Negocio (Cálculo de objetivos)
Para ajustar las calorías del usuario según sus metas, la aplicación aplica las siguientes reglas basadas en la nutrición deportiva:
* **Déficit Calórcio ('BAJAR_PESO'):** Se restan 500 Kcal al metabolismo basal para una pérdida de peso segura y progresiva.
* **Superávit Calórico ('AUMENTAR_MASA'):** Se suman 500 Kcal para promover la ganancia de masa muscular.
* **Mantenimiento ('MANTENER'):** Las calorías se mantienen igual a las de mantenimiento.
* **Optimización ('MEJORAR_RENDIMIENTO'):** Se añaden un ligero superávit de 250 Kcal para aportar energía extra sin comprometer la composición corporal.


## Arquitectura del Dominio (Poliformismo)
El sistema de registro de alimentos está diseñado utilizando principio avanzados de Programación Orientado a Objetos (POO):
* **Clase Abstracta 'Alimento':** Define el contrato base y la fórmula universal de Atwater (Proteínas x4,  Carbohidratos x4, Grasas x9) para calcular las calorías base.
* **'AlimentoPorPeso':** Hereda de 'Alimento'. Calcula los macronutrientes basándose en porciones de 100 gramos mediante regla de tres.
* **'AlimentosPorUnidad':** Hereda de 'Alimento'. Calcula los macronutrientes multiplicando directamente por la cantidad de piezas consumidas.

## Estado Actual del Proyecto (Fin de Fase 1)
El núcleo de la aplicación (Domain model) está completamente funcional en Java puro.
* **Usuarios:** Implementada la validación estricta de datos (Regex) y el cálculo del Metabolismo Basal mediante la fórmula de Mifflin-St Jeor. Ajuste calórico dinámico usando *Switch Expressions* de Java 14+.
* **Alimentos (Polimorfismo):** Catálogo de alimentos escalable usando herencia. Los macronutrientes se calculan dinámicamente ya sea por porciones (peso en gramos) o por unidades exactas.
* **Registro Diario:** Motor de cálculo que utiliza Colecciones (`Map`) y la API de *Java Streams* para sumar y validar el cumplimiento de los objetivos nutricionales diarios con un margen de error realista.
