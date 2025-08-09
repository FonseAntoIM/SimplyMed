Descripción del Proyecto
Este proyecto es una aplicación de consola en Java para la gestión de recetas médicas. Permite a los usuarios importar datos desde un archivo JSON, listar, exportar a formatos CSV y JSON, y guardar/cargar el estado de la aplicación en un archivo binario para persistencia.

El proyecto está construido con Maven, lo que facilita la gestión de dependencias, como la librería Gson para el manejo de archivos JSON.


--------------------------------------------
Arquitectura del Proyecto
El proyecto sigue un patrón de diseño con separación de responsabilidades, dividido en los siguientes paquetes:

* com.simplymed.client.model: Contiene las clases de modelo de datos (POJO).

  - Recipe.java: La clase principal que representa una receta médica. Implementa Serializable para persistencia y Comparable para ordenar por fecha.

  - Medication.java: Una clase de modelo simple que representa un medicamento recetado.

* com.simplymed.client.service: Contiene la lógica de negocio.

  - RecipeService.java: Gestiona la lista de recetas en memoria y proporciona métodos para manipular, importar y exportar datos.

* com.simplymed.client.util: Contiene clases de ayuda para tareas específicas.

  - JsonUtil.java: Clases para serializar y deserializar objetos a JSON.

  - CsvUtil.java: Clases para escribir archivos en formato CSV.

  - IOUtil.java: Clases para guardar y cargar objetos en archivos binarios (serialización).

* com.simplymed.client: Contiene la clase principal de la aplicación.

  - App.java: El punto de entrada de la aplicación. Proporciona la interfaz de usuario en la consola y coordina las operaciones.

--------------------------------------------
Rúbrica de Cumplimiento
A continuación se presenta cómo el proyecto cumple con los puntos clave del "Checkpoint para el Alumno" de Java Standard Edition:

* Sesión 2: Tipos de datos y sentencias de control: La aplicación hace uso de tipos de datos primitivos y de la clase String. También implementa sentencias de control como switch para manejar las opciones del menú y ciclos while para el bucle principal de la aplicación.

* Sesión 3: Clases y objetos: Se utilizan objetos de las clases Recipe, Medication y RecipeService. La interacción con el usuario se realiza a través de la clase Scanner.

* Sesión 4: Elementos de una clase: Las clases del modelo tienen atributos privados, lo que cumple con el concepto de encapsulación. Los métodos get y set permiten el acceso controlado a estos atributos.

* Sesión 5 y 6: Herencia y Polimorfismo: Aunque no se usa de forma extensa, se aplica el polimorfismo a través de las interfaces. Por ejemplo, al ordenar la lista de recetas, se utiliza el método compareTo de la interfaz Comparable implementada en la clase Recipe.

* Sesión 7: Colecciones: Se hace uso de colecciones como List (con implementación ArrayList) y Map (con HashMap) en RecipeService para gestionar los datos de manera eficiente. La interfaz Comparable se utiliza para ordenar las recetas.

* Sesión 8: Manejo de archivos: La aplicación es capaz de leer y escribir archivos de texto plano (CSV y JSON) y archivos binarios (.dat), cumpliendo así con los objetivos de serialización y manejo de archivos.