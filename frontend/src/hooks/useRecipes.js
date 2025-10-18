// Summary: Hook ligero usado por la tabla simple (/recetas/api) para consumir la API.
// Interacts with: api/recipes.js y componentes de pruebas.
import { useEffect, useState } from "react";
import { listRecipes, deleteRecipe } from "../lib/api/recipes.js";

export function useRecipes() {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  /** Descarga la lista de recetas y actualiza estado y errores. */
  async function refresh() {
    try {
      setLoading(true);
      const res = await listRecipes();
      setItems(res?.recipes ?? []);
      setError(null);
    } catch (e) {
      setError(e);
    } finally {
      setLoading(false);
    }
  }

  /** Elimina una receta por id y vuelve a solicitar la lista. */
  async function remove(id) {
    await deleteRecipe(id);
    await refresh();
  }

  useEffect(() => { refresh(); }, []);
  return { items, loading, error, refresh, remove };
}
