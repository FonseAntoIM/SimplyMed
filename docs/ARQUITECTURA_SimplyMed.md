# 🧱 Arquitectura del Proyecto – SimplyMed

## Estructura base

```
simplymed/
├── docs/                  # Capturas de pantalla del proyecto
├── public/                # Assets públicos (favicon, index.html)
├── src/
│   ├── assets/            # Íconos, imágenes
│   ├── components/        # Componentes reutilizables (RecetaCard, Modal, Navbar)
│   │   └── __tests__/     # Pruebas unitarias de componentes
│   ├── context/           # RecetaContext.jsx – maneja el estado global
│   ├── data/              # Datos simulados (mock de recetas)
│   ├── hooks/             # Custom hook: useRecetas.js
│   ├── pages/             # Vistas principales (ruteadas)
│   │   └── __tests__/     # Pruebas unitarias de páginas
│   ├── App.jsx            # Enrutador de la app
│   └── main.jsx           # Punto de entrada de React
├── vitest.setup.js        # Configuración de testing
├── vite.config.js         # Configuración de Vite + Vitest
├── package.json           # Scripts y dependencias
├── .gitignore             # Ignorar node_modules, .env, etc.
├── README.md              # Documentación principal
└── RETOMAR_SimplyMed.md   # Instrucciones para continuar desarrollo
```

---

## Flujo de datos

- `main.jsx` → Renderiza `App.jsx`
- `App.jsx` → Carga rutas con `BrowserRouter`
- Cada ruta renderiza una vista en `/pages`
- `useRecetas()` extrae datos del contexto global
- `RecetaContext` maneja recetas y actualizaciones
- `RecetaCard`, `Modal`, `Navbar` son componentes compartidos
