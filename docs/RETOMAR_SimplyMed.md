# 📌 Instrucciones para Retomar el Proyecto – SimplyMed

> Última actualización: 2025-05-17 11:08:14

---

## ✅ Cómo arrancar el proyecto localmente

1. Clona el repositorio o abre la carpeta `simplymed/` en VS Code.
2. En terminal, asegurate de estar en la raíz del proyecto (donde está `package.json`).
3. Instala dependencias:

```
npm install
```

4. Corre el proyecto local:

```
npm run dev
```

Abre `http://localhost:5173` para verlo en el navegador.

---

## 🧪 Ejecutar pruebas

Para correr los tests:

```
npm run test
```

Asegurate de tener configurado `vite.config.js` y `vitest.setup.js`.

---

## 🧠 Estructura clave del proyecto

- `src/pages/` → vistas principales (cada ruta renderiza un archivo JSX aquí)
- `src/components/` → componentes reutilizables (como tarjetas y modal)
- `src/context/RecetaContext.jsx` → maneja el estado global con `useReducer`
- `src/hooks/useRecetas.js` → custom hook que encapsula lógica del contexto
- `docs/` → screenshots para presentación
- `__tests__/` → pruebas automatizadas (dentro de components o pages)

---

## 💡 Consejos para continuar

- OCR y análisis de texto pueden integrarse con Tesseract.js en una fase 2
- El estado actual está listo para conectar a Firebase, MongoDB o un backend propio
- Podés extender el expediente clínico a estadísticas por mes o por medicamento

---

## 📦 Scripts útiles

```
npm run dev     # inicia servidor local
npm run test    # ejecuta pruebas unitarias
npm run build   # compila para producción
```

---
