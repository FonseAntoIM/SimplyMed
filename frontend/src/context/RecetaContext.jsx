import { createContext, useCallback, useEffect, useMemo, useState } from "react";
import recetasMock from "../data/recetasMock";
import {
  listRecipes,
  createRecipe,
  updateRecipe,
  deleteRecipe,
  importRecipes,
} from "../lib/api/recipes.js";

export const RecetaContext = createContext(null);

const todayIso = () => new Date().toISOString().slice(0, 10);

const parseFrequency = (value) => {
  if (value == null) return 1;
  if (typeof value === "number" && !Number.isNaN(value)) {
    return value || 1;
  }
  const fromString = parseInt(String(value).replace(/[^0-9]/g, ""), 10);
  return Number.isNaN(fromString) ? 1 : Math.max(fromString, 1);
};

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

const mockToDto = (mock = {}) => {
  const dto = legacyToDto({
    ...mock,
    medicamentos: (mock.medicamentos ?? []).map((med) => ({
      ...med,
      frequencyPerDay: parseFrequency(
        med.frecuenciaPerDay ?? med.frecuencia ?? med.frequencyPerDay
      ),
      instructions: med.frecuencia ?? med.instructions ?? "",
    })),
  });
  dto.id = undefined;
  return dto;
};

export const RecetaProvider = ({ children }) => {
  const [recetas, setRecetas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const refresh = useCallback(async () => {
    try {
      setLoading(true);
      const envelope = await listRecipes();
      let current = envelope?.recipes ?? [];

      if (current.length === 0 && recetasMock.length > 0) {
        const seeded = await importRecipes(recetasMock.map(mockToDto));
        current = seeded ?? [];
      }

      setRecetas(current.map(dtoToLegacy));
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

  const agregarReceta = useCallback(
    async (entrada) => {
      const dto = legacyToDto(entrada);
      const saved = dto.id ? await updateRecipe(dto.id, dto) : await createRecipe(dto);
      await refresh();
      return saved;
    },
    [refresh]
  );

  const mergeRecetas = useCallback(
    async (listado) => {
      const payload = (listado ?? []).map(legacyToDto);
      await importRecipes(payload);
      await refresh();
    },
    [refresh]
  );

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
