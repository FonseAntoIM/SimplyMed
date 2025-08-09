// src/utils/jsonIO.js
export function exportRecetasJSON(recetas, fileNamePrefix = 'recetas') {
    const payload = { recetas }; // <-- MISMO formato que la consola Java
    const blob = new Blob([JSON.stringify(payload, null, 2)], { type: 'application/json' });
    const ts = new Date();
    const pad = (n) => String(n).padStart(2, '0');
    const name = `${fileNamePrefix}_${ts.getFullYear()}${pad(ts.getMonth() + 1)}${pad(ts.getDate())}_${pad(ts.getHours())}${pad(ts.getMinutes())}.json`;
  
    const a = document.createElement('a');
    a.href = URL.createObjectURL(blob);
    a.download = name;
    document.body.appendChild(a);
    a.click();
    URL.revokeObjectURL(a.href);
    a.remove();
  }
  
  export async function importarRecetasDesdeArchivo(file) {
    if (!file) throw new Error('No se seleccionó archivo');
    const text = await file.text();
    let json;
    try {
      json = JSON.parse(text);
    } catch {
      throw new Error('JSON inválido');
    }
    if (!json || !Array.isArray(json.recetas)) {
      throw new Error('Estructura inválida. Se esperaba { "recetas": [...] }');
    }
    // Validación ligera por campos mínimos
    return json.recetas.map((r) => ({
      id: r.id ?? Date.now(),
      paciente: r.paciente ?? 'Sin nombre',
      fecha: r.fecha ?? new Date().toISOString().slice(0, 10),
      diagnostico: r.diagnostico ?? '',
      medicamentos: Array.isArray(r.medicamentos) ? r.medicamentos.map((m) => ({
        nombre: m.nombre ?? '',
        dosis: m.dosis ?? '',
        frecuencia: m.frecuencia ?? '',
        unidad: m.unidad ?? undefined,
      })) : [],
    }));
  }
  