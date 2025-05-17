> Última actualización: 2025-05-17 11:08:14
_________________________________________

# 🔹 1. La barra de navegación no es responsive
* Situación actual: Usamos Bootstrap, pero no activamos el botón ni el colapso en móviles.

* ✅ Solución: Hay que usar <Navbar expand="lg" collapseOnSelect> y manejar el estado del colapso con Bootstrap correctamente.

---
# 🔹 2. Validaciones en los campos del formulario
* Actualmente: Solo se valida si los campos están vacíos.

* ✅ Mejoras posibles:

    - Campo dosis → Validar con regex que sea como 500mg, 10ml, 1tab, 1cap.

    - Campo frecuencia → Validar formato como "Cada X horas" o "1 vez al día".


Esto se puede hacer con:

* Regex simples

* Validación visual (is-invalid)

* Mostrar mensajes de error

---
# 🔹 3. Agregar más campos al modelo de receta
¡Se puede hacer sin problema!

* ✅ Ejemplo: agregar campo "presión arterial" al formulario y al objeto nuevaReceta:

```js
Copiar
Editar
const nuevaReceta = {
  id: Date.now(),
  paciente,
  fecha,
  diagnostico,
  medicamentos,
  presionArterial: presion,
};
```

Y también lo ves reflejado en Recetas, Expediente, Modal, etc.

---
# 🔹 4. Subida de archivo CSV en vez de ingresar manualmente
* ✅ Esta planeado: solo necesitamos un botón tipo “Subir receta por archivo” que lea un .csv (con FileReader) y convierta su contenido en una receta.

* Pendiente:

    - Crear el input file

    - Parsear el CSV (con papaparse o manualmente)

    - Insertarlo con agregarReceta()

---
# 🔹 5. ¿Dónde se guardan las recetas actualmente?
* ❗ Actualmente: son temporales.
* Se almacenan en memoria con useReducer, pero se pierden al recargar la página.

## ✅ Soluciones:

* Guardarlas en localStorage con useEffect

* Guardarlas en un archivo .json

* O conectarlas con una API (por ejemplo, Firebase o backend propio)

