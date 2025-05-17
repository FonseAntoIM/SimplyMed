_________________________________________

> Última actualización: 2025-05-17 11:08:14
_________________________________________

# 📁 src/pages/ – Cómo funciona cada vista
## ✅ Inicio.jsx
* Ruta: /

* Propósito: Página de bienvenida (simple).

* Conexión: Se carga desde App.jsx con React Router.


---
## ✅ Recetas.jsx
* Ruta: /recetas

* Propósito: Muestra una lista de recetas médicas (tarjetas).

* Conexiones:

    - Usa el hook useRecetas() → obtiene las recetas del Contexto Global

    - Usa el componente RecetaCard.jsx → renderiza cada receta

    - Al hacer clic en una receta, se abre un Modal.jsx (usando Portal)

    - Usa ReactDOM.createPortal() para renderizar el modal fuera del árbol principal

---
## ✅ NuevaReceta.jsx
* Ruta: /nueva

* Propósito: Formulario para registrar una nueva receta médica

* Conexiones:

    - Usa useState para manejar los inputs del formulario

    - Usa useRecetas() para llamar a agregarReceta() del contexto

    - Redirecciona con useNavigate() a /recetas tras guardar

    - Usa Bootstrap + validaciones visuales (is-invalid)

* El formulario se probó con test unitario usando fireEvent y vi.fn()

---
## ✅ Agenda.jsx
* Ruta: /agenda

* Propósito: Tabla con todos los medicamentos a tomar (agenda médica)

* Conexiones:

    - Usa useRecetas() → carga recetas del contexto

    - Usa flatMap() y map() para agrupar medicamentos por receta

    - No necesita componentes externos, se genera con HTML dinámico

---
## ✅ Expediente.jsx
* Ruta: /expediente

* Propósito: Vista resumen del historial del paciente (tipo expediente clínico)

* Conexiones:

    - Usa useRecetas() para acceder a todas las recetas

    - Agrupa la información por paciente

    - Muestra estadísticas: cantidad de recetas, diagnósticos, medicamentos frecuentes

---
## ✅ NotFound.jsx
* Ruta: cualquier otra (*)

* Propósito: Página 404 personalizada

* Conexiones:

    - Se define como última ruta en App.jsx

    - Permite cumplir el requerimiento de SPA con ruta 404

______________________________
🧠 ¿Cómo se conectan con el router?
En App.jsx tenés:

```jsx
<Route path="/recetas" element={<Recetas />} />
<Route path="/nueva" element={<NuevaReceta />} />
```

Cada componente que se encuentra en src/pages/ se renderiza dinámicamente según la URL. Así, no necesitás múltiples archivos HTML.