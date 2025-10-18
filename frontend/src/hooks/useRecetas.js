import { useContext } from "react";
import { RecetaContext } from "../context/RecetaContext.jsx";

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
