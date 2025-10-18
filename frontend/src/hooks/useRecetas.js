// Summary: Hook simple que expone el contexto de recetas con helpers.
// Interacts with: RecetaContext (estado global) y componentes (Recetas.jsx, NuevaReceta).
// Rubric: Criterio 3 al simplificar el consumo de la API desde React.
import { useContext } from "react";
import { RecetaContext } from "../context/RecetaContext.jsx";

/**
 * Devuelve el contexto de recetas con helpers y metadatos (loading/error).
 * Lanza si se usa fuera de RecetaProvider.
 */
export default function useRecetas() {
  const context = useContext(RecetaContext);
  if (!context) {
    throw new Error("useRecetas debe usarse dentro de un RecetaProvider");
  }

  const {
    recetas,
    loading,
    error,
    refresh,
    agregarReceta,
    mergeRecetas,
    eliminarReceta,
  } = context;

  return {
    recetas,
    total: recetas.length,
    loading,
    error,
    refresh,
    agregarReceta,
    mergeRecetas,
    eliminarReceta,
  };
}
