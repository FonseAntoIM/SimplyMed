# SimplyMed - Gestor de Recetas Médicas

Gestor de medicaion personal/grupal famliar

## Descripción del Proyecto

SimplyMed es una aplicación web diseñada para la gestión personal y familiar de recetas médicas. Nuestro objetivo es proporcionar una herramienta intuitiva y fácil de usar para registrar, organizar y hacer seguimiento de tus medicamentos, mejorando la adherencia a los tratamientos y facilitando el acceso al historial médico personal.

## Funcionalidades (MVP - Versión Inicial)

### Registro de Recetas

* Formulario para ingresar datos de la receta: nombre del medicamento, dosis, frecuencia, duración, médico prescriptor.
* Calendario para programar las tomas de medicamentos.
* Almacenamiento local de las recetas (LocalStorage/IndexedDB).

### Historial de Medicamentos

* Visualización de las recetas registradas en forma de lista o tabla.
* Filtros de búsqueda por fecha, medicamento o médico.
* Calendario con las tomas de medicamentos.
* Gráficas de historial de tomas de medicamentos.

### Recordatorios

* Notificaciones locales en el navegador para recordar las tomas de medicamentos.

### Login/Registro de Usuarios

* Autenticación de usuarios para proteger la información médica.
* Formularios de inicio de sesión y registro.

### Interfaz Intuitiva y Responsiva

* Diseño limpio y fácil de usar, adaptado a diferentes dispositivos.
* Uso de Bootstrap y CSS personalizado para la presentación.

## Funcionalidades Futuras (Versiones Posteriores)

* **Escaneo de Recetas:** Integración de una API de reconocimiento óptico de caracteres (OCR) para extraer automáticamente la información de las recetas escaneadas.
* **Gestión de Diagnósticos:** Registro de diagnósticos y síntomas asociados a cada receta. Comparación de diagnósticos a lo largo del tiempo. Generación de informes y estadísticas personalizadas.
* **Compartir Expediente:** Opción para compartir el expediente médico con familiares o cuidadores. Sincronización de la toma de medicamentos entre usuarios.
* **Integración con Calendarios Externos:** Sincronización de los recordatorios de medicamentos con calendarios como Google Calendar.
* **Base de Datos en la Nube:** Migración del almacenamiento local a una base de datos en la nube (MongoDB, por ejemplo).
* **Aplicación Móvil:** Desarrollo de una aplicación móvil para iOS y Android.
* **Sincronización de datos:** Sincronización de datos entre dispositivos.

## Tecnologías

* **Frontend:** HTML5, CSS3, SASS, Bootstrap, JavaScript (POO, manipulación del DOM, Chart.js).
* **Backend:** (Para versiones futuras) Node.js con Express o Python con Flask/Django.
* **Base de Datos:** LocalStorage/IndexedDB (MVP), (Para versiones futuras) MongoDB o similar.
* **APIs:** API de Notificaciones del navegador, Chart.js (gráficas).

## Organización del Proyecto

* Estructura de carpetas modular para el frontend (css/, js/, pages/, assets/).
* Uso de SASS para la gestión de estilos.
* Código JavaScript organizado en módulos y clases (si se usa POO).
