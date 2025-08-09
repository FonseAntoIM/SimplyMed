# Documentación del proyecto SimplyMed CLI

Este documento describe la funcionalidad y propósito de cada archivo de código clave dentro de la aplicación de consola.

------
## pom.xml
Archivo de configuración de Maven. Se utiliza para gestionar las dependencias del proyecto, como la librería Gson para el manejo de JSON. También define los detalles del proyecto y la forma en que se construye.

## App.java
Esta es la clase principal que contiene el método main(), el punto de entrada de la aplicación. Es responsable de:

- Mostrar el menú de opciones en la consola.

- Recibir la entrada del usuario a través de un Scanner.

- Llamar a los métodos apropiados en RecipeService para ejecutar la lógica de negocio.

## RecipeService.java
La clase central de la aplicación que gestiona todas las operaciones con las recetas. Sus funciones principales son:

- mergeById(): Fusiona una nueva lista de recetas con las existentes, actualizando las que tienen el mismo ID y añadiendo las nuevas.

- list(): Devuelve una copia inmodificable de la lista de recetas.

- importFromJson(): Lee un archivo JSON y crea objetos Recipe y Medication.

- exportJson(): Exporta la lista actual de recetas a un archivo JSON con un timeout para evitar bloqueos.

- exportCsv(): Exporta la lista a un archivo en formato CSV.

- saveBinary(): Guarda el estado actual de la lista de recetas en un archivo binario.

- loadBinaryIfExists(): Carga el estado de la aplicación desde un archivo binario.

## Recipe.java
Clase de modelo que representa una receta médica. Sus funciones incluyen:

- Almacenar la información de una receta: id, paciente, fecha, diagnostico y una lista de medicamentos.

- Implementa Serializable, lo que permite que una receta se guarde en un archivo binario.

- Implementa Comparable, lo que permite que las recetas se ordenen por fecha.

## Medication.java
Clase de modelo que representa un medicamento. Contiene información como nombre, dosis, unidad y frecuencia.

## JsonUtil.java
Clase de utilidad para el manejo de JSON. Se encarga de:

- Proporcionar una instancia de Gson configurada para manejar tipos como LocalDate.

- Contener el método writeRecipesJson() para escribir la lista de recetas en un archivo JSON.

## CsvUtil.java
Clase de utilidad para escribir en archivos CSV. Su método principal se encarga de formatear los datos de las recetas y escribirlos en un archivo con el formato de valores separados por comas.

## IOUtil.java
Clase de utilidad para la entrada/salida de archivos binarios. Proporciona métodos estáticos para:

- saveBinary(): Serializa y guarda una lista de objetos en un archivo.

- loadBinary(): Carga y deserializa una lista de objetos desde un archivo.