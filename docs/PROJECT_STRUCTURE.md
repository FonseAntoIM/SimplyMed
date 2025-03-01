# Estructura del Proyecto SimplyMed

Este documento describe la estructura de carpetas y archivos del proyecto SimplyMed, una aplicación web para la gestión de recetas médicas.

## Estructura General

simplymed/
├── client/          # Frontend
│   ├── css/
│   │   ├── styles.scss
│   │   ├── styles.css
│   │   └── bootstrap.css
│   ├── js/
│   │   ├── app.js
│   │   ├── utils.js
│   │   ├── classes.js
│   │   └── charts.js
│   ├── pages/
│   │   ├── index.html
│   │   ├── login.html
│   │   ├── register.html
│   │   ├── menu.html
│   │   ├── record.html
│   │   └── history.html
│   ├── assets/
│   └── index.html
├── server/          # Backend
│   ├── controllers/  # Lógica de las rutas
│   ├── models/       # Modelos de la base de datos
│   ├── routes/       # Definición de las rutas de la API
│   ├── config/       # Configuración de la base de datos y otras variables
│   ├── server.js      # Punto de entrada del backend
│   ├── package.json   # Dependencias del backend
│   └── ...
├── database/        # Scripts de inicialización de la base de datos (opcional)
├── docs/            # Documentación del proyecto (API, etc.)
├── .env             # Variables de entorno
├── .gitignore       # Archivos a ignorar en Git
├── package.json     # Dependencias del proyecto (si usas un monorepo)
└── README.md        # Documentación general del proyecto



## Descripción de las Carpetas

### `client/` (Frontend)

Contiene todo el código del frontend de la aplicación.

* `css/`: Estilos CSS y SASS.
    * `styles.scss`: Archivo SASS principal.
    * `styles.css`: Archivo CSS compilado.
    * `bootstrap.css`: (Opcional) Bootstrap local.
* `js/`: Código JavaScript.
    * `app.js`: Lógica principal de la aplicación.
    * `utils.js`: Funciones utilitarias.
    * `classes.js`: (Opcional) Clases JavaScript (POO).
    * `charts.js`: (Opcional) Lógica para gráficas (Chart.js).
* `pages/`: Vistas HTML.
    * `index.html`: Presentación del producto.
    * `login.html`: Login.
    * `register.html`: Registro.
    * `menu.html`: Menú general.
    * `record.html`: Registro de recetas.
    * `history.html`: Historial de medicamentos.
* `assets/`: Imágenes, iconos, etc.
* `index.html`: Punto de entrada principal (opcional).

### `server/` (Backend)

Contiene el código del backend de la aplicación.

* `controllers/`: Lógica de las rutas de la API.
* `models/`: Modelos de la base de datos.
* `routes/`: Definición de las rutas de la API.
* `config/`: Configuración de la base de datos y variables de entorno.
* `server.js`: Punto de entrada del backend.
* `package.json`: Dependencias del backend.

### Otras Carpetas y Archivos

* `database/`: Scripts de inicialización de la base de datos (opcional).
* `docs/`: Documentación del proyecto (API, etc.).
* `.env`: Variables de entorno.
* `.gitignore`: Archivos a ignorar en Git.
* `package.json`: Dependencias del proyecto (monorepo opcional).
* `README.md`: Documentación general del proyecto.

## Flujo de Trabajo

* **Frontend:**
    * Desarrollo con HTML, CSS (SASS), JavaScript.
    * Comunicación con el backend a través de la API.
* **Backend:**
    * Desarrollo con Node.js (Express) o Python (Flask/Django).
    * Gestión de la base de datos.
    * Exposición de la API RESTful.

Este documento proporciona una visión general de la estructura del proyecto. Para obtener detalles específicos sobre cada componente, consulta la documentación correspondiente.
