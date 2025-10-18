// Summary: React context que centraliza el estado de recetas y la integracion con la API backend.
// Interacts with: api/recipes.js, useRecetas hook, Recetas.jsx, NuevaRecetaRoute.jsx.
// Rubric: Criterio 3 (UI conectada al backend).
import { createContext, useCallback, useEffect, useMemo, useState } from "react";
import {
  listRecipes,
  createRecipe,
  updateRecipe,
  deleteRecipe,
  importRecipes,
} from "../lib/api/recipes.js";

export const RecetaContext = createContext(null);

// Fallback de fecha ISO (yyyy-mm-dd) cuando el JSON no trae campo.
const todayIso = () => new Date().toISOString().slice(0, 10);

// Normaliza valores numericos provenientes de strings o importaciones.
const parseFrequency = (value) => {
  if (value == null) return 1;
  if (typeof value === "number" && !Number.isNaN(value)) {
    return value || 1;
  }
  const fromString = parseInt(String(value).replace(/[^0-9]/g, ""), 10);
  return Number.isNaN(fromString) ? 1 : Math.max(fromString, 1);
};

// Convierte estructuras legacy (mock/import) a RecipeDTO consumible por la API.
const legacyToDto = (src = {}) => ({
  id: src.id ?? null,
  patientName: src.patientName ?? src.paciente ?? "Paciente demo",
  doctorName: src.doctorName ?? src.medico ?? src.doctor ?? "Dr. Demo",
  date: src.date ?? src.fecha ?? todayIso(),
  notes: src.notes ?? src.diagnostico ?? "",
  medications: (src.medications ?? src.medicamentos ?? []).map((med, index) => ({
    id: med.id ?? null,
    name: med.name ?? med.nombre ?? `Medicamento ${index + 1}`,
    dosage: med.dosage ?? med.dosis ?? "",
    frequencyPerDay: parseFrequency(
      med.frequencyPerDay ?? med.frecuenciaPerDay ?? med.frecuencia ?? med.frequency
    ),
    instructions: med.instructions ?? med.frecuencia ?? "",
  })),
});

// Convierte RecipeDTO a la forma legacy utilizada internamente en el contexto.
const dtoToLegacy = (dto = {}) => ({
  id: dto.id,
  paciente: dto.patientName,
  doctorName: dto.doctorName,
  fecha: dto.date,
  diagnostico: dto.notes ?? "",
  medicamentos: (dto.medications ?? []).map((med) => ({
    nombre: med.name,
    dosis: med.dosage,
    frecuencia: med.instructions || `${med.frequencyPerDay} veces al dia`,
    frecuenciaPerDay: med.frequencyPerDay,
    instructions: med.instructions,
  })),
});

// Elimina ids inexistentes antes de mandar al backend (para import).
const sanitizeForImport = (listado = []) =>
  listado.map((item) => {
    const dto = legacyToDto(item);
    if (!item.id) {
      dto.id = null;
    }
    return dto;
  });

export const RecetaProvider = ({ children }) => {
  const [recetas, setRecetas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Descarga la lista desde el backend y sincroniza estado local.
  const refresh = useCallback(async () => {
    try {
      setLoading(true);
      const envelope = await listRecipes();
      setRecetas((envelope?.recipes ?? []).map(dtoToLegacy));
      setError(null);
    } catch (err) {
      setError(err);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    refresh();
  }, [refresh]);

  // Crea o actualiza una receta segun tenga id.
  const agregarReceta = useCallback(
    async (entrada) => {
      const dto = legacyToDto(entrada);
      const saved = dto.id ? await updateRecipe(dto.id, dto) : await createRecipe(dto);
      await refresh();
      return saved;
    },
    [refresh]
  );

  // Importa/mergea recetas desde JSON y refresca la vista.
  const mergeRecetas = useCallback(
    async (listado) => {
      await importRecipes(sanitizeForImport(listado));
      await refresh();
    },
    [refresh]
  );

  // Elimina una receta por id (si existe) y sincroniza.
  const eliminarReceta = useCallback(
    async (id) => {
      if (id == null) return;
      await deleteRecipe(id);
      await refresh();
    },
    [refresh]
  );

  const value = useMemo(
    () => ({ recetas, loading, error, refresh, agregarReceta, mergeRecetas, eliminarReceta }),
    [recetas, loading, error, refresh, agregarReceta, mergeRecetas, eliminarReceta]
  );

  return <RecetaContext.Provider value={value}>{children}</RecetaContext.Provider>;
};
