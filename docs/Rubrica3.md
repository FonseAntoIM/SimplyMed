
> Última actualización: 2025-05-17 11:08:14


# 📌 RÚBRICA → ¿Cómo se cumple en este proyecto? 


Criterio	Implementación específica en SimplyMed
SPA con React Router	App.jsx define rutas con BrowserRouter, Routes y Route. Rutas: /, /recetas, /nueva, /agenda, /expediente, *.
JSX + props + render dinámico	RecetaCard.jsx recibe receta por props. Se renderizan dinámicamente en Recetas.jsx usando .map().
Hooks (useState, useEffect)	En NuevaReceta.jsx se usan useState para controlar inputs y useEffect para side effects futuros.
Custom Hook	useRecetas.js encapsula lógica del contexto: devuelve recetas, funciones y stats.
Context + useReducer	RecetaContext.jsx maneja el estado global. Se usa en Recetas, NuevaReceta, Agenda y Expediente.
Portal con Modal	Modal.jsx usa ReactDOM.createPortal. Se activa desde Recetas.jsx para ver detalles de receta.
Estilos y validaciones visuales	Uso de Bootstrap en formularios, tablas y cards. Clases dinámicas como is-invalid para campos requeridos.
Pruebas unitarias	RecetaCard.test.jsx: Verifica renderizado. NuevaReceta.test.jsx: Simula el flujo completo con vi.fn().
Archivo README y bitácora	README_SimplyMed_Entrega.md contiene: estructura, tecnologías, pruebas, capturas, fases de desarrollo.

---

# 🧠 ¿Cómo se jutifica cada parte?

## ¿Por qué usar context? 
Para compartir recetas entre vistas sin prop-drilling.

## ¿Qué hace el custom hook?
Centraliza el acceso al contexto (recetas, agregarReceta, estadísticas).

## ¿Por qué Portal?
Para separar el modal del flujo principal del DOM. Evita conflictos de estilo y layout.

## ¿Qué pruebo?
Verifico que se rendericen correctamente los datos y que se llame la función de guardado con datos simulados.

## ¿Escalable?
Sí. Listo para agregar OCR (Tesseract), login, roles, exportación PDF y conexión a backend.