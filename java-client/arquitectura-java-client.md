# Arquitectura del proyecto SimplyMed CLI

Este documento describe la arquitectura de la aplicación de consola en Java para la gestión de recetas médicas. 
El proyecto está organizado en una estructura de paquetes lógica, basada en el principio de separación de responsabilidades.

El proyecto se estructura con Maven, lo que se refleja en la organización de los directorios:
`src/main/java/` y `src/main/resources/`.

## Estructura de directorios:

```
java-client/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── simplymed/
│   │   │           └── client/
│   │   │               ├── App.java
│   │   │               ├── model/
│   │   │               │   ├── Medication.java
│   │   │               │   └── Recipe.java
│   │   │               ├── service/
│   │   │               │   └── RecipeService.java
│   │   │               └── util/
│   │   │                   ├── CsvUtil.java
│   │   │                   ├── IOUtil.java
│   │   │                   └── JsonUtil.java
│   │   └── resources/
│   │       ├── sample.json
│   │       ├── sample_10.json
│   │       ├── sample_merge.json
│   │       └── sample_multi.json
└── ...
```

------
## Componentes y sus responsabilidades

La arquitectura de la aplicación se divide en los siguientes componentes principales:

### 1. Capa de Presentación (`com.simplymed.client`):
   * `App.java`: Es la clase principal de la aplicación. Su única responsabilidad es actuar como el punto de entrada, mostrar el menú en la consola, leer la entrada del usuario (`Scanner`) y delegar las acciones a la capa de servicio (`RecipeService`). Esta clase no contiene lógica de negocio.

### 2. Capa de Lógica de Negocio (`com.simplymed.client.service`):
   * `RecipeService.java`: Actúa como el "cerebro" de la aplicación. Gestiona el estado de la lista de recetas en memoria. Contiene toda la lógica de negocio, como importar, exportar, fusionar, listar y persistir los datos en diferentes formatos. Utiliza las clases de la capa de utilidad para estas tareas.

### 3. Capa de Modelo de Datos (`com.simplymed.client.model`):
   * `Recipe.java`: Es el objeto principal de la aplicación, representando una receta médica. Es una clase `POJO` (Plain Old Java Object) que contiene atributos, getters y setters. Implementa las interfaces `Serializable` para ser guardada en archivos binarios, y `Comparable` para permitir la ordenación de las recetas.
   * `Medication.java`: Una clase `POJO` simple que representa un medicamento asociado a una receta.

### 4. Capa de Utilidades (`com.simplymed.client.util`):
   * `CsvUtil.java`: Contiene métodos estáticos para escribir una lista de recetas en un archivo en formato CSV.
   * `IOUtil.java`: Proporciona métodos para guardar (`saveBinary`) y cargar (`loadBinary`) objetos de la aplicación en archivos binarios utilizando la serialización de Java.
   * `JsonUtil.java`: Encapsula la lógica para manejar la serialización y deserialización de objetos a JSON, utilizando la librería Gson. Se encarga de la configuración del `GsonBuilder` para manejar tipos de datos específicos, como `LocalDate`.